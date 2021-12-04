package Taller2;

class AsignaturaCursada {
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    private String codigo;
    private double nota;

    public AsignaturaCursada(String codigo, double nota) {
        this.codigo = codigo;
        this.nota = nota;
    }
}
