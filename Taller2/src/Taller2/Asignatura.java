package Taller2;

class Asignatura {
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditoNecesario() {
        return creditoNecesario;
    }

    public void setCreditoNecesario(int creditoNecesario) {
        this.creditoNecesario = creditoNecesario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Asignatura(String codigo, String nombre, int creditoNecesario, String tipo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditoNecesario = creditoNecesario;
        this.tipo = tipo;
    }

    private String codigo;
    private String nombre;
    private int creditoNecesario;
    private String tipo;

}
