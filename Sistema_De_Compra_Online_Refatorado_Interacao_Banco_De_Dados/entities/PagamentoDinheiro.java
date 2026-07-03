package JDBC.Sistema_De_Compra_Online_Refatorado_Interacao_Banco_De_Dados.entities;

public final class PagamentoDinheiro extends Pagamento {

    public PagamentoDinheiro(double valor) {
        super(valor);
    }

    @Override
    public void processarPagamento() {
        System.out.println("Pagamento no dinheiro valor de: " + valor);
    }
}