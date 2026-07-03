package JDBC.Sistema_De_Reserva_De_Hotel_Refatorado_Interacao_Banco_De_Dados.Repository;

import JDBC.Sistema_De_Reserva_De_Hotel_Refatorado_Interacao_Banco_De_Dados.ClassConection.ClassConection;
import JDBC.Sistema_De_Reserva_De_Hotel_Refatorado_Interacao_Banco_De_Dados.entities.Chekin;

import java.sql.*;

public class CheckinRepository {

    public void cadastrar(Chekin chekin) throws SQLException {
        String sql = "INSERT INTO Chekin (nomeCliente, cpf, telefone, dataCheckin, dataCheckout, "
                + "cidade, ValorTotal, pagamento, idQuarto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ClassConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, chekin.getNomeCliente());
            ps.setString(2, chekin.getCpf());
            ps.setString(3, chekin.getTelefone());
            ps.setString(4, chekin.getDataCheckin());
            ps.setString(5, chekin.getDataCheckout());
            ps.setString(6, chekin.getCidade());
            ps.setDouble(7, chekin.getValorTotal());
            ps.setString(8, chekin.getPagamento());
            ps.setInt(9, chekin.getIdQuarto());
            ps.execute();
            System.out.println("Reserva cadastrada! Valor cobrado: R$ " + chekin.getValorTotal());
        }
    }

    public void buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Chekin WHERE idChekin = ?";
        try (Connection conn = ClassConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    System.out.println("Reserva nao encontrada.");
                } else {
                    System.out.println("=== Reserva ===");
                    System.out.println("ID: " + rs.getInt("idChekin"));
                    System.out.println("Nome: " + rs.getString("nomeCliente"));
                    System.out.println("CPF: " + rs.getString("cpf"));
                    System.out.println("Telefone: " + rs.getString("telefone"));
                    System.out.println("Cidade: " + rs.getString("cidade"));
                    System.out.println("Check-in: " + rs.getString("dataCheckin"));
                    System.out.println("Check-out: " + rs.getString("dataCheckout"));
                    System.out.println("Valor Total: R$ " + rs.getDouble("ValorTotal"));
                    System.out.println("Pagamento: " + rs.getString("pagamento"));
                }
            }
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM Chekin WHERE idChekin = ?";
        try (Connection conn = ClassConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int linhas = ps.executeUpdate();
            System.out.println(linhas > 0 ? "Reserva deletada com sucesso!" : "Reserva nao encontrada.");
        }
    }
}