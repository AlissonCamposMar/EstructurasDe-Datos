package generics.ejemplos;

public class Elemento<T> {
	
	T dato;
	
	
	public Elemento() {
		
	}


	public T getDato() {
		return dato;
	}


	public void setDato(T dato) {
		this.dato = dato;
	}
	
}
