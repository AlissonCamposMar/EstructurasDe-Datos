package co.edu.uniquindio.dulcelandia.controllers;
import co.edu.uniquindio.dulcelandia.model.Cliente;
import co.edu.uniquindio.dulcelandia.model.Tienda;

public class CrudClienteViewController {
	
	ModelFactoryController modelFactoryController;
	Tienda tienda;
	
	public CrudClienteViewController(){
		modelFactoryController = ModelFactoryController.getInstance();
		tienda = modelFactoryController.getTienda();
	}
	
	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}
	
	public String crearCliente(String nombre, String cedula, String telefono, String email) {
		// TODO Auto-generated method stub
		
		return modelFactoryController.crearCliente(nombre, cedula, telefono, email);
	}
	
	public String actualizarCliente(String nombre, String cedula, String telefono, String email){
		return modelFactoryController.actualizarCliente(nombre, cedula, telefono, email);
	}
	
	public boolean eliminarCliente(Cliente cliente ) {
		// TODO Auto-generated method stub
		return modelFactoryController.eliminarCliente(cliente );
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
