package JDBC.Sistema_De_Passagens_Aereas_Refatorado_Intercao_Banco_De_Dados.Entities;

import JDBC.Sistema_De_Passagens_Aereas_Refatorado_Intercao_Banco_De_Dados.Enums.TipoClasse;

public final class ClasseExecutiva extends Passagem {

    private boolean servicoBordoVip = true;
    private boolean acessoSalaVip = true;

    public ClasseExecutiva(String nomePassageiro, String numeroVoo, double precoOriginal) {
        super(nomePassageiro, numeroVoo, precoOriginal, TipoClasse.EXECUTIVA);
    }

    public boolean isServicoBordoVip() {
        return servicoBordoVip;
    }

    public boolean isAcessoSalaVip() {
        return acessoSalaVip;
    }

    @Override
    public double calcularPrecoFinal() {
        return getPrecoOriginal() * 1.5;
    }

    @Override
    public String toString() {
        return super.toString()
                + " | Serviço VIP: " + (servicoBordoVip ? "Sim" : "Não")
                + " | Sala VIP: " + (acessoSalaVip ? "Sim" : "Não")
                + String.format(" | Preço Final: R$ %.2f", calcularPrecoFinal());
    }
}