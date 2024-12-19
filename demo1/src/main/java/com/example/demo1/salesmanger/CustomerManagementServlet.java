package com.example.demo1.salesmanger;

import com.example.demo1.Customer;
import com.example.demo1.CustomerDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/CustomerManagementServlet")
public class CustomerManagementServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerDAO customerDAO = new CustomerDAO();
        List<Customer> customers = customerDAO.getAllCustomers();
        request.setAttribute("customers", customers);
        request.getRequestDispatcher("customer_management.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        Customer customer = new Customer();
        customer.setName(name);
        customer.setAddress(address);
        customer.setPhone(phone);
        customer.setEmail(email);

        CustomerDAO customerDAO = new CustomerDAO();
        if (customerDAO.addCustomer(customer)) {
            response.sendRedirect("CustomerManagementServlet");
        } else {
            request.setAttribute("error", "添加客户失败！");
            request.getRequestDispatcher("customer_management.jsp").forward(request, response);
        }
    }
}