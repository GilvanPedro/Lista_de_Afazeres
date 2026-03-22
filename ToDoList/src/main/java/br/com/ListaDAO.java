package br.com;

import java.sql.*;

public class ListaDAO {
    public void inserir(String descricao){
        String sql = "INSERT INTO listas (descricao) VALUES (?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (conn == null) {
                System.out.println("Falha na conexão!");
                return;
            }

            stmt.setString(1, descricao);

            stmt.executeUpdate();
            System.out.println("Lista Adicionada!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void marcarComoConcluida(int id) {
        String sql = "UPDATE listas SET status = ? WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, true); // muda pra true
            stmt.setInt(2, id);         // id da tarefa

            int linhas = stmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("Tarefa concluída com sucesso!");
            } else {
                System.out.println("Tarefa não encontrada.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void emAndamento() {
        String sql = "SELECT * FROM listas WHERE status = false";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n=== TAREFAS PARA FAZER ===");

            while (rs.next()) {
                int colunas = rs.getMetaData().getColumnCount();

                for (int i = 1; i <= colunas; i++) {
                    String nomeColuna = rs.getMetaData().getColumnName(i);
                    String valor;

                    // 👇 trata o status
                    if (nomeColuna.equalsIgnoreCase("status")) {
                        boolean status = rs.getBoolean(i);
                        valor = status ? "Concluída" : "Em andamento";
                    } else {
                        valor = rs.getString(i);
                    }

                    System.out.print(nomeColuna + ": " + valor + " | ");
                }

                System.out.println();
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar tarefas: " + e.getMessage());
        }
    }

    public void concluidas() {
        String sql = "SELECT * FROM listas WHERE status = true";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n=== TAREFAS CONCLUÍDAS ===");

            while (rs.next()) {
                int colunas = rs.getMetaData().getColumnCount();

                for (int i = 1; i <= colunas; i++) {
                    String nomeColuna = rs.getMetaData().getColumnName(i);
                    String valor;

                    // 👇 Aqui está a mágica
                    if (nomeColuna.equalsIgnoreCase("status")) {
                        boolean status = rs.getBoolean(i);
                        valor = status ? "Concluída" : "Em andamento";
                    } else {
                        valor = rs.getString(i);
                    }

                    System.out.print(nomeColuna + ": " + valor + " | ");
                }

                System.out.println();
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar tarefas: " + e.getMessage());
        }
    }
}
