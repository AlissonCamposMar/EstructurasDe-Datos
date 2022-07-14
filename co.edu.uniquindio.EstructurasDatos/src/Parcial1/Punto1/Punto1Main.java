package Parcial1.Punto1;

import java.util.Scanner;


/**
 * @author acm91
 *
 */
public class Punto1Main {
	public static void main(String[] args) {
		PilaLineal listaPila = new PilaLineal();
		
		Persona persona1 = new Persona("David","Hombre",30);
		Persona persona2 = new Persona("Montse","Mujer",25);
		Persona persona3 = new Persona("Pablo","Hombre",20);

		// creamos una 'lista' de 'Persona's (ListaSimple)
		ListaPersonas lista = new ListaPersonas();
		lista.A�adirPersona(persona1);
		lista.A�adirPersona(persona2);
		lista.A�adirPersona(persona3);
		
		// Ver la �ltima persona introducida:
		System.out.println ("�ltima persona: " + lista.Consultar�ltimaPersona().getNombre());
		
		// Sacar la �ltima persona de la lista:
		System.out.println ("Eliminada la persona: " + lista.Consultar�ltimaPersona().getNombre());
		
		// Buscar la posici�n de una persona en la lista:
		System.out.println ("Posici�n de Montse: " + 		lista.BuscarPosici�nPersona(persona2));
	}
}