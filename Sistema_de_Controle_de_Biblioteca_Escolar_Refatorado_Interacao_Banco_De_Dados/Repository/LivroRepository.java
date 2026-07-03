package JDBC.Sistema_de_Controle_de_Biblioteca_Escolar_Refatorado_Interacao_Banco_De_Dados.Repository;

import JDBC.Sistema_de_Controle_de_Biblioteca_Escolar_Refatorado_Interacao_Banco_De_Dados.ClassConection.Conection;
import JDBC.Sistema_de_Controle_de_Biblioteca_Escolar_Refatorado_Interacao_Banco_De_Dados.Entities.Livro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroRepository {

    // INSERT
    public void cadastrar(String titulo, int codigo) throws SQLException {
        String sql = "INSERT INTO livro (codigoLivro, tituloLivro, livroDisponivel) VALUES (?, ?, 1)";
        try (Connection conn = Conection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            stmt.setString(2, titulo);
            stmt.execute();
            System.out.println("Livro cadastrado com sucesso!");
        }
    }

    // SELECT - todos os livros
    public List<Livro> listar() throws SQLException {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livro";
        try (Connection conn = Conection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                livros.add(new Livro(
                        rs.getInt("codigoLivro"),
                        rs.getString("tituloLivro"),
                        rs.getInt("livroDisponivel") == 1));
            }
        }
        return livros;
    }

    // SELECT - só disponíveis
    public List<Livro> listarDisponiveis() throws SQLException {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livro WHERE livroDisponivel = 1";
        try (Connection conn = Conection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                livros.add(new Livro(
                        rs.getInt("codigoLivro"),
                        rs.getString("tituloLivro"),
                        true));
            }
        }
        return livros;
    }

    // SELECT - só emprestados
    public List<Livro> listarEmprestados() throws SQLException {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livro WHERE livroDisponivel = 0";
        try (Connection conn = Conection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                livros.add(new Livro(
                        rs.getInt("codigoLivro"),
                        rs.getString("tituloLivro"),
                        false));
            }
        }
        return livros;
    }

    // DELETE
    public boolean cancelar(int codigo) throws SQLException {
        String sql = "DELETE FROM livro WHERE codigoLivro = ?";
        try (Connection conn = Conection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            return stmt.executeUpdate() > 0;
        }
    }

    // UPDATE
    public void marcarEmprestado(int codigo) throws SQLException {
        String sql = "UPDATE livro SET livroDisponivel = 0 WHERE codigoLivro = ?";
        try (Connection conn = Conection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
        }
    }

    // UPDATE
    public boolean marcarDevolvido(int codigo) throws SQLException {
        String sql = "UPDATE livro SET livroDisponivel = 1 WHERE codigoLivro = ?";
        try (Connection conn = Conection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            return stmt.executeUpdate() > 0;
        }
    }
}