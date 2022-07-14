package co.edu.uniquindio.dulcelandia.controllers;

import co.edu.uniquindio.dulcelandia.model.Producto;
import co.edu.uniquindio.dulcelandia.model.Tienda;

public class CrudProductoViewController {

	ModelFactoryController modelFactoryController;
	Tienda tienda;

	public CrudProductoViewController() {
		modelFactoryController = ModelFactoryController.getInstance();
		tienda = modelFactoryController.getTienda();
	}

	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}

	public String crearProducto(String nombreProducto, String codigo, String precio, String cantidadExistencias) {
		// TODO Auto-generated method stub

		return modelFactoryController.crearProducto(nombreProducto, codigo, precio, cantidadExistencias);

	}

	public String actualizarProducto(String nombreProducto, String codigo, String precio, String cantidadExistencias) {
		return modelFactoryController.actualizarProducto(nombreProducto, codigo, precio, cantidadExistencias);
	}

	public boolean eliminarProducto(Producto producto) {
		// TODO Auto-generated method stub
		return modelFactoryController.eliminarProducto(producto);
	}

	public void guardar() {

		modelFactoryController.guardarResourceXML();
	}

	public void registrarAccion(String mensaje, int nivel, String accion) {

		modelFactoryController.registrarAccion(mensaje, nivel, accion);
	}

}
