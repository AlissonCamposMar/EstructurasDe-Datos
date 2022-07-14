package co.edu.uniquindio.dulcelandia.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Empleado extends Persona implements Serializable{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	ArrayList <Venta> listaVentas = new ArrayList<Venta>();
	public Empleado (){
		
	}
	public ArrayList<Venta> getListaVentas() {
		return listaVentas;
	}

	public void setListaVentas(ArrayList<Venta> listaVentas) {
		this.listaVentas = listaVentas;
	}
}