package Modelo;

public class Repartidor {

    private int id;
    private String nombre;

    public Repartidor(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getNombre() { return nombre; }
    public int getId() { return id; }
}

