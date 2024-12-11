package com.example.demo1.sales;

import com.example.demo1.*;
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
import java.sql.SQLException;
import java.util.List;

@WebServlet("/AddContractServlet")
public class AddContractServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDAO productDAO = new ProductDAO();
        List<Product> products = productDAO.getAllProducts();
        if (products == null || products.isEmpty()) {
            System.out.println("No products found in database.");
        }
        CustomerDAO customerDAO = new CustomerDAO();
        List<Customer> customers = customerDAO.getAllCustomers();

        // 使用 SalespersonDAO 获取销售人员
        SalespersonDAO salespersonDAO = null;
        try {
            salespersonDAO = new SalespersonDAO(DatabaseConnectionManager.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<Salesperson> salespersons = salespersonDAO.getAllSalespersons();
        request.setAttribute("salespersons", salespersons);

        request.setAttribute("customers", customers);
        request.setAttribute("products", products); // 设置商品信息到请求中
        request.getRequestDispatcher("add_contract.jsp").forward(request, response); // 转发到JSP页面
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取合同基本信息
        int customerId = Integer.parseInt(request.getParameter("customer_id"));
        int salespersonId = Integer.parseInt(request.getParameter("salesperson_id"));
        String[] productIds = request.getParameterValues("product_id[]");
        String[] quantities = request.getParameterValues("quantity[]");
        String[] unitPrices = request.getParameterValues("unit_price[]");
        System.out.println("Customer ID: " + customerId);
        System.out.println("Salesperson ID: " + salespersonId);

        if (productIds == null || quantities == null || unitPrices == null) {
            request.setAttribute("error", "商品信息不能为空！");
            request.getRequestDispatcher("AddContractServlet").forward(request, response);
            return;
        }

        try {
            Contract contract = new Contract(customerId, salespersonId, "未履行", 0); // 初始总金额为0
            ContractDAO contractDAO = new ContractDAO();
            int contractId = contractDAO.saveContract(contract); // 保存合同并获取合同ID

            double totalAmount = 0.0;
            ContractItemDAO contractItemDAO = new ContractItemDAO();
            for (int i = 0; i < productIds.length; i++) {
                int productId = Integer.parseInt(productIds[i]);
                int quantity = Integer.parseInt(quantities[i]);
                double unitPrice = Double.parseDouble(unitPrices[i]);

                ContractItem item = new ContractItem(contractId, productId, quantity, unitPrice);
                contractItemDAO.saveContractItem(item);

                totalAmount += quantity * unitPrice; // 计算总金额
            }

            // 更新合同总金额
            contract.setTotalAmount(totalAmount);
            contract.setContractId(contractId);
            contractDAO.updateContractTotalAmount(contract); // 更新数据库中的总金额

            response.sendRedirect("SuccessServlet");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "创建合同失败，请重试！");
            request.getRequestDispatcher("add_contract.jsp").forward(request, response);
        }
    }
}
