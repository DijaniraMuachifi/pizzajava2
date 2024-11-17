package org.example.dijanira.dao;

import org.example.dijanira.model.Pizza;
import org.example.dijanira.model.User;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserManager {
    private final DatabaseConnection databaseConnection;

    public UserManager() {
        this.databaseConnection = new DatabaseConnection();
    }


    public boolean addUser(User user) {
        String sql = "INSERT INTO users (name, email, password, role, created_at) VALUES (?, ?, ?, ?, NOW())";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword()); // Senha deve ser criptografada antes de ser salva
            preparedStatement.setString(4, user.getRole());
            //preparedStatement.setString(5, user.getCreatedAt());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println("Error add new user: " + e.getMessage());
            return false;
        }
    }

    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try (Connection connection = databaseConnection.getConnection();
             ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM `users`")) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String email  = resultSet.getString("email");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");

                result.add(new User(name, email, password, role));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public User getUserById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    String password = resultSet.getString("password");
                    String role = resultSet.getString("role");
                    String createdAt = resultSet.getString("created_at");

                    return new User(name, email, password, role);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuário por ID: " + e.getMessage());
        }
        return null; // Retorna null caso o usuário não seja encontrado
    }


    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    String password = resultSet.getString("password");
                    String role = resultSet.getString("role");
                    String createdAt = resultSet.getString("created_at");

                    return new User(name, email, password, role);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
        }
        return null; // Retorna null caso o usuário não seja encontrado
    }


    public boolean isEmailExists(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // Se encontrar um registro, retorna true
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar e-mail: " + e.getMessage());
        }
        return false;
    }
}
