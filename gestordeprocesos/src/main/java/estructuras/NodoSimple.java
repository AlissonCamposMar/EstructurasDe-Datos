package estructuras;
/**
 * @author Brahiam David Tabares Vallejo
 * @author Sandra Milena Quintero Leal
 * @author Tatiana Arboleda Martinez
 */
public class NodoSimple<T> {
	private T dato;
	private String nombre;
	private NodoSimple<T> nodoSiguiente; //Nodo siguiente;
	
	public NodoSimple(T dato, String nombre) {
		super();
		this.dato = dato;
		this.nombre = nombre;
		nodoSiguiente = null;
	}
	
	public void conectar(NodoSimple<T> nodo) {
		// si la posicion indice existe en el vector de enlaces
		
		nodoSiguiente = nodo;		
	}

	public NodoSimple<T> siguienteNodo() {
		return nodoSiguiente;
	}

	public T getDato() {
		return dato;
	}

	public void setDato(T dato) {
		this.dato = dato;
	}

	public String getNombre() {
		return nombre;
	}
	
}
