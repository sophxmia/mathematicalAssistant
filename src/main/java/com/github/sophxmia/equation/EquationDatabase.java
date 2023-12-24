package com.github.sophxmia.equation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EquationDatabase {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mathematicalAssistant";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345";

    public static void saveEquation(String equation) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO equations (equation) VALUES (?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, equation);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Помилка при збереженні рівняння в базу даних: " + e.getMessage());
        }
    }
}
