package Taller2;

class ListaProfesores {

    private int cant;
    private int max;
    private Profesor[] lista;

    public ListaProfesores(int max) {
        int cant = 0;
        this.max = max;
        lista = new Profesor[max];

    }

    public boolean agregarProfesor(Profesor p) {

        if (cant < max) {
            lista[cant] = p;
            cant++;
            return true;
        }
        return false;

    }

    public Profesor buscarProfesorPorRut(String rut) {
        for (int i = 0; i < cant; i++) {
            if (lista[i].getRut().equalsIgnoreCase(rut)) {
                return lista[i];
            }
        }
        return null;
    }

    public Profesor buscarProfesor(String correo) {
        for (int i = 0; i < cant; i++) {
            if (lista[i].getCorreo().equalsIgnoreCase(correo)) {
                return lista[i];
            }
        }
        return null;
    }

    public Profesor getProfesorIndice(int i) {
        if (i < cant) {
            return lista[i];
        }
        return null;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public Profesor[] getLista() {
        return lista;
    }

    public void setLista(Profesor[] lista) {
        this.lista = lista;
    }

}
