package JDBC.Sistema_De_Delivery_De_Restaurante_Refatorado_Interacao_Banco_De_Dados.application;

import JDBC.Sistema_De_Delivery_De_Restaurante_Refatorado_Interacao_Banco_De_Dados.Repository.AutenticadorRepository;
import JDBC.Sistema_De_Delivery_De_Restaurante_Refatorado_Interacao_Banco_De_Dados.Repository.CardapioRepository;
import JDBC.Sistema_De_Delivery_De_Restaurante_Refatorado_Interacao_Banco_De_Dados.Repository.PedidoRepository;
import JDBC.Sistema_De_Delivery_De_Restaurante_Refatorado_Interacao_Banco_De_Dados.entities.Cartao;
import JDBC.Sistema_De_Delivery_De_Restaurante_Refatorado_Interacao_Banco_De_Dados.entities.Dinheiro;
import JDBC.Sistema_De_Delivery_De_Restaurante_Refatorado_Interacao_Banco_De_Dados.entities.Pagamento;
import JDBC.Sistema_De_Delivery_De_Restaurante_Refatorado_Interacao_Banco_De_Dados.entities.Pix;
import JDBC.Sistema_De_Delivery_De_Restaurante_Refatorado_Interacao_Banco_De_Dados.entities.Produto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        AutenticadorRepository autenticador = new AutenticadorRepository();
        CardapioRepository cardapio = new CardapioRepository();
        PedidoRepository pedidoRepo = new PedidoRepository();

        try {
            System.out.println("Sistema de Delivery");
            System.out.println("1 - Login");
            System.out.println("2 - Cadastrar novo usuário");
            System.out.print("Escolha: ");
            int opcaoInicial = Integer.parseInt(sc.nextLine());

            // Se o usuário escolher cadastrar o programa cadastra e encerra
            // O return sai do main direto, sem entrar no menu principal)
            if (opcaoInicial == 2) {
                System.out.print("E-mail: ");
                String novoLogin = sc.nextLine();
                System.out.print("Senha: ");
                String novaSenha = sc.nextLine();
                autenticador.cadastrar(novoLogin, novaSenha);
                return;
            }

            System.out.print("E-mail: ");
            String email = sc.nextLine();
            System.out.print("Senha: ");
            String senha = sc.nextLine();

            // Se o login/senha não baterem, encerra o programa aqui mesmo
            if (!autenticador.autenticar(email, senha)) {
                System.out.println("E-mail ou senha inválidos.");
                return;
            }

            System.out.println("Login realizado com sucesso!");

            boolean continuar = true;
            while (continuar) {

                System.out.println(" MENU ");
                System.out.println("1 - Fazer pedido");
                System.out.println("2 - Ver meus pedidos");
                System.out.println("3 - Cancelar pedido");
                System.out.println("4 - Atualizar senha");
                System.out.println("5 - Cancelar meu cadastro");
                System.out.println("6 - Cadastrar produto no cardápio");
                System.out.println("0 - Sair");

                System.out.print("Escolha: ");

                int menu = Integer.parseInt(sc.nextLine());

                if (menu == 1) {
                    List<Produto> produtos = cardapio.listarProdutos();

                    if (produtos.isEmpty()) {
                        System.out.println("O cardápio ainda está vazio. Cadastre um produto primeiro (opção 6).");
                        continue;
                    }

                    List<Produto> carrinho = new ArrayList<>();
                    int opcao;

                    // O loop do carrinho fica pedindo produtos até o usuário digitar 0.
                    // Cada produto escolhido é adicionado na lista "carrinho".
                    do {
                        System.out.println("Cardápio:");
                        for (Produto p : produtos)
                            System.out.println(p.getCodigo() + " - " + p.getNome()
                                    + "  R$ " + String.format("%.2f", p.getPreco()));
                        System.out.println("0 - Finalizar pedido");
                        System.out.print("Escolha um produto: ");
                        opcao = Integer.parseInt(sc.nextLine());

                        // Percorre o cardápio procurando o produto com o código digitado
                        for (Produto p : produtos) {
                            if (p.getCodigo() == opcao) {
                                carrinho.add(p);
                                System.out.println(p.getNome() + " adicionado ao carrinho!");
                            }
                        }
                    } while (opcao != 0);

                    if (carrinho.isEmpty()) {
                        System.out.println("Carrinho vazio.");
                        continue;
                    }

                    System.out.println("Forma de pagamento:");
                    System.out.println("1 - Pix");
                    System.out.println("2 - Dinheiro");
                    System.out.println("3 - Cartão");
                    System.out.print("Escolha: ");
                    int forma = Integer.parseInt(sc.nextLine());

                    /*Pagamento é declarado como o tipo mãe (Pagamento), mas recebe
                     um objeto diferente dependendo da escolha.
                     Cada subclasse (Pix, Dinheiro, Cartao) processa o pagamento do seu jeito.*/
                    Pagamento pagamento;
                    if (forma == 2) pagamento = new Dinheiro();
                    else if (forma == 3) pagamento = new Cartao();
                    else pagamento = new Pix();

                    // Soma o preço de todos os produtos do carrinho usando Stream
                    double total = carrinho.stream().mapToDouble(Produto::getPreco).sum();
                    // Só cobra a taxa de entrega se o pedido for menor que R$50
                    double taxaEntrega = total < 50 ? 8.0 : 0.0;
                    int numeroPedido = (int) (Math.random() * 10000);

                    pagamento.processar();
                    System.out.println("Produtos do pedido:");
                    carrinho.forEach(p -> System.out.println("- " + p.getNome()));
                    System.out.println("Taxa de entrega: R$ " + String.format("%.2f", taxaEntrega));
                    System.out.println("Total: R$ " + String.format("%.2f", total + taxaEntrega));
                    System.out.println("Número do pedido: " + numeroPedido);

                    pedidoRepo.salvar(email, numeroPedido, pagamento, carrinho, taxaEntrega);

                } else if (menu == 2) {
                    pedidoRepo.listarPedidos(email);

                } else if (menu == 3) {
                    System.out.print("Número do pedido a cancelar: ");
                    int num = Integer.parseInt(sc.nextLine());
                    pedidoRepo.cancelarPedido(num);

                } else if (menu == 4) {
                    System.out.print("Nova senha: ");
                    String novaSenha = sc.nextLine();
                    autenticador.atualizarSenha(email, novaSenha);

                } else if (menu == 5) {
                    /*Só cancela o cadastro se a senha
                    digitada bater com a senha usada no login (variável) "senha")*/
                    System.out.print("Digite sua senha para confirmar: ");
                    String senhaConfirm = sc.nextLine();

                    if (!senhaConfirm.equals(senha)) {
                        System.out.println("Senha incorreta. Cancelamento negado.");
                    } else {
                        autenticador.deletar(email);
                        System.out.println("Cadastro cancelado. Até logo!");
                        continuar = false; // encerra o while do menu, já que o usuário não existe mais
                    }

                } else if (menu == 6) {
                    System.out.print("Código do produto: ");
                    int codigo = Integer.parseInt(sc.nextLine());
                    System.out.print("Nome do produto: ");
                    String nome = sc.nextLine();
                    System.out.print("Preço do produto: ");
                    double preco = Double.parseDouble(sc.nextLine());
                    cardapio.adicionarProduto(codigo, nome, preco);

                } else if (menu == 0) {
                    System.out.println("Saindo...");
                    continuar = false;

                } else {
                    System.out.println("Opção inválida.");
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("Digite apenas números nas opções.");
        }
        sc.close();
    }
}