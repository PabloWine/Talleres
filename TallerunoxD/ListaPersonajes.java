class ListaPersonajes {
    private int cantidad;
    private int maximo;
    private Personaje[] listaPersonajes;

    public ListaPersonajes(int maximo) {
        cantidad = 0;
        this.maximo = maximo;
        listaPersonajes = new Personaje[maximo];
    }

    public boolean añadirPersonaje(Personaje personaje) {
        if (cantidad < maximo) {
            listaPersonajes[cantidad] = personaje;
            cantidad++;
            return true;
        } else {
            return false;
        }
    }

    public Personaje buscarPersonaje(String nombrePersonaje) {
        for (int i = 0; i < cantidad; i++) {
            if (listaPersonajes[i].getNombreCampeon().equalsIgnoreCase(nombrePersonaje)) {
                return listaPersonajes[i];
            }
        }
        return null;
    }

    public Personaje buscarRol(String rol) {
        for (int i = 0; i < cantidad; i++) {
            if (listaPersonajes[i].getRol().equals(rol)) {
                return listaPersonajes[i];
            }
        }
        return null;
    }

    public Personaje getPersonajePuntual(int x) {
        if (x < cantidad) {
            return listaPersonajes[x];
        } else {
            return null;
        }
    }

    public Personaje getRolPuntual(int x) {
        if (x < cantidad) {
            return listaPersonajes[x];
        } else {
            return null;
        }
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

    public Personaje[] getListaPersonajes() {
        return listaPersonajes;
    }

    public void setListaPersonajes(Personaje[] listaPersonajes) {
        this.listaPersonajes = listaPersonajes;
    }

}
