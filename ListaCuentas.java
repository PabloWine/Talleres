
class ListaCuentas {
    private int cantidad;
    private int maximo;
    private Cuenta[] listaCuentas;

    public ListaCuentas(int maximo) {
        cantidad = 0;
        this.maximo = maximo;
        listaCuentas = new Cuenta[maximo];
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getMaximo() {
        return maximo;
    }

    public Cuenta[] getListaCuentas() {
        return listaCuentas;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setMaximo(int maximo) {
        this.maximo = maximo;
    }

    public void setListaCuentas(Cuenta[] listaCuentas) {
        this.listaCuentas = listaCuentas;
    }

    public boolean agregarCuentas(Cuenta cuenta) {
        if (cantidad < maximo) {
            listaCuentas[cantidad] = cuenta;
            cantidad++;
            return true;
        } else {
            return false;
        }
    }

    public Cuenta buscarCuenta(String nombreCuenta) {
        for (int i = 0; i < cantidad; i++) {
            if (listaCuentas[i].getNombreCuenta().equals(nombreCuenta)) {
                return listaCuentas[i];
            }
        }
        return null;
    }

    public boolean eliminarCuenta(String nombreCuenta) {
        int i = 0;
        for (int j = 0; j < cantidad; j++) {
            if (listaCuentas[j].getNombreCuenta().equals(nombreCuenta)) {
                i = j;
                break;
            }
        }
        if (i == cantidad) {
            return false;
        } else {
            listaCuentas[i] = null;
            for (int z = i; z < cantidad; z++) {
                listaCuentas[z] = listaCuentas[z + 1];
            }
            cantidad--;
            return true;
        }
    }

    public Cuenta getRegion(int i) {
        if (i < cantidad) {
            return listaCuentas[i];
        } else {
            return null;
        }
    }

    public Cuenta getCuentaPuntual(int x) {
        if (x < cantidad) {
            return listaCuentas[x];
        } else {
            return null;
        }
    }
}
