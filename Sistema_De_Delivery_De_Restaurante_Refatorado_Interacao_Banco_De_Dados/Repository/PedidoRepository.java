package JDBC.Sistema_De_Delivery_De_Restaurante_Refatorado_Interacao_Banco_De_Dados.Repository;

import JDBC.Sistema_De_Delivery_De_Restaurante_Refatorado_Interacao_Banco_De_Dados.ClassConection.ClassConection;
import JDBC.Sistema_De_Delivery_De_Restaurante_Refatorado_Interacao_Banco_De_Dados.entities.Pagamento;
import JDBC.Sistema_De_Delivery_De_Restaurante_Refatorado_Interacao_Banco_De_Dados.entities.Produto;

import java.sql.*;
import java.util.List;

public class PedidoRepository {

    public void salvar(String usuarioLogin, int numeroPedido, Pagamento pagamento,
                       List<Produto> carrinho, double taxaEntrega) {
        double total = carrinho.stream().mapToDouble(Produto::getPreco).sum();
        double totalFinal = total + taxaEntrega;

        String sql = "INSERT INTO pedidos (usuario_login, numeroPedido, tipoPagamento, "
                + "totalProdutos, taxaEntrega, totalFinal, dataPedido) "
                + "VALUES (?, ?, ?, ?, ?, ?, NOW())";

        try (Connection conn = ClassConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuarioLogin);
            ps.setInt(2, numeroPedido);
            ps.setString(3, pagamento.getTipo().name());
            ps.setDouble(4, total);
            ps.setDouble(5, taxaEntrega);
            ps.setDouble(6, totalFinal);
            ps.execute();
            System.out.println("Pedido salvo com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao salvar pedido: " + e.getMessage());
        }
    }

    public void listarPedidos(String usuarioLogin) {
        String sql = "SELECT * FROM pedidos WHERE usuario_login = ? ORDER BY dataPedido DESC";
        try (Connection conn = ClassConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuarioLogin);

            try (ResultSet rs = ps.executeQuery()) {
                System.out.println("Histórico de pedidos:");
                boolean tem = false;
                while (rs.next()) {
                    tem = true;
                    System.out.println("Pedido #" + rs.getInt("numeroPedido")
                            + " | Pagamento: " + rs.getString("tipoPagamento")
                            + " | Total: R$ " + String.format("%.2f", rs.getDouble("totalFinal"))
                            + " | Data: " + rs.getTimestamp("dataPedido"));
                }
                if (!tem) System.out.println("Nenhum pedido encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar pedidos: " + e.getMessage());
        }
    }

    public void cancelarPedido(int numeroPedido) {
        String sql = "DELETE FROM pedidos WHERE numeroPedido = ?";
        try (Connection conn = ClassConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, numeroPedido);
            int linhas = ps.executeUpdate();
            System.out.println(linhas > 0 ? "Pedido cancelado." : "Pedido não encontrado.");
        } catch (SQLException e) {
            System.out.println("Erro ao cancelar pedido: " + e.getMessage());
        }
    }
}