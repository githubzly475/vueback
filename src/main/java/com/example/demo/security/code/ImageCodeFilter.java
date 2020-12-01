package com.example.demo.security.code;

import com.example.demo.security.exception.ValidateCodeException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Classname ImageCodeFilter
 * @Description 图形验证码过滤器
 * @Author N469
 * @Date 2019-12-24 10:02
 * @Version 1.0
 */
@Component
public class ImageCodeFilter extends OncePerRequestFilter {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 获取token参数
        String token = request.getParameter("token");
        // 必须为/user/login请求和post请求
        if (StringUtils.equals("/user/login", request.getRequestURI()) && StringUtils.isEmpty(token) && StringUtils.equalsIgnoreCase(request.getMethod(), "POST")) {
            try {
                // 1. 进行验证码的校验
                // TODO
                // validateCode(request);
            } catch (ValidateCodeException e) {
                // 2. 如果校验不通过，调用SpringSecurity的校验失败处理器
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        // 3. 通过放行
        chain.doFilter(request, response);
    }

    /**
     * 验证流程
     *
     * @param request
     */
    private void validateCode(HttpServletRequest request) {
        String imageCode = request.getParameter("code");
        String t = request.getParameter("t");
        // 验证验证码
        if (StringUtils.isBlank(imageCode)) {
            throw new ValidateCodeException("验证码不能为空");
        }
        // 从redis中获取之前保存的验证码跟前台传来的验证码进行匹配
        Object kaptcha = redisTemplate.opsForValue().get(Constant.IMAGE_KEY + t);
        if (kaptcha == null) {
            throw new ValidateCodeException("验证码已失效");
        }
        if (!imageCode.toLowerCase().equals(kaptcha)) {
            throw new ValidateCodeException("验证码错误");
        }
    }

    /**
     * 失败处理器
     *
     * @param authenticationFailureHandler
     */
    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

}
