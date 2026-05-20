package co.edu.udistrital.view;

import java.util.Scanner;

/**
 * Clase que maneja la interacción con el usuario a través de la consola.
 * Permite imprimir mensajes y recibir entrada de texto.
 *
 * @author Manuel S
 */
public class ConsoleView {

    private Scanner sc = new Scanner(System.in);

    /**
     * Constructor por defecto de la clase ConsoleView.
     */
    public ConsoleView() {
    }

    /**
     * Imprime un mensaje en consola
     *
     * @param mensaje mensjae a imprimir en consola
     */
    public void imprimir(String mensaje) {
        System.out.println(mensaje);
    }

    /**
     * Recibe un dato ingresado por el usuario
     *
     * @param mensaje mensjae asociado al dato
     *
     * @return cadena de caracteres con el dato
     */
    public String ingresar(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine();
    }
}
