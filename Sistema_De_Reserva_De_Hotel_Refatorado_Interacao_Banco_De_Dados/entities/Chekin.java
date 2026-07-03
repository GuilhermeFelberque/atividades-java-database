package JDBC.Sistema_De_Reserva_De_Hotel_Refatorado_Interacao_Banco_De_Dados.entities;

public class Chekin {

    private int idChekin;
    private String nomeCliente;
    private String cpf;
    private String telefone;
    private String dataCheckin;
    private String dataCheckout;
    private String cidade;
    private double valorTotal;
    private String pagamento;
    private int idQuarto;

    public Chekin(int idChekin, String nomeCliente, String cpf, String telefone,
                  String dataCheckin, String dataCheckout, String cidade,
                  double valorTotal, String pagamento, int idQuarto) {
        this.idChekin = idChekin;
        this.nomeCliente = nomeCliente;
        this.cpf = cpf;
        this.telefone = telefone;
        this.dataCheckin = dataCheckin;
        this.dataCheckout = dataCheckout;
        this.cidade = cidade;
        this.valorTotal = valorTotal;
        this.pagamento = pagamento;
        this.idQuarto = idQuarto;
    }

    public int getIdChekin() { return idChekin; }
    public String getNomeCliente() { return nomeCliente; }
    public String getCpf() { return cpf; }
    public String getTelefone() { return telefone; }
    public String getDataCheckin() { return dataCheckin; }
    public String getDataCheckout() { return dataCheckout; }
    public String getCidade() { return cidade; }
    public double getValorTotal() { return valorTotal; }
    public String getPagamento() { return pagamento; }
    public int getIdQuarto() { return idQuarto; }

    @Override
    public String toString() {
        return "ID: " + idChekin
                + " | Nome: " + nomeCliente
                + " | CPF: " + cpf
                + " | Telefone: " + telefone
                + " | Cidade: " + cidade
                + " | Check-in: " + dataCheckin
                + " | Check-out: " + dataCheckout
                + " | Valor Total: R$ " + valorTotal
                + " | Pagamento: " + pagamento;
    }
}