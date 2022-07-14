package co.edu.uniquindio.restaurante.model;

import java.io.Serializable;

public class Cuenta implements Serializable {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String clienteAsociado;
	private String empleadoAsociado;
	private String total;
	//private EstadoTransaccion tipo;
	private String id;
	
	public String getClienteAsociado() {
		return clienteAsociado;
	}
	public void setClienteAsociado(String clienteAsociado) {
		this.clienteAsociado = clienteAsociado;
	}
	public String getEmpleadoAsociado() {
		return empleadoAsociado;
	}
	public void setEmpleadoAsociado(String empleadoAsociado) {
		this.empleadoAsociado = empleadoAsociado;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
