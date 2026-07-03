package JDBC.Sistema_De_Controle_De_Estacionamento_refatorado_Enum_Interacao_Banco_De_Dados.entities;

public class Carro extends Veiculo {

    public Carro(String placa, int horaEntrada) {
        super(placa, horaEntrada, TipoVeiculo.CARRO);
    }

    @Override
    public double calcularValor(int horas) {
        return TarifaEstacionamento.calcularTarifa(horas);
    }
}