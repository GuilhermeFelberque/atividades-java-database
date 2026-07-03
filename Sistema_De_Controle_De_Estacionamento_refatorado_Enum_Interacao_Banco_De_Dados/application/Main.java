package JDBC.Sistema_De_Controle_De_Estacionamento_refatorado_Enum_Interacao_Banco_De_Dados.application;

import JDBC.Sistema_De_Controle_De_Estacionamento_refatorado_Enum_Interacao_Banco_De_Dados.entities.Estacionamento;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Estacionamento estacionamento = new Estacionamento();
        boolean continuar = true;

        while (continuar) {
            try {
                System.out.println("Sistema de Controle de Estacionamento");
                System.out.println("1 - Entrada de veículo ");
                System.out.println("2 - Saída de veículo ");
                System.out.println("3 - Listar pátio ");
                System.out.println("4 - Atualizar tipo ");
                System.out.println("5 - Ver histórico");
                System.out.println("0 - Sair");
                System.out.print("Escolha: ");
                int opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao) {
                    case 1 -> estacionamento.entradaVeiculo(sc);
                    case 2 -> estacionamento.saidaVeiculo(sc);
                    case 3 -> estacionamento.listarVeiculos();
                    case 4 -> estacionamento.atualizarTipoVeiculo(sc);
                    case 5 -> estacionamento.listarHistorico();
                    case 0 -> {
                        System.out.println("Encerrando o sistema...");
                        continuar = false;
                    }
                    default -> System.out.println("Opção inválida.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida");
                sc.nextLine();
            }
        }
        sc.close();
    }
}