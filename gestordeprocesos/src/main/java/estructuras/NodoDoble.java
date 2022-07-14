package estructuras;
/**
 * @author Brahiam David Tabares Vallejo
 * @author Sandra Milena Quintero Leal
 * @author Tatiana Arboleda Martinez
 */
public class NodoDoble<T> {
	private T dato;
	private String nombre;
	private NodoDoble<T> nodoSiguiente; 
	private NodoDoble<T> nodoAnterior; 
	
	public NodoDoble(T dato, String nombre) {
		super();
		this.dato = dato;
		this.nombre = nombre;
		nodoSiguiente = null;
		nodoAnterior = null;
	}
	
	public void conectar(NodoDoble<T> nodo) {
		// si la posicion indice existe en el vector de enlaces
		nodoSiguiente = nodo;		
	}

	public void conectarAnterior(NodoDoble<T> nodo) {
		// si la posicion indice existe en el vector de enlaces
		nodoAnterior = nodo;		
	}

	
	
	public NodoDoble<T> siguienteNodo() {
		return nodoSiguiente;
	}

	public NodoDoble<T> anteriorNodo() {
		return nodoAnterior;
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
