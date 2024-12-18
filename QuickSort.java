import java.util.List;
import java.util.Comparator;

public class Quicksort {
    public static <T> void ordenar(List<T> lista, int inicio, int fin, Comparator<T> comparador) {
        if (inicio < fin) {
            int pivote = particionar(lista, inicio, fin, comparador);
            ordenar(lista, inicio, pivote - 1, comparador);
            ordenar(lista, pivote + 1, fin, comparador);
        }
    }

    public static void ordenarPorFecha(List<Paquete> listaPaquetes, int inicio, int fin) {
        ordenar(listaPaquetes, inicio, fin, Comparator.comparing(Paquete::getFecha));
    }

    private static <T> int particionar(List<T> lista, int inicio, int fin, Comparator<T> comparador) {
        T pivote = lista.get(fin);
        int i = (inicio - 1);

        for (int j = inicio; j < fin; j++) {
            if (comparador.compare(lista.get(j), pivote) <= 0) {
                i++;
                T temp = lista.get(i);
                lista.set(i, lista.get(j));
                lista.set(j, temp);
            }
        }

        T temp = lista.get(i + 1);
        lista.set(i + 1, lista.get(fin));
        lista.set(fin, temp);

        return i + 1;
    }
}
