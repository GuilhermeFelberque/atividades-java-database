package JDBC.Sistema_De_Compra_Online_Refatorado_Interacao_Banco_De_Dados.Repository;

import JDBC.Sistema_De_Compra_Online_Refatorado_Interacao_Banco_De_Dados.Class_Connection.ClassConnectionCompra;
import JDBC.Sistema_De_Compra_Online_Refatorado_Interacao_Banco_De_Dados.entities.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class produtoDAO {
    public produtoDAO(com.mysql.jdbc.Connection conn) {

    }

    public Produto buscarPorID(int id) throws SQLException {
        // SQL agora filtra pelo ID
        String sql = "SELECT id, nome, preco_unitario, marca FROM produtos WHERE id = ?";

        try (Connection conexao = ClassConnectionCompra.getConnection()) {

            // Cria o PreparedStatement
            PreparedStatement stmt = conexao.prepareStatement(sql);

            // Substitui o ? pelo ID digitado
            stmt.setInt(1, id);

            // Executa o SELECT
            ResultSet rs = stmt.executeQuery();

            // Verifica se encontrou produto
            if (rs.next()) {

                // Pega os dados do banco
                int idProduto = rs.getInt("id");
                String nomeProduto = rs.getString("nome");
                double preco_unitario = rs.getDouble("preco_unitario");
                String nomeMarcaProduto = rs.getString("marca");

                // Cria objeto Produto
                Produto produto = new Produto(idProduto, nomeProduto, preco_unitario, nomeMarcaProduto);

                System.out.println("ID Produto: " + idProduto +
                        "| Nome Produto: " + nomeProduto +
                        "| Preço produto: " + preco_unitario +
                        "| Marca Produto: " + nomeMarcaProduto);

                return produto; // retorna produto encontrado
            }
            return null; // se não encontrou

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}