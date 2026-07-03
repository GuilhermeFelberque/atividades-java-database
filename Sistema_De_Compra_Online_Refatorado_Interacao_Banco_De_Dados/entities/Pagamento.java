package JDBC.Sistema_De_Compra_Online_Refatorado_Interacao_Banco_De_Dados.entities;

public abstract class Pagamento {

    protected double valor;

    public Pagamento(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    public abstract void processarPagamento();
}