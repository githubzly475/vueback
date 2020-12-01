package com.zg.vueback.security.handler;

import com.zg.vueback.security.util.AjaxResponseBody;
import com.zg.vueback.security.util.ResultEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IDEA
 * @author:hxb
 *
 * @Date: 2019/5/22 16:08
 * @Description: 认证失败
 */
@Component
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        AjaxResponseBody respBody = new AjaxResponseBody();
        String msg = e.getMessage() != null  ? e.getMessage() : "";
        if(ResultEnum.USER_PWD_ERROR.getMessage().equals(msg)){
            respBody.setMsg(ResultEnum.USER_PWD_ERROR.getText());
            respBody.setCode(ResultEnum.USER_PWD_ERROR.getCode());
        } else if(ResultEnum.USER_DISABLED.getMessage().equals(msg)){
            respBody.setMsg(ResultEnum.USER_DISABLED.getText());
            respBody.setCode(ResultEnum.USER_DISABLED.getCode());
        } else {
            respBody.setMsg(ResultEnum.AUTH_FAILED.getText());
            respBody.setCode(ResultEnum.AUTH_FAILED.getCode());
        }
        out.write(new ObjectMapper().writeValueAsString(respBody));
        out.flush();
        out.close();
    }
}
