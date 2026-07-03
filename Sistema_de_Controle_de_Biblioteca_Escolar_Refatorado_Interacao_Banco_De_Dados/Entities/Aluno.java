package JDBC.Sistema_de_Controle_de_Biblioteca_Escolar_Refatorado_Interacao_Banco_De_Dados.Entities;

public class Aluno extends Pessoa {

    private int matricula;
    private boolean possuiMulta;
    private int livrosEmprestados;

    public Aluno(int matricula, String nomeAluno, boolean possuiMulta, int livrosEmprestados) {
        super(nomeAluno); // chama o construtor da classe Pessoa
        this.matricula = matricula;
        this.possuiMulta = possuiMulta;
        this.livrosEmprestados = livrosEmprestados;
    }

    public int getMatricula() {
        return matricula;
    }

    public boolean isPossuiMulta() {
        return possuiMulta;
    }

    public int getLivrosEmprestados() {
        return livrosEmprestados;
    }

    @Override
    public int getLimiteEmprestimos() {
        return 3; // aluno pode ter no máximo 3 livros emprestados ao mesmo tempo
    }

    @Override
    public String toString() {
        return "Matricula: " + matricula
                + " | Nome: " + getNome() // getNome() vem da classe Pessoa
                + " | Multa: " + (possuiMulta ? "Sim" : "Nao")
                + " | Livros emprestados: " + livrosEmprestados;
    }
}