package com.example.demo1;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.*;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 获取登录用户
        HttpSession session = httpRequest.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        // 获取请求的 URL
        String uri = httpRequest.getRequestURI();

        // 如果用户未登录，跳转到登录页面
        if (user == null && !uri.endsWith("LoginServlet") && !uri.endsWith("login.jsp")) {
            httpResponse.sendRedirect("login.jsp");
            return;
        }

        // 权限控制逻辑
        if (user != null) {
            String role = user.getRole();

            // 定义不同角色可访问的页面路径
            Map<String, List<String>> rolePages = new HashMap<>();
            rolePages.put("销售人员", Arrays.asList("add_contract.jsp","ViewContractsServlet", "contract.jsp", "customer.jsp", "pl_turnover.jsp", "salesperson_dashboard.jsp", "search_contract.jsp","AddContractServlet","SuccessServlet"));
            rolePages.put("销售管理员", Arrays.asList("sales_admin_dashboard.jsp", "customer_manage.jsp", "salesperson_manage.jsp"));
            rolePages.put("仓库管理员", Arrays.asList("warehouse_admin_dashboard.jsp", "delivery_note.jsp", "stock_view.jsp"));

            // 检查当前用户角色是否允许访问请求页面
            boolean isAllowed = rolePages.getOrDefault(role, Collections.emptyList()).stream()
                    .anyMatch(uri::endsWith);

            if (!isAllowed) {
                httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "您没有权限访问该页面！");
                return;
            }
        }

        // 放行请求
        chain.doFilter(request, response);
    }
}
