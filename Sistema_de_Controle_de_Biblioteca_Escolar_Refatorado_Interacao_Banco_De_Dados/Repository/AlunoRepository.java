package JDBC.Sistema_de_Controle_de_Biblioteca_Escolar_Refatorado_Interacao_Banco_De_Dados.Repository;

import JDBC.Sistema_de_Controle_de_Biblioteca_Escolar_Refatorado_Interacao_Banco_De_Dados.ClassConection.Conection;
import JDBC.Sistema_de_Controle_de_Biblioteca_Escolar_Refatorado_Interacao_Banco_De_Dados.Entities.Aluno;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoRepository {

    // INSERT
    public void cadastrar(String nome, int matricula) throws SQLException {
        String sql = "INSERT INTO aluno (matricula, nomeAluno, possuiMulta, livrosEmprestados) VALUES (?, ?, 0, 0)";
        try (Connection conn = Conection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, matricula);
            stmt.setString(2, nome);
            stmt.execute();
            System.out.println("Aluno cadastrado com sucesso!");
        }
    }

    // SELECT - lista todos, devolvendo objetos Aluno
    public List<Aluno> listar() throws SQLException {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM aluno";
        try (Connection conn = Conection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                alunos.add(new Aluno(
                        rs.getInt("matricula"),
                        rs.getString("nomeAluno"),
                        rs.getInt("possuiMulta") == 1,
                        rs.getInt("livrosEmprestados")));
            }
        }
        return alunos;
    }

    // SELECT - alunos disponíveis para empréstimo
    public List<Aluno> listarParaEmprestimo() throws SQLException {
        return listar();
    }

    // SELECT - alunos com empréstimo ativo
    public List<Aluno> listarComEmprestimo() throws SQLException {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM aluno WHERE livrosEmprestados > 0";
        try (Connection conn = Conection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                alunos.add(new Aluno(
                        rs.getInt("matricula"),
                        rs.getString("nomeAluno"),
                        rs.getInt("possuiMulta") == 1,
                        rs.getInt("livrosEmprestados")));
            }
        }
        return alunos;
    }

    // Busca um único aluno pela matrícula (novo método - precisamos dele pra validar a regra de empréstimo)
    public Aluno buscarPorMatricula(int matricula) throws SQLException {
        String sql = "SELECT * FROM aluno WHERE matricula = ?";
        try (Connection conn = Conection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, matricula);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Aluno(
                            rs.getInt("matricula"),
                            rs.getString("nomeAluno"),
                            rs.getInt("possuiMulta") == 1,
                            rs.getInt("livrosEmprestados"));
                }
                return null;
            }
        }
    }

    // DELETE
    public boolean cancelar(int matricula) throws SQLException {
        String sql = "DELETE FROM aluno WHERE matricula = ?";
        try (Connection conn = Conection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, matricula);
            return stmt.executeUpdate() > 0;
        }
    }

    // UPDATE
    public void adicionarEmprestimo(int matricula) throws SQLException {
        String sql = "UPDATE aluno SET livrosEmprestados = livrosEmprestados + 1 WHERE matricula = ?";
        try (Connection conn = Conection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, matricula);
            stmt.executeUpdate();
        }
    }

    // UPDATE
    public void removerEmprestimo(int matricula) throws SQLException {
        String sql = "UPDATE aluno SET livrosEmprestados = livrosEmprestados - 1 WHERE matricula = ? AND livrosEmprestados > 0";
        try (Connection conn = Conection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, matricula);
            stmt.executeUpdate();
        }
    }
}