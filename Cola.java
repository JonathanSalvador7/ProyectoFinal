ublic class Cola {
    private NodoCola frente;
    private NodoCola finalCola;
    private int tamano;

    public Cola() {
        this.frente = null;
        this.finalCola = null;
        this.tamano = 0;
    }

    public void encolar(Paquete paquete) {
        NodoCola nuevoNodo = new NodoCola(paquete);
        if (finalCola != null) {
            finalCola.siguiente = nuevoNodo;
        }
        finalCola = nuevoNodo;
        if (frente == null) {
            frente = finalCola;
        }
        tamano++;
    }

    public Paquete desencolar() {
        if (frente == null) {
            return null; 
        }
        Paquete paquete = frente.dato;
        frente = frente.siguiente;
        if (frente == null) {
            finalCola = null; 
        }
        tamano--;
        return paquete;
    }

    public boolean estaVacia() {
        return tamano == 0;
    }

    public int tamano() {
        return tamano;
    }

    public void imprimir() {
        NodoCola actual = frente;
        while (actual != null) {
            System.out.println(actual.dato);
            actual = actual.siguiente;
        }
    }

    private static class NodoCola {
        Paquete dato;
        NodoCola siguiente;

        NodoCola(Paquete dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }
}
