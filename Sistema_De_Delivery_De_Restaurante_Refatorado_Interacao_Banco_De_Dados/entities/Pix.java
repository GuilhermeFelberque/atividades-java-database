package JDBC.Sistema_De_Delivery_De_Restaurante_Refatorado_Interacao_Banco_De_Dados.entities;

import JDBC.Sistema_De_Delivery_De_Restaurante_Refatorado_Interacao_Banco_De_Dados.Enums.TipoPagamento;

public final class Pix extends Pagamento {
    public Pix() {
        super(TipoPagamento.PIX);
    }

    @Override
    public void processar() {
        System.out.println("Gerando chave Pix...");
    }
}