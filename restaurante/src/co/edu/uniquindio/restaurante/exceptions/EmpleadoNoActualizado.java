package co.edu.uniquindio.restaurante.exceptions;

import co.edu.uniquindio.restaurante.util.util;

public class EmpleadoNoActualizado extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmpleadoNoActualizado(String mensaje){
		super(mensaje);
		util.logging("EXCEPCION LANZADA", "EMPLEADO NO ACTUALIZADO", "INFORMACION NO AÑADIDA");
	}	
}
