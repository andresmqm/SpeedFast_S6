package Controlador;

import BaseDatos.Conexion;
import Modelo.Pedidos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PedidoDAO {

    private Conexion con;

    public PedidoDAO() {

        this.con = new Conexion();
    }

    public void guardar(Pedidos pedido) {

        String query = "INSERT INTO pedido (direccion, tipo, estado) VALUES (?, ?, ?)";

        try (Connection cnx = con.obtenerConexion();
             PreparedStatement ps = cnx.prepareStatement(query)) {

            ps.setString(1, pedido.getDireccion());
            ps.setString(2, pedido.getTipo());
            ps.setString(3, pedido.getEstado());

            ps.executeUpdate();
            ps.close();
            cnx.close();
            System.out.println("Pedido guardado con éxito.");

        } catch (SQLException e) {
            System.out.println("Error SQL al guardar: " + e.getMessage());

        }catch (Exception e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }

      }

    public ArrayList<Pedidos> listarTodos() {

        ArrayList<Pedidos> lista = new ArrayList<>();
        String query = "SELECT * FROM pedido";

        try (Connection cnx = con.obtenerConexion();
             PreparedStatement ps = cnx.prepareStatement(query);
             ResultSet resu = ps.executeQuery()) {

            while (resu.next()) {

                Pedidos p = new Pedidos(

                        String.valueOf(resu.getInt("id")),
                        resu.getString("direccion"),
                        resu.getString("tipo"),
                        resu.getString("estado")
                );

                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar pedidos en la Base de Datos: " + e.getMessage());

        }catch (Exception e) {
            System.out.println("Error al listar pedidos: " + e.getMessage());
        }
        return lista;
    }

    }
