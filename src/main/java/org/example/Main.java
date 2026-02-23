package org.example;

import Controlador.PedidoDAO;
import Modelo.Pedidos;
import Vistas.VentanaPrincipal;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        PedidoDAO dao = new PedidoDAO();

        ArrayList<Pedidos> lista = dao.listarTodos();

        VentanaPrincipal ventana = new VentanaPrincipal(lista);
        ventana.setVisible(true);

    }
}