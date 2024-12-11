package com.example.demo1;

import com.example.demo1.DatabaseConnectionManager;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    // 获取所有商品信息
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM Product";


        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("product_id");
                String name = resultSet.getString("name");
                BigDecimal price = resultSet.getBigDecimal("price");
                int stock = resultSet.getInt("stock");
                int threshold = resultSet.getInt("threshold");
                String category = resultSet.getString("category");

                productList.add(new Product(id, name, price, stock, threshold, category));
            }
        } catch (SQLException e) {
            System.err.println("Error occurred while saving contract: " + e.getMessage());
            e.printStackTrace();
        }


        return productList;
    }

    // 添加新商品
    public boolean addProduct(Product product) {
        String sql = "INSERT INTO Product (name, price, stock, threshold, category) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setBigDecimal(2, product.getPrice());
            preparedStatement.setInt(3, product.getStock());
            preparedStatement.setInt(4, product.getThreshold());
            preparedStatement.setString(5, product.getCategory());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error occurred while saving contract: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    // 更新商品信息
    public boolean updateProduct(Product product) {
        String sql = "UPDATE Product SET name = ?, price = ?, stock = ?, threshold = ?, category = ? WHERE product_id = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setBigDecimal(2, product.getPrice());
            preparedStatement.setInt(3, product.getStock());
            preparedStatement.setInt(4, product.getThreshold());
            preparedStatement.setString(5, product.getCategory());
            preparedStatement.setInt(6, product.getProductId());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error occurred while saving contract: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    // 删除商品
    public boolean deleteProduct(int productId) {
        String sql = "DELETE FROM Product WHERE product_id = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, productId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error occurred while saving contract: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }
}
