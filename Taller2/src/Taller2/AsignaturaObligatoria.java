package Taller2;

class AsignaturaObligatoria extends Asignatura {
    private int nivelMalla;
    private int cantidadPreRequisitos;
    private String codigos;

    public AsignaturaObligatoria(String codigo, String nombre, int creditoNecesario, String tipo, int nivelMalla,
            int cantidadPreRequisitos, String codigos) {

        super(codigo, nombre, creditoNecesario, tipo);

        this.nivelMalla = nivelMalla;
        this.cantidadPreRequisitos = cantidadPreRequisitos;
        this.codigos = codigos;
    }

    public int getNivelMalla() {
        return nivelMalla;
    }

    public void setNivelMalla(int nivelMalla) {
        this.nivelMalla = nivelMalla;
    }

    public int getCantidadPreRequisitos() {
        return cantidadPreRequisitos;
    }

    public void setCantidadPreRequisitos(int cantidadPreRequisitos) {
        this.cantidadPreRequisitos = cantidadPreRequisitos;
    }

    public String getCodigos() {
        return codigos;
    }

    public void setCodigos(String codigos) {
        this.codigos = codigos;
    }

}
