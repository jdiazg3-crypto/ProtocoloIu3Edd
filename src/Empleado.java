
public class Empleado {
    private final int identificacion;
    private final String nombre;
    private final String cargo;
    private final double salario;

    public Empleado(int identificacion, String nombre, String cargo, double salario) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.cargo = cargo;
        this.salario = salario;
    }

    public int getIdentificacion() { return identificacion; }
    public String getNombre() { return nombre; }
    public String getCargo() { return cargo; }
    public double getSalario() { return salario; }

    @Override
    public String toString() {
        return String.format("Empleado{id=%d, nombre='%s', cargo='%s', salario=%.0f}",
                identificacion, nombre, cargo, salario);
    }
}