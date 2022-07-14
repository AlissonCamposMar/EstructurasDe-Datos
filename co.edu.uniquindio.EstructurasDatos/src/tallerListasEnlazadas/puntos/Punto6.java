package tallerListasEnlazadas.puntos;

import java.util.Iterator;

import tallerListasEnlazadas.listas.ListaDoble;

public class Punto6 {

	public static void main( String[] args ) {
		ListaDoble<Integer> lista1 = new ListaDoble<>();
		ListaDoble<Integer> lista2 = new ListaDoble<>();
		ListaDoble<Integer> listaConcatenada = new ListaDoble<>();
		
		lista1.agregarfinal( 1 );
		lista1.agregarfinal( 1 );
		lista1.agregarfinal( 1 );
		lista1.agregarfinal( 1 );
		lista1.agregarfinal( 1 );
		lista1.agregarfinal( 1 );
		lista1.agregarfinal( 1 );
		
		lista2.agregarfinal( 2 );
		lista2.agregarfinal( 2);
		lista2.agregarfinal( 2 );
		
		listaConcatenada = concatenarListas( lista1, lista2 );
		
		listaConcatenada.imprimirLista();
	}

	private static ListaDoble<Integer> concatenarListas(ListaDoble<Integer> a, ListaDoble<Integer> b) {
		ListaDoble<Integer> resul = new ListaDoble<>();
		
		Iterator<Integer> it1 = a.iterator();
		
		while( it1.hasNext() ) {
			resul.agregarfinal( ( it1.next() ) );
		}
		
		Iterator<Integer> it2 = b.iterator();
		
		while( it2.hasNext() ) {
			resul.agregarfinal( ( it2.next() ) );
		}
		
		return resul;
	}

	
//	private static <T> ListaDoble<T> concatenarListas( ListaDoble<T> a, ListaDoble<T> b  ) {
//		ListaDoble<T> resul = new ListaDoble<>();
//		
//		Iterator<T> it1 = a.iterator();
//		
//		while( it1.hasNext() ) {
//			resul.agregarfinal( ( it1.next() ) );
//		}
//		
//		Iterator<T> it2 = b.iterator();
//		
//		while( it2.hasNext() ) {
//			resul.agregarfinal( ( it2.next() ) );
//		}
//		
//		return resul;
//	}

}
