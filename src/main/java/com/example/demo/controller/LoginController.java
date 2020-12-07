package com.example.demo.controller;

import com.example.demo.cache.RedisCache;
import com.example.demo.cache.UserCache;
import com.example.demo.entity.system.SysUser;
import com.example.demo.log.annotation.SysLog;
import com.example.demo.security.code.CaptchaUtil;
import com.example.demo.security.code.Constant;
import com.example.demo.security.util.JwtTokenUtil;
import com.example.demo.security.util.ResultEnum;
import com.example.demo.security.util.SecurityUser;
import com.example.demo.service.system.SysUserService;
import com.example.demo.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 登录
 *
 * @author: N469
 * @date: 2019-12-05 09:37
 */
@RestController
public class LoginController {

    private static final  String tokenHead = "tokenHead";
    private static final Long expiration = 180000000L;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Resource
    private UserCache userCache;
    @Resource
    private RedisTemplate<Object, Object> redisTemplate;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private SysUserService sysUserService;

    /**
     * 生成验证码
     *
     * @param response
     * @param request
     * @throws IOException
     */
    @GetMapping("/captcha.jpg")
    public void captcha(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        // 生成图片验证码
        BufferedImage image = CaptchaUtil.createImage();
        // 生成文字验证码
        String randomText = CaptchaUtil.drawRandomText(image);
        // 保存到验证码到 redis 有效期两分钟
        String t = request.getParameter("t");
        redisTemplate.opsForValue().set(Constant.IMAGE_KEY + t, randomText.toLowerCase(), 2, TimeUnit.MINUTES);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpeg", out);
    }

    /**
     * 登陆页面
     *
     * @return
     */
    @PostMapping("/user/login")
    public ResultData login(SysUser user, HttpServletRequest request) {

        //用户验证
        Authentication authentication = null;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername()去验证用户名和密码，
            // 如果正确，则存储该用户名密码到security 的 context中
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        } catch (BadCredentialsException e) {
            return ResultData.error(ResultEnum.USER_PWD_ERROR.getCode(), ResultEnum.USER_PWD_ERROR.getText());
        }
        //存储认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //生成token
        final SecurityUser userDetails = (SecurityUser) authentication.getPrincipal();
        final String access_token = jwtTokenUtil.generateToken(userDetails);
        final String refresh_token = jwtTokenUtil.generateTokenRefreshToken(userDetails);

        // 设置token缓存有效期
        redisCache.set("token_" + access_token, refresh_token, expiration * 2 / 1000);

        Map<String, Object> map = new HashMap<>(16);
        map.put("tokenValue", access_token);
        map.put("tokenHead", tokenHead);

        return ResultData.ok("登录成功",map);

    }

    /**
     * 退出
     * @return
     */
    @SysLog(description = "退出登录")
    @RequestMapping("/logout")
    public String logout() {
        // 清楚缓存 权限菜单+token缓存
        String userName = userCache.getUsername();
        redisCache.del(userName);
        redisCache.del(userName+"token");
        return "success";
    }

    @GetMapping("/user/info")
    public ResultData getUserInfo(String token) {

        SysUser user = userCache.getBaseUserInfo();

        // redis
        Map<String, Object> map = new HashMap<>(16);
        String[] ad = {"admin"};
        map.put("token", token);
        map.put("roles", ad);
        map.put("name", "test");
        map.put("user", user);
        map.put("avatar","https://cn.vuejs.org/images/logo.png");

        return ResultData.ok(map);
    }

}
