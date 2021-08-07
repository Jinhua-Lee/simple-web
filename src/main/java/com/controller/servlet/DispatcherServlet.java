package com.controller.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 分发处理器
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/8/7 20:06
 */
@WebServlet(value = "/", name = "DispatcherServlet")
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        String method = req.getMethod();
        System.out.println("servletPath = " + servletPath);
        System.out.println("method = " + method);

        if (Objects.equals(servletPath, "/frontend/get-main-page-info")
                && Objects.equals(method, "GET")) {

        }

    }
}
