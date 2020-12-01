package com.zg.vueback.security.config;

import com.zg.vueback.security.JwtAuthenticationTokenFilter;
import com.zg.vueback.security.MyUserDetailsServiceImpl;
import com.zg.vueback.security.handler.EntryPointUnauthorizedHandler;
import com.zg.vueback.security.handler.MyAuthenticationFailureHandler;
import com.zg.vueback.security.handler.MyLogoutSuccessHandler;
import com.zg.vueback.security.handler.RestAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * Created with IDEA
 * @author:hxb
 * @date: 2019/5/21 10:53
 * @description: Spring Security 配置类
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 获取用户信息
     */
    @Autowired
    private MyUserDetailsServiceImpl myUserDetailsService;

    /**
     * 认证失败
     */
    @Autowired
    private EntryPointUnauthorizedHandler entryPointUnauthorizedHandler;
    /**
     * 权限不足
     */
    @Autowired
    private RestAccessDeniedHandler restAccessDeniedHandler;
    /**
     * jwt
     */
    @Autowired
    private JwtAuthenticationTokenFilter authenticationTokenFilter;

    /**
     * 退出
     */
    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;

    /**
     * 验证码
     */
//    @Autowired
//    private ImageCodeFilter imageCodeFilter;

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    /**
     * 从容器中取出 AuthenticationManagerBuilder，执行方法里面的逻辑之后，放回容器
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        imageCodeFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        http
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeRequests()
            .antMatchers("/","/user/login/**","user/login/**","/favicon.ico","/captcha.jpg").anonymous()
            .antMatchers(HttpMethod.GET,"/*.html","/**/*.html","/**/*.css","/**/*.js")
            .permitAll()
            .anyRequest().authenticated()
            .and()
            .headers().frameOptions().disable();
//            .and()
//            .logout().logoutUrl("/user/logout").logoutSuccessHandler(myLogoutSuccessHandler).deleteCookies("JSESSIONID");
//            .invalidateHttpSession(true)
//            .and().sessionManagement().maximumSessions(1);

        // 处理异常情况：认证失败和权限不足
        http
            .exceptionHandling()
                .authenticationEntryPoint(entryPointUnauthorizedHandler)
                .accessDeniedHandler(restAccessDeniedHandler);

        // 添加JWT filter
        http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

    }

}
