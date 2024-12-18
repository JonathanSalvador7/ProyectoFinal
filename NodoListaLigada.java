class NodoListaLigada {
    private Paquete paquete;
    private NodoListaLigada siguiente;

    public NodoListaLigada(Paquete paquete) {
        this.paquete = paquete;
        this.siguiente = null;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public NodoListaLigada getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoListaLigada siguiente) {
        this.siguiente = siguiente;
    }
}
}
