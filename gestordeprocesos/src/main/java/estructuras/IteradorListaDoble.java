package estructuras;

import java.util.Iterator;

class IteradorListaDoble<T> implements Iterator<T>{

	
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
	
	public IteradorListaDoble(NodoDoble<T> nodoInicial) {
		nodoActual = nodoInicial;
	}
	
	@Override
	public boolean hasNext() {
		return nodoActual != null;
	}

	@Override
	public T next() {
		NodoDoble<T> auxiliar = nodoActual;
		nodoActual = nodoActual.siguienteNodo();
		return auxiliar.getDato();
	}
	
}
