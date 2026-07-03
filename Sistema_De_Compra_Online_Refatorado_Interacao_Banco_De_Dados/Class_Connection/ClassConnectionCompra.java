package JDBC.Sistema_De_Compra_Online_Refatorado_Interacao_Banco_De_Dados.Class_Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ClassConnectionCompra {
    public static java.sql.Connection getConnection() throws SQLException {
        try {
            String url = "jdbc:mysql://localhost:3306/compraonline";
            String user = "root";
            String password = "";
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}