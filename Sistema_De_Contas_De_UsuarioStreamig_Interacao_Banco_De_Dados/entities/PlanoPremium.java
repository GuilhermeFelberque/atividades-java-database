package JDBC.Sistema_De_Contas_De_UsuarioStreamig_Interacao_Banco_De_Dados.entities;

public final class PlanoPremium extends ContaStreaming {

    private String qualidadeVideo = "4K Ultra HD";
    private int limiteTelas = 4;
    private boolean permiteDownload = true;

    public PlanoPremium(String nome, String email) {
        super(nome, email, 50.00, TipoPlano.PREMIUM);
    }

    @Override
    public String toString() {

        String statusDownload = permiteDownload
                ? "Ativo (Permite assistir offline)"
                : "Inativo";

        return super.toString()
                + "\nQualidade Máxima: " + qualidadeVideo
                + "\nLimite de Telas: " + limiteTelas + " telas"
                + "\nDownload Offline: " + statusDownload
                + String.format("\nValor Mensal: R$ %.2f\n", getPrecoBase());
    }
}