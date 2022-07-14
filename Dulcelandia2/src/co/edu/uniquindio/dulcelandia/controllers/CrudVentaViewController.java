package co.edu.uniquindio.dulcelandia.controllers;

import co.edu.uniquindio.dulcelandia.model.Tienda;
import co.edu.uniquindio.dulcelandia.model.Venta;
import co.edu.uniquindio.dulcelandia.model.Cliente;
import co.edu.uniquindio.dulcelandia.model.Producto;
import co.edu.uniquindio.dulcelandia.model.Proveedor;;

public class CrudVentaViewController {
	ModelFactoryController modelFactoryController;
	Tienda tienda;


	public CrudVentaViewController(){
		modelFactoryController = ModelFactoryController.getInstance();
		tienda = modelFactoryController.getTienda();
	}

	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}

//	public ModelFactoryController getModelFactoryController(){
//		return modelFactoryController;
//	}

//	public void crearVenta(Cliente cliente, Producto producto, Proveedor proveedor){
//		modelFactoryController.crearVenta(cliente, producto, proveedor);
//
//	}
}
