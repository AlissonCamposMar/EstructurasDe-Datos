package co.edu.uniquindio.restaurante.exceptions;

import co.edu.uniquindio.restaurante.util.util;

public class CuentaNoActualizada extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CuentaNoActualizada(String mensaje){
		super(mensaje);
		util.logging("EXCEPCION LANZADA", "CUENTA NO ACTUALIZADA", "INFORMACION NO AÑADIDA");
	}	
}
