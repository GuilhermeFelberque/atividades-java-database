package JDBC.Sistema_De_Controle_De_Estacionamento_refatorado_Enum_Interacao_Banco_De_Dados.entities;

import java.sql.Timestamp;

// Carrega os dados necessários para calcular a saída do veículo
public class DadosSaida {
    public String tipo;
    // Timestamp: Guarda a data e hora exata em milissegundos para usar no banco de dados ou logs.
    public Timestamp horaEntrada;
    public int tempo;

    public DadosSaida(String tipo, Timestamp horaEntrada, int tempo) {
        this.tipo = tipo;
        this.horaEntrada = horaEntrada;
        this.tempo = tempo;
    }
}