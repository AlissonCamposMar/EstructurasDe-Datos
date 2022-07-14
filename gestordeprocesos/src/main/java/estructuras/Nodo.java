package estructuras;
/**
 * @author Brahiam David Tabares Vallejo
 * @author Sandra Milena Quintero Leal
 * @author Tatiana Arboleda Martinez
 */
import java.util.ArrayList;

public class Nodo {
	private String dato;
	private String nombre;
	private ArrayList<Nodo> enlaces; //Nodo siguiente;
	
	public Nodo(String dato, String nombre) {
		super();
		this.dato = dato;
		this.nombre = nombre;
		this.enlaces = new ArrayList<>();
		this.enlaces.add(null);
	}
	
	public void conectar(Nodo nodo, int indice) {
		// si la posicion indice existe en el vector de enlaces
		
				if (indice < enlaces.size()) {
					enlaces.set(indice, nodo);
				} else {
					int n = indice - enlaces.size();

					// la lista de enlaces crece. En las posiciones intermedias
					// se asigna null
					for (int i = 0; i < n; i++) {
						enlaces.add(null);
					}
					
						
					// en la posici�n indice del vector enlaces se almacena
					// una referencia al nodo destino
					enlaces.add(nodo);
				}
	}

	public Nodo siguienteNodo() {
		return enlaces.get(0);
	}

	public String getDato() {
		return dato;
	}

	public void setDato(String dato) {
		this.dato = dato;
	}

	public String getNombre() {
		// TODO Auto-generated method stub
		return nombre;
	}
	
}
