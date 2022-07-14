package estructuras;

import java.util.Iterator;
/**
 * @author Alisson Campos Marin
 * @author Jenny Marcela Tellez
 * @author Johan Andrey Ortiz
 */
class IteradorListaDoble<T> implements Iterator<T>{

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
