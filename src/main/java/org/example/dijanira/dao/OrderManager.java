package org.example.dijanira.dao;

import org.example.dijanira.model.Orders;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderManager {
    private final DatabaseConnection databaseConnection;

    public OrderManager() {
        this.databaseConnection = new DatabaseConnection();
    }
    public Orders getOrderById(Long id) {
        Orders result = null;
        String sql = "SELECT * FROM `order` WHERE id = ?";  // Use a 'prepared statement'

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Set the parameter for the query (id)
            preparedStatement.setInt(1, Math.toIntExact(id));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    long orderId = resultSet.getLong("id");
                    String pizzaName = resultSet.getString("pizzaname");
                    int amount = resultSet.getInt("amount");
                    Timestamp taken = resultSet.getTimestamp("taken");
                    Timestamp dispatched = resultSet.getTimestamp("dispatched");

                    result = new Orders(orderId, pizzaName, amount, taken, dispatched);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while fetching order: " + e.getMessage());
        }
        return result;
    }


    // Retrieve all orders
    public List<Orders> getAll() {
        List<Orders> result = new ArrayList<>();
        String sql = "SELECT * FROM `order`  ORDER BY `order`.`id` DESC LIMIT 20";

        try (Connection connection = databaseConnection.getConnection();
             ResultSet resultSet = connection.createStatement().executeQuery(sql)) {

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String pizzaName = resultSet.getString("pizzaname");
                int amount = resultSet.getInt("amount");
                Timestamp taken = resultSet.getTimestamp("taken");
                Timestamp dispatched = resultSet.getTimestamp("dispatched");

                result.add(new Orders(id, pizzaName, amount, taken, dispatched));
            }
        } catch (SQLException e) {
            System.out.println("Error while fetching orders: " + e.getMessage());
        }
        return result;
    }

    // Add a new order
    public void addOrder(Orders order) {
        String sql = "INSERT INTO `order` (pizzaname, amount, taken, dispatched) VALUES (?, ?, ?, ?)";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, order.getPizzaName());
            preparedStatement.setInt(2, order.getAmount());
            preparedStatement.setTimestamp(3, order.getTaken());

            // Check if dispatched is provided
            if (order.getDispatched() != null) {
                preparedStatement.setTimestamp(4, order.getDispatched());
            } else {
                preparedStatement.setNull(4, Types.TIMESTAMP);
            }

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while adding order: " + e.getMessage());
        }
    }
    // Update an existing order
    public void updateOrder(Orders order) {
        String sql = "UPDATE `order` SET pizzaname = ?, amount = ?, taken = ?, dispatched = ? WHERE id = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, order.getPizzaName());
            preparedStatement.setInt(2, order.getAmount());
            preparedStatement.setTimestamp(3, order.getTaken());
            preparedStatement.setTimestamp(4, order.getDispatched());
            preparedStatement.setLong(5, order.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while updating order: " + e.getMessage());
        }
    }

    // Delete an order
    public void deleteOrder(long id) {
        String sql = "DELETE FROM `order` WHERE id = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while deleting order: " + e.getMessage());
        }
    }

}
