package com.example.demo1.warekeeper;

import com.example.demo1.Product;
import com.example.demo1.ProductDAO;
import com.example.demo1.PurchaseOrder;
import com.example.demo1.PurchaseOrderDAO;
import com.example.demo1.contract.ContractItemDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/GeneratePurchaseOrderServlet")
public class GeneratePurchaseOrderServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDAO productDAO = new ProductDAO();
        ContractItemDAO contractItemDAO = new ContractItemDAO();
        PurchaseOrderDAO purchaseOrderDAO = new PurchaseOrderDAO();

        // 获取当前所有商品的库存
        List<Product> productList = productDAO.getAllProducts();
        Map<Integer, Product> productMap = new HashMap<>();
        for (Product product : productList) {
            productMap.put(product.getProductId(), product);
        }
        System.out.println("productList: " + productList);

        // 获取合同商品需求
        Map<Integer, Integer> requiredProducts = contractItemDAO.getRequiredProductQuantities();
        Map<Integer, Integer> purchaseOrderDetails = new HashMap<>();

        System.out.println("requiredProducts: " + requiredProducts);

        // 计算缺失的数量，准备进货清单
        for (Map.Entry<Integer, Integer> entry : requiredProducts.entrySet()) {
            int productId = entry.getKey();
            int requiredQuantity = entry.getValue();
            Product product = productMap.get(productId);

            if (product != null) {
                int currentStock = product.getStock();
                int shortage = requiredQuantity - currentStock;

                if (shortage > 0) {
                    int replenishAmount = shortage + (6 * product.getThreshold());
                    purchaseOrderDetails.put(productId, replenishAmount);
                }
            }
        }
        System.out.println("purchaseOrderDetails: " + purchaseOrderDetails);
        // 将需要进货的商品和数量传递到 JSP
        request.setAttribute("purchaseOrderDetails", purchaseOrderDetails);
        request.getRequestDispatcher("view_purchase_order.jsp").forward(request, response);
    }
}