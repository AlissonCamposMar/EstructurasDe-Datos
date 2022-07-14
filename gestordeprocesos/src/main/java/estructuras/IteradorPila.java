package estructuras;

import java.util.Iterator;

class IteradorPila<T> implements Iterator<T>{

	
	/*
	 * Nodo<T> nodoActual = nodoTope;
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
	
	public IteradorPila(NodoSimple<T> nodoTope) {
		nodoActual = nodoTope;
	}
	
	@Override
	public boolean hasNext() {
		return nodoActual != null;
	}

	@Override
	public T next() {
		NodoSimple<T> auxiliar = nodoActual;
		nodoActual = nodoActual.siguienteNodo();
		return auxiliar.getDato();
	}
	
}
