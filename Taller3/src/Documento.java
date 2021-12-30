public class Documento extends Envio {

    private double grosor;

    public Documento(String codigo, double peso, double grosor) {
        super(codigo, peso);
        this.grosor = grosor;
    }

    @Override
    public double valor() {
        return getPeso() * getGrosor() * 100;
    }

    public double getGrosor() {
        return grosor;
    }

    public void setGrosor(double grosor) {
        this.grosor = grosor;
    }

}
