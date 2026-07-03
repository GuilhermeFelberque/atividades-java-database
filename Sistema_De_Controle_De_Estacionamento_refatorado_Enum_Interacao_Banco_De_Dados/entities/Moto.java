package JDBC.Sistema_De_Controle_De_Estacionamento_refatorado_Enum_Interacao_Banco_De_Dados.entities;

public class Moto extends Veiculo {

    public Moto(String placa, int horaEntrada) {
        super(placa, horaEntrada, TipoVeiculo.MOTO);
    }

    @Override
    public double calcularValor(int horas) {
        return TarifaEstacionamento.calcularTarifa(horas) * 0.8;
    }
}