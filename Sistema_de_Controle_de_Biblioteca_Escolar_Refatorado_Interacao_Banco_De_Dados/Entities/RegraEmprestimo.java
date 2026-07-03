package JDBC.Sistema_de_Controle_de_Biblioteca_Escolar_Refatorado_Interacao_Banco_De_Dados.Entities;

public final class RegraEmprestimo {
    private RegraEmprestimo() {}

    // Verifica se o aluno pode pegar mais um livro emprestado
    public static boolean podeEmprestar(Aluno aluno) {
        return !aluno.isPossuiMulta()
                && aluno.getLivrosEmprestados() < aluno.getLimiteEmprestimos();
    }
}