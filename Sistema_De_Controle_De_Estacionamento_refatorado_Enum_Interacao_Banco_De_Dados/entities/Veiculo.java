package JDBC.Sistema_De_Controle_De_Estacionamento_refatorado_Enum_Interacao_Banco_De_Dados.entities;

public abstract class Veiculo {
    private String placa;
    private int horaEntrada;
    private TipoVeiculo tipo;

    public Veiculo(String placa, int horaEntrada, TipoVeiculo tipo) {
        this.placa = placa;
        this.horaEntrada = horaEntrada;
        this.tipo = tipo;
    }

    public String getPlaca() { return placa; }
    public int getHoraEntrada() { return horaEntrada; }
    public TipoVeiculo getTipo() { return tipo; }

    public abstract double calcularValor(int horas);
}