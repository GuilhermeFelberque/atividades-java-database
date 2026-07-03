package JDBC.Sistema_De_Controle_De_Estacionamento_refatorado_Enum_Interacao_Banco_De_Dados.Repository;

import JDBC.Sistema_De_Controle_De_Estacionamento_refatorado_Enum_Interacao_Banco_De_Dados.ClassConnection.ClassConnection;
import JDBC.Sistema_De_Controle_De_Estacionamento_refatorado_Enum_Interacao_Banco_De_Dados.entities.DadosSaida;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstacionamentoRepository {

    // conta quantos veículos estão atualmente no pátio
    public int contarVeiculos() throws SQLException {
        String sql = "SELECT COUNT(*) FROM movimentacao";
        try (Connection con = ClassConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    // verifica se a placa já está no pátio
    public boolean veiculoNoPatio(String placa) throws SQLException {
        String sql = "SELECT * FROM movimentacao WHERE placa = ?";
        try (Connection con = ClassConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, placa);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    // salva o veículo na tabela veiculos (insere se for novo, atualiza se já existir)
    public void salvarVeiculo(String placa, String tipo) throws SQLException {
        String sqlVerifica = "SELECT * FROM veiculos WHERE placa = ?";

        try (Connection con = ClassConnection.getConnection();
             PreparedStatement psVerifica = con.prepareStatement(sqlVerifica)) {

            psVerifica.setString(1, placa);
            ResultSet rs = psVerifica.executeQuery();

            if (rs.next()) {
                // já existe -> atualiza o tipo
                String sqlUpdate = "UPDATE veiculos SET tipo = ? WHERE placa = ?";
                try (PreparedStatement psUpdate = con.prepareStatement(sqlUpdate)) {
                    psUpdate.setString(1, tipo);
                    psUpdate.setString(2, placa);
                    psUpdate.executeUpdate();
                }
            } else {
                // não existe -> insere
                String sqlInsert = "INSERT INTO veiculos (placa, tipo) VALUES (?, ?)";
                try (PreparedStatement psInsert = con.prepareStatement(sqlInsert)) {
                    psInsert.setString(1, placa);
                    psInsert.setString(2, tipo);
                    psInsert.executeUpdate();
                }
            }
        }
    }

    // registra a entrada do veículo no pátio
    public void registrarEntrada(String placa) throws SQLException {
        String sql = "INSERT INTO movimentacao (placa, hora_entrada) VALUES (?, NOW())";
        try (Connection con = ClassConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, placa);
            ps.executeUpdate();
        }
    }

    // atualiza o tipo de um veículo já cadastrado
    public boolean atualizarTipo(String placa, String novoTipo) throws SQLException {
        String sql = "UPDATE veiculos SET tipo = ? WHERE placa = ?";
        try (Connection con = ClassConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, novoTipo);
            ps.setString(2, placa);
            return ps.executeUpdate() > 0;
        }
    }

    // busca os dados necessários para calcular a saída (tipo, hora de entrada, tempo em horas)
    public DadosSaida buscarDadosParaSaida(String placa) throws SQLException {
        String sql = "SELECT v.tipo, m.hora_entrada, " +
                "TIMESTAMPDIFF(HOUR, m.hora_entrada, NOW()) AS tempo " +
                "FROM movimentacao m " +
                "JOIN veiculos v ON v.placa = m.placa " +
                "WHERE m.placa = ?";
        try (Connection con = ClassConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, placa);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                String tipo = rs.getString("tipo");
                Timestamp horaEntrada = rs.getTimestamp("hora_entrada");
                int tempo = rs.getInt("tempo");
                return new DadosSaida(tipo, horaEntrada, tempo);
            }
        }
    }

    // remove o veículo do pátio (ele já vai sair)
    public void removerMovimentacao(String placa) throws SQLException {
        String sql = "DELETE FROM movimentacao WHERE placa = ?";
        try (Connection con = ClassConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, placa);
            ps.executeUpdate();
        }
    }

    // salva o registro de saída no histórico
    public void salvarHistorico(String placa, String tipo, Timestamp horaEntrada, double valor) throws SQLException {
        String sql = "INSERT INTO historico (placa, tipo, hora_entrada, hora_saida, valor) " +
                "VALUES (?, ?, ?, NOW(), ?)";
        try (Connection con = ClassConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, placa);
            ps.setString(2, tipo);
            ps.setTimestamp(3, horaEntrada);
            ps.setDouble(4, valor);
            ps.executeUpdate();
        }
    }

    // lista os veículos atualmente no pátio
    public List<String> listarPatio() throws SQLException {
        String sql = "SELECT v.placa, v.tipo, m.hora_entrada " +
                "FROM movimentacao m " +
                "JOIN veiculos v ON v.placa = m.placa";
        List<String> linhas = new ArrayList<>();
        try (Connection con = ClassConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                linhas.add("Placa: " + rs.getString("placa") +
                        " | Tipo: " + rs.getString("tipo") +
                        " | Entrada: " + rs.getTimestamp("hora_entrada"));
            }
        }
        return linhas;
    }

    // lista o histórico de saídas
    public List<String> listarHistorico() throws SQLException {
        String sql = "SELECT placa, tipo, hora_entrada, hora_saida, valor FROM historico";
        List<String> linhas = new ArrayList<>();
        try (Connection con = ClassConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                linhas.add("Placa: " + rs.getString("placa") +
                        " | Tipo: " + rs.getString("tipo") +
                        " | Entrada: " + rs.getTimestamp("hora_entrada") +
                        " | Saída: " + rs.getTimestamp("hora_saida") +
                        " | Valor: R$ " + rs.getDouble("valor"));
            }
        }
        return linhas;
    }
}