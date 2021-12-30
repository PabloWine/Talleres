import java.util.LinkedList;

class Cliente {
    private String rut;
    private String nombre;
    private String apellido;
    private double saldo;
    private Localizacion loca;
    private LinkedList<Envio> envios;
    private LinkedList<Envio> recibidos;

    public Cliente(String rut, String nombre, String apellido, double saldo) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.saldo = saldo;
        envios = new LinkedList<Envio>();
        recibidos = new LinkedList<Envio>();
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Localizacion getLoca() {
        return loca;
    }

    public void setLoca(Localizacion loca) {
        this.loca = loca;
    }

    public LinkedList<Envio> getEnvios() {
        return envios;
    }

    public void setEnvios(LinkedList<Envio> envios) {
        this.envios = envios;
    }

    public LinkedList<Envio> getRecibidos() {
        return recibidos;
    }

    public void setRecibidos(LinkedList<Envio> recibidos) {
        this.recibidos = recibidos;
    }

    @Override
    public String toString() {
        String text = "";
        text += "Cliente: \nRut: " + rut + "\nNombre: " + nombre + "\nApellido: " + apellido + "\nSaldo: " + saldo
                + "\nLocalizacion: " + loca.getNombre() + "\n";
        text += "Envios \n";
        for (int i = 0; i < envios.size(); i++) {
            Envio v = envios.get(i);
            text += "Codigo: " + v.getCodigo() + "\n";
        }
        text += "Recibidos \n";
        for (int j = 0; j < recibidos.size(); j++) {
            Envio v = recibidos.get(j);
            text += "Codigo: " + v.getCodigo() + "\n";
        }
        return text;
    }

}