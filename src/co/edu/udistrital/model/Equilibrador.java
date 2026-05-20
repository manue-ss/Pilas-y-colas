package co.edu.udistrital.model;

import co.edu.udistrital.model.estructuras.Stack;

/**
 * Modelo encargado de verificar si una expresión de símbolos de agrupación
 * está equilibrada. No realiza operaciones de entrada/salida.
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
     * Valida mediante una expresión regular que la cadena ingresada contenga 
     * única y exclusivamente caracteres de agrupación válidos.
     * 
     * @param expresion La cadena que se desea validar.
     * @return {@code true} si la cadena contiene solo los símbolos '(', ')', '[', ']', '{', '}'. 
     *         Retorna {@code false} si contiene otros caracteres, si es nula o si está vacía.
     */
    public static boolean soloContieneSimbolos(String expresion) {
        if (expresion == null || expresion.isEmpty()) {
            return false; 
        }
        return expresion.matches("[\\(\\[\\{\\)\\]\\}]+");
    }
    
}
