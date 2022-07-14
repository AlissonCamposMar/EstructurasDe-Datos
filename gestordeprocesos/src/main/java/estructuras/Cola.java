package estructuras;

import java.util.Iterator;

/**
 * @author Brahiam David Tabares Vallejo
 * @author Sandra Milena Quintero Leal
 * @author Tatiana Arboleda Martinez
 */
public class Cola<T> implements Iterable<T> {

	private NodoSimple<T> nodoInicial; // referencia al primer nodo
	private NodoSimple<T> nodoFinal; // referencia al ultimo nodo
	private int longitud; // cuantos nodos hay

	public Cola() {
		nodoInicial = null;
		nodoFinal = null;
		longitud = 0;
	}
	
	/**
	 * agrega un nodo al final de la lista
	 * 
	 * @param nodo
	 */
	private void add(NodoSimple<T> nuevoNodo) {
		// la lista esta vacia
		if (estaVacia()) {
			nodoInicial = nuevoNodo;
			nodoFinal = nuevoNodo;
		} else {
			nodoFinal.conectar(nuevoNodo);
			nodoFinal = nuevoNodo;
		}

		// la ista tiene al menos un elemento
		longitud = longitud + 1;
	}

	
	
	
	/**
	 * MODIFICADO agrega un nodo al final de la lista
	 * 
	 * @param elemento
	 */
	public void add(T elemento) { // .add del arraylist
		// la lista esta vacia
		NodoSimple<T> nuevoNodoSimple = new NodoSimple<T>(elemento, elemento.toString());
		add(nuevoNodoSimple);
	}

	public boolean estaVacia() {
		return longitud == 0;
	}


	public T peek() {
		return nodoInicial.getDato();
	}

	public int getLongitud() {
		return longitud;
	}

	@Override
	public String toString() {
		String msj = "Cola: { ";
		NodoSimple<T> nodoActual = nodoInicial;
		for (int i = 0; i < getLongitud(); i++) {
			msj = msj + nodoActual.getNombre() + " -> ";
			nodoActual = nodoActual.siguienteNodo();
		}
		return msj + " }";
	}

	public T poll() {
		T dato = peek();
		eliminarPrimero();
		return dato;
	}

	/**
	 * metodo que elimina el primer nodo
	 */
	private void eliminarPrimero() {
		if (estaVacia()) {
			return; // o exception
		}
		if (longitud == 1) {
			vaciarCola();
		} else {
			nodoInicial = nodoInicial.siguienteNodo();
			longitud--;
		}

	}

	/*
	 * Metodo que permite vaciar la lista
	 */

	public void vaciarCola() {
		nodoFinal = null;
		nodoInicial = null;
		longitud = 0;
	}

	@Override
	public Iterator<T> iterator() {
		return new IteradorCola<>(nodoInicial);
	}



}

