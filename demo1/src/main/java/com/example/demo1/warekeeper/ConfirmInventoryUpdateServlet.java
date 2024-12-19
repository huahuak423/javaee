package com.example.demo1.warekeeper;

import com.example.demo1.ProductDAO;
import com.example.demo1.PurchaseOrder;
import com.example.demo1.PurchaseOrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/ConfirmInventoryUpdateServlet")
public class ConfirmInventoryUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PurchaseOrderDAO purchaseOrderDAO = new PurchaseOrderDAO();
        ProductDAO productDAO = new ProductDAO();

        // 获取所有未完成的进货单
        List<PurchaseOrder> pendingOrders = purchaseOrderDAO.getPendingOrders();

        for (PurchaseOrder order : pendingOrders) {
            // 更新对应商品的库存
            productDAO.updateStock(order.getProductID(), order.getQuantity());
            // 更新进货单状态为已完成
            purchaseOrderDAO.updateOrderStatus(order.getOrderID(), "已完成");
            System.out.println("OrderID: " + order.getOrderID());
        }

        response.sendRedirect("SuccessServlet");
    }

}
