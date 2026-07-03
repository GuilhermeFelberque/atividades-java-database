package JDBC.Sistema_De_Compra_Online_Refatorado_Interacao_Banco_De_Dados.aplication;

import JDBC.Sistema_De_Compra_Online_Refatorado_Interacao_Banco_De_Dados.Class_Connection.ClassConnectionCompra;
import JDBC.Sistema_De_Compra_Online_Refatorado_Interacao_Banco_De_Dados.Repository.produtoDAO;
import JDBC.Sistema_De_Compra_Online_Refatorado_Interacao_Banco_De_Dados.entities.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        try {
            // Cria objeto para ler dados do usuário

            System.out.println("sistema de compra online");
            System.out.println("-----cadastro-----");
            System.out.println("");
            System.out.println("digite seu nome:");
            String nome = sc.nextLine();
            System.out.println("digite seu cpf:");
            String cpf = sc.nextLine();
            System.out.println("digite seu endereço:");
            String endereco = sc.nextLine();

            // Conexão com o banco de dados
            Connection conn = (Connection) ClassConnectionCompra.getConnection();

            // Cria objeto cliente com os dados informados
            Cliente cliente = new Cliente(nome, cpf, endereco);

            // 1) INSERT na tabela pessoa (só nome e cpf, que é o que ela tem)
            String sqlPessoa = "INSERT INTO pessoa (nome, cpf) VALUES (?, ?)";
            PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa, Statement.RETURN_GENERATED_KEYS);
            stmtPessoa.setString(1, cliente.getNome());
            stmtPessoa.setString(2, cliente.getCpf());
            stmtPessoa.executeUpdate();

            int idPessoa = -1;
            try (ResultSet rsPessoa = stmtPessoa.getGeneratedKeys()) {
                if (rsPessoa.next()) {
                    idPessoa = rsPessoa.getInt(1);
                }
            }

            // 2) INSERT na tabela cliente (endereco + fk para pessoa)
            String sqlCliente = "INSERT INTO cliente (id_pessoa, endereco) VALUES (?, ?)";
            PreparedStatement stmtCliente = conn.prepareStatement(sqlCliente, Statement.RETURN_GENERATED_KEYS);
            stmtCliente.setInt(1, idPessoa);
            stmtCliente.setString(2, cliente.getEndereco());
            stmtCliente.executeUpdate();

            // idCliente agora é o id da tabela cliente (é ele que a tabela compra espera)
            int idCliente = -1;
            try (ResultSet rsCliente = stmtCliente.getGeneratedKeys()) {
                if (rsCliente.next()) {
                    idCliente = rsCliente.getInt(1);
                }
            }

            System.out.println("cadastrado com sucesso");

            int opcaoMenu;

            do {
                System.out.println("deseja atualizar o cadastro ou continuar compra:");
                System.out.println("");
                System.out.println("1 - Comprar");
                System.out.println("2 - Atualizar cliente");
                System.out.println("3 - Deletar cliente");
                System.out.println("4 - Buscar cliente");

                opcaoMenu = sc.nextInt();
                sc.nextLine();

                switch (opcaoMenu) {

                    case 1:
                        //a parte do sistem de carrinho entra em ação
                        System.out.println("vc escolheu compra");

                        break;

                    case 2:
                        // UPDATE (endereco mora em cliente, então atualiza via subquery pelo cpf da pessoa)

                        System.out.println("Digite o CPF do cliente:");
                        String cpfUpdate = sc.nextLine();

                        System.out.println("Novo endereço:");
                        String novoEndereco = sc.nextLine();

                        String sqlUpdate = "UPDATE cliente SET endereco = ? " +
                                "WHERE id_pessoa = (SELECT id FROM pessoa WHERE cpf = ?)";

                        PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate);
                        stmtUpdate.setString(1, novoEndereco);
                        stmtUpdate.setString(2, cpfUpdate);

                        int linhas = stmtUpdate.executeUpdate();
                        if (linhas > 0) {
                            System.out.println("Cliente atualizado com sucesso!");
                        } else {
                            System.out.println("Cliente não encontrado!");
                        }
                        break;

                    case 3:
                        // DELETE (precisa apagar primeiro em cliente por causa da FK, depois em pessoa)

                        System.out.println("Digite o CPF para deletar:");
                        String cpfDelete = sc.nextLine();

                        String sqlDeleteCliente = "DELETE FROM cliente " +
                                "WHERE id_pessoa = (SELECT id FROM pessoa WHERE cpf = ?)";
                        PreparedStatement stmtDeleteCliente = conn.prepareStatement(sqlDeleteCliente);
                        stmtDeleteCliente.setString(1, cpfDelete);
                        stmtDeleteCliente.executeUpdate();

                        String sqlDeletePessoa = "DELETE FROM pessoa WHERE cpf = ?";
                        PreparedStatement stmtDeletePessoa = conn.prepareStatement(sqlDeletePessoa);
                        stmtDeletePessoa.setString(1, cpfDelete);

                        linhas = stmtDeletePessoa.executeUpdate();

                        if (linhas > 0) {
                            System.out.println("Cliente deletado com sucesso!");
                        } else {
                            System.out.println("Cliente não encontrado!");
                        }
                        break;

                    case 4:
                        // SELECT (junta pessoa + cliente pra trazer o endereco também)

                        System.out.println("Digite o CPF para buscar:");
                        String cpfBusca = sc.nextLine();

                        String sqlSelect = "SELECT p.nome, p.cpf, c.endereco " +
                                "FROM pessoa p JOIN cliente c ON c.id_pessoa = p.id " +
                                "WHERE p.cpf = ?";

                        PreparedStatement stmtSelect = conn.prepareStatement(sqlSelect);
                        stmtSelect.setString(1, cpfBusca);

                        ResultSet rs = stmtSelect.executeQuery();

                        if (rs.next()) {
                            System.out.println("Nome: " + rs.getString("nome"));
                            System.out.println("CPF: " + rs.getString("cpf"));
                            System.out.println("Endereço: " + rs.getString("endereco"));
                        } else {
                            System.out.println("Cliente não encontrado!");
                        }
                        break;
                }
            } while (opcaoMenu != 1);

            // Cria DAO para acessar produtos no banco
            produtoDAO produtoDAO = new produtoDAO(conn);

            // Cria carrinho de compras
            carrinho carrinho = new carrinho();

            String continuar;

            do {
                System.out.println("digite o ID do produto");
                int iDproduto = sc.nextInt();

                Produto produto = produtoDAO.buscarPorID(iDproduto);

                if (produto != null) {
                    carrinho.adicionarProduto(produto);
                    System.out.println("produto adicionadado: " + produto.nomeProduto());
                } else {
                    System.out.println("produto não encontrado");
                }
                System.out.println("deseja adicionar mais produto?");
                continuar = sc.next();

            } while (continuar.equalsIgnoreCase("S"));

            System.out.println("Resumo da compra");

            for (Produto p : carrinho.getProdutos()) {
                System.out.println(p.nomeProduto() + " -$" + p.precoProdutoo());
            }
            System.out.println("total: " + carrinho.calcularTotal());

            System.out.println("Escolha a forma de pagamento:");
            System.out.println("1 - CARTAO");
            System.out.println("2 - PIX");
            System.out.println("3 - BOLETO");
            System.out.println("4 - DINHEIRO");
            int opcao = sc.nextInt();
            sc.nextLine();

            Pagamento pagamento;

            switch (opcao) {
                case 1:
                    pagamento = new PagamentoCartao(carrinho.calcularTotal());
                    break;
                case 2:
                    pagamento = new PagamentoPix(carrinho.calcularTotal());
                    break;
                case 3:
                    pagamento = new PagamentoBoleto(carrinho.calcularTotal());
                    break;
                case 4:
                    pagamento = new PagamentoDinheiro(carrinho.calcularTotal());
                    break;
                default:
                    throw new InputMismatchException();
            }
            pagamento.processarPagamento();

            System.out.println("Pagamento escolhido: " + pagamento.getClass().getSimpleName().replace("Pagamento", "").toUpperCase());
            System.out.println("pagamento realizado com sucesso");

            String sqlCompra = "INSERT INTO compra (id_cliente, valor, frete, FormaDePagamento) VALUES (?, ?, ?, ?)";

            try (java.sql.Connection conexao = ClassConnectionCompra.getConnection()) {

                PreparedStatement stmtCompra = conexao.prepareStatement(sqlCompra);

                stmtCompra.setInt(1, idCliente); // agora é o id de fato da tabela cliente
                stmtCompra.setDouble(2, carrinho.calcularTotal());
                stmtCompra.setDouble(3, 10.0);
                stmtCompra.setString(4, pagamento.getClass().getSimpleName().replace("Pagamento", "").toUpperCase());

                stmtCompra.executeUpdate();

                System.out.println("Compra salva com sucesso!");

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (InputMismatchException e) {
            System.out.println("entrada invalida");
        } catch (MySQLIntegrityConstraintViolationException e) {
            System.out.println("dados ja existentes");
        } finally {
            sc.close();
        }
    }
}