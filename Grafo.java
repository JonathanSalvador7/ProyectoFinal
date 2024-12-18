import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.*;

public class Grafo {
    private Map<String, List<Conexion>> conexiones;

    public Grafo() {
        this.conexiones = new HashMap<>();
    }

    public void agregarConexion(String remitente, String destinatario, Paquete paquete) {
        if (remitente == null || destinatario == null || paquete == null) {
            throw new IllegalArgumentException("Remitente, destinatario y paquete no pueden ser nulos.");
        }
        conexiones.putIfAbsent(remitente, new ArrayList<>());
        conexiones.get(remitente).add(new Conexion(destinatario, paquete));
    }

    public List<Conexion> obtenerConexiones(String remitente) {
        return conexiones.getOrDefault(remitente, Collections.emptyList());
    }

    public boolean eliminarConexion(String remitente, String destinatario) {
        if (conexiones.containsKey(remitente)) {
            List<Conexion> listaConexiones = conexiones.get(remitente);
            return listaConexiones.removeIf(conexion -> conexion.getDestinatario().equals(destinatario));
        }
        return false;
    }

    public void mostrarConexiones() {
        for (String remitente : conexiones.keySet()) {
            System.out.println("Remitente: " + remitente);
            for (Conexion conexion : conexiones.get(remitente)) {
                System.out.println("  -> Destinatario: " + conexion.getDestinatario() + ", Paquete: " + conexion.getPaquete());
            }
        }
    }
}
