/**
 * Ordenamiento manual (Merge Sort) por identificación.
 * Se usa Merge Sort O(n log n) porque con 1.000.000 de empleados un
 * algoritmo O(n²) sería inviable.
 */
public final class Ordenamiento {
    private Ordenamiento() {}

    public static void porIdentificacion(Empleado[] a) {
        if (a == null || a.length < 2) return;
        Empleado[] aux = new Empleado[a.length];
        mergeSort(a, aux, 0, a.length - 1);
    }

    private static void mergeSort(Empleado[] a, Empleado[] aux, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + ((hi - lo) >>> 1);
        mergeSort(a, aux, lo, mid);
        mergeSort(a, aux, mid + 1, hi);
        mezclar(a, aux, lo, mid, hi);
    }

    private static void mezclar(Empleado[] a, Empleado[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) aux[k] = a[k];
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (aux[i].getIdentificacion() <= aux[j].getIdentificacion()) a[k] = aux[i++];
            else a[k] = aux[j++];
        }
    }

    public static boolean estaOrdenado(Empleado[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1].getIdentificacion() > a[i].getIdentificacion()) return false;
        }
        return true;
    }
}