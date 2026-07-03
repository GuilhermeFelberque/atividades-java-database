package JDBC.Sistema_De_Reserva_De_Hotel_Refatorado_Interacao_Banco_De_Dados.Application;

import JDBC.Sistema_De_Reserva_De_Hotel_Refatorado_Interacao_Banco_De_Dados.Repository.CheckinRepository;
import JDBC.Sistema_De_Reserva_De_Hotel_Refatorado_Interacao_Banco_De_Dados.Repository.QuartosRepository;
import JDBC.Sistema_De_Reserva_De_Hotel_Refatorado_Interacao_Banco_De_Dados.entities.Chekin;
import JDBC.Sistema_De_Reserva_De_Hotel_Refatorado_Interacao_Banco_De_Dados.entities.Quartos;
import JDBC.Sistema_De_Reserva_De_Hotel_Refatorado_Interacao_Banco_De_Dados.entities.QuartoSimples;
import JDBC.Sistema_De_Reserva_De_Hotel_Refatorado_Interacao_Banco_De_Dados.entities.QuartoDuplo;
import JDBC.Sistema_De_Reserva_De_Hotel_Refatorado_Interacao_Banco_De_Dados.entities.QuartoSuite;

import java.sql.SQLException;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        QuartosRepository quartosRepo = new QuartosRepository();
        CheckinRepository checkinRepo = new CheckinRepository();
        boolean continuar = true;

        try {
            while (continuar) {

                System.out.println("    SISTEMA DE RESERVA DE HOTEL ");
                System.out.println("1 - Cadastrar quarto");
                System.out.println("2 - Fazer reserva");
                System.out.println("3 - Buscar reserva");
                System.out.println("4 - Deletar reserva");
                System.out.println("5 - Listar quartos");
                System.out.println("6 - Deletar quarto");
                System.out.println("0 - Sair");
                System.out.print("Escolha: ");

                int opcao = Integer.parseInt(sc.nextLine());

                switch (opcao) {

                    case 1 -> {
                        System.out.println(" Cadastrar Quarto ");
                        System.out.print("Numero do quarto: ");
                        int numero = Integer.parseInt(sc.nextLine());

                        System.out.println("Tipo: 1- Simples, 2- Duplo, 3- Suite");
                        int tipoOpcao = Integer.parseInt(sc.nextLine());

                        System.out.print("Quantidade de dias: ");
                        int dias = Integer.parseInt(sc.nextLine());

                        // Declarado como Quartos (classe-mãe), pois pode receber
                        Quartos quarto;

                        if (tipoOpcao == 1) {
                            quarto = new QuartoSimples(0, numero, dias);
                        } else if (tipoOpcao == 2) {
                            quarto = new QuartoDuplo(0, numero, dias);
                        } else if (tipoOpcao == 3) {
                            quarto = new QuartoSuite(0, numero, dias);
                        } else {
                            System.out.println("Tipo invalido.");
                            break;
                        }

                        System.out.println("Valor da diaria: R$ " + quarto.getValorDiaria());
                        System.out.println("Valor total: R$ " + quarto.getValorTotal());
                        quartosRepo.cadastrar(quarto);
                    }

                    case 2 -> {
                        System.out.println(" Fazer Reserva ");
                        quartosRepo.listar();

                        System.out.print("ID do quarto: ");
                        int idQuarto = Integer.parseInt(sc.nextLine());

                        double valorTotal = quartosRepo.buscarValorTotal(idQuarto);
                        System.out.println("Valor Total: R$ " + valorTotal);

                        System.out.print("Nome do cliente: ");
                        String nomeCliente = sc.nextLine();

                        System.out.print("CPF: ");
                        String cpf = sc.nextLine();

                        System.out.print("Telefone: ");
                        String telefone = sc.nextLine();

                        System.out.print("Cidade: ");
                        String cidade = sc.nextLine();

                        System.out.print("Data Checkin (yyyy-MM-dd HH:mm:ss): ");
                        String dataCheckin = sc.nextLine();

                        System.out.print("Data Checkout (yyyy-MM-dd HH:mm:ss): ");
                        String dataCheckout = sc.nextLine();

                        System.out.println("Pagamento: 1- PIX, 2- Dinheiro, 3- Cartao");
                        int pagOpcao = Integer.parseInt(sc.nextLine());

                        String pagamento = "";
                        if (pagOpcao == 1) pagamento = "PIX";
                        else if (pagOpcao == 2) pagamento = "Dinheiro";
                        else if (pagOpcao == 3) pagamento = "Cartao";
                        else {
                            System.out.println("Pagamento invalido.");
                            break;
                        }

                        Chekin chekin = new Chekin(0, nomeCliente, cpf, telefone, dataCheckin,
                                dataCheckout, cidade, valorTotal, pagamento, idQuarto);
                        checkinRepo.cadastrar(chekin);
                    }

                    case 3 -> {
                        System.out.println(" Buscar Reserva ");
                        System.out.print("ID da reserva: ");
                        int id = Integer.parseInt(sc.nextLine());
                        checkinRepo.buscarPorId(id);
                    }

                    case 4 -> {
                        System.out.println(" Deletar Reserva ");
                        System.out.print("ID da reserva: ");
                        int id = Integer.parseInt(sc.nextLine());
                        checkinRepo.deletar(id);
                    }

                    case 5 -> {
                        System.out.println(" Listar Quartos ");
                        quartosRepo.listar();
                    }

                    case 6 -> {
                        System.out.println(" Deletar Quarto ");
                        quartosRepo.listar();
                        System.out.print("ID do quarto: ");
                        int id = Integer.parseInt(sc.nextLine());
                        quartosRepo.deletar(id);
                    }

                    case 0 -> {
                        System.out.println("Sistema encerrado. Ate logo!");
                        continuar = false;
                    }

                    default -> System.out.println("Opcao invalida. Tente novamente.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro no banco de dados: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Digite apenas numeros no menu.");
        }

        sc.close();
    }
}