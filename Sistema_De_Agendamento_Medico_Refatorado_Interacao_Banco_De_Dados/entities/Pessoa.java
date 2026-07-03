package JDBC.Sistema_De_Agendamento_Medico_Refatorado_Interacao_Banco_De_Dados.entities;

public abstract class Pessoa {

    private String nome;

    public Pessoa(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    // Cada tipo de pessoa pode ter uma forma diferente de se identificar
    public abstract String getIdentificacao();
}