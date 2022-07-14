package co.edu.uniquindio.dulcelandia.model;

import java.io.Serializable;

import co.edu.uniquindio.dulcelandia.exceptions.SinExistenciasDelProductoException;

public class Producto implements Serializable{
	private String nombreProducto;
	private String codigo;
	private String precio;
	private String cantidadExistente;
//	private Proveedor proveedor;
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public Producto(){

	}

//	@Override
//	public String toString() {
//		return "Juguete [codigo=" + codigo + ", nombre=" + nombreProducto + ", precio=" + precio
//				+ ", cantidadExistencias=" + cantidadExistente + ", proveedor=" + proveedor + "]";
//	}

//	/**
//	 * @return the proveedor
//	 */
//	public Proveedor getProveedor() {
//		return proveedor;
//	}
//	/**
//	 * @param proveedor the proveedor to set
//	 */
//	public void setProveedor(Proveedor proveedor) {
//		this.proveedor = proveedor;
//	}
//
//	/**
//	 * @param proveedor the proveedor to set
//	 */
//	public void setProveedor(String nombreProveedor) {
//		this.proveedor = new Proveedor(nombreProveedor);
//	}


	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	public String getCantidadExistente() {
		return cantidadExistente;
	}
	public void setCantidadExistente(String cantidadExistente) {
		this.cantidadExistente = cantidadExistente;
	}


//	/**
//	 * Permite aumentar existencias
//	 * @param cantidad La cantidad a aumentar
//	 */
//	public void aumentarExistencias(int cantidad) {
//		cantidadExistente+=cantidad;
//	}
//
//	/**
//	 * Permite disminuir existencias
//	 * @param cantidad La cantidad a disminuir
//	 * @throws SinExistenciasDelProductoException
//	 */
//	public void disminuirExistencias(int cantidad) throws SinExistenciasDelProductoException
//	{
//		int cantidadExistencia = Integer.parseInt(cantidadExistente);
//		if(cantidadExistencia-cantidad>=0)
//		 {cantidadExistencia-=cantidad;}
//		else
//		{
//			throw new SinExistenciasDelProductoException("No hay existencias del producto para realizar la venta");
//		}
//	}
}
