package JDBC.Sistema_De_Compra_Online_Refatorado_Interacao_Banco_De_Dados.entities;

public final class PagamentoBoleto extends Pagamento {
    public PagamentoBoleto(double valor) {
        super(valor);
    }

    @Override
    public void processarPagamento() {
        System.out.println("Pagamento via BOLETO no valor de:" + valor);
    }
}