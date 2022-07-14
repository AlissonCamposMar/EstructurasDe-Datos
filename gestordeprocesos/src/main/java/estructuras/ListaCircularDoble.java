package estructuras;

import java.util.Iterator;

/**
 * @author Brahiam David Tabares Vallejo
 * @author Sandra Milena Quintero Leal
 * @author Tatiana Arboleda Martinez
 */
public class ListaCircularDoble<T> implements Iterable<T>{

	private NodoDoble<T> nodoInicial; // referencia al primer nodo
	private NodoDoble<T> nodoFinal; // referencia al ultimo nodo
	private NodoDoble<T> nodoActual; // referencia al nodo actual (en el que estoy ubicado actualmente)
	private int longitud; // cuantos nodos hay

	public ListaCircularDoble() {
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
	public void agregarFinal(NodoDoble<T> nuevoNodo) {
		// la lista esta vacia
		nodoActual = nuevoNodo;
		if (estaVacia()) {
			nodoInicial = nuevoNodo;
			nodoFinal = nuevoNodo;
		} else {
			nodoFinal.conectar(nuevoNodo);
			nuevoNodo.conectarAnterior(nodoFinal);
			nodoFinal = nuevoNodo;
		}
		nodoInicial.conectarAnterior(nodoFinal);
		nodoFinal.conectar(nodoInicial);
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
		NodoDoble<T> nuevoNodo = new NodoDoble<T>(elemento, elemento.toString());
		agregarFinal(nuevoNodo);
	}

	/**
	 * ubicarse en el primer nodo de la lista
	 */
	public void irPrimerNodo() {
		nodoActual = nodoInicial;
	}

	/**
	 * ubicarse en el �ltomo nodo de la lista
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

	/**
	 * MODIFICADO-----------------------------------------------------------------------------------------
	 * ubicarse en siguiente nodo de la lista, si esta en el ultimo retornara null
	 */
	public boolean irAnteriorNodo() {
		if (nodoActual != null) {
			nodoActual = nodoActual.anteriorNodo();
			return true;
		} else {
			return false;
		}
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

	public NodoDoble<T> obtenerNodoActual() {
		// obtener la posicion de memoria donde esta el nodo actual
		return nodoActual;
	}

	public int obtenerPosicionNodoActual() {
		return buscarPosicionNodo(nodoActual);
	}

	@Override
	public String toString() {
		String msj = "ListaDoble: { ";
		irPrimerNodo();
		for(int i = 0 ; i < getLongitud() ; i++) {
			msj = msj + nodoActual.getDato() + " -> ";
			nodoActual = nodoActual.siguienteNodo();
		}
		return msj + "}";
	}

	/**
	 * agrega un nodo al inicio de la lista
	 * 
	 * @param dato
	 */
	public void agregarInicio(T elemento) { // .add del arraylist
		// la lista esta vacia
		NodoDoble<T> nuevoNodoDoble = new NodoDoble<T>(elemento, elemento.toString());
		agregarInicio(nuevoNodoDoble);
	}

	public void agregarInicio(NodoDoble<T> nuevoNodo) {
		// la lista esta vacia
		if (estaVacia()) {
			agregarFinal(nuevoNodo);
		} else {
			nuevoNodo.conectar(nodoInicial);
			nodoInicial.conectarAnterior(nuevoNodo);
			nodoInicial = nuevoNodo;
			nodoActual = nuevoNodo;
			nodoInicial.conectarAnterior(nodoFinal);
			nodoFinal.conectar(nodoInicial);
			longitud = longitud + 1;
		}
	}

	/**
	 * MODIFICADO agrega un nodo a la derecha del actual de la lista
	 * 
	 * @param dato
	 */
	public void agregar(T elemento) { // .add del arraylist
		NodoDoble<T> nuevoNodo = new NodoDoble<T>(elemento, elemento.toString());
		agregar(nuevoNodo);
	}

	/**
	 * MODIFICADO agrega un nodo a la derecha del actual de la lista
	 * 
	 * @param dato
	 */
	public void agregar(NodoDoble<T> nuevoNodo) { // .add del arraylist
		// la lista esta vacia
		if (estaVacia() || nodoActual == nodoFinal) {
			agregarFinal(nuevoNodo);
		} else {
			NodoDoble<T> nodoAuxiliar = nodoActual.siguienteNodo();
			nodoActual.conectar(nuevoNodo);
			nuevoNodo.conectarAnterior(nodoActual);
			// nuevoNodoDoble.conectar(nodoActual,1); EN CASO DE SER DOBLEMENTE ENLAZADA
			nuevoNodo.conectar(nodoAuxiliar);
			nodoAuxiliar.conectarAnterior(nuevoNodo);
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
			nodoInicial.conectarAnterior(nodoFinal);
			nodoFinal.conectar(nodoInicial);
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
			nodoActual = nodoFinal.anteriorNodo();
			nodoFinal  = nodoActual; 
			nodoFinal.conectar(nodoInicial);
			nodoInicial.conectarAnterior(nodoFinal);
			longitud--;
		}
	}
	
	public void eliminarPosicion(int i) {
		irAPosicion(i);
		eliminarNodoActual();
	}

	/*
	 * Metodo que permite eliminar un nodo intermedio de la lista Eliminar nodo
	 * Actual
	 */

	private void irAPosicion(int posicion) {
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
			NodoDoble<T> nodoAnterior = nodoActual.anteriorNodo();
			NodoDoble<T> nodoSiguiente = nodoActual.siguienteNodo();
			nodoAnterior.conectar( nodoSiguiente );
			nodoSiguiente.conectarAnterior(nodoAnterior);
			nodoActual = nodoAnterior;
			longitud--;
		}
	}

	/*
	 * Eliminar nodo que tenga un elemento x
	 */
	public void eliminarNodoElementoX(T elemento) {
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
	public int buscarPosicionNodo(NodoDoble<T> nodo) {
		NodoDoble<T> nodoAuxiliar = nodoInicial;

		for (int i = 0; i < getLongitud(); i++) {
			if (nodoAuxiliar == nodo) {
				return i;
			}
			nodoAuxiliar = nodoAuxiliar.siguienteNodo();
		}

		return -1;
	}

	/*
	 * Metodo que permite localizar el nodo que almacena un determinado Elemento
	 */
	public NodoDoble<T> localizarNodoDeElemento(T elemento) {
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
		return new IteradorListaCircularDoble<T>(nodoInicial);
	}


}
