package Controlador;

import BaseDatos.Conexion;
import Modelo.Pedidos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Modelo.TipoPedido;
import Modelo.EstadoPedido;

public class PedidoDAO {

    private Conexion con;

    public PedidoDAO() {

        this.con = new Conexion();
    }

    //CRUD
    //1.CREAR

    public void guardar(Pedidos pedido) {

        String query = "INSERT INTO pedido (direccion, tipo, estado) VALUES (?, ?, ?)";

        try (Connection cnx = con.obtenerConexion();
             PreparedStatement ps = cnx.prepareStatement(query)) {

            ps.setString(1, pedido.getDireccion());
            ps.setString(2, pedido.getTipo().name());
            ps.setString(3, pedido.getEstado().name());

            ps.executeUpdate();
            System.out.println("Pedido guardado con éxito.");

        } catch (SQLException e) {
            System.out.println("Error SQL al guardar: " + e.getMessage());

        }catch (Exception e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }

      }

      //2.READ

    public ArrayList<Pedidos> listarTodos() {

        ArrayList<Pedidos> lista = new ArrayList<>();
        String query = "SELECT * FROM pedido";

        try (Connection cnx = con.obtenerConexion();
             PreparedStatement ps = cnx.prepareStatement(query);
             ResultSet resu = ps.executeQuery()) {

            while (resu.next()) {

                Pedidos p = new Pedidos(

                        resu.getInt("id"),
                        resu.getString("direccion"),
                        TipoPedido.valueOf(resu.getString("tipo")),
                        EstadoPedido.valueOf(resu.getString("estado"))
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

    //3. UPDATE

    public void actualizar(Pedidos pedido) {

        String query = "UPDATE pedido SET direccion = ?, tipo = ?, estado = ? WHERE id = ?";

        try (Connection cnx = con.obtenerConexion();
             PreparedStatement ps = cnx.prepareStatement(query)) {

            ps.setString(1, pedido.getDireccion());
            ps.setString(2, pedido.getTipo().name());
            ps.setString(3, pedido.getEstado().name());
            ps.setInt(4, pedido.getId());

            ps.executeUpdate();

            System.out.println("Pedido actualizado con éxito.");

        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
    }

    //4. DELETE

    public void eliminar(int id) {

        String query = "DELETE FROM pedido WHERE id = ?";

        try (Connection cnx = con.obtenerConexion();
             PreparedStatement ps = cnx.prepareStatement(query)) {

            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Pedido eliminado con éxito.");

        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }

    }
