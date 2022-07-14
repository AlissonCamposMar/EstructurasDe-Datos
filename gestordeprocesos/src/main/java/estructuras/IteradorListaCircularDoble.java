package estructuras;

import java.util.Iterator;

class IteradorListaCircularDoble<T> implements Iterator<T>{

	
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
	private NodoDoble<T> nodoActual; 
	private NodoDoble<T> nodoInicial;
	private boolean quedanNodos;
	
	public IteradorListaCircularDoble(NodoDoble<T> nodoInicial) {
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
		NodoDoble<T> auxiliar = nodoActual;
		nodoActual = nodoActual.siguienteNodo();
		if( nodoActual == nodoInicial ) {
			quedanNodos = false;
		}
		return auxiliar.getDato();
	}
	
}
