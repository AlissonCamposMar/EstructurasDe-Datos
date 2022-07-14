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
		lista.AñadirPersona(persona1);
		lista.AñadirPersona(persona2);
		lista.AñadirPersona(persona3);
		
		// Ver la última persona introducida:
		System.out.println ("Última persona: " + lista.ConsultarÚltimaPersona().getNombre());
		
		// Sacar la última persona de la lista:
		System.out.println ("Eliminada la persona: " + lista.ConsultarÚltimaPersona().getNombre());
		
		// Buscar la posición de una persona en la lista:
		System.out.println ("Posición de Montse: " + 		lista.BuscarPosiciónPersona(persona2));
	}
}