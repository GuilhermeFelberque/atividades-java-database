package JDBC.Sistema_De_Delivery_De_Restaurante_Refatorado_Interacao_Banco_De_Dados.entities;

import JDBC.Sistema_De_Delivery_De_Restaurante_Refatorado_Interacao_Banco_De_Dados.Enums.TipoPagamento;

public final class Cartao extends Pagamento {
    public Cartao() {
        super(TipoPagamento.CARTAO);
    }

    @Override
    public void processar() {
        System.out.println("Conectando com a maquininha de cartão...");
    }
}