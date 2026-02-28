package BaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.Class.forName;

public class Conexion {

    public Connection obtenerConexion(){

        Connection conexion = null;

        try {
            Class.forName ("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/speedFast_bd", "root", "");
            System.out.println("conexion exitosa");

        }catch (SQLException e){
            System.out.println("conexion no encontrada" + e.getMessage());

        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return conexion;}

}