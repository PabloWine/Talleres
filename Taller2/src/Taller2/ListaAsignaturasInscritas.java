package Taller2;

class ListaAsignaturasInscritas {

    private int cant;
    private int max;
    private AsignaturaInscrita[] lista;

    public ListaAsignaturasInscritas(int max) {
        int cant = 0;
        this.max = max;
        lista = new AsignaturaInscrita[max];

    }

    public boolean agregarAsignaturaInscrita(AsignaturaInscrita a) {
        if (cant < max) {
            lista[cant] = a;
            cant++;
            return true;
        }
        return false;

    }

    public AsignaturaInscrita buscarAsignaturaInscrita(String codigo) {
        for (int i = 0; i < cant; i++) {
            if (lista[i].getCodigo().equalsIgnoreCase(codigo)) {
                return lista[i];
            }
        }
        return null;
    }

    public AsignaturaInscrita getAsignaturaInscritaIndice(int i) {
        if (i < cant) {
            return lista[i];
        }
        return null;
    }

    public boolean eliminarAsignaturaInscrita(String codigo) {
        int i = 0;
        for (int j = 0; j < cant; j++) {
            if (lista[j].getCodigo().equalsIgnoreCase(codigo)) {
                i = j;
                break;
            }
        }
        if (i == cant) {
            return false;
        } else {
            lista[i] = null;
            for (int z = i; z < cant; z++) {
                lista[z] = lista[z + 1];
            }
            cant--;
            return true;
        }
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

    public AsignaturaInscrita[] getLista() {
        return lista;
    }

    public void setLista(AsignaturaInscrita[] lista) {
        this.lista = lista;
    }

}
