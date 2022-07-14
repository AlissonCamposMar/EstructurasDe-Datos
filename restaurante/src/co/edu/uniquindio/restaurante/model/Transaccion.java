package co.edu.uniquindio.restaurante.model;

import java.io.Serializable;

public class Transaccion implements Serializable{	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String clienteAsociado;
	private String empleadoAsociado;
	private String id;
	//private EstadoTransaccion tipo;
	private String tipo;
	
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}	
}
