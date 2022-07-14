package co.edu.uniquindio.colas;

import co.edu.uniquindio.listaSimple.pilas.Nodo;

/**
 *
 * @param <T>
 */
public class Cola<T> {

	public Nodo<T> nodoPrimero, nodoUltimo;
	public int tamanio;



	/**
	 * Agrega un elemento en la Cola
	 * @param dato elemento a guardar en la Cola
	 */
	public void encolar(T dato) {

		Nodo<T> nodo = new Nodo<>(dato);

		if(estaVacia()) {
			nodoPrimero = nodoUltimo = nodo;
		}else {
			nodoUltimo.setSiguienteNodo(nodo);
			nodoUltimo = nodo;
		}

		tamanio++;
	}

	/**
	 * Retorna y elimina el elemento que está al incio de la Cola
	 * @return Primer elemento de la Cola
	 */
	public T desencolar() {

		if(estaVacia()) {
			throw new RuntimeException("La Cola está vacía");
		}

		T dato = nodoPrimero.getValorNodo();
		nodoPrimero = nodoPrimero.getSiguienteNodo();

		if(nodoPrimero==null) {
			nodoUltimo = null;
		}

		tamanio--;
		return dato;
	}

	/**
	 * Verifica si la Cola está vacía
	 * @return true si está vacía
	 */
	public boolean estaVacia() {
		return nodoPrimero == null;
	}



	/**
	 * Borra completamente la Cola
	 */
	public void borrarCola() {
		nodoPrimero = nodoUltimo = null;
		tamanio = 0;
	}

	/**
	 * @return the primero
	 */
	public Nodo<T> getPrimero() {
		return nodoPrimero;
	}

	/**
	 * @return the ultimo
	 */
	public Nodo<T> getUltimo() {
		return nodoUltimo;
	}

	/**
	 * @return the tamano
	 */
	public int getTamano() {
		return tamanio;
	}
	
	/**
	 * Imprime una pila en consola
	 */
	public void imprimir() {
		Nodo<T> aux = nodoPrimero;
		while(aux!=null) {
			System.out.println(aux.getValorNodo());
			aux = aux.getSiguienteNodo();
		}
		System.out.println();
	}
}