package co.edu.uniquindio.restaurante.controllers;


import co.edu.uniquindio.restaurante.model.Restaurante;
import co.edu.uniquindio.restaurante.model.Transaccion;

public class CrudTransaccionViewController {
	
	ModelFactoryController modelFactoryController;
	Restaurante restaurante;
	
	public CrudTransaccionViewController() {
		modelFactoryController = ModelFactoryController.getInstance();
		restaurante = modelFactoryController.getRestaurante();
	}
	
	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}
	
	public String crearTransaccion(String estado, String id, String cliente, String empleado) {
		return modelFactoryController.crearTransaccion(estado, id, cliente, empleado) ;
	}

	public Boolean eliminarTransaccion(Transaccion transaccionSeleccionada) {
		return modelFactoryController.eliminarTransaccion(transaccionSeleccionada);
	}
	
	public Boolean actualizarTransaccion(Transaccion transaccionSeleccionada, Transaccion nuevaTransaccion) {
		return modelFactoryController.actualizarTransaccion(transaccionSeleccionada, nuevaTransaccion);
	}
}
