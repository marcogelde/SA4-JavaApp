package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/sua_base";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static Connection getConexao() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao Banco de Dados.", e);
        }
    }
}
