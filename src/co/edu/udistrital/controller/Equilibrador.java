/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udistrital.controller;
import co.edu.udistrital.model.estructuras.Stack;
import co.edu.udistrital.view.ConsoleView;

/**
 * Clase controladora encargada de verificar si una expresión de símbolos 
 * de agrupación (paréntesis, corchetes y llaves) está correctamente equilibrada.
 * También gestiona la interacción con el usuario mediante un menú de consola.
 * 
 * @author acurr
 */
public class Equilibrador {
    
    /**
     * Verifica si una expresión de caracteres tiene sus símbolos de agrupación
     * correctamente equilibrados utilizando una pila.
     * 
     * @param expresion La cadena de texto que contiene los símbolos a evaluar.
     * @return Un String con el resultado de la evaluación: "Equilibrada" si es correcta, 
     *         o un mensaje de error detallando la causa del desequilibrio.
     */
    public String verificarEquilibrio(String expresion){
        Stack<Character> pila = new Stack<>();
        
        for(int i=0; i<expresion.length(); i++){
            char caracter = expresion.charAt(i);
            if (caracter == '[' || caracter ==  '{' || caracter == '('){
                pila.push(caracter);
                
            }
            else if (caracter == ']' || caracter ==  '}' || caracter == ')'){
                if (pila.isEmpty()){
                    return "error (Pila vacia y caracter de finalizacion)";
                }
                char cima = pila.pop();
                if(!esPareja(cima,caracter)){
                    return "Error (La cima no corresponde al valor de cierre)";
                }   
            }
            
        }
        if (pila.isEmpty()){
            return "Equilibrada";
        }
        else{
            return "Error (La pila no quedo vacia";
        }
               
    }
    
    /**
     * Compara un carácter de apertura con uno de cierre para determinar 
     * si son del mismo tipo (ej. '(' con ')').
     * 
     * @param apertura El carácter de apertura a evaluar.
     * @param cierre El carácter de cierre a evaluar.
     * @return {@code true} si los caracteres forman una pareja válida, {@code false} en caso contrario.
     */
    public boolean esPareja(char apertura, char cierre){
        return (apertura =='(' && cierre == ')')||
               (apertura =='[' && cierre == ']')||
               (apertura =='{' && cierre == '}');
    }
    
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
    
    /**
     * Valida mediante una expresión regular que la cadena ingresada contenga 
     * única y exclusivamente caracteres de agrupación válidos.
     * 
     * @param expresion La cadena que se desea validar.
     * @return {@code true} si la cadena contiene solo los símbolos '(', ')', '[', ']', '{', '}'. 
     *         Retorna {@code false} si contiene otros caracteres, si es nula o si está vacía.
     */
    public static boolean soloContieneSimbolos(String expresion) {
    // Verificamos que no sea nula o vacía primero (opcional dependiendo de tu lógica)
    if (expresion == null || expresion.isEmpty()) {
        return false; 
    }
    
    // El patrón regex significa: 
    // [ ... ] -> Conjunto de caracteres permitidos
    // \\( \\) etc -> Escapamos los símbolos porque tienen significado en regex
    // + -> Debe contener al menos 1 o más de estos caracteres, y nada más.
    return expresion.matches("[\\(\\[\\{\\)\\]\\}]+");
    }
    
}
