package estructuras;

import java.util.Iterator;
/**
 * @author Alisson Campos Marin
 * @author Jenny Marcela Tellez
 * @author Johan Andrey Ortiz
 */
class IteradorPila<T> implements Iterator<T>{

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
