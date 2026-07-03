package JDBC.Sistema_de_Controle_de_Biblioteca_Escolar_Refatorado_Interacao_Banco_De_Dados.Entities;

public class Livro {

    private int codigoLivro;
    private String tituloLivro;
    private boolean livroDisponivel;

    public Livro(int codigoLivro, String tituloLivro, boolean livroDisponivel) {
        this.codigoLivro = codigoLivro;
        this.tituloLivro = tituloLivro;
        this.livroDisponivel = livroDisponivel;
    }

    public int getCodigoLivro() {
        return codigoLivro;
    }

    public String getTituloLivro() {
        return tituloLivro;
    }

    public boolean isLivroDisponivel() {
        return livroDisponivel;
    }

    @Override
    public String toString() {
        return "Codigo: " + codigoLivro
                + " | Titulo: " + tituloLivro
                + " | Disponivel: " + (livroDisponivel ? "Sim" : "Nao");
    }
}