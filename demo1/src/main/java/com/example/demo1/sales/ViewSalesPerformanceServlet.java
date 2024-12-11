package com.example.demo1.sales;

import com.example.demo1.contract.ContractDAO;
import com.example.demo1.User; // 确保导入正确的 User 类
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/ViewSalesPerformanceServlet")
public class ViewSalesPerformanceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取当前用户
        User user = (User) request.getSession().getAttribute("user");

        // 确保用户已登录
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // 获取当前销售人员的ID
        Integer salespersonId = user.getSalespersonId();

        // 检查 salespersonId 的有效性
        if (salespersonId == null) {
            response.sendRedirect("salesperson_dashboard.jsp");
            return;
        }

        // 调用DAO获取已完成和未履行/履行中销售额
        ContractDAO contractDAO = new ContractDAO();
        double completedSales = contractDAO.getTotalSalesBySalespersonId(salespersonId);
        double pendingSales = contractDAO.getPendingSalesBySalespersonId(salespersonId);

        // 设置请求属性并转发到JSP页面
        request.setAttribute("completedSales", completedSales);
        request.setAttribute("pendingSales", pendingSales);

        // 转发到动态 JSP 页面
        request.getRequestDispatcher("pl_turnover.jsp").forward(request, response);
        // 转发到动态 JSP 页面
        request.getRequestDispatcher("pl_turnover.jsp").forward(request, response);
    }
}
