package JDBC.Sistema_De_Reserva_De_Hotel_Refatorado_Interacao_Banco_De_Dados.entities;

public final class QuartoDuplo extends Quartos {

    private static final double VALOR_DIARIA = 300.00;

    public QuartoDuplo(int idQuarto, int numero, int diasEstadia) {
        super(idQuarto, numero, diasEstadia, TipoQuarto.DUPLO);
    }

    @Override
    public double getValorDiaria() {
        return VALOR_DIARIA;
    }
}