package Taller2;

class ListaParalelos {

    private int cant;
    private int max;
    private Paralelo[] lista;

    public ListaParalelos(int max) {
        int cant = 0;
        this.max = max;
        lista = new Paralelo[max];

    }

    public boolean agregarParalelo(Paralelo p) {
        if (cant < max) {
            lista[cant] = p;
            cant++;
            return true;
        }
        return false;

    }

    public Paralelo buscarParalelo(String paralelo) {
        for (int i = 0; i < cant; i++) {
            if (lista[i].getParalelo().equalsIgnoreCase(paralelo)) {
                return lista[i];
            }
        }
        return null;
    }

    public Paralelo getParaleloIndice(int i) {
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

    public Paralelo[] getLista() {
        return lista;
    }

    public void setLista(Paralelo[] lista) {
        this.lista = lista;
    }

}
