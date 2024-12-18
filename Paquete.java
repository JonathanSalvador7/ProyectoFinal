public class Paquete {
    private String remitente;
    private String destinatario;
    private String fecha;
    private String direccion;
    private String colonia;
    private String codigoPostal;

    private Paquete siguiente; 
    public Paquete(String remitente, String destinatario, String fecha, String direccion, String colonia, String codigoPostal) {
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.fecha = fecha;
        this.direccion = direccion;
        this.colonia = colonia;
        this.codigoPostal = codigoPostal;
        this.siguiente = null;
    }

    public String getRemitente() {
        return remitente;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public Paquete getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Paquete siguiente) {
        this.siguiente = siguiente;
    }

    public Paquete(String fecha) {
        this.fecha = fecha;
    }

    public String getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "Paquete{" +
        "remitente='" + remitente + '\'' +
        ", destinatario='" + destinatario + '\'' +
        ", fecha='" + fecha + '\'' +
        ", direccion='" + direccion + '\'' +
        ", colonia='" + colonia + '\'' +
        ", codigoPostal='" + codigoPostal + '\'' +
        '}';
    }

}
