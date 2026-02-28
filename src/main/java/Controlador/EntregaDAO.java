package Controlador;

import BaseDatos.Conexion;
import Modelo.Entrega;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EntregaDAO {

    private Conexion con = new Conexion();

    public void guardar(Entrega entrega) {
        String query = "INSERT INTO entregas (id_pedido, id_repartidor, fecha) VALUES (?, ?, NOW())";

        try (Connection c = con.obtenerConexion();
             PreparedStatement ps = c.prepareStatement(query)) {

            // EXTRAEMOS DATOS DEL OBJETO

            ps.setInt(1, entrega.getIdPedido());
            ps.setInt(2, entrega.getIdRepartidor());

            ps.executeUpdate();
            System.out.println("Entrega guardada exitosamente en la BD.");

        } catch (SQLException e) {
            System.out.println("Error al cargar en la base de datos la entrega: " + e.getMessage());
        }catch (Exception e) {
            System.out.println("Error al registrar entrega" + e.getMessage());
        }
    }
}