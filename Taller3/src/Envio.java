public abstract class Envio {
    private String codigo;
    private Cliente clienteRemitente;
    private Cliente clienteDestinatario;
    private double peso;

    public Envio(String codigo, double peso) {
        this.codigo = codigo;
        this.peso = peso;
        clienteRemitente = null;
        clienteDestinatario = null;
    }

    public abstract double valor();

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Cliente getClienteRemitente() {
        return clienteRemitente;
    }

    public void setClienteRemitente(Cliente clienteRemitente) {
        this.clienteRemitente = clienteRemitente;
    }

    public Cliente getClienteDestinatario() {
        return clienteDestinatario;
    }

    public void setClienteDestinatario(Cliente clienteDestinatario) {
        this.clienteDestinatario = clienteDestinatario;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Envio [clienteDestinatario=" + clienteDestinatario + ", clienteRemitente=" + clienteRemitente
                + ", codigo=" + codigo + ", peso=" + peso + "]";
    }

}
