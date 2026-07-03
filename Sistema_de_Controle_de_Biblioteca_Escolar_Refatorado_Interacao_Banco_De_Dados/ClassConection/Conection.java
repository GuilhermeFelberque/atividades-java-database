package JDBC.Sistema_de_Controle_de_Biblioteca_Escolar_Refatorado_Interacao_Banco_De_Dados.ClassConection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Conection {

    private static final String URL = "jdbc:mysql://localhost:3306/sistemabibliotecaescolar";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static java.sql.Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}