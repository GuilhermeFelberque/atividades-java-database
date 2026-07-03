package JDBC.Sistema_De_Reserva_De_Hotel_Refatorado_Interacao_Banco_De_Dados.Repository;

import JDBC.Sistema_De_Reserva_De_Hotel_Refatorado_Interacao_Banco_De_Dados.ClassConection.ClassConection;
import JDBC.Sistema_De_Reserva_De_Hotel_Refatorado_Interacao_Banco_De_Dados.entities.Quartos;

import java.sql.*;

public class QuartosRepository {

    public void cadastrar(Quartos quarto) throws SQLException {
        String sql = "INSERT INTO Quartos (numero, ValorDiaria, tipoQuartos, diasEstadia) VALUES (?, ?, ?, ?)";
        try (Connection conn = ClassConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, quarto.getNumero());
            ps.setDouble(2, quarto.getValorDiaria());
            ps.setString(3, quarto.getTipoQuarto().name());
            ps.setInt(4, quarto.getDiasEstadia());
            ps.execute();
            System.out.println("Quarto cadastrado com sucesso!");
        }
    }

    public void listar() throws SQLException {
        String sql = "SELECT *, (ValorDiaria * diasEstadia) AS totalQuarto FROM Quartos";
        try (Connection conn = ClassConection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("=== Quartos Cadastrados ===");
            while (rs.next())
                System.out.println("ID: " + rs.getInt("idQuarto")
                        + " | Numero: " + rs.getInt("numero")
                        + " | Tipo: " + rs.getString("tipoQuartos")
                        + " | Diaria: R$ " + rs.getDouble("ValorDiaria")
                        + " | Dias: " + rs.getInt("diasEstadia")
                        + " | Total: R$ " + rs.getDouble("totalQuarto"));
        }
    }

    public double buscarValorTotal(int idQuarto) throws SQLException {
        String sql = "SELECT (ValorDiaria * diasEstadia) AS total FROM Quartos WHERE idQuarto = ?";
        try (Connection conn = ClassConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idQuarto);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next())
                    return rs.getDouble("total");
                return 0;
            }
        }
    }

    public void deletar(int idQuarto) throws SQLException {
        String sql = "DELETE FROM Quartos WHERE idQuarto = ?";
        try (Connection conn = ClassConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idQuarto);
            int linhas = ps.executeUpdate();
            System.out.println(linhas > 0 ? "Quarto deletado com sucesso!" : "Quarto nao encontrado.");
        }
    }
}