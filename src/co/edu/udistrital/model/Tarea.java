package co.edu.udistrital.model;

/**
 * Clase que representa una tarea a ser procesada en el sistema.
 *
 * @author sg812
 */
public class Tarea {

    private final String nombre;
    private final int ti;

    /**
     * Constructor para inicializar una nueva tarea.
     *
     * @param nombre Nombre o descripción de la tarea.
     * @param ti     Tiempo de duración de la tarea.
     */
    public Tarea(String nombre, int ti) {
        this.nombre = nombre;
        this.ti = ti;
    }

    /**
     * Obtiene el nombre asignado a la tarea.
     *
     * @return El nombre de la tarea.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el tiempo de duración de la tarea.
     *
     * @return El valor numérico del tiempo (ti).
     */
    public int getTi() {
        return ti;
    }

    /**
     * Construye una cadena de texto mostrando los datos de la tarea.
     *
     * @return Retorna un String con el formato "nombre - ti min".
     */
    @Override
    public String toString() {
        return "[" + nombre + " - " + ti + "min]";
    }
}
