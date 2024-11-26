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
            // 初始化 UserDAO
            Connection connection = DatabaseConnectionManager.getConnection();
            userDAO = new UserDAO(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize LoginServlet due to database connection issues.", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        // 获取表单参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        try {
            // 验证用户登录信息
            User user = userDAO.validateUser(username, password, role);

            if (user != null) {
                // 检查是否为有效用户
                if (user.isActive()) {
                    // 登录成功，将用户信息存入 session
                    HttpSession session = req.getSession();
                    session.invalidate(); // 防止固定会话攻击
                    session = req.getSession(true);
                    session.setAttribute("user", user);

                    // 根据角色跳转到不同的页面
                    switch (role) {
                        case "销售人员":
                            req.getRequestDispatcher("salesperson_dashboard.jsp").forward(req, resp);
                            break;
                        case "销售管理员":
                            req.getRequestDispatcher("sales_admin_dashboard.jsp").forward(req, resp);
                            break;
                        case "仓库管理员":
                            req.getRequestDispatcher("warehouse_admin_dashboard.jsp").forward(req, resp);
                            break;
                        default:
                            req.setAttribute("errorMsg", "无效的角色！");
                            req.getRequestDispatcher("login.jsp").forward(req, resp);
                    }
                } else {
                    // 用户被禁用
                    req.setAttribute("errorMsg", "账号已被禁用，请联系管理员！");
                    req.getRequestDispatcher("login.jsp").forward(req, resp);
                }
            } else {
                // 登录失败
                req.setAttribute("errorMsg", "用户名或密码错误！");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("errorMsg", "系统错误，请稍后再试！");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}