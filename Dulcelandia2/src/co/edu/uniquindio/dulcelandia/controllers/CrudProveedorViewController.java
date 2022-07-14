package co.edu.uniquindio.dulcelandia.controllers;
import co.edu.uniquindio.dulcelandia.model.Proveedor;
import co.edu.uniquindio.dulcelandia.model.Tienda;

public class CrudProveedorViewController {
	ModelFactoryController modelFactoryController;
	Tienda tienda;
	
	public CrudProveedorViewController(){
		modelFactoryController = ModelFactoryController.getInstance();
		tienda = modelFactoryController.getTienda();
	}

	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}
	
	public String crearProveedor(String nombre, String cedula, String telefono, String email) {
		// TODO Auto-generated method stub
		
		return modelFactoryController.crearProveedor(nombre, cedula, telefono, email);
	}
	
	public boolean eliminarProveedor(Proveedor proveedor ) {
		// TODO Auto-generated method stub
		return modelFactoryController.eliminarProveedor(proveedor);
	}
	
	public String actualizarProveedor(String nombre, String cedula, String telefono, String email){
		return modelFactoryController.actualizarProveedor(nombre, cedula, telefono, email);
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
