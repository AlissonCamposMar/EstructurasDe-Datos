package estructuras;

import java.util.Iterator;

/**
 * @author Brahiam David Tabares Vallejo
 * @author Sandra Milena Quintero Leal
 * @author Tatiana Arboleda Martinez
 */
public class ListaEnlazada<T> implements Iterable<T>{

	private NodoSimple<T> nodoInicial; // referencia al primer nodo
	private NodoSimple<T> nodoFinal; // referencia al ultimo nodo
	private NodoSimple<T>nodoActual; // referencia al nodo actual (en el que estoy ubicado actualmente)
	private int longitud; // cuantos nodos hay

	public ListaEnlazada() {
		nodoInicial = null;
		nodoFinal = null;
		nodoActual = null;
		longitud = 0;
	}

	/**
	 * agrega un nodo al final de la lista
	 * 
	 * @param nodo
	 */
	public void agregarFinal(NodoSimple<T>nuevoNodo) {
		// la lista esta vacia
		nodoActual = nuevoNodo;
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
	public void agregarFinal(T elemento) { // .add del arraylist
		// la lista esta vacia
		NodoSimple<T>nuevoNodoSimple = new NodoSimple<T>(elemento, elemento.toString());
		agregarFinal(nuevoNodoSimple);
	}

	/**
	 * ubicarse en el primer nodo de la lista
	 */
	public void irPrimerNodo() {
		nodoActual = nodoInicial;
	}

	/**
	 * ubicarse en el últomo nodo de la lista
	 */
	public void irUltimoNodo() {
		nodoActual = nodoFinal;
	}

	public boolean estaVacia() {
		return longitud == 0;
	}

	/**
	 * MODIFICADO-----------------------------------------------------------------------------------------
	 * ubicarse en siguiente nodo de la lista, si esta en el ultimo retornara null
	 */
	public boolean irSiguienteNodo() {
		boolean irSiguiente = false;
		if (nodoActual != null) {
			nodoActual = nodoActual.siguienteNodo();
			irSiguiente = true;
		}
		return irSiguiente;
	}

	public T obtenerDatoActual() {
		return nodoActual.getDato();
	}

	public T obtenerDatoPrimero() {
		irPrimerNodo();
		return obtenerDatoActual();
	}

	public int getLongitud() {
		return longitud;
	}

	public NodoSimple<T>obtenerNodoActual() {
		// obtener la posicion de memoria donde esta el nodo actual
		return nodoActual;
	}

	public int obtenerPosicionNodoActual() {
		return buscarPosicionNodo(nodoActual);
	}

	@Override
	public String toString() {
		String msj = "ListaEnlazada: { ";
		irPrimerNodo();
		while (nodoActual != null) {
			msj = msj + nodoActual.getNombre() + " -> ";
			nodoActual = nodoActual.siguienteNodo();
		}
		return msj + " }";
	}

	/**
	 * agrega un nodo al inicio de la lista
	 * 
	 * @param dato
	 */
	public void agregarInicio(T elemento) { // .add del arraylist
		// la lista esta vacia
		NodoSimple<T>nuevoNodoSimple= new NodoSimple<T>(elemento, elemento.toString());
		agregarInicio(nuevoNodoSimple);
	}

	public void agregarInicio(NodoSimple<T>nuevoNodo) {
		// la lista esta vacia
		if (estaVacia()) {
			agregarFinal(nuevoNodo);
		} else {
			nuevoNodo.conectar(nodoInicial);
			nodoInicial = nuevoNodo;
			nodoActual = nuevoNodo;
			longitud = longitud + 1;
		}
	}

	/**
	 * MODIFICADO agrega un nodo a la derecha del actual de la lista
	 * 
	 * @param dato
	 */
	public void agregar(T elemento) { // .add del arraylist
		NodoSimple<T>nuevoNodoSimple= new NodoSimple<T>(elemento, elemento.toString());
		agregar(nuevoNodoSimple);
	}

	/**
	 * MODIFICADO agrega un nodo a la derecha del actual de la lista
	 * 
	 * @param dato
	 */
	public void agregar(NodoSimple<T>nuevoNodo) { // .add del arraylist
		// la lista esta vacia
		if (estaVacia() || nodoActual == nodoFinal) {
			agregarFinal(nuevoNodo);
		} else {
			NodoSimple<T>nodoAuxiliar = nodoActual.siguienteNodo();
			nodoActual.conectar(nuevoNodo);
			// nuevoNodoSimple.conectar(nodoActual,1); EN CASO DE SER DOBLEMENTE ENLAZADA
			nuevoNodo.conectar(nodoAuxiliar);
			nodoActual = nuevoNodo;
			longitud = longitud + 1;
		}
	}

	/**
	 * metodo que elimina el primer nodo
	 */
	public void eliminarPrimero() {
		if (estaVacia()) {
			return; // o exception
		}
		if (longitud == 1) {
			vaciarLista();
		} else {
			irPrimerNodo(); // hace que nodoActual sea el nodoInicial
			nodoActual = nodoActual.siguienteNodo();
			nodoInicial = nodoActual;
			longitud--;
		}

	}

	/*
	 * metodo para elimninar el ultimo nodo de la lista .
	 *
	 */
	public void eliminarUltimo() {
		if (estaVacia()) {
			return; // o exception
		}

		if (longitud == 1) {
			vaciarLista();
		} else {
			
			nodoActual = buscarAnterior(nodoFinal);
			nodoFinal  = nodoActual; 
			nodoFinal.conectar(null);
			longitud--;
		}
	}
	
	public NodoSimple<T>buscarAnterior(NodoSimple<T>nodo) {
		NodoSimple<T>nodoAuxiliar = nodoInicial;
		while (nodoAuxiliar != null) {
			if (nodoAuxiliar.siguienteNodo() == nodo) {
				return nodoAuxiliar;
			} else {
				nodoAuxiliar = nodoAuxiliar.siguienteNodo();
			}
		}
		return nodoAuxiliar;
	}

	public void eliminarPosicion(int i) {
		irAPosicion(i);
		eliminarNodoActual();
	}

	/*
	 * Metodo que permite eliminar un nodo intermedio de la lista Eliminar nodo
	 * Actual
	 */

	public void irAPosicion(int posicion) {
		// Valida la posicion debe estar entre [0 y longitud)
		if( posicion < longitud && posicion >= 0 ) {
			irPrimerNodo();
			for(int i = 0 ; i < posicion ; i++)	{
				nodoActual = nodoActual.siguienteNodo();
			}
		}
	}

	public void eliminarNodoActual() {
		if (estaVacia()) {
			return; // o exception
		} else if (longitud == 1) {
			vaciarLista();
		} else if( nodoActual == nodoInicial ) {
			eliminarPrimero();
		} else if( nodoActual == nodoFinal ) {
			eliminarUltimo();
		} else {
			NodoSimple<T>nodoAnterior = buscarAnterior(nodoActual);
			nodoAnterior.conectar( nodoActual.siguienteNodo() );
			nodoActual = nodoAnterior;
			longitud--;
		}
	}

	/*
	 * Eliminar nodo que tenga un elemento x
	 */
	public void eliminarNodoElementoX(String elemento) {
		irPrimerNodo();
		for(int i = 0; i < getLongitud() ; i++ ) {
			if( obtenerDatoActual().equals(elemento) ) {
				eliminarNodoActual();
				return;
			}	
			nodoActual = nodoActual.siguienteNodo();
		}	
	}	

	/*
	 * Metodo que permnite buscar un elemento en la lista y retorna su posicion
	 */
	public int buscarElementoLista(T elemento) {
		irPrimerNodo();
		for(int i = 0; i < getLongitud() ; i++ ) {
			if( obtenerDatoActual().equals(elemento) ) {
				return i;
			}
			nodoActual = nodoActual.siguienteNodo();
		}
		return -1;
	}
	
	/*
	 * Metodo que me permite buscar la posicion de un nodo
	 */
	public int buscarPosicionNodo(NodoSimple<T>nodo) {
		if (estaVacia())
			return -1;
		else {
			NodoSimple<T>nodoAuxiliar = nodoInicial;
			boolean flag = true;
			int contador = 0;
			while (flag) {
				if (nodoAuxiliar == nodo) {
					return contador;
				} else {
					if (contador == longitud) {
						flag = false;
					} else {
						nodoAuxiliar = nodoAuxiliar.siguienteNodo();
						contador++;
					}
				}
			}
			return -1;
		}
	}

	/*
	 * Metodo que permite localizar el nodo que almacena un determinado Elemento
	 */
	public NodoSimple<T>localizarNodoDeElemento(T elemento) {
		irPrimerNodo();
		for(int i = 0; i < getLongitud() ; i++ ) {
			if( obtenerDatoActual().equals(elemento) ) {
				return nodoActual;
			}
			nodoActual = nodoActual.siguienteNodo();
		}
		return null;
	}

	/*
	 * Metodo que permite vacias la lista
	 */

	public void vaciarLista() {
		nodoActual = null;
		nodoFinal = null;
		nodoInicial = null;
		longitud = 0;
	}

	@Override
	public Iterator<T> iterator() {
		return new IteradorListaSimple<T>(nodoInicial);
	}


}
