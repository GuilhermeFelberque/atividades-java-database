package JDBC.Sistema_De_Passagens_Aereas_Refatorado_Intercao_Banco_De_Dados.Application;

import JDBC.Sistema_De_Passagens_Aereas_Refatorado_Intercao_Banco_De_Dados.Entities.ClasseEconomica;
import JDBC.Sistema_De_Passagens_Aereas_Refatorado_Intercao_Banco_De_Dados.Entities.ClasseExecutiva;
import JDBC.Sistema_De_Passagens_Aereas_Refatorado_Intercao_Banco_De_Dados.Entities.Passagem;
import JDBC.Sistema_De_Passagens_Aereas_Refatorado_Intercao_Banco_De_Dados.Repository.PassagemRepository;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PassagemRepository repo = new PassagemRepository();
        boolean continuar = true;

        while (continuar) {

            System.out.println(" SISTEMA DE PASSAGENS AÉREAS ");
            System.out.println("1 - Comprar passagem");
            System.out.println("2 - Listar passagens");
            System.out.println("3 - Buscar por voo");
            System.out.println("4 - Cancelar passagem");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            try {
                int opcao = Integer.parseInt(sc.nextLine());

                if (opcao == 1) {
                    System.out.print("Nome do passageiro: ");
                    String nome = sc.nextLine();

                    if (nome.trim().isEmpty()) {
                        System.out.println("Nome não pode ser vazio.");
                        continue;
                    }

                    System.out.println("Classe: 1- Econômica  2- Executiva");
                    int classe = Integer.parseInt(sc.nextLine());

                    /*Declarado como Passagem (classe-mãe), pois pode receber
                    *tanto um ClasseEconomica quanto um ClasseExecutiva. */
                    Passagem passagem;

                    if (classe == 1) {
                        System.out.println("Deseja despachar mala? 1- Sim  2- Não");
                        int malaOpcao = Integer.parseInt(sc.nextLine());
                        boolean mala = malaOpcao == 1;

                        double preco = 350.00;
                        System.out.println("Preço da passagem: R$ " + String.format("%.2f", mala ? preco + 120.00 : preco));

                        String voo = "VOO-" + (int)(Math.random() * 9000 + 1000);
                        System.out.println("Número do voo: " + voo);

                        passagem = new ClasseEconomica(nome, voo, preco, mala);

                    } else if (classe == 2) {

                        double preco = 800.00;
                        System.out.println("Preço da passagem: R$ " + String.format("%.2f", preco * 1.5));

                        String voo = "VOO-" + (int)(Math.random() * 9000 + 1000);
                        System.out.println("Número do voo: " + voo);

                        passagem = new ClasseExecutiva(nome, voo, preco);

                    } else {
                        System.out.println("Classe inválida.");
                        continue;
                    }

                    System.out.println(passagem);
                    repo.salvar(passagem);

                } else if (opcao == 2) {
                    repo.listar();

                } else if (opcao == 3) {
                    System.out.print("Número do voo: ");
                    String voo = sc.nextLine();
                    repo.buscarPorVoo(voo);

                } else if (opcao == 4) {
                    repo.listar();
                    System.out.print("ID da passagem a cancelar: ");
                    int id = Integer.parseInt(sc.nextLine());
                    repo.deletar(id);

                } else if (opcao == 0) {
                    System.out.println("Encerrando sistema...");
                    continuar = false;

                } else {
                    System.out.println("Opção inválida.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Digite apenas números.");
            }
        }

        sc.close();
    }
}