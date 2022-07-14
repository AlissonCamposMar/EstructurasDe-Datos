package co.edu.uniquindio.dulcelandia.model;

import java.io.Serializable;

public class Proveedor extends Persona implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String nompreProducto;
	private String precioProducto;
	private String cantidadProducto;
//	private String nombreProveedor;
//
//	public String getNombreProveedor(){
//		return nombreProveedor;
//	}
//	public void setNombreProveedor(String nombreProveedor){
//		this.nombreProveedor = nombreProveedor;
//	}
//
//	public Proveedor(String nombreProveedor){
//		this.nombreProveedor = nombreProveedor;
//	}


	public String getNombreProducto() {
		return nompreProducto;
	}
	public void setNombreProducto(String nompreProducto) {
		this.nompreProducto = nompreProducto;
	}
	public String getPrecioProducto() {
		return precioProducto;
	}
	public void setPrecioProducto(String precioProducto) {
		this.precioProducto = precioProducto;
	}
	public String getCantidadProducto() {
		return cantidadProducto;
	}
	public void setCantidadProducto(String cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}


}
