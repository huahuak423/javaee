package com.example.demo1;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() {
        try {
            // 使用连接池获取数据库连接
            Connection connection = DatabaseConnectionManager.getConnection();
            userDAO = new UserDAO(connection);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize database connection in LoginServlet", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 获取登录表单的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        try {
            // 验证用户信息
            User user = userDAO.validateUser(username, password, role);
            if (user != null) {
                // 将用户信息保存到 session 中
                HttpSession session = req.getSession();
                session.setAttribute("user", user);

                // 根据角色跳转到不同的页面
                switch (role) {
                    case "salesperson":
                        resp.sendRedirect("salesperson_dashboard.jsp");
                        break;
                    case "sales_admin":
                        resp.sendRedirect("sales_admin_dashboard.jsp");
                        break;
                    case "warehouse_admin":
                        resp.sendRedirect("warehouse_admin_dashboard.jsp");
                        break;
                    default:
                        // 如果角色不匹配，跳转到错误页面
                        resp.sendError(HttpServletResponse.SC_FORBIDDEN, "无效的角色！");
                }
            } else {
                // 登录失败处理
                resp.getWriter().println("<html><body><h3>登录失败：用户名或密码错误</h3></body></html>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "系统错误，请稍后再试！");
        }
    }

    @Override
    public void destroy() {
        // 如果需要，可以在销毁时关闭连接资源
        super.destroy();
    }
}