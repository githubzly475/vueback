package com.example.demo.security.handler;

import com.example.demo.security.util.AjaxResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
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
 * @Date: 2019/5/22 16:09
 * @Description: 权限不足
 */
@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        PrintWriter out = response.getWriter();
        AjaxResponseBody respBody = new AjaxResponseBody();
        respBody.setMsg("您没有该访问权限，请联系管理员授权!");
        respBody.setCode(HttpServletResponse.SC_FORBIDDEN);
        out.write(new ObjectMapper().writeValueAsString(respBody));
        out.flush();
        out.close();

    }
}
