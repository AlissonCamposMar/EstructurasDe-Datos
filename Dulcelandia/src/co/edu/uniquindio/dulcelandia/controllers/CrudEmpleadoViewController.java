 package co.edu.uniquindio.dulcelandia.controllers;

import co.edu.uniquindio.dulcelandia.model.Empleado;
import co.edu.uniquindio.dulcelandia.model.Tienda;

public class CrudEmpleadoViewController {
	
	ModelFactoryController modelFactoryController;
	Tienda tienda;
	
	public CrudEmpleadoViewController(){
		modelFactoryController = ModelFactoryController.getInstance();
		tienda = modelFactoryController.getTienda();
	}
	
	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}
	
	public String crearEmpleado(String nombre, String cedula, String telefono, String email) {
		// TODO Auto-generated method stub
		
		return modelFactoryController.crearEmpleado(nombre, cedula, telefono, email);
	}
	
	public boolean eliminarEmpleado(Empleado empleado ) {
		// TODO Auto-generated method stub
		return modelFactoryController.eliminarEmpleado(empleado );
	}
	
	public String actualizarEmpleado(String nombre, String cedula, String telefono, String email){
		return modelFactoryController.actualizarEmpleado(nombre, cedula, telefono, email);
	}

	public void guardar() {
		// TODO Auto-generated method stub
		modelFactoryController.guardarResourceXML();
	}

	public void registrarAccion(String mensaje, int nivel, String accion) {
		// TODO Auto-generated method stub
		modelFactoryController.registrarAccion(mensaje, nivel, accion);
	}
	
}

