ublic class NodoAVL {
    private Paquete paquete;
    private NodoAVL izq, der;
    private int altura;

    public NodoAVL(Paquete paquete) {
        this.paquete = paquete;
        this.izq = null;
        this.der = null;
        this.altura = 1;  
    }

    public Paquete getPaquete() {
        return this.paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    public NodoAVL getIzq() {
        return izq;
    }

    public void setIzq(NodoAVL izq) {
        this.izq = izq;
    }

    public NodoAVL getDer() {
        return der;
    }

    public void setDer(NodoAVL der) {
        this.der = der;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
    
}
