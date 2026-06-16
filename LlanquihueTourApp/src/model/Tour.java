package model;

public class Tour {
 
    private String nombre;
    private String tipo;
    private int duracionHoras;
    private double precio;
    private int cuposDisponibles;

 
    public Tour(String nombre, String tipo, int duracionHoras, double precio, int cuposDisponibles) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.duracionHoras = duracionHoras;
        this.precio = precio;
        this.cuposDisponibles = cuposDisponibles;
    }

 
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getDuracionHoras() {
        return duracionHoras;
    }

    public void setDuracionHoras(int duracionHoras) {
        this.duracionHoras = duracionHoras;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCuposDisponibles() {
        return cuposDisponibles;
    }

    public void setCuposDisponibles(int cuposDisponibles) {
        this.cuposDisponibles = cuposDisponibles;
    }

   
    @Override
    public String toString() {
        return String.format("Tour: %s | Tipo: %s | Duración: %d horas | Precio: $%.0f | Cupos: %d",
                nombre, tipo, duracionHoras, precio, cuposDisponibles);
    }
}