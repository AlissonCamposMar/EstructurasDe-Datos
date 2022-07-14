package co.edu.uniquindio.restaurante.exceptions;

import co.edu.uniquindio.restaurante.util.util;

public class TransaccionNoCreada extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TransaccionNoCreada(String mensaje){
		super(mensaje);
		util.logging("EXCEPCION LANZADA", "TRANSACCION NO CREADA", "INFORMACION NO AÑADIDA");
	}	
}
