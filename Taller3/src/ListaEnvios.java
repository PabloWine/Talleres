
public class ListaEnvios {
    private NodoEnvio first;
    public int size;

    public ListaEnvios() {
        first = null;
        size = 0;
    }

    public void ingresar(Envio e) {
        NodoEnvio n = new NodoEnvio(e);
        if (first == null) {
            first = n;
            first.setNext(n);
        } else {
            NodoEnvio aux = first;
            while (aux.getNext() != first) {
                aux = aux.getNext();
            }
            aux.setNext(n);
            n.setPrev(aux);
            n.setNext(first);
            first.setPrev(n);

        }
        size += 1;
    }

    public NodoEnvio getFirst() {
        return first;
    }

    public void setFirst(NodoEnvio first) {
        this.first = first;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Envio buscar(String codigo) {
        NodoEnvio aux = first;
        do {
            aux = aux.getNext();
        } while ((aux != first) && (!(aux.getEnvio().getCodigo().equals(codigo))));
        return aux.getEnvio();
    }

    public boolean eliminarPorCodigo(String codigo) {

        if (first == null) {
            return false;
        } else {
            String dato = codigo;
            NodoEnvio aux = first;
            while (aux.getNext() != first && !aux.getEnvio().getCodigo().equals(dato)) {
                aux = aux.getNext();
            }
            if (aux.getEnvio().getCodigo().equals(dato)) {
                if (aux.getNext() == first) {
                    aux.getPrev().setNext(first);
                    first.setPrev(aux.getPrev());
                    return true;
                } else {
                    if (aux == first) {
                        first.getPrev().setNext(first.getNext());
                        first.getNext().setPrev(first.getPrev());
                        first = first.getNext();
                    } else {
                        aux.getPrev().setNext(aux.getNext());
                        aux.getNext().setPrev(aux.getPrev());
                    }
                    return true;

                }

            } else {
                return false;
            }
        }
    }

}
