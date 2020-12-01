package com.zg.vueback.security.handler;

import com.zg.vueback.security.util.AjaxResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
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
 * @Date: 2019/5/22 11:00
 * @Description: 登出
 */
@Component
public class MyLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        AjaxResponseBody respBody = new AjaxResponseBody();
        respBody.setMsg("登出成功");
        respBody.setCode(200);
        ObjectMapper om = new ObjectMapper();
        PrintWriter out = response.getWriter();
        out.write(om.writeValueAsString(respBody));
        out.flush();
        out.close();
    }
}
