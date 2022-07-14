package Parcial1.Punto1;

import java.util.Stack;

public class ListaPersonas <T> {
    private Stack<Persona> pila = new Stack();  

    void A�adirPersona(Persona nuevaPersona){
        pila.push(nuevaPersona);
    }

    Persona Consultar�ltimaPersona(){
        return pila.peek();  
    }

    Persona Sacar�ltimaPersona(){
        return pila.pop();
    }

    int BuscarPosici�nPersona(Persona persona){
      return pila.search(persona);
    }
} 