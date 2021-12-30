import java.util.LinkedList;

public class Localizacion {
    private String nombre;
    private int cantEnvios;
    private int cantRecibidos;
    private double ganancias;
    private int cant;

    public Localizacion(String nombre) {
        this.nombre = nombre;
        cant = 0;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantEnvios() {
        return cantEnvios;
    }

    public void setCantEnvios(int cantEnvios) {
        this.cantEnvios = cantEnvios;
    }

    public int getCantRecibidos() {
        return cantRecibidos;
    }

    public void setCantRecibidos(int cantRecibidos) {
        this.cantRecibidos = cantRecibidos;
    }

    public double getGanancias() {
        return ganancias;
    }

    public void setGanancias(double ganancias) {
        this.ganancias = ganancias;
    }

}
