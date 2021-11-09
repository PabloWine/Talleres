class ListaEstadisticas {
    private int cantidad;
    private int maximo;
    private Estadistica[] listaEstadisticas;

    public ListaEstadisticas(int maximo) {
        cantidad = 0;
        this.maximo = maximo;
        listaEstadisticas = new Estadistica[maximo];
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getMaximo() {
        return maximo;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setMaximo(int maximo) {
        this.maximo = maximo;
    }

    public Estadistica[] getListaEstadisticas() {
        return listaEstadisticas;
    }

    public void setListaEstadisticas(Estadistica[] listaEstadisticas) {
        this.listaEstadisticas = listaEstadisticas;
    }

    public boolean añadirEstadistica(Estadistica estadistica) {
        if (cantidad < maximo) {
            listaEstadisticas[cantidad] = estadistica;
            cantidad++;
            return true;
        } else {
            return false;
        }
    }

    public Estadistica getEstadisticaPuntual(int x) {
        if (x < cantidad) {
            return listaEstadisticas[x];
        } else {
            return null;
        }
    }

    public Estadistica buscarEstadistica(String nombrePersonaje) {
        for (int i = 0; i < cantidad; i++) {
            if (listaEstadisticas[i].getNombreCampeon().equalsIgnoreCase(nombrePersonaje)) {
                return listaEstadisticas[i];
            }
        }
        return null;
    }
}
