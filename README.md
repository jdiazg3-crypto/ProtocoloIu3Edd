# Ejercicio 21 – Control de empleados

**Asignatura:** Estructuras de datos – Ingeniería del software, Universidad de Cartagena
**Actividad:** Unidad 3 – Taller práctico (algoritmos de búsqueda)

## Descripción
Programa en Java que administra un arreglo de objetos `Empleado` (identificación, nombre, cargo y salario) e implementa manualmente:

- Búsqueda lineal por identificación.
- Búsqueda de empleados por cargo (recorrido secuencial).
- Ordenamiento por identificación (Merge Sort manual).
- Búsqueda binaria por identificación.
- Comparación de rendimiento con diferentes tamaños de arreglo (100 a 1.000.000).

## Estructura
```
src/
  Empleado.java       -> clase del modelo
  Busqueda.java       -> lineal, por cargo y binaria (con contador de comparaciones)
  Ordenamiento.java   -> Merge Sort por identificación
  Main.java           -> demostración y comparación de rendimiento
```

## Complejidad
| Algoritmo | Mejor | Promedio | Peor | Requisito |
|---|---|---|---|---|
| Lineal por id | O(1) | O(n) | O(n) | Ninguno |
| Por cargo | O(n) | O(n) | O(n) | Ninguno |
| Binaria por id | O(1) | O(log n) | O(log n) | Ordenado por id |
| Merge Sort | O(n log n) | O(n log n) | O(n log n) | – |

La búsqueda por cargo no usa binaria porque el arreglo está ordenado por identificación y los cargos se repiten.

## Ejecución
Requiere Java 17 o superior.

**IntelliJ IDEA:** abrir la carpeta, marcar `src` como *Sources Root* y ejecutar `Main`.

**Terminal:**
```
javac -encoding UTF-8 -d out src/*.java
java -cp out Main
```