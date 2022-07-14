package tallerListasEnlazadas.puntos;

import java.util.Iterator;

import tallerListasEnlazadas.listas.ListaSimpleCircular;

public class Punto2 {

	public static void main( String[] args ) {
		
		//Declaracion de la lista
		ListaSimpleCircular<Integer> numeros = new ListaSimpleCircular<>();
		
		// Llenado de la lista
		numeros.agregarfinal( 1 );
		numeros.agregarfinal( 2 );
		numeros.agregarfinal( 1 );
		numeros.agregarfinal( 3 );
		numeros.agregarfinal( 1 );
		numeros.agregarfinal( 2 );
		numeros.agregarfinal( 1 );
		numeros.agregarfinal( 3 );
		numeros.agregarfinal( 3 );
		numeros.agregarfinal( 1 );
		numeros.agregarfinal( 2 );
		
		//valor a contar en la lista
		int valorABuscar = 2;
		
		//Contamos
		int cantidad = getRepeticiones( valorABuscar, numeros );
		
		//Imprimimos
		numeros.imprimirLista();
		System.out.println( "cantidad de \"" + valorABuscar + "\": " + cantidad );
	}
	
	/**
	 * Método que cuenta la cantidad de repeticiones de un número en una ListaSimpleCircular
	 * @param valor a contar en la lista
	 * @param ListaSimpleCircular con los números donde buscaremos
	 * @return cantidad de veces que se encuentre el número ingresado por parámetro
	 */
	private static int getRepeticiones( int valor, ListaSimpleCircular<Integer> numeros ) {
		int contador = 0;
		Iterator<Integer> it = numeros.iterator();
		int aux = 0;
		
		while( it.hasNext() ) {
			aux = it.next();
			if( aux == valor ) {
				contador ++;
			}
		}
		
		return contador;
	}

}
