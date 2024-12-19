package com.example.demo1.warekeeper;

import com.example.demo1.PurchaseOrder;
import com.example.demo1.PurchaseOrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/ConfirmPurchaseOrderServlet")
public class ConfirmPurchaseOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderDetailsParam = request.getParameter("orderDetails");
        Map<Integer, Integer> purchaseOrderDetails = new HashMap<>();

        // 解析 orderDetails 参数
        if (orderDetailsParam != null) {
            String[] entries = orderDetailsParam.split(","); // 假设用逗号分隔
            for (String entry : entries) {
                String[] pair = entry.split("=");
                if (pair.length == 2) {
                    try {
                        int productId = Integer.parseInt(pair[0]);
                        int quantity = Integer.parseInt(pair[1]);
                        purchaseOrderDetails.put(productId, quantity);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        // 处理解析错误
                    }
                }
            }
        }

        PurchaseOrderDAO purchaseOrderDAO = new PurchaseOrderDAO();

        // 循环生成进货单
        for (Map.Entry<Integer, Integer> entry : purchaseOrderDetails.entrySet()) {
            int productId = entry.getKey();
            int quantity = entry.getValue();
            PurchaseOrder order = new PurchaseOrder(0,productId, quantity, null, "未履行"); // 设置供应商
            purchaseOrderDAO.createPurchaseOrder(order);
        }
        response.sendRedirect("SuccessServlet");
    }
}
