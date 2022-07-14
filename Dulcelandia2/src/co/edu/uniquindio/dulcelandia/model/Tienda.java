
package co.edu.uniquindio.dulcelandia.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import co.edu.uniquindio.dulcelandia.exceptions.ActualizarClienteException;
import co.edu.uniquindio.dulcelandia.exceptions.ActualizarEmpleadoException;
import co.edu.uniquindio.dulcelandia.exceptions.ActualizarProductoException;
import co.edu.uniquindio.dulcelandia.exceptions.ActualizarProveedorException;
import co.edu.uniquindio.dulcelandia.exceptions.ActualizarVentaException;
import co.edu.uniquindio.dulcelandia.exceptions.ClienteException;
import co.edu.uniquindio.dulcelandia.exceptions.EmpleadoException;
import co.edu.uniquindio.dulcelandia.exceptions.ProductoException;
import co.edu.uniquindio.dulcelandia.exceptions.ProveedorException;
import co.edu.uniquindio.dulcelandia.exceptions.VentaException;
import co.edu.uniquindio.dulcelandia.persistencia.Persistencia;
import co.edu.uniquindio.dulcelandia.model.Venta;


public class Tienda implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	ArrayList <Venta> listaVentas = new ArrayList<Venta>();
	ArrayList <Cliente> listaClientes = new ArrayList<Cliente>();
	ArrayList <Empleado> listaEmpleados= new ArrayList<Empleado>();
	ArrayList <Proveedor> listaProveedores = new ArrayList<Proveedor>();
	ArrayList <Producto> listaProductos = new ArrayList<Producto>();
	ArrayList <Usuario> listaUsuarios = new ArrayList<Usuario>();

	public Tienda(){
		
	}

	public ArrayList<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList<Venta> getListaVentas() {
		return listaVentas;
	}
	public void setListaVentas(ArrayList<Venta> listaVentas) {
		this.listaVentas = listaVentas;
	}
	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}
	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}
	public ArrayList<Empleado> getListaEmpleados() {
		return listaEmpleados;
	}
	public void setListaEmpleados(ArrayList<Empleado> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}
	public ArrayList<Proveedor> getListaProveedores() {
		return listaProveedores;
	}
	public void setListaProveedores(ArrayList<Proveedor> listaProveedores) {
		this.listaProveedores = listaProveedores;
	}
	public ArrayList<Producto> getListaProductos() {
		return listaProductos;
	}
	public void setListaProductos(ArrayList<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}
	public void crearProducto(String nombreProducto, String codigo, String precio, String cantidadExistente)throws ProductoException {
		// TODO Auto-generated method stub
		Producto productoExistente = obtenerProducto(codigo);
		if (productoExistente!= null)
			throw new ProductoException("El producto no se puede crear, ya existe");
		else{
			Producto nuevoProducto = new Producto();
			nuevoProducto.setNombreProducto(nombreProducto);
			nuevoProducto.setCodigo(codigo);
			nuevoProducto.setPrecio(precio);
			nuevoProducto.setCantidadExistente(cantidadExistente);
			getListaProductos().add(nuevoProducto);
			}
	}

	public void actualizarProducto(String nombreProducto, String codigo, String precio, String cantidadExistente)throws ActualizarProductoException{
		Producto productoExistente = obtenerProducto(codigo);
		if(productoExistente != null){
			if(productoExistente.getNombreProducto() == nombreProducto && productoExistente.getCodigo()== codigo && productoExistente.getPrecio()==precio && productoExistente.getCantidadExistente() == cantidadExistente){
				throw new ActualizarProductoException("El producto no se puede actualizar, no hay información nueva");
			}else{
				productoExistente.setNombreProducto(nombreProducto);
				productoExistente.setCodigo(codigo);
				productoExistente.setCantidadExistente(cantidadExistente);
				productoExistente.setPrecio(precio);

			}
		}
	}

	public Producto obtenerProducto(String codigo) {
		// TODO Auto-generated method stub
		Producto productoEncontrado= null;
		for (Producto producto:getListaProductos()){
			if(producto.getCodigo().equalsIgnoreCase(codigo)){
				productoEncontrado=producto;
				break;
			}
		}
		return productoEncontrado;
	}
	public boolean eliminarProducto(String codigo) {
		// TODO Auto-generated method stub
		boolean bandera=false;
		String notificacion = "";
		Producto producto = obtenerProducto(codigo);
		if (producto != null){
			getListaProductos().remove(producto);
			
			try {
				Persistencia.guardarProductos(listaProductos);
				
				Persistencia.guardaRegistroLog(notificacion, 1, "Eliminar Producto");
				MessageDialog.openInformation(new Shell(), "Información", "Se ha eliminado el Producto");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			bandera = true;
		}
		return bandera;
		}

	public void crearCliente(String nombre, String cedula, String telefono, String email)throws ClienteException {
		// TODO Auto-generated method stub
		Cliente clienteExistente = obtenerCliente(cedula);
		if (clienteExistente!= null)
			throw new ClienteException("El cliente no se puede crear, ya existe");
		else{
			Cliente nuevoCliente = new Cliente();
			nuevoCliente.setNombre(nombre);
			nuevoCliente.setCedula(cedula);
			nuevoCliente.setTelefono(telefono);
			nuevoCliente.setEmail(email);
			getListaClientes().add(nuevoCliente);
			}
	}
	
	

	public void actualizarCliente(String nombre, String cedula, String telefono, String email)throws ActualizarClienteException{
		Cliente clienteExistente = obtenerCliente(cedula);
		if(clienteExistente != null){
			if(clienteExistente.getNombre() == nombre && clienteExistente.getCedula()== cedula && clienteExistente.getTelefono()==telefono && clienteExistente.getEmail() == email){
				throw new ActualizarClienteException("El cliente no se puede actualizar, no hay información nueva");
			}else{
				clienteExistente.setNombre(nombre);
				clienteExistente.setCedula(cedula);
				clienteExistente.setTelefono(telefono);
				clienteExistente.setEmail(email);
			}
		}
	}

	public Cliente obtenerCliente(String cedula) {
		// TODO Auto-generated method stub
		Cliente clienteEncontrado= null;
		for (Cliente cliente:getListaClientes()){
			if(cliente.getCedula().equalsIgnoreCase(cedula)){
				clienteEncontrado=cliente;
				break;
			}
		}
		return clienteEncontrado;
	}


	public boolean eliminarCliente(String cedula) {
		boolean bandera=false;
		String notificacion = "";
		Cliente cliente = obtenerCliente(cedula);
		if (cliente != null){
			getListaClientes().remove(cliente);
			try {
				Persistencia.guardarClientes(listaClientes);
				
				Persistencia.guardaRegistroLog(notificacion, 1, "Eliminar Cliente");
				MessageDialog.openInformation(new Shell(), "Información", "Se ha eliminado el Cliente");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			bandera = true;
		}
		return bandera;
	}
	
	public void crearEmpleado(String nombre, String cedula, String telefono, String email)throws EmpleadoException {
		// TODO Auto-generated method stub
		Empleado empleadoExistente = obtenerEmpleado(cedula);
		if (empleadoExistente!= null)
			throw new EmpleadoException("El empleado no se puede crear, ya existe");
		else{
			Empleado nuevoEmpleado = new Empleado();
			nuevoEmpleado.setNombre(nombre);
			nuevoEmpleado.setCedula(cedula);
			nuevoEmpleado.setTelefono(telefono);
			nuevoEmpleado.setEmail(email);
			getListaEmpleados().add(nuevoEmpleado);
			}
	}

	public Empleado obtenerEmpleado(String cedula) {
		// TODO Auto-generated method stub
		Empleado empleadoEncontrado= null;
		for (Empleado empleado:getListaEmpleados()){
			if(empleado.getCedula().equalsIgnoreCase(cedula)){
				empleadoEncontrado=empleado;
				break;
			}
		}
		return empleadoEncontrado;
	}

	public boolean eliminarEmpleado(String cedula) {
		String notificacion = "";
		boolean bandera=false;
		Empleado empleado = obtenerEmpleado(cedula);
		if (empleado != null){
			getListaEmpleados().remove(empleado);
			
			try {
				Persistencia.guardarEmpleados(listaEmpleados);
				
				Persistencia.guardaRegistroLog(notificacion, 1, "Eliminar Empleado");
				MessageDialog.openInformation(new Shell(), "Información", "Se ha eliminado el Empleado");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			bandera = true;
		}
		return bandera;
	}

	public void actualizarEmpleado(String nombre, String cedula, String telefono, String email)throws ActualizarEmpleadoException{
		Empleado empleadoExistente = obtenerEmpleado(cedula);
		if(empleadoExistente != null){
			if(empleadoExistente.getNombre() == nombre && empleadoExistente.getCedula()== cedula && empleadoExistente.getTelefono()==telefono && empleadoExistente.getEmail() == email){
				throw new ActualizarEmpleadoException("El empleado no se puede actualizar, no hay información nueva");
			}else{
				empleadoExistente.setNombre(nombre);
				empleadoExistente.setCedula(cedula);
				empleadoExistente.setTelefono(telefono);
				empleadoExistente.setEmail(email);
			}
		}
	}

	public void crearProveedor(String nombre, String cedula, String telefono, String email)throws ProveedorException {
		// TODO Auto-generated method stub
		Proveedor proveedorExistente = obtenerProveedor(cedula);
		if (proveedorExistente!= null)
			throw new ProveedorException("El proveedor no se puede crear, ya existe");
		else{
			Proveedor nuevoProveedor = new Proveedor();
			nuevoProveedor.setNombre(nombre);
			nuevoProveedor.setCedula(cedula);
			nuevoProveedor.setTelefono(telefono);
			nuevoProveedor.setEmail(email);
			getListaProveedores().add(nuevoProveedor);
			}
	}

	public Proveedor obtenerProveedor(String cedula) {
		// TODO Auto-generated method stub
		Proveedor proveedorEncontrado= null;
		for (Proveedor proveedor:getListaProveedores()){
			if(proveedor.getCedula().equalsIgnoreCase(cedula)){
				proveedorEncontrado=proveedor;
				break;
			}
		}
		return proveedorEncontrado;
	}

	public boolean eliminarProveedor(String cedula) {
		boolean bandera=false;
		String notificacion = "";
		Proveedor proveedor = obtenerProveedor(cedula);
		if (proveedor != null){
			getListaProveedores().remove(proveedor);
			
			try {
				Persistencia.guardarProveedores(listaProveedores);
				
				Persistencia.guardaRegistroLog(notificacion, 1, "Eliminar Proveedor");
				MessageDialog.openInformation(new Shell(), "Información", "Se ha eliminado el Proveedor");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			bandera = true;
		}
		return bandera;
	}

	public void actualizarProveedor(String nombre, String cedula, String telefono, String email)throws ActualizarProveedorException{
		Proveedor proveedorExistente = obtenerProveedor(cedula);
		if(proveedorExistente != null){
			if(proveedorExistente.getNombre() == nombre && proveedorExistente.getCedula()== cedula && proveedorExistente.getTelefono()==telefono &&proveedorExistente.getEmail() == email){
				throw new ActualizarProveedorException("El proveedor no se puede actualizar, no hay información nueva");
			}else{
				proveedorExistente.setNombre(nombre);
				proveedorExistente.setCedula(cedula);
				proveedorExistente.setTelefono(telefono);
				proveedorExistente.setEmail(email);
			}
		}
	}



	public void crearVenta(String estadoVenta,String numVenta, String clienteVenta, String empleadoVenta) throws VentaException {
		Venta Venta = null;
		Venta ventaNueva = null;
		
		Venta = obtenerVenta(numVenta); 
		if(Venta!=null){
		throw new VentaException("La Venta no se puede crear");
		}else {
		ventaNueva= new Venta();
		ventaNueva.setEstadoVenta(estadoVenta);
		ventaNueva.setNumVenta(numVenta);
		ventaNueva.setClienteVenta(clienteVenta);
		ventaNueva.setEmpleadoVenta(empleadoVenta);
		getListaVentas().add(ventaNueva);
		}
	}

	public void actualizarVenta(String estadoVenta,String numVenta, String clienteVenta, String empleadoVenta)throws ActualizarVentaException, ActualizarEmpleadoException{
		Venta ventaCreada  = obtenerVenta(numVenta);
		if(ventaCreada != null){
			if(ventaCreada.getEstadoVenta() == estadoVenta && ventaCreada.getNumVenta()== numVenta && ventaCreada.getClienteVenta()== clienteVenta && ventaCreada.getEmpleadoVenta() == empleadoVenta){
				throw new ActualizarEmpleadoException("El empleado no se puede actualizar, no hay información nueva");
			}else{
				ventaCreada.setEstadoVenta(estadoVenta);
				ventaCreada.setNumVenta(numVenta);
				ventaCreada.setClienteVenta(clienteVenta);
				ventaCreada.setEmpleadoVenta(empleadoVenta);
			}
		}
	}
	
	
public Venta obtenerVenta(String numeroVenta) {
	// TODO Auto-generated method stub
	Venta ventaEncontrada= null;
	for (Venta venta:getListaVentas()){
		if(venta.getNumVenta().equalsIgnoreCase(numeroVenta)){
			ventaEncontrada=venta;
			break;
		}
	}
	return ventaEncontrada;
}}