package co.edu.uniquindio.restaurante.exceptions;

import co.edu.uniquindio.restaurante.util.util;

public class EmpleadoNoEliminado extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmpleadoNoEliminado(String mensaje){
		super(mensaje);
		util.logging("EXCEPCION LANZADA", "EMPLEADO NO ELIMINADO", "INFORMACION NO AÑADIDA");
	}	
}
