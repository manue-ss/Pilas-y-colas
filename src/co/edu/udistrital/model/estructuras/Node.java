package co.edu.udistrital.model.estructuras;

/**
 * Clase que representa un nodo individual en una estructura de datos dinámica.
 * Almacena un dato de tipo genérico y una referencia al siguiente nodo enlazado.
 *
 * @author Manuel Salazar
 * @since 0.1
 * @param <T> Tipo de dato genérico que almacenará el nodo.
 */
class Node<T> {

    private Node<T> siguiente;
    private T dato;

    /**
     * Constructor por defecto. Inicializa el nodo con referencias nulas.
     */
    public Node() {
        siguiente = null;
    }

    /**
     * Constructor que inicializa el nodo con un dato específico.
     *
     * @param dato El dato a almacenar en el nodo.
     */
    public Node(T dato) {
        this.dato = dato;
        siguiente = null;
    }

    /**
     * Obtiene la referencia al siguiente nodo.
     *
     * @return El nodo siguiente en la secuencia, o {@code null} si no hay siguiente.
     */
    public Node<T> getSiguiente() {
        return siguiente;
    }

    /**
     * Establece la referencia al siguiente nodo.
     *
     * @param siguiente El nodo que será enlazado como el siguiente.
     */
    public void setSiguiente(Node<T> siguiente) {
        this.siguiente = siguiente;
    }

    /**
     * Obtiene el dato almacenado en el nodo.
     *
     * @return El dato de tipo genérico contenido en el nodo.
     */
    public T getDato() {
        return dato;
    }

    /**
     * Establece el dato a almacenar en el nodo.
     *
     * @param dato El dato de tipo genérico a asignar.
     */
    public void setDato(T dato) {
        this.dato = dato;
    }

    /**
     * Devuelve una representación en formato de cadena del dato contenido en el nodo.
     *
     * @return Representación en texto del dato almacenado.
     */
    @Override
    public String toString() {
        return dato.toString();
    }

}
