package JDBC.Sistema_De_Delivery_De_Restaurante_Refatorado_Interacao_Banco_De_Dados.Repository;

import JDBC.Sistema_De_Delivery_De_Restaurante_Refatorado_Interacao_Banco_De_Dados.ClassConection.ClassConection;
import JDBC.Sistema_De_Delivery_De_Restaurante_Refatorado_Interacao_Banco_De_Dados.entities.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardapioRepository {

    public List<Produto> listarProdutos() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT codigo, nome, preco FROM produtos";
        try (Connection conn = ClassConection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next())
                lista.add(new Produto(
                        rs.getInt("codigo"),
                        rs.getString("nome"),
                        rs.getDouble("preco")));
        } catch (SQLException e) {
            System.out.println("Erro ao carregar cardápio: " + e.getMessage());
        }
        return lista;
    }

    public void adicionarProduto(int codigo, String nome, double preco) {
        String sql = "INSERT IGNORE INTO produtos (codigo, nome, preco) VALUES (?, ?, ?)";
        try (Connection conn = ClassConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            ps.setString(2, nome);
            ps.setDouble(3, preco);
            ps.execute();
            System.out.println("Produto adicionado!");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar produto: " + e.getMessage());
        }
    }

    public void atualizarPreco(int codigo, double novoPreco) {
        String sql = "UPDATE produtos SET preco = ? WHERE codigo = ?";
        try (Connection conn = ClassConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, novoPreco);
            ps.setInt(2, codigo);
            int linhas = ps.executeUpdate();
            System.out.println(linhas > 0 ? "Preço atualizado!" : "Produto não encontrado.");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar preço: " + e.getMessage());
        }
    }

    public void removerProduto(int codigo) {
        String sql = "DELETE FROM produtos WHERE codigo = ?";
        try (Connection conn = ClassConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            ps.execute();
            System.out.println("Produto removido.");
        } catch (SQLException e) {
            System.out.println("Erro ao remover produto: " + e.getMessage());
        }
    }
}