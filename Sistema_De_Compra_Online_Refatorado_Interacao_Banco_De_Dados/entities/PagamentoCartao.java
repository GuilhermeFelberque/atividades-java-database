package JDBC.Sistema_De_Compra_Online_Refatorado_Interacao_Banco_De_Dados.entities;

public final class PagamentoCartao extends Pagamento {

    public PagamentoCartao(double valor) {
        super(valor);
    }

    @Override
    public void processarPagamento() {
        System.out.println("Pagamento no cartao no valor de: " + valor);
    }
}