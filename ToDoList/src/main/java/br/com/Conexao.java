package br.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    static String url = "jdbc:mysql://127.0.0.1:3306/toDoList?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    static String user = "root";
    static String password = "";

    public static Connection conectar() {
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;

        } catch (Exception e) {
            throw new RuntimeException("Erro na conexão: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}