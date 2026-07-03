package JDBC.Sistema_De_Compra_Online_Refatorado_Interacao_Banco_De_Dados.entities;

public class Produto {
    private int id;
    private String nomeProduto;
    private Double precoProdutoo;
    private String nomeMarcaProduto;

    public Produto(int id, String nomeProduto, Double precoProdutoo, String nomeMarcaProduto) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.precoProdutoo = precoProdutoo;
        this.nomeMarcaProduto = nomeMarcaProduto;
    }

    public int id() {
        return id;
    }

    public String nomeProduto() {
        return nomeProduto;
    }

    public Double precoProdutoo() {
        return precoProdutoo;
    }

    public String nomeMarcaProduto() {
        return nomeMarcaProduto;
    }

    public void setNomeMarcaProduto(String nomeMarcaProduto) {
        this.nomeMarcaProduto = nomeMarcaProduto;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public void setPrecoProdutoo(Double precoProdutoo) {
        this.precoProdutoo = precoProdutoo;
    }
}