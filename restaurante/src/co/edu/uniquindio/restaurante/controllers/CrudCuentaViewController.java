package co.edu.uniquindio.restaurante.controllers;

import co.edu.uniquindio.restaurante.model.Cuenta;
import co.edu.uniquindio.restaurante.model.Empleado;
import co.edu.uniquindio.restaurante.model.Restaurante;

public class CrudCuentaViewController {
	
	ModelFactoryController modelFactoryController;
	Restaurante restaurante;
	
	public CrudCuentaViewController() {
		modelFactoryController = ModelFactoryController.getInstance();
		restaurante = modelFactoryController.getRestaurante();
	}
	
	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}
	
	public String crearCuenta(String total, String id, String cliente, String empleado) {
		return modelFactoryController.crearCuenta(total, id, cliente, empleado) ;
	}

	public Boolean eliminarCuenta(Cuenta cuentaSeleccionada) {
		return modelFactoryController.eliminarCuenta(cuentaSeleccionada);
	}
	
	public Boolean actualizarCuenta(Cuenta cuentaSeleccionada, Cuenta nuevaCuenta) {
		return modelFactoryController.actualizarCuenta(cuentaSeleccionada, nuevaCuenta);
	}
}
