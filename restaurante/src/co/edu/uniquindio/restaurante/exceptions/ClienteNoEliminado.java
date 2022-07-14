package co.edu.uniquindio.restaurante.exceptions;

import co.edu.uniquindio.restaurante.util.util;

public class ClienteNoEliminado extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClienteNoEliminado(String mensaje){
		super(mensaje);
		util.logging("EXCEPCION LANZADA", "CLIENTE NO ELIMINADO", "INFORMACION NO AÑADIDA");
	}	
}
