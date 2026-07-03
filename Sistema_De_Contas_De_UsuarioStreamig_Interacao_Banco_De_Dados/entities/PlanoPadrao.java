package JDBC.Sistema_De_Contas_De_UsuarioStreamig_Interacao_Banco_De_Dados.entities;

public final class PlanoPadrao extends ContaStreaming {

    private String qualidadeVideo = "Full HD";
    private int limiteTelas = 2;

    public PlanoPadrao(String nome, String email) {
        super(nome, email, 30.00, TipoPlano.PADRAO);
    }

    @Override
    public String toString() {
        return super.toString()
                + "\nQualidade Máxima: " + qualidadeVideo
                + "\nLimite de Telas: " + limiteTelas + " telas"
                + String.format("\nValor Mensal: R$ %.2f\n", getPrecoBase());
    }
}