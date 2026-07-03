package JDBC.Sistema_De_Contas_De_UsuarioStreamig_Interacao_Banco_De_Dados.application;

import JDBC.Sistema_De_Contas_De_UsuarioStreamig_Interacao_Banco_De_Dados.Repository.UsuarioRepository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        // JDBC.Sistema_De_Controle_De_Estacionamento_refatorado_Enum_Interacao_Banco_De_Dados.Repository que faz o acesso ao banco (INSERT, SELECT, UPDATE, DELETE)
        UsuarioRepository repo = new UsuarioRepository();
        // Scanner para ler o que o usuário digita no console
        Scanner sc = new Scanner(System.in);
        // Controla se o menu continua rodando ou não
        boolean continuar = true;
        while (continuar) {
            // Exibe o menu de opções
            System.out.println("SISTEMA");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Buscar usuário");
            System.out.println("3 - Atualizar usuário");
            System.out.println("4 - Deletar usuário");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            int opcao;
            try {
                // Converte o texto digitado em número
                opcao = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                // Se o usuário digitar algo que não é número, avisa e encerra o programa
                System.out.println("Digite apenas números.");
                return;
            }
            try {


                switch (opcao) {
                    // CADASTRAR USUARIO
                    case 1 -> {
                        System.out.println("Digiteo nome:");
                        String nome = sc.nextLine();

                        System.out.println("Digite o email:");
                        String email = sc.nextLine();

                        // Salva o novo usuário no banco de dados
                        repo.salvar(nome, email);

                        System.out.println("Usuario cadastrado com sucesso");
                    }
                    // BUSCAR USUARIO
                    case 2 -> {
                        System.out.println("Digite o email");
                        String email = sc.nextLine();

                        // Busca o usuário no banco pelo email; retorna nulo se não encontrar
                        String resultado = repo.buscarPorEmail(email);
                        if (resultado != null) {
                            System.out.println("Usuário encontrado" + resultado);
                        } else {
                            System.out.println("Nenhum usuário encontrado com esse email");
                        }
                    }
                    // ATUALIZAR USUARIO
                    case 3 -> {
                        System.out.println("Digite o email do usuário que deseja atualizar:");
                        String email = sc.nextLine();

                        System.out.println("Digite o novo nome:");
                        String novoNome = sc.nextLine();

                        // Atualiza o nome do usuário correspondente a esse email
                        repo.atualizar(email, novoNome);
                        System.out.println("Usuário atualizado com sucesso");
                    }
                    // DELETAR USUARIO
                    case 4 -> {
                        System.out.println("Digite o email do usuário a deletar:");
                        String email = sc.nextLine();

                        // Pede confirmação antes de deletar
                        System.out.println("Tem certeza? (S/N):");
                        String confirmar = sc.nextLine();
                        if (confirmar.equalsIgnoreCase("S")) {
                            // Remove o usuário do banco de dados
                            repo.deletar(email);
                            System.out.println("Usuário deletado com sucesso");
                        } else {
                            System.out.println("Operação cancelada");
                        }
                    }
                    // SAIR DO SISTEMA
                    case 0 -> {
                        System.out.println("Encerrando o sistema");
                        continuar = false; // faz o while parar na próxima verificação
                    }
                    // Qualquer opção que não seja 0, 1, 2, 3 ou 4
                    default -> System.out.println("Opção inválida. Tente novamente");
                }
            }catch (SQLException e){
                System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
            }
        }
        sc.close();
    }
}