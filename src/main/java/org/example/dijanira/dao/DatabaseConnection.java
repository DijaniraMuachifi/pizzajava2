package org.example.dijanira.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/pizza_java?useSSL=false&serverTimezone=UTC&characterEncoding=utf8";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, "root", "");
    }
}
