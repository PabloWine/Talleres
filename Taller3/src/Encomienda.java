public class Encomienda extends Envio {

    private double largo;
    private double ancho;
    private double profundidad;

    public Encomienda(String codigo, double peso, double largo, double ancho, double profundidad) {
        super(codigo, peso);
        this.largo = largo;
        this.ancho = ancho;
        this.profundidad = profundidad;
    }

    @Override
    public double valor() {
        double volumen = largo * ancho * profundidad;
        return getPeso() * volumen * 50;
    }

}
