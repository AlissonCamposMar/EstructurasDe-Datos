package co.edu.uniquindio.restaurante.exceptions;

import co.edu.uniquindio.restaurante.util.util;

public class EmpleadoNoCreado extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmpleadoNoCreado(String mensaje){
		super(mensaje);
		util.logging("EXCEPCION LANZADA", "EMPLEADO NO CREADO", "INFORMACION NO AÑADIDA");
	}	
}
