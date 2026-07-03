package JDBC.Sistema_De_Delivery_De_Restaurante_Refatorado_Interacao_Banco_De_Dados.entities;

import JDBC.Sistema_De_Delivery_De_Restaurante_Refatorado_Interacao_Banco_De_Dados.Enums.TipoPagamento;

public final class Dinheiro extends Pagamento {
    public Dinheiro() {
        super(TipoPagamento.DINHEIRO);
    }

    @Override
    public void processar() {
        System.out.println("Separe o valor em dinheiro. O entregador levará troco se necessário.");
    }
}