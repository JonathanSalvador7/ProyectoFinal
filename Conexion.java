public class Conexion {
    private String destinatario;
    private Paquete paquete;

    public Conexion(String destinatario, Paquete paquete) {
        if (destinatario == null || paquete == null) {
            throw new IllegalArgumentException("Destinatario y paquete no pueden ser nulos.");
        }
        this.destinatario = destinatario;
        this.paquete = paquete;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    @Override
    public String toString() {
        return "Destinatario: " + destinatario + ", Paquete: " + paquete;
    }
}
