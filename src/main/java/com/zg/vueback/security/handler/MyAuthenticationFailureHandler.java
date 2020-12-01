package com.zg.vueback.security.handler;

import com.zg.vueback.security.exception.ValidateCodeException;
import com.zg.vueback.security.util.AjaxResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 验证码相关认证错误处理
 *
 * @author: N469
 * @date: 2019-12-24 08:30
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        String message;
        ObjectMapper om = new ObjectMapper();
        if (e instanceof ValidateCodeException) {
            message = e.getMessage();
        } else {
            message = "认证失败，请联系网站管理员！";
        }
        response.setContentType("application/json;charset=utf-8");
        AjaxResponseBody respBody = new AjaxResponseBody();
        respBody.setMsg(message);
        respBody.setCode(500);
        PrintWriter out = response.getWriter();
        out.write(om.writeValueAsString(respBody));
        out.flush();
        out.close();
    }
}
