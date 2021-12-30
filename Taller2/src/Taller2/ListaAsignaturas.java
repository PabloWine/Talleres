package Taller2;

class ListaAsignaturas {

    private int cant;
    private int max;
    private Asignatura[] lista;

    public ListaAsignaturas(int max) {
        int cant = 0;
        this.max = max;
        lista = new Asignatura[max];

    }

    public boolean agregarAsignatura(Asignatura a) {
        if (cant < max) {
            lista[cant] = a;
            cant++;
            return true;
        }
        return false;

    }

    public Asignatura buscarAsignatura(String codigo) {
        for (int i = 0; i < cant; i++) {
            if (lista[i].getCodigo().equalsIgnoreCase(codigo)) {
                return lista[i];
            }
        }
        return null;
    }

    public Asignatura getAsignaturaIndice(int i) {
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

    public Asignatura[] getLista() {
        return lista;
    }

    public void setLista(Asignatura[] lista) {
        this.lista = lista;
    }

}
