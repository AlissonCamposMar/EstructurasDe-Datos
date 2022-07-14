package tallerListasEnlazadas.puntos;

import tallerListasEnlazadas.listas.ListaDoble;
import tallerListasEnlazadas.listas.NodoDoble;

/**
 * imprimir hacia atrás una lista doblemente enlazada
 * @author Yiran Hernández
 *
 */
public class Punto3 {

	public static void main( String[] args ) {
		
		ListaDoble<Integer> lista = new ListaDoble<>();
		
		lista.agregarfinal( 1 );
		lista.agregarfinal( 2 );
		lista.agregarfinal( 3 );
		lista.agregarfinal( 4 );
		lista.agregarfinal( 5 );
		lista.agregarfinal( 6 );
		lista.agregarfinal( 7 );
		lista.agregarfinal( 8 );
		lista.agregarfinal( 9 );
		
		lista.imprimirLista();
//		lista.imprimirHaciaAtras();
		imprimirHaciaAtras(lista);

	}
	
	
	public static void imprimirHaciaAtrasNormal(ListaDoble<Integer> list) {

		for(NodoDoble<Integer> aux = list.getNodoUltimo(); aux!=null; aux = aux.getAnteriorNodo()) {
			System.out.print( aux.getValorNodo()+"\t" );
		}
		System.out.println();

	}
	
	public static <T> void imprimirHaciaAtras(ListaDoble<T> list) {
		
		for(NodoDoble<T> aux = list.getNodoUltimo(); aux!=null; aux = aux.getAnteriorNodo()) {
			System.out.print( aux.getValorNodo()+"\t" );
		}
		System.out.println();
		
	}

}
