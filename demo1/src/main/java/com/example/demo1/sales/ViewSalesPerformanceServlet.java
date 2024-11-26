package com.example.demo1.sales;

import com.example.demo1.contract.ContractDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/ViewSalesPerformanceServlet")
public class ViewSalesPerformanceServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取当前销售人员的ID
        int salespersonId = (int) request.getSession().getAttribute("salespersonId");

        // 调用DAO获取销售额
        ContractDAO contractDAO = new ContractDAO();
        double totalSales = contractDAO.getTotalSalesBySalespersonId(salespersonId);

        // 设置请求属性并转发到JSP页面
        request.setAttribute("totalSales", totalSales);
        try {
            request.getRequestDispatcher("view_sales_performance.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}