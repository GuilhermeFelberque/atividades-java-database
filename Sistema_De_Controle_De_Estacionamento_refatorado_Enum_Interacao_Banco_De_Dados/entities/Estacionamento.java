package JDBC.Sistema_De_Controle_De_Estacionamento_refatorado_Enum_Interacao_Banco_De_Dados.entities;

import JDBC.Sistema_De_Controle_De_Estacionamento_refatorado_Enum_Interacao_Banco_De_Dados.Repository.EstacionamentoRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Estacionamento {

    private final int TOTAL_VAGAS = 5;
    private final EstacionamentoRepository repo = new EstacionamentoRepository();

    // entrada do veículo
    public void entradaVeiculo(Scanner sc) {
        try {
            if (repo.contarVeiculos() >= TOTAL_VAGAS) {
                System.out.println("Estacionamento lotado.");
                return;
            }

            System.out.print("Digite a placa: ");
            String placa = sc.nextLine().trim().toUpperCase();

            System.out.print("Digite o tipo do veículo (carro/moto): ");
            String tipo = sc.nextLine().trim().toLowerCase();

            if (!tipo.equals("carro") && !tipo.equals("moto")) {
                System.out.println("Tipo inválido. Use 'carro' ou 'moto'.");
                return;
            }

            if (repo.veiculoNoPatio(placa)) {
                System.out.println("Este veículo já está no pátio.");
                return;
            }

            repo.salvarVeiculo(placa, tipo);
            repo.registrarEntrada(placa);

            System.out.println("Veículo " + placa + " estacionado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro no banco: " + e.getMessage());
        }
    }

    // atualiza o tipo do veículo
    public void atualizarTipoVeiculo(Scanner sc) {
        System.out.print("Digite a placa do veículo para atualizar o tipo: ");
        String placa = sc.nextLine().trim().toUpperCase();

        System.out.print("Novo tipo (carro/moto): ");
        String novoTipo = sc.nextLine().trim().toLowerCase();

        if (!novoTipo.equals("carro") && !novoTipo.equals("moto")) {
            System.out.println("Tipo inválido.");
            return;
        }

        try {
            boolean atualizado = repo.atualizarTipo(placa, novoTipo);
            System.out.println(atualizado
                    ? "Tipo atualizado com sucesso!"
                    : "Placa não encontrada.");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        }
    }

    // saída do veículo
    public void saidaVeiculo(Scanner sc) {
        System.out.print("Digite a placa do veículo: ");
        String placa = sc.nextLine().trim().toUpperCase();

        try {
            DadosSaida dados = repo.buscarDadosParaSaida(placa);

            if (dados == null) {
                System.out.println("Veículo não encontrado no pátio.");
                return;
            }

            int tempo = dados.tempo == 0 ? 1 : dados.tempo;

            Veiculo veiculo = dados.tipo.equalsIgnoreCase("carro")
                    ? new Carro(placa, 0)
                    : new Moto(placa, 0);

            double valor = veiculo.calcularValor(tempo);

            System.out.println("Tempo estacionado: " + tempo + " hora(s)");
            System.out.printf("Valor total: R$ %.2f%n", valor);
            System.out.print("Pagamento aprovado? (s/n): ");
            String pagamento = sc.nextLine().trim();

            if (pagamento.equalsIgnoreCase("s")) {
                repo.removerMovimentacao(placa);
                repo.salvarHistorico(placa, dados.tipo, dados.horaEntrada, valor);
                System.out.println("Saída liberada e registrada no histórico!");
            } else {
                System.out.println("Pagamento não autorizado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao processar saída: " + e.getMessage());
        }
    }

    // lista veículos no pátio
    public void listarVeiculos() {
        System.out.println(" VEÍCULOS NO PÁTIO ");
        try {
            List<String> linhas = repo.listarPatio();
            if (linhas.isEmpty()) {
                System.out.println("Nenhum veículo estacionado.");
            } else {
                linhas.forEach(System.out::println);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }
    }

    // lista histórico de saídas
    public void listarHistorico() {
        System.out.println(" HISTÓRICO DE SAÍDAS ");
        try {
            List<String> linhas = repo.listarHistorico();
            if (linhas.isEmpty()) {
                System.out.println("Nenhum registro no histórico.");
            } else {
                linhas.forEach(System.out::println);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar histórico: " + e.getMessage());
        }
    }
}