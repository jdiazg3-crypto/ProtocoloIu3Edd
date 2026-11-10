import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        demostracion();
        System.out.println();
        compararRendimiento();
    }

    private static void demostracion() {
        Empleado[] empleados = {
                new Empleado(1050, "Laura Gómez", "Analista", 3200000),
                new Empleado(1010, "Carlos Pérez", "Desarrollador", 4500000),
                new Empleado(1090, "Marta Ruiz", "Gerente", 8000000),
                new Empleado(1030, "Andrés Díaz", "Desarrollador", 4700000),
                new Empleado(1070, "Sofía Castro", "Analista", 3300000),
                new Empleado(1020, "Julián Mora", "Soporte", 2400000),
                new Empleado(1060, "Paola Vega", "Desarrollador", 4600000),
                new Empleado(1040, "Diego Torres", "Soporte", 2500000)
        };

        System.out.println("=== EMPLEADOS (sin ordenar) ===");
        imprimir(empleados);

        System.out.println("\n=== 1. Búsqueda lineal por identificación ===");
        probarLineal(empleados, 1030);   // existe
        probarLineal(empleados, 1040);   // existe, al final
        probarLineal(empleados, 9999);   // no existe

        System.out.println("\n=== 2. Búsqueda por cargo ===");
        probarCargo(empleados, "Desarrollador");
        probarCargo(empleados, "Soporte");
        probarCargo(empleados, "Contador");   // no existe

        System.out.println("\n=== 3. Ordenamiento por identificación ===");
        Ordenamiento.porIdentificacion(empleados);
        imprimir(empleados);
        System.out.println("¿Ordenado? " + Ordenamiento.estaOrdenado(empleados));

        System.out.println("\n=== 4. Búsqueda binaria por identificación ===");
        probarBinaria(empleados, 1030);
        probarBinaria(empleados, 1090);
        probarBinaria(empleados, 9999);
    }

    private static void probarLineal(Empleado[] a, int id) {
        int pos = Busqueda.linealPorId(a, id);
        if (pos >= 0) System.out.println("Lineal id " + id + " -> posición " + pos + " | " + a[pos] + " | comparaciones: " + Busqueda.comparaciones);
        else System.out.println("Lineal id " + id + " -> NO existe | comparaciones: " + Busqueda.comparaciones);
    }

    private static void probarBinaria(Empleado[] a, int id) {
        int pos = Busqueda.binariaPorId(a, id);
        if (pos >= 0) System.out.println("Binaria id " + id + " -> posición " + pos + " | " + a[pos] + " | comparaciones: " + Busqueda.comparaciones);
        else System.out.println("Binaria id " + id + " -> NO existe | comparaciones: " + Busqueda.comparaciones);
    }

    private static void probarCargo(Empleado[] a, String cargo) {
        List<Empleado> r = Busqueda.porCargo(a, cargo);
        System.out.println("Cargo '" + cargo + "' -> " + r.size() + " empleado(s) | comparaciones: " + Busqueda.comparaciones);
        for (Empleado e : r) System.out.println("   " + e);
    }

    private static void imprimir(Empleado[] a) {
        for (Empleado e : a) System.out.println("  " + e);
    }

    private static void compararRendimiento() {
        int[] tamanos = {100, 1_000, 10_000, 100_000, 1_000_000};
        int corridas = 10;
        String[] cargos = {"Analista", "Desarrollador", "Soporte", "Gerente"};
        Random rnd = new Random(42);

        System.out.println("=== COMPARACIÓN DE RENDIMIENTO (promedio de " + corridas + " corridas, peor caso: id inexistente) ===");
        System.out.printf("%-10s | %-14s | %-14s | %-15s | %-15s%n",
                "Tamaño", "Comp. lineal", "Comp. binaria", "Tiempo lin (ns)", "Tiempo bin (ns)");

        for (int n : tamanos) {
            Empleado[] arr = generar(n, cargos, rnd);
            Ordenamiento.porIdentificacion(arr);

            int idInexistente = -1;
            long sumaLin = 0, sumaBin = 0;
            long compLin = 0, compBin = 0;

            // calentamiento de la JVM
            for (int i = 0; i < 3; i++) {
                Busqueda.linealPorId(arr, idInexistente);
                Busqueda.binariaPorId(arr, idInexistente);
            }

            for (int i = 0; i < corridas; i++) {
                long t0 = System.nanoTime();
                Busqueda.linealPorId(arr, idInexistente);
                sumaLin += System.nanoTime() - t0;
                compLin = Busqueda.comparaciones;

                t0 = System.nanoTime();
                Busqueda.binariaPorId(arr, idInexistente);
                sumaBin += System.nanoTime() - t0;
                compBin = Busqueda.comparaciones;
            }
            System.out.printf("%-10d | %-14d | %-14d | %-15d | %-15d%n",
                    n, compLin, compBin, sumaLin / corridas, sumaBin / corridas);
        }
    }

    /** Genera n empleados con identificaciones únicas y desordenadas. */
    private static Empleado[] generar(int n, String[] cargos, Random rnd) {
        int[] ids = new int[n];
        for (int i = 0; i < n; i++) ids[i] = i + 1;
        for (int i = n - 1; i > 0; i--) { // Fisher-Yates
            int j = rnd.nextInt(i + 1);
            int tmp = ids[i]; ids[i] = ids[j]; ids[j] = tmp;
        }
        Empleado[] arr = new Empleado[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Empleado(ids[i], "Empleado" + ids[i],
                    cargos[rnd.nextInt(cargos.length)], 2_000_000 + rnd.nextInt(6_000_000));
        }
        return arr;
    }
}