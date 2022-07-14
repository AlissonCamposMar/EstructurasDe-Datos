package estructuras;
/**
 * @author Brahiam David Tabares Vallejo
 * @author Sandra Milena Quintero Leal
 * @author Tatiana Arboleda Martinez
 */
public class ListaEnlazadaAnterior {

	private Nodo nodoInicial; // referencia al primer nodo
	private Nodo nodoFinal; // referencia al ultimo nodo
	private Nodo nodoActual; // referencia al nodo actual (en el que estoy ubicado actualmente)
	private int longitud; // cuantos nodos hay

	public ListaEnlazadaAnterior() {
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
	public void agregarFinal(Nodo nuevoNodo) {
		// la lista esta vacia
		nodoActual = nuevoNodo;
		if (longitud == 0) {
			nodoInicial = nuevoNodo;
			nodoFinal = nuevoNodo;
		} else {
			nodoFinal.conectar(nodoActual, 0);
			nodoFinal = nodoActual;
		}

		// la ista tiene al menos un elemento
		longitud = longitud + 1;
	}

	/**
	 * MODIFICADO agrega un nodo al final de la lista
	 * 
	 * @param elemento
	 */
	public void agregarFinal(String elemento) { // .add del arraylist
		// la lista esta vacia
		Nodo nuevoNodo = new Nodo(elemento, elemento);
		agregarFinal(nuevoNodo);
	}

	public boolean estaVacia() {
		return longitud == 0;
	}

	/**
	 * agrega un nodo al inicio de la lista
	 * 
	 * @param dato
	 */
	public void agregarInicio(String elemento) { // .add del arraylist
		// la lista esta vacia
		Nodo nuevoNodo = new Nodo(elemento, elemento);
		agregarInicio(nuevoNodo);
	}

	public void agregarInicio(Nodo nuevoNodo) {
		// la lista esta vacia
		if (estaVacia()) {
			agregarFinal(nuevoNodo);
		} else {
			nuevoNodo.conectar(nodoInicial, 0);
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
	public void agregar(String elemento) { // .add del arraylist
		Nodo nuevoNodo = new Nodo(elemento, elemento);
		agregar(nuevoNodo);
	}

	/**
	 * MODIFICADO agrega un nodo a la derecha del actual de la lista
	 * 
	 * @param dato
	 */
	public void agregar(Nodo nuevoNodo) { // .add del arraylist
		// la lista esta vacia
		if (estaVacia()) {
			agregarInicio(nuevoNodo);
		} else if (nodoActual == nodoFinal) {
			agregarFinal(nuevoNodo);
		} else {
			Nodo nodoAuxiliar = nodoActual.siguienteNodo();
			nodoActual.conectar(nuevoNodo, 0);
			// nuevoNodo.conectar(nodoActual,1); EN CASO DE SER DOBLEMENTE ENLAZADA
			nuevoNodo.conectar(nodoAuxiliar, 0);
			nodoActual = nuevoNodo;
			longitud = longitud + 1;
		}
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

	public String obtenerDatoActual() {
		return nodoActual.getDato();
	}

	public String obtenerDatoPrimero() {
		irPrimerNodo();
		return obtenerDatoActual();
	}

	public int getLongitud() {
		return longitud;
	}

	@Override
	public String toString() {
		String msj = "ListaEnlazada: { ";
		irPrimerNodo();
		while (nodoActual != null) {
			msj = msj + nodoActual.getNombre() + " -> ";
			nodoActual = nodoActual.siguienteNodo();
		}
		return msj + "Final";
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
			irPrimerNodo();
			boolean flag = true;
			while (flag) {
				if (nodoActual.siguienteNodo() == nodoFinal) {
					nodoFinal = nodoActual;
					nodoFinal.conectar(null, 0);
					longitud--;
					flag = false;
				} else {
					nodoActual = nodoActual.siguienteNodo();
				}
			}
		}
	}
	/*
	 * Metodo que permite eliminar un nodo intermedio de la lista
	 */

	public void eliminarIntermedio() {
		if (longitud == 0) {
			return; // o exception
		} else if (longitud == 1) {
			vaciarLista();
		} else {
			Nodo nodoAux = nodoActual;
			Nodo nodoEnlace;
			nodoEnlace = nodoActual.siguienteNodo();
			nodoAux.conectar(nodoEnlace.siguienteNodo(), 0);
			longitud--;

		}
	}

	/*
	 * Eliminar nodo que tenga un elemento x
	 */
	public void eliminarNodoElementoX(String dato) {
		int contador = 1;
		if (longitud == 0) {
			return; // o exception
		} else {
			boolean flag = true;
			irPrimerNodo();
			while (flag) {
				if ((contador == 1) && (nodoActual.getDato().equals(dato))) {
					eliminarPrimero();
					longitud--;
					flag = false;
				} else if ((contador == longitud) && (nodoActual.getDato().equals(dato))) {
					eliminarUltimo();
					longitud--;
					flag = false;
				} else if (nodoActual.getDato().equals(dato)) {
					Nodo nodoAux = nodoActual;
					boolean centinela = true;
					irPrimerNodo();
					while (centinela) {
						if (nodoActual.siguienteNodo() == nodoAux) {
							nodoActual.conectar(nodoAux.siguienteNodo(), 0);
							longitud--;
							centinela = false;
							flag = false;
						} else {
							nodoActual = nodoActual.siguienteNodo();
						}
					}
				} else {
					if (contador == longitud) {
						System.out.println("El dato no existe en la lista");
						flag = false;
					} else {
						nodoActual = nodoActual.siguienteNodo();
						contador++;
					}
				}

			}

		}
	}

	/*
	 * Metodo que permnite buscar un elemento en la lista y retorna su posicion
	 */
	public int buscarElementoLista(String elemento) {
		int contador = 1;
		irPrimerNodo();
		if (longitud == 0) {
			System.out.println("la lista esta vacia");
			return 0;
		} else {
			boolean flag = true;
			while (flag) {
				if (nodoActual.getDato().equals(elemento))
					return contador;
				else {
					if (contador == longitud) {
						flag = false;
					} else {
						nodoActual = nodoActual.siguienteNodo();
						contador++;
					}
				}
			}
		}
		return 0;
	}

	/*
	 * Metodo que permite localizar un nodo , retornado su posicion
	 */
	public Nodo localizarNodo(String dato) {
		int contador = 1;
		irPrimerNodo();
		if (longitud == 0) {
			System.out.println("la lista esta vacia");
			return nodoActual;
		} else {
			boolean flag = true;
			while (flag) {
				if (nodoActual.getDato().equals(dato))
					return nodoActual;
				else {
					if (contador == longitud) {
						flag = false;
					} else {
						nodoActual = nodoActual.siguienteNodo();
						contador++;
					}
				}
			}
		}
		return null;
	}

	/*
	 * Metodo que me permite buscar la posicion de un nodo
	 */
	public int buscarPosicionNodo(Nodo nodo) {
		int contador = 1;
		irPrimerNodo();
		if (longitud == 0)
			return 0;
		else {
			boolean flag = true;
			while (flag) {
				if (nodoActual == nodo) {
					return contador;
				} else {
					if (contador == longitud) {
						flag = false;
					} else {
						nodoActual = nodoActual.siguienteNodo();
						contador++;
					}
				}

			}
		}
		return 0;
	}
	/*
	 * Metodo que permite vacias la lista
	 */

	public void vaciarLista() {
//		irPrimerNodo();
//		while (longitud != 0) {
//			eliminarPrimero();
//		}
		nodoActual = null;
		nodoFinal = null;
		nodoInicial = null;
		longitud = 0; 
	}

}
