package Taller2;

class ListaEstudiantes {

    private int cant;
    private int max;
    private Estudiante[] lista;

    public ListaEstudiantes(int max) {
        int cant = 0;
        this.max = max;
        lista = new Estudiante[max];

    }

    public boolean agregarEstudiante(Estudiante e) {
        if (cant < max) {
            lista[cant] = e;
            cant++;
            return true;
        }
        return false;

    }

    public Estudiante buscarEstudiante(String correo) {
        for (int i = 0; i < cant; i++) {
            if (lista[i].getCorreo().equalsIgnoreCase(correo)) {
                return lista[i];
            }
        }
        return null;
    }

    public Estudiante getEstudianteIndice(int i) {
        if (i < cant) {
            return lista[i];
        }
        return null;
    }

    public boolean eliminarEstudiante(String rut) {
        int i = 0;
        for (int j = 0; j < cant; j++) {
            if (lista[j].getRut().equalsIgnoreCase(rut)) {
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

    public Estudiante[] getLista() {
        return lista;
    }

    public void setLista(Estudiante[] lista) {
        this.lista = lista;
    }

}
