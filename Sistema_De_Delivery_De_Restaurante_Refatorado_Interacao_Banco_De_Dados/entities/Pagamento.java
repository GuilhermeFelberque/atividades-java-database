package JDBC.Sistema_De_Delivery_De_Restaurante_Refatorado_Interacao_Banco_De_Dados.entities;

import JDBC.Sistema_De_Delivery_De_Restaurante_Refatorado_Interacao_Banco_De_Dados.Enums.TipoPagamento;

public abstract class Pagamento {
    private TipoPagamento tipo;

    public Pagamento(TipoPagamento tipo) {
        this.tipo = tipo;
    }

    public TipoPagamento getTipo() {
        return tipo;
    }

    public abstract void processar();

    @Override
    public String toString() {
        return tipo.name();
    }
}