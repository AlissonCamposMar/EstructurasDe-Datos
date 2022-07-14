package co.edu.uniquindio.restaurante.exceptions;

import co.edu.uniquindio.restaurante.util.util;

public class ClienteNoCreado extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClienteNoCreado(String mensaje){
		super(mensaje);
		util.logging("EXCEPCION LANZADA", "CLIENTE NO CREADO", "INFORMACION NO AÑADIDA");
	}	
}
