package JDBC.Sistema_De_Agendamento_Medico_Refatorado_Interacao_Banco_De_Dados.aplication;

import JDBC.Sistema_De_Agendamento_Medico_Refatorado_Interacao_Banco_De_Dados.entities.Paciente;
import JDBC.Sistema_De_Agendamento_Medico_refatorado_Enum_Interacao_Banco_De_Dados.Repository.PacienteRepository;
import java.sql.SQLException;
import java.util.Scanner;

public class acao {
    public static void main(String[] args) throws SQLException {
        // JDBC.Sistema_De_Controle_De_Estacionamento_refatorado_Enum_Interacao_Banco_De_Dados.Repository que faz o acesso ao banco (INSERT, SELECT, UPDATE, DELETE)
        PacienteRepository repo = new PacienteRepository();
        // Scanner para ler o que o usuário digita no console
        Scanner sc = new Scanner(System.in);
        try {

            // Controla se o menu continua rodando ou não
            boolean continuar = true;
            while (continuar) {
                // Exibe o menu de opções
                System.out.println("Sistema de Agendamento Médico");
                System.out.println("1 - Cadastrar novo paciente");
                System.out.println("2 - Buscar paciente");
                System.out.println("3 - Atualizar nome do paciente");
                System.out.println("4 - Deletar paciente");
                System.out.println("0 - Sair");
                System.out.print("Escolha: ");
                int opcao;
                // Converte o texto digitado em número
                opcao = Integer.parseInt(sc.nextLine());
                switch (opcao) {
                    // CADASTRAR PACIENTE
                    case 1 -> {
                        System.out.println("Digite o cpf:");
                        String cpf = sc.nextLine();
                        // Valida se o CPF tem exatamente 11 dígitos numéricos
                        if (!cpf.matches("\\d{11}")){
                            System.out.println("CPF inválido. Digite apenas 11 números");
                            break; // interrompe o case sem cadastrar
                        }
                        System.out.println("Digite o nome:");
                        String nome = sc.nextLine();
                        // .*\.* permite somente letras
                        // Verifica se o nome contém algum número
                        if (nome.matches(".*\\d.*")){
                            System.out.println("Nome inválido. Não pode conter números");
                            break; // interrompe o case sem cadastrar
                        }
                        // Salva o novo paciente no banco de dados
                        repo.salvar(cpf, nome);
                        System.out.println("Cadastrdo com sucesso");
                    }
                    // BUSCAR PACIENTE
                    case 2 -> {
                        System.out.println("Digite o cpf:");
                        String cpf = sc.nextLine();
                        // Valida se o CPF tem exatamente 11 dígitos numéricos
                        if (!cpf.matches("\\d{11}")) {
                            System.out.println("CPF inválido. Digite apenas 11 números");
                            break; // interrompe o case sem buscar
                        }
                        // Busca o paciente no banco pelo CPF; retorna null se não achar
                        Paciente paciente = repo.buscarPorCpf(cpf);
                        if (paciente != null) {
                            System.out.println("Paciente encotrado" + paciente);
                        } else {
                            System.out.println("Nenhum paciente encotrado com esse cpf");
                        }
                    }
                    // ATUALIZAR NOME DO PACIENTE
                    case 3 -> {
                        System.out.println("Digite o CPF do paciente que deseja atualziar");
                        String cpf = sc.nextLine();
                        if (!cpf.matches("\\d{11}")){
                            System.out.println("CPF inválido. Digite apenas 11 números");
                            break; // interrompe o case sem atualizar
                        }
                        System.out.println("Digite o novo nome");
                        String novoNome = sc.nextLine();
                        // Tenta atualizar o nome retorna true se encontrou e alterou, false se não achou o CPF
                        boolean atualizar = repo.atualizarNome(cpf, novoNome);
                        if (atualizar) {
                            System.out.println("Nome do paciente alterado");
                        } else {
                            System.out.println("Paciente não encontrado");
                        }
                    }
                    // DELETAR PACIENTE
                    case 4 -> {
                        System.out.println("Digite o CPF do paciente que deseja deletar");
                        String cpf = sc.nextLine();
                        // Valida se o CPF tem exatamente 11 dígitos numéricos
                        if (!cpf.matches("\\d{11}")){
                            System.out.println("CPF inválido. Digite apenas 11 números");
                            break; // interrompe o case sem deletar
                        }
                        boolean deletar = repo.deletar(cpf);
                        if (deletar){
                            System.out.println("Paciente deletado com sucesso");
                        }else {
                            System.out.println("Paciente não encontrado");
                        }
                    }
                    // SAIR DO SISTEMA
                    case 0 -> {
                        System.out.println("Sistema encerrado");
                        continuar = false; // faz o while parar na próxima verificação
                    }
                }
            }
        }catch (SQLException e){
            System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
            // Captura erro quando o usuário digita algo que não é número no menu
        }catch (NumberFormatException e){
            System.out.println("Digite apenas númneros");
        }
    }
}