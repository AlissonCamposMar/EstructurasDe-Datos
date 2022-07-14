package Parcial1.Punto1;

import java.util.Stack;

public class ListaPersonas <T> {
    private Stack<Persona> pila = new Stack();  

    void AñadirPersona(Persona nuevaPersona){
        pila.push(nuevaPersona);
    }

    Persona ConsultarÚltimaPersona(){
        return pila.peek();  
    }

    Persona SacarÚltimaPersona(){
        return pila.pop();
    }

    int BuscarPosiciónPersona(Persona persona){
      return pila.search(persona);
    }
} 