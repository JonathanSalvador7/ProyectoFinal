import java.util.Comparator;
import java.util.function.Consumer;

public class ArbolAVL {
    private NodoAVL raiz;
    private Comparator<Paquete> comparador;

    public ArbolAVL(Comparator<Paquete> comparador) {
        this.raiz = null;
        this.comparador = comparador;
    }

    public void insertar(Paquete paquete) {
        System.out.println("Insertando paquete: " + paquete);
        this.raiz = insertar(this.raiz, paquete);
    }

    private NodoAVL insertar(NodoAVL nodo, Paquete paquete) {
        if (nodo == null) {
            return new NodoAVL(paquete);
        }

        if (comparador.compare(paquete, nodo.getPaquete()) < 0) {
            nodo.setIzq(insertar(nodo.getIzq(), paquete));
        } else if (comparador.compare(paquete, nodo.getPaquete()) > 0) {
            nodo.setDer(insertar(nodo.getDer(), paquete));
        } else {
            return nodo; 
        }

        nodo.setAltura(1 + Math.max(altura(nodo.getIzq()), altura(nodo.getDer())));

        return balancear(nodo, paquete);
    }

    private int altura(NodoAVL nodo) {
        return nodo == null ? 0 : nodo.getAltura();
    }

    private NodoAVL balancear(NodoAVL nodo, Paquete paquete) {
        int balance = obtenerBalance(nodo);

        if (balance > 1 && comparador.compare(paquete, nodo.getDer().getPaquete()) > 0) {
            return rotacionIzquierda(nodo);
        }

        if (balance < -1 && comparador.compare(paquete, nodo.getIzq().getPaquete()) < 0) {
            return rotacionDerecha(nodo);
        }

        if (balance > 1 && comparador.compare(paquete, nodo.getDer().getPaquete()) < 0) {
            nodo.setDer(rotacionDerecha(nodo.getDer()));
            return rotacionIzquierda(nodo);
        }

        if (balance < -1 && comparador.compare(paquete, nodo.getIzq().getPaquete()) > 0) {
            nodo.setIzq(rotacionIzquierda(nodo.getIzq()));
            return rotacionDerecha(nodo);
        }

        return nodo;
    }

    private int obtenerBalance(NodoAVL nodo) {
        return nodo == null ? 0 : altura(nodo.getDer()) - altura(nodo.getIzq());
    }

    private NodoAVL rotacionIzquierda(NodoAVL x) {
        NodoAVL y = x.getDer();
        NodoAVL z = y.getIzq();

        y.setIzq(x);
        x.setDer(z);

        x.setAltura(1 + Math.max(altura(x.getIzq()), altura(x.getDer())));
        y.setAltura(1 + Math.max(altura(y.getIzq()), altura(y.getDer())));

        return y;
    }

    private NodoAVL rotacionDerecha(NodoAVL x) {
        NodoAVL y = x.getIzq();
        NodoAVL z = y.getDer();

        y.setDer(x);
        x.setIzq(z);

        x.setAltura(1 + Math.max(altura(x.getIzq()), altura(x.getDer())));
        y.setAltura(1 + Math.max(altura(y.getIzq()), altura(y.getDer())));

        return y;
    }

    public Paquete buscarPorCodigo(String codigo) {
        return buscarPorCodigo(this.raiz, codigo);
    }

    private Paquete buscarPorCodigo(NodoAVL nodo, String codigo) {
        if (nodo == null) {
            return null;
        }

        int comparacion = codigo.compareTo(nodo.getPaquete().getCodigoPostal());
        if (comparacion == 0) {
            return nodo.getPaquete();
        } else if (comparacion < 0) {
            return buscarPorCodigo(nodo.getIzq(), codigo);
        } else {
            return buscarPorCodigo(nodo.getDer(), codigo);
        }
    }

    public void eliminar(Paquete paquete) {
        this.raiz = eliminar(this.raiz, paquete);
    }

    private NodoAVL eliminar(NodoAVL nodo, Paquete paquete) {
        if (nodo == null) {
            return null;
        }

        int comparacion = comparador.compare(paquete, nodo.getPaquete());

        if (comparacion < 0) {
            nodo.setIzq(eliminar(nodo.getIzq(), paquete));
        } else if (comparacion > 0) {
            nodo.setDer(eliminar(nodo.getDer(), paquete));
        } else {
            if (nodo.getIzq() == null || nodo.getDer() == null) {
                NodoAVL temp = (nodo.getIzq() != null) ? nodo.getIzq() : nodo.getDer();
                nodo = temp;
            } else {
                NodoAVL sucesor = obtenerMinimo(nodo.getDer());
                nodo.setPaquete(sucesor.getPaquete());
                nodo.setDer(eliminar(nodo.getDer(), sucesor.getPaquete()));
            }
        }

        if (nodo == null) {
            return null;
        }

        nodo.setAltura(1 + Math.max(altura(nodo.getIzq()), altura(nodo.getDer())));

        return balancear(nodo, paquete);
    }

    private NodoAVL obtenerMinimo(NodoAVL nodo) {
        while (nodo.getIzq() != null) {
            nodo = nodo.getIzq();
        }
        return nodo;
    }

    public void imprimir() {
        imprimir(this.raiz);
    }

    private void imprimir(NodoAVL nodo) {
        if (nodo != null) {
            imprimir(nodo.getIzq());
            System.out.println(nodo.getPaquete());
            imprimir(nodo.getDer());
        }
    }

    public void inOrden(Consumer<Paquete> accion) {
        inOrden(raiz, accion);
    }

    private void inOrden(NodoAVL nodo, Consumer<Paquete> accion) {
        if (nodo != null) {
            inOrden(nodo.getIzq(), accion);
            accion.accept(nodo.getPaquete());
            inOrden(nodo.getDer(), accion); 
        }

    }

    private static void ordenarPaquetesPorRemitente(ArbolAVL arbolPorRemitente) {
        ListaLigada listaPaquetes = new ListaLigada();

        arbolPorRemitente.inOrden(paquete -> {
                    listaPaquetes.agregar(paquete);  
            });

        if (listaPaquetes.estaVacia()) {
            System.out.println("No se encontraron paquetes.");
        } else {
            System.out.println("Paquetes ordenados por remitente:");
            listaPaquetes.imprimir(); 
        }
    }
    

}
