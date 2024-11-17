package org.example.dijanira.dao;

import org.example.dijanira.model.Pizza;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PizzaManager {
    private final DatabaseConnection databaseConnection;

    public PizzaManager() {
        this.databaseConnection = new DatabaseConnection();
    }

    public List<Pizza> getAll() {
        List<Pizza> result = new ArrayList<>();
        try (Connection connection = databaseConnection.getConnection();
             ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM pizza")) {

            while (resultSet.next()) {
                String pname = resultSet.getString("pname");
                String categoryname = resultSet.getString("categoryname");
                Boolean vegetarian = resultSet.getBoolean("vegetarian");

                result.add(new Pizza(pname, categoryname, vegetarian));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public void addPizza(Pizza pizza) {
        String sql = "INSERT INTO pizza (pname, categoryname, vegetarian) VALUES (?, ?, ?)";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, pizza.getPname());
            preparedStatement.setString(2, pizza.getCategoryname());
            preparedStatement.setBoolean(3, pizza.getVegetarian());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar pizza: " + e.getMessage());
        }
    }

    public Pizza getPizzaByName(String pname) {
        Pizza pizza = null;
        String sql = "SELECT * FROM pizza WHERE pname = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, pname);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String categoryname = resultSet.getString("categoryname");
                Boolean vegetarian = resultSet.getBoolean("vegetarian");
                pizza = new Pizza(pname, categoryname, vegetarian);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return pizza;
    }

    public void updatePizza(Pizza pizza) {
        String sql = "UPDATE pizza SET categoryname = ?, vegetarian = ? WHERE pname = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, pizza.getCategoryname());
            preparedStatement.setBoolean(2, pizza.getVegetarian());
            preparedStatement.setString(3, pizza.getPname());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar pizza: " + e.getMessage());
        }
    }

    public void deletePizza(String pname) {
        String sql = "DELETE FROM pizza WHERE pname = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, pname);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir pizza: " + e.getMessage());
        }
    }
}
