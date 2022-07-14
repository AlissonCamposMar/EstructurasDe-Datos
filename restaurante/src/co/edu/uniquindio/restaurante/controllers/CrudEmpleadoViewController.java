package co.edu.uniquindio.restaurante.controllers;

import co.edu.uniquindio.restaurante.model.Empleado;
import co.edu.uniquindio.restaurante.model.Restaurante;

public class CrudEmpleadoViewController {
	
	ModelFactoryController modelFactoryController;
	Restaurante restaurante;
	
	public CrudEmpleadoViewController() {
		modelFactoryController = ModelFactoryController.getInstance();
		restaurante = modelFactoryController.getRestaurante();
	}
	
	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}
	
	public String crearEmpleado(String nombre, String apellido, String cedula, String edad) {
		return modelFactoryController.crearEmpleado(nombre, apellido, cedula, edad) ;
	}

	public Boolean eliminarEmpleado(Empleado empleadoSeleccionado) {
		return modelFactoryController.eliminarEmpleado(empleadoSeleccionado);
	}
	
	public Boolean actualizarEmpleado(Empleado empleado, Empleado nuevoEmpleado) {
		return modelFactoryController.actualizarEmpleado(empleado, nuevoEmpleado);
	}
}
