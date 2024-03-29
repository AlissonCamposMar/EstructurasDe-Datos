package estructuras;

import java.util.Iterator;
/**
 * @author Alisson Campos Marin
 * @author Jenny Marcela Tellez
 * @author Johan Andrey Ortiz
 */
class IteradorListaCircularSimple<T> implements Iterator<T>{

	private NodoSimple<T> nodoActual; 
	private NodoSimple<T> primerNodo;
	private boolean quedanNodos;
	
	public IteradorListaCircularSimple(NodoSimple<T> nodoInicial) {
		nodoActual = nodoInicial;
		this.primerNodo = nodoInicial;
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
		if( nodoActual == primerNodo ) {
			quedanNodos = false;
		}
		return auxiliar.getDato();
	}
	
}
