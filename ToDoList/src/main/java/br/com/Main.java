package br.com;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListaDAO listaDAO = new ListaDAO();

        boolean rodando = true;

        while(rodando){
            System.out.println("""
                    
                    ========= Lista de Afazeres =========
                    1- Adicionar Tarefa
                    2- Visualizar Tarefas
                    3- Marcar Tarefa Como Concluida
                    4- Ver Tarefas Concluidas
                    5- Sair;
                    """);
            byte opc = sc.nextByte();
            sc.nextLine();

            switch (opc){
                case 1:
                    System.out.println("O que precisa ser feito?");
                    String descricao = sc.nextLine();

                    listaDAO.inserir(descricao);
                    break;

                case 2:
                    listaDAO.emAndamento();
                    break;

                case 3:
                    System.out.println("Id da tarefa que deseja marcar como concluida: ");
                    int id = sc.nextInt();

                    listaDAO.marcarComoConcluida(id);
                    break;

                case 4:
                    listaDAO.concluidas();
                    break;

                case 5:
                    rodando = false;
                    break;

                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
    }
}