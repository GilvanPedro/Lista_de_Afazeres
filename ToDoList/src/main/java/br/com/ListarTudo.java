package br.com;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ListarTudo {

    public static void listar(String tabela) {

        String sql = "SELECT * FROM " + tabela;

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("=== TABELA: " + tabela.toUpperCase() + " ===");

            while (rs.next()) {
                int colunas = rs.getMetaData().getColumnCount();

                for (int i = 1; i <= colunas; i++) {
                    String nomeColuna = rs.getMetaData().getColumnName(i);
                    String valor = rs.getString(i);

                    System.out.print(nomeColuna + ": " + valor + " | ");
                }

                System.out.println();
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar " + tabela + ": " + e.getMessage());
        }
    }
}