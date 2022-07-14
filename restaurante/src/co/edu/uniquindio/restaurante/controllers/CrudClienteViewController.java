package co.edu.uniquindio.restaurante.controllers;

import co.edu.uniquindio.restaurante.model.Cliente;
import co.edu.uniquindio.restaurante.model.Restaurante;

public class CrudClienteViewController {
	
	ModelFactoryController modelFactoryController;
	Restaurante restaurante;
	
	public CrudClienteViewController() {
		modelFactoryController = ModelFactoryController.getInstance();
		restaurante = modelFactoryController.getRestaurante();
	}
	
	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}
	
	public String crearCliente(String nombre, String apellido, String cedula, String edad) {
		return modelFactoryController.crearCliente(nombre, apellido, cedula, edad) ;
	}

	public Boolean eliminarCliente(Cliente empleadoCliente) {
		return modelFactoryController.eliminarCliente(empleadoCliente);
	}
	
	public Boolean actualizarCliente(Cliente cliente, Cliente nuevoCliente) {
		return modelFactoryController.actualizarCliente(cliente, nuevoCliente);
	}
}
