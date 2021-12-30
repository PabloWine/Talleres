package Taller2;

class AsignaturaOpcional extends Asignatura {
    private int creditosPreRequisito;

    public AsignaturaOpcional(String codigo, String nombre, int creditoNecesario, String tipo,
            int creditosPreRequisitos) {
        super(codigo, nombre, creditoNecesario, tipo);

        this.creditosPreRequisito = creditosPreRequisito;
    }

    public int getCreditosPreRequisito() {
        return creditosPreRequisito;
    }

    public void setCreditosPreRequisito(int creditosPreRequisito) {
        this.creditosPreRequisito = creditosPreRequisito;
    }

}
