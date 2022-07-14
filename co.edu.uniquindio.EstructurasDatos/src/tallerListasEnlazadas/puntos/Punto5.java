package tallerListasEnlazadas.puntos;

import tallerListasEnlazadas.listas.ListaDobleCircular;

public class Punto5 {

	public static void main( String[] args ) {
		
		ListaDobleCircular<Integer> lista = new ListaDobleCircular<>();
		
		lista.agregarFinal( 1 );
		lista.agregarFinal( 2 );
		lista.agregarFinal( 3 );
		lista.agregarFinal( 4 );
		lista.agregarFinal( 5 );
		lista.agregarFinal( 6 );
		lista.agregarFinal( 7 );
		
		lista.insertar( 0, 3 );
		lista.imprimirLista();
		
		int pos = lista.buscar(1);

		System.out.println( pos );
	}

}
