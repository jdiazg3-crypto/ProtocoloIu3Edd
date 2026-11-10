import java.util.List;

public class Main {

    public static void main(String[] args) {
        demostracion();
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
}