package co.edu.udistrital.model;

import java.util.NoSuchElementException;

/**
 * Cola genérica con referencia a la cabeza - {@link #head} - y a la cola -
 * {@link #tail} - para reducir la complejidad temporal.
 *
 * @author Manuel Salazar
 * @since 0.1
 */
public class Queue<T> {

    private Node<T> head;
    private Node<T> tail;
    private int tamanio;

    /**
     * Constructor por defecto. Inicializa los valores de {@link #head} y
     * {@link #tail} en nulo y el tamaño en 0.
     */
    public Queue() {
        this.head = null;
        this.tail = null;
        this.tamanio = 0;
    }

    /**
     * Añade un elemento al final de la cola.
     *
     * @param d Valor del elemento a añadir a la cola.
     *
     * @return Retorna {@code True} si la adición fue satisfactoria.
     */
    public boolean enqueue(T d) {
        Node<T> nuevo = new Node<>(d);

        if (head == null) {
            head = nuevo;
        } else {
            tail.setSiguiente(nuevo);
        }

        tail = nuevo;
        tamanio++;

        return true;
    }

    /**
     * Extrae el elemento al inicio de la cola.
     *
     * @return El valor del elemento al inicio de la cola.
     *
     * @throws NoSuchElementException Si se intenta desencolar cuando la cola
     *                                está vacía.
     */
    public T dequeue() {
        if (isEmpty()) {
            // Detiene la ejecución y lanza un mensaje descriptivo
            throw new NoSuchElementException("No se puede desencolar: La cola está vacía.");
        }

        Node<T> antiguaCabeza = head;

        head = antiguaCabeza.getSiguiente();

        if (antiguaCabeza == tail) {
            tail = null;
        }

        tamanio--;
        return antiguaCabeza.getDato();
    }

    /**
     * Verifica si la cola está o no vacía.
     *
     * @return Retorna {@code True} si esta vacia.
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Consulta el tamaño de la cola.
     *
     * @return Retorna el tamaño de la cola.
     */
    public int size() {
        return this.tamanio;
    }

    /**
     * Obtiene el valor del elemento al frente de la cola sin extraerlo.
     *
     * @return El valor del elemento en la cabeza de la cola.
     *
     * @throws NoSuchElementException Si se consulta el frente cuando la cola
     *                                está vacía.
     */
    public T front() {
        if (isEmpty()) {
            throw new NoSuchElementException("La cola está vacía. No hay elementos en el frente.");
        }
        return this.head.getDato();
    }

    /**
     * Vacia la cosa.
     *
     * @return Retorna {@code true} si logró vaciar la cola.
     */
    public boolean clear() {
        head = null;
        tail = null;
        tamanio = 0;

        return true;
    }

    /**
     * Construye una cadena de textos mostrando los elementos de la cola.
     *
     * @return Retorna un {@code String} con la cola en formato de lista (... ->
     *         ... -> ...).
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> actual = head;

        sb.append("Head -> ");
        while (actual != null) {
            sb.append(actual.getDato());
            sb.append(" -> ");
            actual = actual.getSiguiente();
        }
        sb.append("<- Tail");

        return sb.toString();
    }

}
