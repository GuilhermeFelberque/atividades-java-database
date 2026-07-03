package JDBC.Sistema_de_Controle_de_Biblioteca_Escolar_Refatorado_Interacao_Banco_De_Dados.Application;

import JDBC.Sistema_de_Controle_de_Biblioteca_Escolar_Refatorado_Interacao_Banco_De_Dados.Entities.Aluno;
import JDBC.Sistema_de_Controle_de_Biblioteca_Escolar_Refatorado_Interacao_Banco_De_Dados.Entities.Livro;
import JDBC.Sistema_de_Controle_de_Biblioteca_Escolar_Refatorado_Interacao_Banco_De_Dados.Entities.RegraEmprestimo;
import JDBC.Sistema_de_Controle_de_Biblioteca_Escolar_Refatorado_Interacao_Banco_De_Dados.Repository.AlunoRepository;
import JDBC.Sistema_de_Controle_de_Biblioteca_Escolar_Refatorado_Interacao_Banco_De_Dados.Repository.LivroRepository;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);
        AlunoRepository alunoRepo = new AlunoRepository();
        LivroRepository livroRepo = new LivroRepository();
        int opcao;

        do {

            System.out.println(" SISTEMA DE BIBLIOTECA ");
            System.out.println("1 - Cadastrar aluno");
            System.out.println("2 - Cadastrar livro");
            System.out.println("3 - Realizar emprestimo");
            System.out.println("4 - Listar alunos");
            System.out.println("5 - Listar livros");
            System.out.println("6 - Cancelar cadastro de aluno");
            System.out.println("7 - Cancelar cadastro de livro");
            System.out.println("8 - Cancelar emprestimo");
            System.out.println("0 - Sair");

            System.out.print("Escolha: ");

            try {
                opcao = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                // Cai aqui se o usuário digitar algo que não é número
                System.out.println("Digite apenas numeros.");
                sc.nextLine();
                opcao = -1; // valor neutro para não cair em nenhum case do switch
                continue; // volta pro início do loop e mostra o menu de novo
            }

            try {
                switch (opcao) {
                    // CADASTRAR ALUNO
                    case 1:
                        System.out.println(" Cadastrar Aluno ");
                        System.out.print("Nome: ");
                        String nome = sc.nextLine();
                        System.out.print("Matricula: ");
                        int matricula = sc.nextInt();
                        sc.nextLine();
                        alunoRepo.cadastrar(nome, matricula);
                        break;
                    // CADASTRAR LIVRO
                    case 2:
                        System.out.println(" Cadastrar Livro ");
                        System.out.print("Titulo: ");
                        String titulo = sc.nextLine();
                        System.out.print("Codigo: ");
                        int codigo = sc.nextInt();
                        sc.nextLine();
                        livroRepo.cadastrar(titulo, codigo);
                        break;
                    // REALIZAR EMPRESTIMO
                    case 3:
                        System.out.println(" Realizar Emprestimo ");
                        for (Aluno a : alunoRepo.listarParaEmprestimo()) System.out.println(a);
                        System.out.print("Matricula do aluno: ");
                        int matEmp = sc.nextInt();
                        sc.nextLine();

                        // Busca o aluno completo pra checar a regra de empréstimo
                        Aluno aluno = alunoRepo.buscarPorMatricula(matEmp);
                        if (aluno == null) {
                            System.out.println("Aluno nao encontrado.");
                            break;
                        }
                        // Verifica se o aluno está liberado (sem multa, dentro do limite
                        // de livros permitidos) antes de deixar ele pegar outro livro
                        if (!RegraEmprestimo.podeEmprestar(aluno)) {
                            System.out.println("Este aluno nao pode pegar mais livros emprestados (multa ou limite atingido).");
                            break;
                        }

                        for (Livro l : livroRepo.listarDisponiveis()) System.out.println(l);
                        System.out.print("Codigo do livro: ");
                        int codEmp = sc.nextInt();
                        sc.nextLine();
                        // marca o livro como emprestado e soma esse empréstimo na conta do aluno
                        livroRepo.marcarEmprestado(codEmp);
                        alunoRepo.adicionarEmprestimo(matEmp);
                        System.out.println("Emprestimo realizado com sucesso!");
                        break;
                    // LISTA OS ALUNOS
                    case 4:
                        System.out.println(" Lista de Alunos ");
                        List<Aluno> alunos = alunoRepo.listar();
                        if (alunos.isEmpty()) {
                            System.out.println("Nenhum aluno cadastrado.");
                        } else {
                            for (Aluno a : alunos) System.out.println(a);
                        }
                        break;
                    // LISTA OS LIVROS
                    case 5:
                        System.out.println(" Lista de Livros ");
                        List<Livro> livros = livroRepo.listar();
                        if (livros.isEmpty()) {
                            System.out.println("Nenhum livro cadastrado.");
                        } else {
                            for (Livro l : livros) System.out.println(l);
                        }
                        break;
                    // CANCELA CADASTRO DO ALUNO
                    case 6:
                        System.out.println(" Cancelar Cadastro de Aluno ");
                        for (Aluno a : alunoRepo.listar()) System.out.println(a);
                        System.out.print("Matricula do aluno a cancelar: ");
                        int matCancelar = sc.nextInt();
                        sc.nextLine();
                        boolean alunoRemovido = alunoRepo.cancelar(matCancelar);
                        System.out.println(alunoRemovido ? "Aluno removido com sucesso!" : "Aluno nao encontrado.");
                        break;
                    // CANCELA CADASTRO DO LIVRO
                    case 7:
                        System.out.println(" Cancelar Cadastro de Livro ");
                        for (Livro l : livroRepo.listar()) System.out.println(l);
                        System.out.print("Codigo do livro a cancelar: ");
                        int codCancelar = sc.nextInt();
                        sc.nextLine();
                        boolean livroRemovido = livroRepo.cancelar(codCancelar);
                        System.out.println(livroRemovido ? "Livro removido com sucesso!" : "Livro nao encontrado.");
                        break;
                    // CANCELA O EMPRESTIMO
                    case 8:
                        System.out.println(" Cancelar Emprestimo ");
                        // Passo 1: mostra os livros emprestados e pede qual código devolver
                        for (Livro l : livroRepo.listarEmprestados()) System.out.println(l);
                        System.out.print("Codigo do livro a devolver: ");
                        int codDev = sc.nextInt();
                        sc.nextLine();
                        // Passo 2: mostra os alunos que têm empréstimo ativo e pede a matrícula
                        for (Aluno a : alunoRepo.listarComEmprestimo()) System.out.println(a);
                        System.out.print("Matricula do aluno: ");
                        int matDev = sc.nextInt();
                        sc.nextLine();
                        boolean devolvido = livroRepo.marcarDevolvido(codDev);
                        if (devolvido) {
                            // Só desconta o empréstimo do aluno se o livro realmente foi encontrado e marcado como devolvido
                            alunoRepo.removerEmprestimo(matDev);
                            System.out.println("Emprestimo cancelado com sucesso!");
                        } else {
                            System.out.println("Livro nao encontrado.");
                        }
                        break;
                        // ENCERRA O SISTEMA
                    case 0:
                        System.out.println("Sistema encerrado. Ate logo!");
                        break;

                    default:
                        System.out.println("Opcao invalida. Tente novamente.");
                }

            } catch (SQLException e) {
                System.out.println("Erro no banco de dados: " + e.getMessage());
            } catch (NumberFormatException e){
                System.out.println("Digite apenas númneros");
            }

        } while (opcao != 0); // repete o menu até o usuário escolher 0 (Sair)

        sc.close();
    }
}