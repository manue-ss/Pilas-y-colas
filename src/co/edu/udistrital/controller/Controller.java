/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udistrital.controller;

/**
 *
 * @author acurr
 */
public class Controller {
    
    public void run(){
        
    }
    
    private void equilibrador(){
            /**
     * Muestra el menú principal de la aplicación en la consola.
     * Permite al usuario elegir entre usar cadenas preestablecidas o ingresar una propia.
     */
    public void imprimirMenuEquilibrador(){
        ConsoleView vista = new ConsoleView();
        int selector = 9;
        do{
            vista.imprimir("Bienvenido al equilibrador"); 
            vista.imprimir("Seleccione una opcion");
            vista.imprimir("1. Usar cadena preestablecida"); 
            vista.imprimir("2. Ingresar cadena"); 
            int opcion = -1;
            try {
                opcion = Integer.parseInt(vista.ingresar(""));
            } catch (NumberFormatException e) {}
            switch (opcion){   
                case 1:
                    casoPreestablecido();
                    selector =0 ;
                    break;
                case 2:
                    casoNoDefinido();
                    selector =0 ;
                    break;    
               default:
                    vista.imprimir("Opcion no valida");
           }
        }while (selector != 0);


    }

    /**
     * Despliega un submenú que evalúa casos de prueba fijos o preestablecidos.
     * Evalúa las cadenas "[()]" y "[(])" como demostración del funcionamiento.
     */
    public void casoPreestablecido(){
        ConsoleView vista = new ConsoleView();
        int caso = 9;
        String respuesta;
        do{
            vista.imprimir("Seleccione una opcion");
            vista.imprimir("1. [()]"); 
            vista.imprimir("2. [(])");
            int opcion = -1;
            try {
                opcion = Integer.parseInt(vista.ingresar(""));
            } catch (NumberFormatException e) {}
            switch(opcion){
                case 1: 
                    respuesta = verificarEquilibrio("[()]");
                    vista.imprimir(respuesta);
                    caso = 0;
                    break;
                case 2: 
                    respuesta = verificarEquilibrio("[(])");
                    vista.imprimir(respuesta);
                    caso = 0;
                    break;
                default:
                    vista.imprimir("Opcion no valida");
            }
        }while(caso != 0);

    }

    /**
     * Solicita al usuario ingresar una cadena por consola, valida que la entrada
     * contenga únicamente símbolos permitidos y luego evalúa si está equilibrada.
     */
    public void casoNoDefinido (){
        ConsoleView vista = new ConsoleView();
        int validacion = 1;
        do{
           String cadena = vista.ingresar("Por favor ingrese su cadena: ");
           if (soloContieneSimbolos(cadena)){
               String respuesta = verificarEquilibrio(cadena);
               vista.imprimir(respuesta);
               validacion = 0;
           }
           else{
               vista.imprimir("Ingrese una expresion valida");
           }
        }while (validacion  != 0);


    }    
    }
    
    private void asignador(){
        
    }
}
