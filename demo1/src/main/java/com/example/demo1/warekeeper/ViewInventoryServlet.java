package com.example.demo1.warekeeper;

import com.example.demo1.Product;
import com.example.demo1.ProductDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/ViewInventoryServlet")
public class ViewInventoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDAO productDAO = new ProductDAO();
        List<Product> productList = productDAO.getAllProducts();

        // 将商品数据传递到 JSP 页面
        request.setAttribute("productList", productList);
        request.getRequestDispatcher("stock_view.jsp").forward(request, response);
    }
}
