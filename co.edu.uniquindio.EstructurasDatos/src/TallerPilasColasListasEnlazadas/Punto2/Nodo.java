package TallerPilasColasListasEnlazadas.Punto2;

/**
 * Clase que define los elementos que debe tener un Nodo de la lista.
 */
public class Nodo {

    // Variable para enlazar los nodos.
    private Nodo siguienteNodo;
    // Variable en la cual se va a guardar el valor.
    private int valorNodo;
    
	/**
	 * Constructor de la clase Nodo
	 * @param dato Elemento que se guarda en el Nodo
	 */
	public void Nodo() {
		this.valorNodo = 0;
		this.siguienteNodo = null;
	}

	public void verNodo() {
		System.out.print("["+ valorNodo +"]");
	}
    
	//Metodos get y set de la clase Nodo
	
	public Nodo getSiguienteNodo() {
		return siguienteNodo;
	}
	public void setSiguienteNodo(Nodo siguienteNodo) {
		this.siguienteNodo = siguienteNodo;
		
	}
    
	public int getValorNodo() {
		return valorNodo;
	}
	public void setValorNodo(int valorNodo) {
		this.valorNodo = valorNodo;
	}
    
	public String toString() {
		return "Nodo [valorNodo=" + valorNodo + ", siguienteNodo=" + siguienteNodo + "]";
	}
}