package co.edu.uniquindio.restaurante.exceptions;

import co.edu.uniquindio.restaurante.util.util;

public class TransaccionNoEliminada extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TransaccionNoEliminada(String mensaje){
		super(mensaje);
		util.logging("EXCEPCION LANZADA", "TRANSACCION NO ELIMINADA", "INFORMACION NO AÑADIDA");
	}	
}
