package co.edu.udistrital.model;

import co.edu.udistrital.model.estructuras.Queue;

/**
 * Modelo que representa la lógica de asignación de tareas. Esta clase NO
 * realiza operaciones de entrada/salida.
 *
 * @author sg812
 */
public class AsignacionTareas {

    private Queue<Tarea> cola;

    /**
     * Constructor por defecto. Inicializa la cola de tareas.
     */
    public AsignacionTareas() {
        cola = new Queue<>();
    }

    /**
     * Añade una nueva tarea a la cola, insertándola en la posición
     * correspondiente para mantener un orden de prioridad (de menor a mayor
     * tiempo).
     *
     * @param nombre   Nombre o descripción de la tarea.
     * @param duracion Tiempo de duración de la tarea.
     *
     * @throws IllegalArgumentException Si el nombre está vacío o la duración es
     *                                  inválida.
     */
    public void addTask(String nombre, int duracion) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (duracion <= 0) {
            throw new IllegalArgumentException("La duración debe ser un entero mayor a cero.");
        }

        Tarea nueva = new Tarea(nombre, duracion);
        Queue<Tarea> colaAuxiliar = new Queue<>();

        while (!cola.isEmpty() && cola.front().getTi() <= nueva.getTi()) {
            colaAuxiliar.enqueue(cola.dequeue());
        }

        colaAuxiliar.enqueue(nueva);

        while (!cola.isEmpty()) {
            colaAuxiliar.enqueue(cola.dequeue());
        }

        while (!colaAuxiliar.isEmpty()) {
            cola.enqueue(colaAuxiliar.dequeue());
        }
    }

    /**
     * Busca y elimina de la cola una tarea específica introducida mediante su
     * nombre. Hace uso de una cola auxiliar para preservar las demás tareas en
     * su orden correcto.
     *
     * @param nombre Nombre de la tarea a eliminar.
     *
     * @return {@code true} si la tarea fue encontrada y eliminada,
     *         {@code false} en caso contrario.
     */
    public boolean removeTaskByName(String nombre) {
        if (cola.isEmpty()) {
            return false;
        }

        Queue<Tarea> colaAuxiliar = new Queue<>();
        boolean encontrado = false;

        while (!cola.isEmpty()) {
            Tarea actual = cola.dequeue();
            if (actual.getNombre().equalsIgnoreCase(nombre) && !encontrado) {
                encontrado = true;
            } else {
                colaAuxiliar.enqueue(actual);
            }
        }

        while (!colaAuxiliar.isEmpty()) {
            cola.enqueue(colaAuxiliar.dequeue());
        }

        return encontrado;
    }

    /**
     * Devuelve una representación en texto de las tareas que están pendientes
     * por procesar.
     *
     * @return Un {@code String} con la lista de tareas.
     */
    public String listTasks() {
        if (cola.isEmpty()) {
            return "No hay tareas pendientes en la cola.";
        }
        return cola.toString();
    }

    /**
     * Verifica si la cola de tareas está vacía.
     *
     * @return {@code true} si no hay tareas pendientes, {@code false} en caso
     *         contrario.
     */
    public boolean isEmpty() {
        return cola.isEmpty();
    }

    /**
     * Consulta el número de tareas pendientes en la cola.
     *
     * @return El tamaño actual de la cola de tareas.
     */
    public int size() {
        return cola.size();
    }

    /**
     * Extrae secuencialmente las tareas de la cola simulando su ejecución en
     * múltiples procesadores. Asigna cada tarea al procesador que se libere más
     * pronto, sumando los tiempos y calculando el tiempo medio de finalización
     * total al concluir el proceso.
     *
     * @param numProcesadores Cantidad de procesadores disponibles para ejecutar
     *                        las tareas.
     *
     * @return Un {@code String} con el reporte detallado de qué procesador
     *         ejecutó cada tarea y el tiempo medio de finalización.
     *
     * @throws IllegalArgumentException Si el número de procesadores no es
     *                                  válido.
     */
    public String processTasks(int numProcesadores) {
        if (cola.isEmpty()) {
            return "No hay tareas para procesar.";
        }
        if (numProcesadores <= 0) {
            throw new IllegalArgumentException("El número de procesadores debe ser mayor a cero.");
        }

        int[] tiempoProcesadores = new int[numProcesadores];
        long sumaTiempos = 0;
        int totalTareas = cola.size();
        StringBuilder sb = new StringBuilder();

        while (!cola.isEmpty()) {
            Tarea actual = cola.dequeue();

            int mejorProcesador = 0;
            for (int i = 1; i < numProcesadores; i++) {
                if (tiempoProcesadores[i] < tiempoProcesadores[mejorProcesador]) {
                    mejorProcesador = i;
                }
            }

            tiempoProcesadores[mejorProcesador] += actual.getTi();
            int tiempoFinalizacion = tiempoProcesadores[mejorProcesador];
            sumaTiempos += tiempoFinalizacion;

            sb.append("Procesador ").append(mejorProcesador + 1)
                    .append(" ejecutó ").append(actual.getNombre())
                    .append(" (Finalizó en el min ").append(tiempoFinalizacion).append(")\n");
        }

        double promedio = (double) sumaTiempos / totalTareas;
        sb.append("\nTiempo medio de finalización: ").append(promedio);
        return sb.toString();
    }
}
