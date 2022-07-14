package tallerListasEnlazadas.puntos;

import java.util.Iterator;

import tallerListasEnlazadas.listas.ListaSimple;

/**
 * PUNTO 3
 * 
 *
 */
public class Punto1 {

	public static void main( String[] args ) {
		
		//Declaramos la lista de numeros
		ListaSimple<Integer> numeros = new ListaSimple<>();
		
		//LLenamos la lista
		numeros.agregarfinal( 1 );
		numeros.agregarfinal( 2 );
		numeros.agregarfinal( 3 );
		numeros.agregarfinal( 4 );
		numeros.agregarfinal( 5 );
		numeros.agregarfinal( 6 );
		numeros.agregarfinal( 7 );
		numeros.agregarfinal( 8 );
		numeros.agregarfinal( 9 );
		numeros.agregarfinal( 10 );

		//Declaramos y llenamos la lista con los impares que hayan presentes en la lista "numeros"
		ListaSimple<Integer> numerosImpares = getListaSimpleImpares( numeros );
		numerosImpares.imprimirLista();
	}
	
	/**
	 * M�todo que retorna una lista de n�meros impares que hayan presentes en la listaSimple ingresada por par�metro
	 * @param ListaSimpleEnlazada de n�meros
	 * @return ListaSimple de n�meros impares
	 */
	private static ListaSimple<Integer> getListaSimpleImpares( ListaSimple<Integer> numeros ){
		ListaSimple<Integer> impares = new ListaSimple<>();
		
		Iterator<Integer> it = numeros.iterator();
		
		int aux = 0;
		
		while( it.hasNext() ) {
			aux = it.next();
			if( aux % 2 != 0 ) {
				impares.agregarfinal( aux );
			}
		}
		
		return impares;
	}

}
