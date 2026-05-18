/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udistrital.controller;
import co.edu.udistrital.model.estructuras.Stack;
import co.edu.udistrital.view.ConsoleView;

/**
 *
 * @author acurr
 */
public class Equilibrador {
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
    
    public boolean esPareja(char apertura, char cierre){
        return (apertura =='(' && cierre == ')')||
               (apertura =='[' && cierre == ']')||
               (apertura =='{' && cierre == '}');
    }
    
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
