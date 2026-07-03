package JDBC.Sistema_De_Passagens_Aereas_Refatorado_Intercao_Banco_De_Dados.Entities;

import JDBC.Sistema_De_Passagens_Aereas_Refatorado_Intercao_Banco_De_Dados.Enums.TipoClasse;

public final class ClasseEconomica extends Passagem {

    private boolean despacharMala;
    private double taxaBagagem = 120.0;

    public ClasseEconomica(String nomePassageiro, String numeroVoo, double precoOriginal, boolean despacharMala) {
        super(nomePassageiro, numeroVoo, precoOriginal, TipoClasse.ECONOMICA);
        this.despacharMala = despacharMala;
    }

    public boolean isDespacharMala() {
        return despacharMala;
    }

    @Override
    public double calcularPrecoFinal() {
        if (despacharMala)
            return getPrecoOriginal() + taxaBagagem;
        return getPrecoOriginal();
    }

    @Override
    public String toString() {
        return super.toString()
                + " | Mala Despachada: " + (despacharMala ? "Sim" : "Não")
                + String.format(" | Preço Final: R$ %.2f", calcularPrecoFinal());
    }
}