package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public static Connection obtenerConexion() {
        String url = "jdbc:mysql://localhost:3306/recursos_humanos";
        String usuario = "root";
        String contraseña = "Admin123";

        try {
            return DriverManager.getConnection(url, usuario, contraseña);
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
            return null;
        }
    }
}