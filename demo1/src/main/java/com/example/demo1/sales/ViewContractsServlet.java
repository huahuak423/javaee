package com.example.demo1.sales;

import com.example.demo1.contract.Contract;
import com.example.demo1.contract.ContractDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/ViewContractsServlet")
public class ViewContractsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取当前登录的销售人员ID（假设保存在Session中）
        int salespersonId = (int) request.getSession().getAttribute("salespersonId");

        // 调用DAO方法查询合同
        ContractDAO contractDAO = new ContractDAO();
        List<Contract> contracts = contractDAO.findContractsBySalespersonId(salespersonId);

        // 将合同列表传递到前端
        request.setAttribute("contracts", contracts);
        try {
            request.getRequestDispatcher("view_contracts.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}