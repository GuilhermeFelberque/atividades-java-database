package JDBC.Sistema_De_Contas_De_UsuarioStreamig_Interacao_Banco_De_Dados.Repository;

import JDBC.Sistema_De_Contas_De_UsuarioStreamig_Interacao_Banco_De_Dados.ConnectionFactory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioRepository {

    // INSERT
    public void salvar(String nome, String email) throws SQLException {

        String sql = "INSERT INTO usuarios(nome, email) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, email);

            stmt.executeUpdate();
        }
    }

    // SELECT
    public String buscarPorEmail(String email) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE email = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return "Nome: " + rs.getString("nome") + " | Email: " + rs.getString("email");
                }
                return null;
            }
        }
    }

    // UPDATE
    public void atualizar(String email, String novoNome) throws SQLException {

        String sql = "UPDATE usuarios SET nome = ? WHERE email = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novoNome);
            stmt.setString(2, email);

            stmt.executeUpdate();
        }
    }

    // DELETE
    public void deletar(String email) throws SQLException {

        String sql = "DELETE FROM usuarios WHERE email = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);

            stmt.executeUpdate();
        }
    }
}