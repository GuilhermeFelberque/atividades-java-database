package JDBC.Sistema_De_Agendamento_Medico_refatorado_Enum_Interacao_Banco_De_Dados.Repository;

import JDBC.Sistema_De_Agendamento_Medico_Refatorado_Interacao_Banco_De_Dados.entities.Paciente;
import JDBC.Sistema_De_Agendamento_Medico_refatorado_Enum_Interacao_Banco_De_Dados.ConnectionFactory.ConnectionFactory;
import JDBC.Sistema_De_Agendamento_Medico_refatorado_Enum_Interacao_Banco_De_Dados.entities.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PacienteRepository {
    public void salvar(String cpf, String nome) throws SQLException {
        String sql = "INSERT INTO pacientes (cpf, nome) VALUES (?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.setString(2, nome);
            stmt.executeUpdate();
        }
    }

    public Paciente buscarPorCpf(String cpf) throws SQLException {
        String sql = "SELECT cpf , nome FROM pacientes WHERE cpf = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Paciente(rs.getString("cpf"), rs.getString("nome"));
                }
                return null;
            }
        }
    }

    public boolean atualizarNome(String cpf, String novoNome) throws SQLException {
        String sql = "UPDATE pacientes SET nome = ? WHERE cpf = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, novoNome);
            stmt.setString(2, cpf);
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean deletar(String cpf) throws SQLException {
        String sql = "DELETE FROM pacientes WHERE cpf = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            return stmt.executeUpdate() > 0;
        }
    }
}