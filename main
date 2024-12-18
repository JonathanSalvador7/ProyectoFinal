import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String rutaArchivo = "datos.csv";
        ArbolAVL arbolPorCodigoPostal = new ArbolAVL((p1, p2) -> p1.getCodigoPostal().compareTo(p2.getCodigoPostal()));
        ArbolAVL arbolPorRemitente = new ArbolAVL((p1, p2) -> p1.getRemitente().compareTo(p2.getRemitente()));
        Grafo grafoDirecciones = new Grafo();

        cargarArchivo(rutaArchivo, grafoDirecciones, arbolPorCodigoPostal, arbolPorRemitente);

        Comparator<Paquete> comparadorPorRemitente = (p1, p2) -> p1.getRemitente().compareTo(p2.getRemitente());

        Scanner scanner = new Scanner(System.in);
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\nMenú:");
            System.out.println("0.- Salir");
            System.out.println("1.- Agregar paquete"); //GRAFOS
            System.out.println("2.- Eliminar paquete");//GRAFOS
            System.out.println("3.- Mostrar conexiones ");//GRAFOS
            System.out.println("4.- Mostrar paquetes ordenados por remitente");//ARBOLES AVL
            System.out.println("5.- Mostrar paquetes ordenados por código postal");//ARBOLES AVL
            System.out.println("6.- Ver paquetes de un remitente"); //GRAFOS
            System.out.println("7.- Mostrar paquetes ordenados por fecha");//QUICKSORF y listas ligadas
            System.out.print("Elige una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {

                case 1:
                    agregarPaquete(scanner, grafoDirecciones, arbolPorCodigoPostal, arbolPorRemitente);
                    break;
                case 2:
                    eliminarPaquete(scanner, grafoDirecciones);
                    break;
                case 3:
                    grafoDirecciones.mostrarConexiones();
                    break;

                case 4:
                    System.out.println("Paquetes ordenados por remitente:");
                    arbolPorRemitente.imprimir();  
                    break;

                case 5:
                    System.out.println("Paquetes ordenados por código postal:");
                    arbolPorCodigoPostal.imprimir();
                    break;

                case 6:
                    System.out.print("Remitente a buscar: ");
                    String remitenteBuscado = scanner.nextLine();
                    List<Conexion> conexionesRemitente = grafoDirecciones.obtenerConexiones(remitenteBuscado);

                    if (conexionesRemitente == null || conexionesRemitente.isEmpty()) {
                        System.out.println("No se encontraron paquetes para el remitente: " + remitenteBuscado);
                    } else {
                        System.out.println("Paquetes del remitente " + remitenteBuscado + ":");
                        for (Conexion conexion : conexionesRemitente) {
                            System.out.println(conexion.getPaquete());
                        }
                    }
                    break;
                case 7:
                    ordenarPaquetesPorFecha(arbolPorRemitente);
                    break;

                case 0:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción inválida. Intenta nuevamente.");
            }
        }
        scanner.close();
    }

    private static void agregarPaquete(Scanner scanner, Grafo grafoDirecciones, ArbolAVL arbolPorCodigoPostal) {
        System.out.print("Remitente: ");
        String remitente = scanner.nextLine();
        System.out.print("Destinatario: ");
        String destinatario = scanner.nextLine();
        System.out.print("Fecha: ");
        String fecha = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Colonia: ");
        String colonia = scanner.nextLine();
        System.out.print("Código Postal: ");
        String codigoPostal = scanner.nextLine();

        Paquete paquete = new Paquete(remitente, destinatario, fecha, direccion, colonia, codigoPostal);

        grafoDirecciones.agregarConexion(remitente, destinatario, paquete);

        grafoDirecciones.mostrarConexiones(); 

        System.out.println("Paquete agregado exitosamente.");
    }

    private static void eliminarPaquete(Scanner scanner, Grafo grafoDirecciones) {
        System.out.print("Remitente del paquete a eliminar: ");
        String remitente = scanner.nextLine();
        System.out.print("Destinatario del paquete a eliminar: ");
        String destinatario = scanner.nextLine();

        if (grafoDirecciones.eliminarConexion(remitente, destinatario)) {
            System.out.println("Paquete eliminado exitosamente.");
        } else {
            System.out.println("El paquete no se encontró.");
        }
    }

    private static void cargarArchivo(String rutaArchivo, Grafo grafoDirecciones, ArbolAVL arbolPorCodigoPostal, ArbolAVL arbolPorRemitente) {
        System.out.println("Cargando archivo desde la ruta predeterminada: " + rutaArchivo);

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length < 6) {
                    continue; 
                }

                String remitente = datos[0].trim();
                String destinatario = datos[1].trim();
                String fecha = datos[2].trim();
                String direccion = datos[3].trim();
                String colonia = datos[4].trim();
                String codigoPostal = datos[5].trim();

                Paquete paquete = new Paquete(remitente, destinatario, fecha, direccion, colonia, codigoPostal);

                arbolPorCodigoPostal.insertar(paquete);
                arbolPorRemitente.insertar(paquete);
                grafoDirecciones.agregarConexion(remitente, destinatario, paquete);
            }
            System.out.println("Archivo cargado exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    private static void agregarPaquete(Scanner scanner, Grafo grafoDirecciones, ArbolAVL arbolPorCodigoPostal, ArbolAVL arbolPorRemitente) {
        System.out.print("Remitente: ");
        String remitente = scanner.nextLine();
        System.out.print("Destinatario: ");
        String destinatario = scanner.nextLine();
        System.out.print("Fecha: ");
        String fecha = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Colonia: ");
        String colonia = scanner.nextLine();
        System.out.print("Código Postal: ");
        String codigoPostal = scanner.nextLine();

        Paquete paquete = new Paquete(remitente, destinatario, fecha, direccion, colonia, codigoPostal);

        grafoDirecciones.agregarConexion(remitente, destinatario, paquete);

        arbolPorCodigoPostal.insertar(paquete);

        arbolPorRemitente.insertar(paquete); 

        grafoDirecciones.mostrarConexiones(); 

        System.out.println("Paquete agregado exitosamente.");
    }

    private static void ordenarPaquetesPorFecha(ArbolAVL arbolPorRemitente) {
        List<Paquete> listaPaquetes = new ArrayList<>();

        arbolPorRemitente.inOrden(paquete -> listaPaquetes.add(paquete));

        if (listaPaquetes.isEmpty()) {
            System.out.println("No hay paquetes para ordenar.");
            return;
        }

        Quicksort.ordenarPorFecha(listaPaquetes, 0, listaPaquetes.size() - 1);

        System.out.println("Paquetes ordenados por fecha:");
        for (Paquete paquete : listaPaquetes) {
            System.out.println(paquete);
        }
    }

}
