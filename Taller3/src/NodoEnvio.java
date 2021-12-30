public class NodoEnvio {

    private Envio envio;
    private NodoEnvio next;
    private NodoEnvio prev;

    public NodoEnvio(Envio e) {
        this.envio = e;
        this.next = null;
        this.prev = null;
    }

    public NodoEnvio getPrev() {
		return prev;
	}

	public void setPrev(NodoEnvio prev) {
		this.prev = prev;
	}

	public Envio getEnvio() {
		return envio;
	}

	public NodoEnvio getNext() {
		return next;
	}

	public void setNext(NodoEnvio next) {
		this.next = next;
	}

}
