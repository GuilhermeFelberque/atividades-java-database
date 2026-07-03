package JDBC.Sistema_De_Reserva_De_Hotel_Refatorado_Interacao_Banco_De_Dados.entities;

public abstract class Quartos {

    private int idQuarto;
    private int numero;
    private int diasEstadia;
    private TipoQuarto tipoQuarto;

    public Quartos(int idQuarto, int numero, int diasEstadia, TipoQuarto tipoQuarto) {
        this.idQuarto = idQuarto;
        this.numero = numero;
        this.diasEstadia = diasEstadia;
        this.tipoQuarto = tipoQuarto;
    }

    public int getIdQuarto() {
        return idQuarto;
    }

    public int getNumero() {
        return numero;
    }

    public int getDiasEstadia() {
        return diasEstadia;
    }

    public TipoQuarto getTipoQuarto() {
        return tipoQuarto;
    }

    // Cada subclasse define o próprio valor de diária - polimorfismo
    public abstract double getValorDiaria();

    public double getValorTotal() {
        return getValorDiaria() * diasEstadia;
    }

    @Override
    public String toString() {
        return "Quarto " + numero + " | Tipo: " + tipoQuarto
                + " | Diaria: R$ " + getValorDiaria()
                + " | Dias: " + diasEstadia
                + " | Total: R$ " + getValorTotal();
    }
}