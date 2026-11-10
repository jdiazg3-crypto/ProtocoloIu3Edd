import java.util.ArrayList;
import java.util.List;


public final class Busqueda {

    public static long comparaciones = 0;

    private Busqueda() {}



    public static int linealPorId(Empleado[] a, int id) {
        comparaciones = 0;
        if (a == null) return -1;
        for (int i = 0; i < a.length; i++) {
            comparaciones++;
            if (a[i].getIdentificacion() == id) return i;
        }
        return -1;
    }
static List<Empleado> porCargo(Empleado[] a, String cargo) {
        comparaciones = 0;
        List<Empleado> resultado = new ArrayList<>();
        if (a == null) return resultado;
        for (Empleado e : a) {
            comparaciones++;
            if (e.getCargo().equalsIgnoreCase(cargo)) resultado.add(e);
        }
        return resultado;
    }


    public static int binariaPorId(Empleado[] a, int id) {
        comparaciones = 0;
        if (a == null || a.length == 0) return -1;
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >>> 1);
            int idMid = a[mid].getIdentificacion();
            comparaciones++;
            if (idMid == id) return mid;
            if (idMid < id) lo = mid + 1;
            else hi = mid - 1;
        }
        return -1;
    }
}