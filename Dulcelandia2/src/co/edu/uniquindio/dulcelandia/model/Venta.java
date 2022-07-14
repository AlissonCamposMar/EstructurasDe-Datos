package co.edu.uniquindio.dulcelandia.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Venta implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String estadoVenta;
	private String clienteVenta;
	private String empleadoVenta;
	private String numVenta;
	public String getClienteVenta() {
		return clienteVenta;
	}
	public void setClienteVenta(String clienteVenta) {
		this.clienteVenta = clienteVenta;
	}
	public String getEmpleadoVenta() {
		return empleadoVenta;
	}
	public void setEmpleadoVenta(String empleadoVenta) {
		this.empleadoVenta = empleadoVenta;
	}
	public String getNumVenta() {
		return numVenta;
	}
	public void setNumVenta(String numVenta) {
		this.numVenta = numVenta;
	}
	public Venta(){
		
	}
	public String getEstadoVenta() {
		return estadoVenta;
	}
	public void setEstadoVenta(String estadoVenta) {
		this.estadoVenta = estadoVenta;
	}
	
	
}

