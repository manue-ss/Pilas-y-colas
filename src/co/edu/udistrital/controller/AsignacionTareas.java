package co.edu.udistrital.controller;

import co.edu.udistrital.model.Tarea;
import co.edu.udistrital.model.Queue;
import co.edu.udistrital.view.ConsoleView;

/**
 * Controlador principal que gestiona el sistema de asignación de tareas.
 *
 * @author sg812
 */
public class AsignacionTareas {

    private ConsoleView vista;
    private Queue<Tarea> cola;

    /**
     * Constructor por defecto. Inicializa los valores de la vista y la cola.
     */
    public AsignacionTareas() {
        vista = new ConsoleView();
        cola = new Queue<>();
    }

    /**
     * Inicia el menú principal del sistema.
     */
    public void iniciar() {
        int opcion = 0;
        while (opcion != 5) {
            vista.imprimir("\nSISTEMA DE TAREAS"
                    + "\n1. Dar de alta tarea"
                    + "\n2. Eliminar tarea"
                    + "\n3. Mostrar tareas"
                    + "\n4. Procesar tareas"
                    + "\n5. Salir");

            try {
                opcion = Integer.parseInt(vista.ingresar("Seleccione una opción: "));
                switch (opcion) {
                    case 1:
                        darDeAlta();
                        break;
                    case 2:
                        eliminar();
                        break;
                    case 3:
                        mostrar();
                        break;
                    case 4:
                        procesar();
                        break;
                    case 5:
                        vista.imprimir("\nSistema cerrado con éxito.");
                        break;
                    default:
                        vista.imprimir("\nOpción no válida.");
                }
            } catch (NumberFormatException e) {
                vista.imprimir("\nError: Ingrese un número válido.");
            }
        }
    }

    /**
     * Solicita los datos para añadir una nueva tarea a la cola, insertándola en
     * la posición correspondiente para mantener un orden de prioridad (de menor
     * a mayor tiempo).
     */
    private void darDeAlta() {
        try {
            String nombre = vista.ingresar("Nombre de la tarea: ");

            if (nombre.trim().isEmpty()) {
                throw new IllegalArgumentException("El nombre no puede estar vacío.");
            }

            int duracion = Integer.parseInt(vista.ingresar("Duración de la tarea: "));

            if (duracion <= 0) {
                throw new NumberFormatException();
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

            vista.imprimir("\nTarea registrada exitosamente.");

        } catch (NumberFormatException e) {
            vista.imprimir("\nError: La duración debe ser un número entero mayor a cero.");
        } catch (IllegalArgumentException e) {
            vista.imprimir("\nError: " + e.getMessage());
        }
    }

    /**
     * Busca y elimina de la cola una tarea específica introducida por el
     * usuario mediante su nombre. Hace uso de una cola auxiliar para preservar
     * las demás tareas en su orden correcto.
     */
    private void eliminar() {
        if (cola.isEmpty()) {
            vista.imprimir("\nLa cola está vacía.");
            return;
        }

        String nombreBuscar = vista.ingresar("Nombre de la tarea a eliminar: ");
        Queue<Tarea> colaAuxiliar = new Queue<>();
        boolean encontrado = false;

        while (!cola.isEmpty()) {
            Tarea actual = cola.dequeue();

            if (actual.getNombre().equalsIgnoreCase(nombreBuscar) && !encontrado) {
                vista.imprimir("\nLa tarea '" + nombreBuscar + "' ha sido eliminada.");
                encontrado = true;
            } else {
                colaAuxiliar.enqueue(actual);
            }
        }

        while (!colaAuxiliar.isEmpty()) {
            cola.enqueue(colaAuxiliar.dequeue());
        }

        if (!encontrado) {
            vista.imprimir("\nNo se encontró una tarea con ese nombre.");
        }
    }

    /**
     * Muestra la lista de tareas que están pendientes por procesar.
     */
    private void mostrar() {
        if (cola.isEmpty()) {
            vista.imprimir("\nNo hay tareas pendientes en la cola.");
        } else {
            vista.imprimir(cola.toString());
        }
    }

    /**
     * Extrae secuencialmente las tareas de la cola simulando su ejecución. Va
     * sumando el tiempo transcurrido y calcula el tiempo medio de finalización
     * total al concluir el proceso.
     */
    private void procesar() {
        if (cola.isEmpty()) {
            vista.imprimir("\nNo hay tareas para procesar.");
            return;
        }

        int tiempo = 0;
        long sumaTiempos = 0;
        int totalTareas = cola.size();

        vista.imprimir("");
        while (!cola.isEmpty()) {
            Tarea actual = cola.dequeue();
            tiempo += actual.getTi();
            sumaTiempos += tiempo;

            vista.imprimir(actual.getNombre() + " finalizó en el minuto " + tiempo);
        }

        double promedio = (double) sumaTiempos / totalTareas;
        vista.imprimir("\nTiempo medio de finalización: " + promedio);
    }
}
