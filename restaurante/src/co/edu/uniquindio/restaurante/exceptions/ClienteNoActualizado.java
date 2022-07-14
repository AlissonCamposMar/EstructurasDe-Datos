package co.edu.uniquindio.restaurante.exceptions;

import co.edu.uniquindio.restaurante.util.util;

public class ClienteNoActualizado extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClienteNoActualizado(String mensaje){
		super(mensaje);
		util.logging("EXCEPCION LANZADA", "CLIENTE NO ACTUALIZADO", "INFORMACION NO AÑADIDA");
	}	
}
