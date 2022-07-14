package estructuras;

import java.util.Iterator;
/**
 * @author Alisson Campos Marin
 * @author Jenny Marcela Tellez
 * @author Johan Andrey Ortiz
 */
class IteradorCola<T> implements Iterator<T>{

	private NodoSimple<T> nodoActual; 
	
	public IteradorCola(NodoSimple<T> nodoInicial) {
		nodoActual = nodoInicial;
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
