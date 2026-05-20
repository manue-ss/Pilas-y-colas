package co.edu.udistrital.model.estructuras;

import java.util.NoSuchElementException;

/**
 * Cola genérica con referencia al tope - {@link #top} - para reducir la
 * complejidad temporal.
 *
 * @author Manuel Salazar
 * @since 0.1
 */
public class Stack<T> {

    private Node<T> top;
    private int tamanio;

    /**
     * Constructor por defecto. Inicializa los valores de {@link #top} en nulo y
     * el tamaño en 0.
     */
    public Stack() {
        this.top = null;
        this.tamanio = 0;
    }

    /**
     * Añade un elemento al inicio de la pila.
     *
     * @param d Valor del elemento a añadir a la cola.
     *
     */
    public void push(T d) {
        Node<T> nuevo = new Node<>(d);

        nuevo.setSiguiente(top);
        top = nuevo;

        tamanio++;
    }

    /**
     * Extrae el elemento en la cima de la pila.
     *
     * @return El valor del elemento en la cima de la pila.
     *
     * @throws NoSuchElementException Si se intenta desapilar cuando la pila
     *                                está vacía.
     */
    public T pop() {
        if (isEmpty()) {
            // Detiene la ejecución y lanza un mensaje descriptivo
            throw new NoSuchElementException("No se puede desapilar: La pila está vacía.");
        }

        Node<T> antiguaCabeza = top;

        top = antiguaCabeza.getSiguiente();

        tamanio--;
        return antiguaCabeza.getDato();

    }

    /**
     * Verifica si la cola está o no vacía.
     *
     * @return Retorna {@code True} si esta vacia.
     */
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * Obtiene el valor del elemento al tope de la pila sin extraerlo.
     *
     * @return El valor del elemento en tope de la pila.
     *
     * @throws NoSuchElementException Si se consulta el frente cuando la cola
     *                                está vacía.
     */
    public T top() {
        if (isEmpty()) {
            throw new NoSuchElementException("La pila está vacía. No hay elementos en el frente.");
        }
        return this.top.getDato();

    }

    /**
     * Consulta el tamaño de la pila.
     *
     * @return Retorna el tamaño de la pila.
     */
    public int size() {
        return tamanio;
    }

    /**
     * Vacia la pila
     */
    public void clear() {
        this.top = null;
        this.tamanio = 0;
    }

    /**
     * Construye una cadena de texto mostrando los elementos de la pila.
     *
     * @return Retorna un {@code String} con la pila en formato de lista.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> actual = top;

        sb.append("Top ");
        while (actual != null) {
            sb.append(" -> ");
            sb.append(actual.getDato());
            actual = actual.getSiguiente();
        }

        return sb.toString();
    }

}
