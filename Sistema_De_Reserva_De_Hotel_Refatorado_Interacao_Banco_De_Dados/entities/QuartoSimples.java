package JDBC.Sistema_De_Reserva_De_Hotel_Refatorado_Interacao_Banco_De_Dados.entities;

public final class QuartoSimples extends Quartos {

    private static final double VALOR_DIARIA = 150.00;

    public QuartoSimples(int idQuarto, int numero, int diasEstadia) {
        super(idQuarto, numero, diasEstadia, TipoQuarto.SIMPLES);
    }

    @Override
    public double getValorDiaria() {
        return VALOR_DIARIA;
    }
}