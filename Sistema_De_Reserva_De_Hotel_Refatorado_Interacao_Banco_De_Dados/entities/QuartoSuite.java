package JDBC.Sistema_De_Reserva_De_Hotel_Refatorado_Interacao_Banco_De_Dados.entities;

public final class QuartoSuite extends Quartos {

    private static final double VALOR_DIARIA = 600.00;

    public QuartoSuite(int idQuarto, int numero, int diasEstadia) {
        super(idQuarto, numero, diasEstadia, TipoQuarto.SUITE);
    }

    @Override
    public double getValorDiaria() {
        return VALOR_DIARIA;
    }
}