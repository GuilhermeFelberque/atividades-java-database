package JDBC.Sistema_De_Passagens_Aereas_Refatorado_Intercao_Banco_De_Dados.Repository;

import JDBC.Sistema_De_Passagens_Aereas_Refatorado_Intercao_Banco_De_Dados.ClassConection.ClassConection;
import JDBC.Sistema_De_Passagens_Aereas_Refatorado_Intercao_Banco_De_Dados.Entities.Passagem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PassagemRepository {

    public void salvar(Passagem passagem) {
        String sql = "INSERT INTO passagens (nomePassageiro, numeroVoo, tipoClasse, precoOriginal, precoFinal) "
                + "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ClassConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, passagem.getNomePassageiro());
            ps.setString(2, passagem.getNumeroVoo());
            ps.setString(3, passagem.getTipoClasse().name());
            ps.setDouble(4, passagem.getPrecoOriginal());
            ps.setDouble(5, passagem.calcularPrecoFinal());
            ps.execute();
            System.out.println("Passagem salva com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao salvar passagem: " + e.getMessage());
        }
    }

    public void listar() {
        String sql = "SELECT * FROM passagens";
        try (Connection conn = ClassConection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println(" Passagens Cadastradas ");
            boolean tem = false;
            while (rs.next()) {
                tem = true;
                System.out.println("ID: " + rs.getInt("id")
                        + " | Passageiro: " + rs.getString("nomePassageiro")
                        + " | Voo: " + rs.getString("numeroVoo")
                        + " | Classe: " + rs.getString("tipoClasse")
                        + " | Preço Final: R$ " + String.format("%.2f", rs.getDouble("precoFinal")));
            }
            if (!tem) System.out.println("Nenhuma passagem encontrada.");
        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }
    }

    public void buscarPorVoo(String numeroVoo) {
        String sql = "SELECT * FROM passagens WHERE numeroVoo = ?";
        try (Connection conn = ClassConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, numeroVoo);
            try (ResultSet rs = ps.executeQuery()) {
                boolean tem = false;
                while (rs.next()) {
                    tem = true;
                    System.out.println("ID: " + rs.getInt("id")
                            + " | Passageiro: " + rs.getString("nomePassageiro")
                            + " | Classe: " + rs.getString("tipoClasse")
                            + " | Preço Final: R$ " + String.format("%.2f", rs.getDouble("precoFinal")));
                }
                if (!tem) System.out.println("Nenhuma passagem encontrada para esse voo.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM passagens WHERE id = ?";
        try (Connection conn = ClassConection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int linhas = ps.executeUpdate();
            System.out.println(linhas > 0 ? "Passagem cancelada!" : "Passagem não encontrada.");
        } catch (SQLException e) {
            System.out.println("Erro ao cancelar: " + e.getMessage());
        }
    }
}