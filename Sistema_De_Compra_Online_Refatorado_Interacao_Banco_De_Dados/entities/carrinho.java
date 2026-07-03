package JDBC.Sistema_De_Compra_Online_Refatorado_Interacao_Banco_De_Dados.entities;

import java.util.ArrayList;
import java.util.List;

public class carrinho {

    private List<Produto> produtos = new ArrayList<>();

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public double calcularTotal() {

        double total = 0;

        for (Produto p : produtos) {
            total += p.precoProdutoo();
        }

        return total;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }
}