package Controlador;

import BaseDatos.Conexion;
import Modelo.Repartidor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepartidorDAO {

    private Conexion con = new Conexion();

    public List<Repartidor> listarTodos() {

        List<Repartidor> lista = new ArrayList<>();
        String query = "SELECT * FROM repartidores";

        try (Connection cnx = con.obtenerConexion();
             Statement st = cnx.createStatement();
             ResultSet resul = st.executeQuery(query)) {

            while (resul.next()) {
                lista.add(new Repartidor(
                        resul.getInt("id"),
                        resul.getString("nombre")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar la Base de Datos de repartidores: " + e.getMessage());
        }catch (Exception e) {
            System.out.println("Error al listar repartidores:" + e.getMessage());
        }
        return lista;
    }
}