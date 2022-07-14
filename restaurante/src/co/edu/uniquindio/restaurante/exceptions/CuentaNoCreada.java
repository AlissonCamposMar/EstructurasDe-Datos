package co.edu.uniquindio.restaurante.exceptions;

import co.edu.uniquindio.restaurante.util.util;

public class CuentaNoCreada extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CuentaNoCreada(String mensaje){
		super(mensaje);
		util.logging("EXCEPCION LANZADA", "CUENTA NO CREADA", "INFORMACION NO AÑADIDA");
	}	
}
