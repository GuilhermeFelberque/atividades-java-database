package JDBC.Sistema_De_Controle_De_Estacionamento_refatorado_Enum_Interacao_Banco_De_Dados.entities;

public final class TarifaEstacionamento {
    private TarifaEstacionamento() {}

    public static double calcularTarifa(int horas) {
        if (horas <= 1) return 10.0;
        return 10.0 + (horas - 1) * 5.0;
    }
}