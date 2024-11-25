package com.example.demo1.sales;

import com.example.demo1.contract.Contract;
import com.example.demo1.contract.ContractDAO;
import com.example.demo1.contract.ContractItem;
import com.example.demo1.contract.ContractItemDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/AddContractServlet")
public class AddContractServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取合同基本信息
        int customerId = Integer.parseInt(request.getParameter("customer_id"));
        int salespersonId = Integer.parseInt(request.getParameter("salesperson_id"));
        String[] productIds = request.getParameterValues("product_id[]");
        String[] quantities = request.getParameterValues("quantity[]");
        String[] unitPrices = request.getParameterValues("unit_price[]");

        // 验证输入
        if (productIds == null || quantities == null || unitPrices == null || productIds.length == 0) {
            request.setAttribute("error", "商品信息不能为空！");
            request.getRequestDispatcher("add_contract.jsp").forward(request, response);
            return;
        }

        try {
            // 创建合同对象并保存到数据库
            Contract contract = new Contract(customerId, salespersonId);
            ContractDAO contractDAO = new ContractDAO();
            int contractId = contractDAO.saveContract(contract);

            // 保存合同商品信息
            ContractItemDAO contractItemDAO = new ContractItemDAO();
            for (int i = 0; i < productIds.length; i++) {
                int productId = Integer.parseInt(productIds[i]);
                int quantity = Integer.parseInt(quantities[i]);
                double unitPrice = Double.parseDouble(unitPrices[i]);

                ContractItem item = new ContractItem(contractId, productId, quantity, unitPrice);
                contractItemDAO.saveContractItem(item);
            }

            // 重定向到成功页面
            response.sendRedirect("success.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "创建合同失败，请重试！");
            request.getRequestDispatcher("add_contract.jsp").forward(request, response);
        }
    }
}