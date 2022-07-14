package co.edu.uniquindio.dulcelandia.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Venta implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList <Producto> listaProductos = new ArrayList<Producto>();
	public Venta(){
		
	}
	public ArrayList<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(ArrayList<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}
	
	
}

