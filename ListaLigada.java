public class ListaLigada {
    private Nodo cabeza;

    public ListaLigada() {
        cabeza = null;
    }

    public void agregar(Paquete paquete) {
        Nodo nuevoNodo = new Nodo(paquete);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo temp = cabeza;
            while (temp.getSiguiente() != null) {
                temp = temp.getSiguiente();
            }
            temp.setSiguiente(nuevoNodo);
        }
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public void imprimir() {
        Nodo temp = cabeza;
        while (temp != null) {
            System.out.println(temp.getPaquete());
            temp = temp.getSiguiente();
        }
    }

    
}
