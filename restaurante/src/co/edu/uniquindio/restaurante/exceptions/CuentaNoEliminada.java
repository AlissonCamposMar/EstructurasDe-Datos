package co.edu.uniquindio.restaurante.exceptions;

import co.edu.uniquindio.restaurante.util.util;

public class CuentaNoEliminada extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CuentaNoEliminada(String mensaje){
		super(mensaje);
		util.logging("EXCEPCION LANZADA", "CUENTA NO ELIMINADA", "INFORMACION NO AÑADIDA");
	}	
}
