package co.edu.udistrital.controller;

import co.edu.udistrital.view.ConsoleView;
import co.edu.udistrital.model.AsignacionTareas;
import co.edu.udistrital.model.Equilibrador;

/**
 * Controlador principal que gestiona la selección de programas.
 */
public class Controller {
    
    private ConsoleView vista;
    

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
                        asignacion.iniciar();
                        break;
                    case 2:
                        vista.imprimir("\n--- Iniciando Equilibrador ---");
                        Equilibrador equilibrador = new Equilibrador();
                        equilibrador.imprimirMenuEquilibrador();
                        break;
                    case 3:
                        vista.imprimir("\nCerrando el sistema general. ¡Hasta pronto!");
                        break;
                    default:
                        vista.imprimir("\nOpción no válida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                vista.imprimir("\nError: Ingrese un número válido.");
            }
        }
    }
}