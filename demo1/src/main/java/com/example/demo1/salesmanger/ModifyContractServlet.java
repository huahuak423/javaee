package com.example.demo1.salesmanger;

import com.example.demo1.DatabaseConnectionManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/ModifyContractServlet")
public class ModifyContractServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取修改后的合同基本信息
        int contractId = Integer.parseInt(request.getParameter("contract_id"));
        int customerId = Integer.parseInt(request.getParameter("customer_id"));
        int salespersonId = Integer.parseInt(request.getParameter("salesperson_id"));
        String status = request.getParameter("status");
        String remarks = request.getParameter("remarks");

        // 更新合同信息
        String sql = "UPDATE Contract SET customer_id = ?, salesperson_id = ?, status = ?, remarks = ? WHERE contract_id = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, salespersonId);
            preparedStatement.setString(3, status);
            preparedStatement.setString(4, remarks);
            preparedStatement.setInt(5, contractId);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                response.sendRedirect("success.jsp");
            } else {
                request.setAttribute("error", "合同更新失败，请重试。");
                request.getRequestDispatcher("modify_contract.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "发生错误，请重试。");
            request.getRequestDispatcher("modify_contract.jsp").forward(request, response);
        }
    }
}