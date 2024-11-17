package org.example.dijanira.dao;

import org.example.dijanira.model.Category;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryManager {
    private final DatabaseConnection databaseConnection;

    public CategoryManager() {
        this.databaseConnection = new DatabaseConnection();
    }


    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM category";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String cname = resultSet.getString("cname");
                double price = resultSet.getDouble("price");

                categories.add(new Category(cname, price));
            }
        } catch (SQLException e) {
            System.out.println("Error to list: " + e.getMessage());
        }

        return categories;
    }

    public void addCategory(Category category) {
        String sql = "INSERT INTO category (cname, price) VALUES (?, ?)";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, category.getCname());
            preparedStatement.setDouble(2, category.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error a to insert: " + e.getMessage());
        }
    }


    public void updateCategory(Category category) {
        String sql = "UPDATE category SET price = ? WHERE cname = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setDouble(1, category.getPrice());
            preparedStatement.setString(2, category.getCname());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error to update: " + e.getMessage());
        }
    }

    // Deletar uma categoria
    public void deleteCategory(String cname) {
        String sql = "DELETE FROM category WHERE cname = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, cname);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error to delete: " + e.getMessage());
        }
    }
}
