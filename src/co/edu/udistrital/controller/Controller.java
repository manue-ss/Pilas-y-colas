package co.edu.udistrital.controller;

import co.edu.udistrital.view.ConsoleView;
import co.edu.udistrital.model.AsignacionTareas;
import co.edu.udistrital.model.Equilibrador;

/**
 * Controlador principal que gestiona la selección de programas.
 *
 * @author sg812
 */
public class Controller {

    private ConsoleView vista;

    /**
     * Constructor de la clase Controller. Inicializa la vista de consola para
     * la interacción con el usuario.
     */
    public Controller() {
        vista = new ConsoleView();
    }

    /**
     * Inicia el menú principal para seleccionar el subprograma a ejecutar.
     */
    public void run() {
        int opcion = 0;

        while (opcion != 3) {
            vista.imprimir("\n=== MENÚ PRINCIPAL ===");
            vista.imprimir("1. Sistema de Asignación de Tareas");
            vista.imprimir("2. Equilibrador de Símbolos");
            vista.imprimir("3. Salir del programa");

            try {
                opcion = Integer.parseInt(vista.ingresar("Seleccione qué programa desea ejecutar: "));

                switch (opcion) {
                    case 1:
                        vista.imprimir("\n--- Iniciando Asignación de Tareas ---");
                        AsignacionTareas asignacion = new AsignacionTareas();
                        int opt = 0;
                        while (opt != 5) {
                            vista.imprimir("\nSistema de Tareas\n1. Dar de alta tarea\n2. Eliminar tarea\n3. Mostrar tareas\n4. Procesar tareas\n5. Volver");
                            try {
                                opt = Integer.parseInt(vista.ingresar("Seleccione una opción: "));
                                switch (opt) {
                                    case 1:
                                        try {
                                            String nombre = vista.ingresar("Nombre de la tarea: ");
                                            int duracion = Integer.parseInt(vista.ingresar("Duración de la tarea: "));
                                            asignacion.addTask(nombre, duracion);
                                            vista.imprimir("Tarea registrada exitosamente.");
                                        }
                                        catch (NumberFormatException e) {
                                            vista.imprimir("Error: La duración debe ser un número entero.");
                                        }
                                        catch (IllegalArgumentException e) {
                                            vista.imprimir("Error: " + e.getMessage());
                                        }
                                        break;
                                    case 2:
                                        if (asignacion.isEmpty()) {
                                            vista.imprimir("La cola está vacía.");
                                        } else {
                                            String nombreBuscar = vista.ingresar("Nombre de la tarea a eliminar: ");
                                            boolean eliminado = asignacion.removeTaskByName(nombreBuscar);
                                            if (eliminado) {
                                                vista.imprimir("La tarea '" + nombreBuscar + "' ha sido eliminada.");
                                            } else {
                                                vista.imprimir("No se encontró una tarea con ese nombre.");
                                            }
                                        }
                                        break;
                                    case 3:
                                        vista.imprimir(asignacion.listTasks());
                                        break;
                                    case 4:
                                        if (asignacion.isEmpty()) {
                                            vista.imprimir("No hay tareas para procesar.");
                                        } else {
                                            try {
                                                int numP = Integer.parseInt(vista.ingresar("Ingrese el número de procesadores: "));
                                                String resultado = asignacion.processTasks(numP);
                                                vista.imprimir(resultado);
                                            }
                                            catch (NumberFormatException e) {
                                                vista.imprimir("Error: El número de procesadores debe ser un entero.");
                                            }
                                            catch (IllegalArgumentException e) {
                                                vista.imprimir("Error: " + e.getMessage());
                                            }
                                        }
                                        break;
                                    case 5:
                                        break;
                                    default:
                                        vista.imprimir("Opción no válida.");
                                }
                            }
                            catch (NumberFormatException e) {
                                vista.imprimir("Error: Ingrese un número válido.");
                            }
                        }
                        break;
                    case 2:
                        vista.imprimir("\n--- Iniciando Equilibrador ---");
                        Equilibrador equilibrador = new Equilibrador();
                        int opcEq = 0;
                        while (opcEq != 4) {
                            vista.imprimir("\nEquilibrador\n1. Caso preestablecido [()]\n2. Caso preestablecido [(])\n3. Ingresar cadena\n4. Volver");
                            try {
                                opcEq = Integer.parseInt(vista.ingresar("Seleccione una opción: "));
                                switch (opcEq) {
                                    case 1:
                                        vista.imprimir(equilibrador.verificarEquilibrio("[()]"));
                                        break;
                                    case 2:
                                        vista.imprimir(equilibrador.verificarEquilibrio("[(])"));
                                        break;
                                    case 3:
                                        String cadena = vista.ingresar("Por favor ingrese su cadena: ");
                                        if (Equilibrador.soloContieneSimbolos(cadena)) {
                                            vista.imprimir(equilibrador.verificarEquilibrio(cadena));
                                        } else {
                                            vista.imprimir("Error: Símbolos no válidos");
                                        }
                                        break;
                                    case 4:
                                        break;
                                    default:
                                        vista.imprimir("Opción no válida");
                                }
                            }
                            catch (NumberFormatException e) {
                                vista.imprimir("Error: Ingrese un número válido.");
                            }
                        }
                        break;
                    case 3:
                        vista.imprimir("\nCerrando el sistema general. ¡Hasta pronto!");
                        break;
                    default:
                        vista.imprimir("\nOpción no válida. Intente de nuevo.");
                }
            }
            catch (NumberFormatException e) {
                vista.imprimir("\nError: Ingrese un número válido.");
            }
        }
    }
}
