package JDBC.Sistema_De_Delivery_De_Restaurante_Refatorado_Interacao_Banco_De_Dados.ClassConection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ClassConection {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/sistemadelivery";
        String user = "root";
        String password = "";
        return DriverManager.getConnection(url, user, password);
    }
}