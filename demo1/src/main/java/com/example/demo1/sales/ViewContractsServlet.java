package com.example.demo1.sales;

import com.example.demo1.User;
import com.example.demo1.contract.Contract;
import com.example.demo1.contract.ContractDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ViewContractsServlet")
public class ViewContractsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 获取登录用户
        HttpSession session = httpRequest.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        // 确保用户已登录
        if (user == null) {
            httpResponse.sendRedirect("login.jsp"); // 如果用户未登录，跳转到登录页面
            return;
        }

        // 获取当前登录的销售人员ID（从Session中的User对象）
        Integer salespersonId = user.getSalespersonId();

        // 确保销售人员ID有效
        if (salespersonId == null) {
            httpResponse.sendRedirect("salesperson_dashboard.jsp"); // 如果ID无效，跳转到销售人员页面
            return;
        }

        // 实例化 DAO
        ContractDAO contractDAO = new ContractDAO();

        // 调用 DAO 方法查询合同
        List<Contract> contracts = contractDAO.findContractsBySalespersonId(salespersonId);

        // 将合同列表传递到前端
        request.setAttribute("contracts", contracts);

        // 转发到 JSP 页面（比如 view_contracts.jsp，用于展示合同列表）
        request.getRequestDispatcher("search_contract.jsp").forward(request, response);
    }
}
