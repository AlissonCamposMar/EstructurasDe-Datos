package co.edu.uniquindio.dulcelandia.controllers;
import co.edu.uniquindio.dulcelandia.model.Tienda;
import co.edu.uniquindio.dulcelandia.model.Usuario;
import co.edu.uniquindio.dulcelandia.persistencia.Persistencia;
import co.edu.uniquindio.dulcelandia.services.IModelFactoryService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import co.edu.uniquindio.dulcelandia.exceptions.ActualizarClienteException;
import co.edu.uniquindio.dulcelandia.exceptions.ActualizarEmpleadoException;
import co.edu.uniquindio.dulcelandia.exceptions.ActualizarProductoException;
import co.edu.uniquindio.dulcelandia.exceptions.ActualizarProveedorException;
import co.edu.uniquindio.dulcelandia.exceptions.ClienteException;
import co.edu.uniquindio.dulcelandia.exceptions.EmpleadoException;
import co.edu.uniquindio.dulcelandia.exceptions.ProductoException;
import co.edu.uniquindio.dulcelandia.exceptions.ProveedorException;
import co.edu.uniquindio.dulcelandia.model.Cliente;
import co.edu.uniquindio.dulcelandia.model.Empleado;
import co.edu.uniquindio.dulcelandia.model.Producto;
import co.edu.uniquindio.dulcelandia.model.Proveedor;

public class ModelFactoryController implements IModelFactoryService {

	private static final String RUTA_ARCHIVO_USUARIOS = "src/resources/archivoUsuarios.txt";
	
	private Tienda tienda;
	private static class SingletonHolder{
		private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
	}

	 public static ModelFactoryController getInstance(){
		 return SingletonHolder.eINSTANCE;
	 }

	 public ModelFactoryController() {
//		iniciarYSalvarDatosPrueba();
		//cargarDatosArchivos();
		//guardarResourceBinario();
		//cargarResourceBinario();
		//guardarResourceXML();
		cargarResourceXML();
		 
		 if(tienda == null){
			 inicializarDatos();
			 guardarResourceXML();
		 }
		 Persistencia.guardaRegistroLog("Inicio de sesión del usuario Juan", 1, "Inicio de sesion");
	 }

	 private void cargarResourceXML() {
		// TODO Auto-generated method stub
		tienda = Persistencia.cargarRecursoTiendaXML();
	}

	public void guardarResourceXML() {
		// TODO Auto-generated method stub
		Persistencia.guardarRecursoTiendaXML(tienda);
	}

	private void cargarResourceBinario() {
		tienda = Persistencia.cargarRecursoTiendaBinario();

	}

	private void guardarResourceBinario() {

		Persistencia.guardarRecursoTiendaBinario(tienda);
	}

	private void cargarDatosArchivos() {
		tienda = new Tienda();
		try {
			Persistencia.cargarDatosArchivos(tienda);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void registrarAccion(String mensaje, int nivel, String accion) {
		Persistencia.guardaRegistroLog(mensaje, nivel, accion);

	}

//	private void iniciarYSalvarDatosPrueba() {
//		 inicializarDatos();
//		try {
//			Persistencia.guardarProductos(getTienda().getListaProductos());
//			Persistencia.guardarClientes(getTienda().getListaClientes());
//			Persistencia.guardarEmpleados(getTienda().getListaEmpleados());
//			Persistencia.guardarProveedores(getTienda().getListaProveedores());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public void inicializarDatos(){
		 tienda = new Tienda();

		 Producto producto = new Producto();
		 producto.setNombreProducto("Barrilete");
		 producto.setCantidadExistente("10");
		 producto.setCodigo("4321");
		 producto.setPrecio("200");
		 tienda.getListaProductos().add(producto);

		 producto = new Producto();
		 producto.setNombreProducto("Bombon");
		 producto.setCantidadExistente("4");
		 producto.setCodigo("1234");
		 producto.setPrecio("400");
		 tienda.getListaProductos().add(producto);

		 Cliente cliente = new Cliente();
		 cliente.setNombre("Marta");
		 cliente.setCedula("456");
		 cliente.setTelefono("3135625983");
		 cliente.setEmail("marta@gmail.com");
		 tienda.getListaClientes().add(cliente);

		 cliente = new Cliente();
		 cliente.setNombre("Camilo");
		 cliente.setCedula("789");
		 cliente.setTelefono("3105626983");
		 cliente.setEmail("camilo@gmail.com");
		 tienda.getListaClientes().add(cliente);

		 Empleado empleado = new Empleado();
		 empleado.setNombre("Juan");
		 empleado.setCedula("123");
		 empleado.setTelefono("315566983");
		 empleado.setEmail("juan@gmail.com");
		 tienda.getListaEmpleados().add(empleado);

		 Proveedor proveedor = new Proveedor();
		 proveedor.setNombre("Mateo");
		 proveedor.setCedula("498");
		 proveedor.setTelefono("315565983");
		 proveedor.setEmail("mateo@gmail.com");
		 tienda.getListaProveedores().add(proveedor);

		 Usuario usuario = new Usuario();
		 usuario.setUsuario("carlos");
		 usuario.setContraseña("1234");
		 tienda.getListaUsuarios().add(usuario);

		 usuario = new Usuario();
		 usuario.setUsuario("juan");
		 usuario.setContraseña("4321");
		 tienda.getListaUsuarios().add(usuario);


	 }

	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}

	/**
	 * crear producto
	 * @param nombreProducto
	 * @param codigo
	 * @param precio
	 * @param cantidadExistente
	 * @return
	 */
	public String crearProducto(String nombreProducto, String codigo, String precio, String cantidadExistente) {
		// TODO Auto-generated method stub
		
		String notificacion ="";
		try {
			getTienda().crearProducto(nombreProducto, codigo, precio, cantidadExistente);
			notificacion = "Producto creado con exito";
			
			//guardar producto
			Persistencia.guardarProductos(getTienda().getListaProductos());
			
			Persistencia.guardaRegistroLog(notificacion, 1, "Producto guardado");
			MessageDialog.openInformation(new Shell(), "Información", "Se ha guardado el producto");
		} catch (ProductoException e) {
			// TODO Auto-generated catch block
			notificacion=e.getMessage();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notificacion;

	}
	
	/**
	 * Actualizar producto
	 * @param nombreProducto
	 * @param codigo
	 * @param precio
	 * @param cantidadExistente
	 * @return
	 */
	public String actualizarProducto(String nombreProducto, String codigo, String precio, String cantidadExistente){
		
		String notificacion = "";
		try{
			getTienda().actualizarProducto(nombreProducto, codigo, precio, cantidadExistente);
			notificacion = "Producto actualizado con exito";
			
			//guardar actualizado
			Persistencia.guardarProductos(getTienda().getListaProductos());
			Persistencia.guardaRegistroLog(notificacion, 1, "actualizar producto");
			
			MessageDialog.openInformation(new Shell(), "Información", "Se ha actualizado el producto");
		} catch(ActualizarProductoException e){
			notificacion = e.getMessage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notificacion;
	}

	/**
	 * eliminar producto
	 * @param producto
	 * @return
	 */
	public boolean eliminarProducto(Producto producto) {
		// TODO Auto-generated method stub
		return getTienda().eliminarProducto(producto.getCodigo());
	}

	/**
	 * crear cliente
	 * @param nombre
	 * @param cedula
	 * @param telefono
	 * @param email
	 * @return
	 */
	public String crearCliente(String nombre, String cedula, String telefono, String email){
		
		String notificacion = "";
		
		try {
			getTienda().crearCliente(nombre, cedula, telefono, email);
			notificacion = "Cliente creado con exito";
			
			//cuardar clientes
			Persistencia.guardarClientes(getTienda().getListaClientes());
			
			Persistencia.guardaRegistroLog(notificacion, 1, "crear cliente");
			MessageDialog.openInformation(new Shell(), "Información", "Se ha guardado el liente");
		} catch (ClienteException e) {
			// TODO Auto-generated catch block
			notificacion=e.getMessage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notificacion;
	}

	/**
	 * actualizar cliente
	 * @param nombre
	 * @param cedula
	 * @param telefono
	 * @param email
	 * @return
	 */
	public String actualizarCliente(String nombre, String cedula, String telefono, String email){
		
		String notificacion = "";
		try{
			getTienda().actualizarCliente(nombre, cedula, telefono, email);
			notificacion = "Cliente actualizado con exito";
			
			Persistencia.guardarClientes(getTienda().getListaClientes());
			Persistencia.guardaRegistroLog(notificacion, 1, "Cliente actualizado");
			
			MessageDialog.openInformation(new Shell(), "Informacion", "Se ha actualizado el cliente");
		}catch(ActualizarClienteException e){
			notificacion = e.getMessage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notificacion;
	}

	/**
	 * eliminar cliente
	 * @param cliente
	 * @return
	 */
	public boolean eliminarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		 return getTienda().eliminarCliente(cliente.getCedula());

	}

	/**
	 * crear empleado
	 * @param nombre
	 * @param cedula
	 * @param telefono
	 * @param email
	 * @return
	 */
	public String crearEmpleado(String nombre, String cedula, String telefono, String email){
		
		String notificacion = "";
		try {
			getTienda().crearEmpleado(nombre, cedula, telefono, email);
			notificacion = "Empleado creado con exito";
			
			//guardar el empleado
			Persistencia.guardarEmpleados(getTienda().getListaEmpleados());
			
			Persistencia.guardaRegistroLog(notificacion, 1, "Crear Empleado");
			MessageDialog.openInformation(new Shell(), "Información", "Se ha guardado el Empleado");
			
		}catch (NumberFormatException e) {
			notificacion = e.getMessage();
			
		} catch (EmpleadoException e) {
			// TODO Auto-generated catch block
			notificacion=e.getMessage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notificacion;
	}

	/**
	 * eliminar empleado
	 * @param empleado
	 * @return
	 */
	public boolean eliminarEmpleado(Empleado empleado) {
		// TODO Auto-generated method stub
		 return getTienda().eliminarEmpleado(empleado.getCedula());
	}

	/**
	 * actualizar empleado
	 * @param nombre
	 * @param cedula
	 * @param telefono
	 * @param email
	 * @return
	 */
	public String actualizarEmpleado(String nombre, String cedula, String telefono, String email){
		
		String notificacion = "";
		try {
			getTienda().actualizarEmpleado(nombre, cedula, telefono, email);
			notificacion = "Empleado actualizado con exito";
			
			Persistencia.guardarEmpleados(getTienda().getListaEmpleados());
			Persistencia.guardaRegistroLog(notificacion, 1, "empleado actualizado");
			
			MessageDialog.openInformation(new Shell(), "Información", "Se ha actualizado el empleado");
			
		} catch (ActualizarEmpleadoException e) {
			// TODO Auto-generated catch block
			notificacion=e.getMessage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notificacion;
	}

	/**
	 * crear proveedor
	 * @param nombre
	 * @param cedula
	 * @param telefono
	 * @param email
	 * @return
	 */
	public String crearProveedor(String nombre, String cedula, String telefono, String email){
		
		String notificacion = "";
		try {
			getTienda().crearProveedor(nombre, cedula, telefono, email);
			notificacion = "Proveedor creado con exito";
			
			//guardar proveedor
			Persistencia.guardarProveedores(getTienda().getListaProveedores());
			
			//registrar la operacion
			Persistencia.guardaRegistroLog(notificacion,1,"crear proveedor");
		
			MessageDialog.openInformation(new Shell(), "Información", "Se ha guardado el proveedor");
		} catch (ProveedorException e) {
			// TODO Auto-generated catch block
			notificacion=e.getMessage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notificacion;
	}

	/**
	 * eliminar proveedor
	 * @param proveedor
	 * @return
	 */
	public boolean eliminarProveedor(Proveedor proveedor) {
		// TODO Auto-generated method stub
		 return getTienda().eliminarProveedor(proveedor.getCedula());
	}

	/**
	 * actualizar proveedor
	 * @param nombre
	 * @param cedula
	 * @param telefono
	 * @param email
	 * @return
	 */
	public String actualizarProveedor(String nombre, String cedula, String telefono, String email){
		
		String notificacion = "";
		try {
			getTienda().actualizarProveedor(nombre, cedula, telefono, email);
			notificacion = "Proveedor actualizado con exito";
			
			//guardar proveedor actualizado
			Persistencia.guardarProveedores(getTienda().getListaProveedores());
			Persistencia.guardaRegistroLog(notificacion, 1, "actualizar proveedor");
			
			MessageDialog.openInformation(new Shell(), "Información", "Se ha actualizado el proveedor");
		} catch (ActualizarProveedorException e) {
			// TODO Auto-generated catch block
			notificacion=e.getMessage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notificacion;
	}

	/**
	 * 
	 * @param cliente
	 * @param producto
	 * @param proveedor
	 */
	public void crearVenta(Cliente cliente, Producto producto, Proveedor proveedor) {
		// TODO Auto-generated method stub
		return;

	}




}
