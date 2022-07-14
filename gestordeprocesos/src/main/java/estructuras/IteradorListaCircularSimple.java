package estructuras;

import java.util.Iterator;

class IteradorListaCircularSimple<T> implements Iterator<T>{

	
	/*
	 * Nodo<T> nodoActual = nodoInicial;
	 * while( nodoActual != null ){
	 * 	T informacion = nodoAcutal.getDato();
	 *  ....
	 *  nodoActual = nodoActual.siguienteNodo();
	 * } 
	 * 
	 * -------
	 * Iterador<t> iterador = new Iterador(nodoInicial);
	 * 
	 * while( iterador.hasNext() ){
	 *  T informacion = iterador.next();
	 *  ...
	 * }
	 * 
	 * 
	 */
	private NodoSimple<T> nodoActual; 
	private NodoSimple<T> nodoInicial;
	private boolean quedanNodos;
	
	public IteradorListaCircularSimple(NodoSimple<T> nodoInicial) {
		nodoActual = nodoInicial;
		this.nodoInicial = nodoInicial;
		quedanNodos = nodoInicial != null;
	}
	
	@Override
	public boolean hasNext() {
		return quedanNodos;
	}

	@Override
	public T next() {
		NodoSimple<T> auxiliar = nodoActual;
		nodoActual = nodoActual.siguienteNodo();
		if( nodoActual == nodoInicial ) {
			quedanNodos = false;
		}
		return auxiliar.getDato();
	}
	
}
