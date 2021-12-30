package Taller2;

class AsignaturaInscrita {
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Paralelo getParalelo() {
        return paralelo;
    }

    public void setParalelo(Paralelo paralelo) {
        this.paralelo = paralelo;
    }

    private String codigo;
    private Paralelo paralelo;

    public AsignaturaInscrita(String codigo, String paralelo) {
        this.codigo = codigo;
        paralelo = null;
    }

}
