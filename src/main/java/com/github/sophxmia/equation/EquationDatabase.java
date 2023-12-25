package com.github.sophxmia.equation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sofia Maliarenko
 * Sets database for the project.
 * 1. Якщо рівняння є коректним, зберегти його у БД.
 * 2. Надати можливість ввести корені рівняння, під час введення перевіряти,
 * чи є задане число коренем, і якщо так – зберігати його в БД.
 * 3. Реалізувати функції пошуку рівнянь у БД за їхніми коренями.
 */
public class EquationDatabase {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mathematicalAssistant";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345!";

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

    public static List<String> findEquationsByRoot(double root) {
        List<String> equations = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String sql = "SELECT equation FROM equations WHERE ? = ANY(roots)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setDouble(1, root);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        equations.add(resultSet.getString("equation"));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Помилка при пошуку рівнянь за коренем: " + e.getMessage());
        }

        return equations;
    }

    public static void saveRoot(String equation, double root) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String sql = "UPDATE equations SET roots = JSON_ARRAY_APPEND(roots,'$', ?) WHERE equation = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setDouble(1, root);
                preparedStatement.setString(2, equation);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Помилка при збереженні кореня в базу даних: " + e.getMessage());
        }
    }
}
