package Taller2;

class ListaAsignaturasCursadas {

    private int cant;
    private int max;
    private AsignaturaCursada[] lista;

    public ListaAsignaturasCursadas(int max) {

        this.max = max;
        cant = 0;
        lista = new AsignaturaCursada[max];

    }

    public boolean agregarAsignaturaCursada(AsignaturaCursada ac) {
        if (cant < max) {
            lista[cant] = ac;
            cant++;
            return true;
        }
        return false;

    }

    public AsignaturaCursada buscarAsignaturaCursada(String codigo) {
        for (int i = 0; i < cant; i++) {
            if (lista[i].getCodigo().equalsIgnoreCase(codigo)) {
                return lista[i];
            }
        }
        return null;
    }

    public AsignaturaCursada getAsignaturaCursadaIndice(int i) {
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

    public AsignaturaCursada[] getLista() {
        return lista;
    }

    public void setLista(AsignaturaCursada[] lista) {
        this.lista = lista;
    }

}
