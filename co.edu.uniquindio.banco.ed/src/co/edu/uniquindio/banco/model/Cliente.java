package co.edu.uniquindio.banco.model;
import java.io.Serializable;

public class Cliente extends Persona implements Serializable{

	
	@Override
	public String toString() {
		return "Cliente [getCedula()=" + getCedula() + ", getNombre()=" + getNombre() + ", getApellido()="
				+ getApellido() + ", getDireccion()=" + getDireccion() + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Cliente() {
		
	}


}
