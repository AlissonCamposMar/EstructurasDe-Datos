package co.edu.uniquindio.restaurante.exceptions;

import co.edu.uniquindio.restaurante.util.util;

public class TransaccionNoActualizada extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TransaccionNoActualizada(String mensaje){
		super(mensaje);
		util.logging("EXCEPCION LANZADA", "TRANSACCION NO ACTUALIZADA", "INFORMACION NO AÑADIDA");
	}	
}
