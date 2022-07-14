package co.edu.uniquindio.dulcelandia.persistencia;

import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

//import co.edu.uniquindio.banco.exceptions.UsuarioExcepcion;
import co.edu.uniquindio.dulcelandia.model.Tienda;
import co.edu.uniquindio.dulcelandia.model.Cliente;
import co.edu.uniquindio.dulcelandia.model.Empleado;
import co.edu.uniquindio.dulcelandia.model.Producto;
import co.edu.uniquindio.dulcelandia.model.Proveedor;
import co.edu.uniquindio.dulcelandia.exceptions.UsuarioException;
import co.edu.uniquindio.dulcelandia.model.Usuario;



public class Persistencia {

	public static final String RUTA_ARCHIVO_PROVEEDORES = "C:/td2/persistencia/archivos/archivoProveedores.txt";
	public static final String RUTA_ARCHIVO_PRODUCTOS = "C:/td2/persistencia/archivos/archivoProductos.txt";
	public static final String RUTA_ARCHIVO_CLIENTES= "C:/td2/persistencia/archivos/archivoClientes.txt";
	public static final String RUTA_ARCHIVO_USUARIOS= "C:/td2/persistencia/archivos/archivoUsuarios.txt";
	public static final String RUTA_ARCHIVO_LOG = "C:/td2/persistencia/log/TiendaLog.txt";
	public static final String RUTA_ARCHIVO_COPIA_LOG = "C:/td2/persistencia/respaldo/TiendaLog.txt";
	public static final String RUTA_ARCHIVO_EMPLEADOS = "C:/td2/persistencia/archivos/archivoEmpleados.txt";
	public static final String RUTA_ARCHIVO_OBJETOS = "C:/td2/persistencia/archivos/archivoObjetos.txt";
	
	public static final String RUTA_ARCHIVO_MODELO_TIENDA_BINARIO = "C:/td2/persistencia/model.dat";
	public static final String RUTA_ARCHIVO_MODELO_TIENDA_XML = "C:/td2/persistencia/model.xml";


	
	
	public static void cargarDatosArchivos(Tienda tienda) throws FileNotFoundException, IOException {
		
		
		//cargar archivo de productos
		ArrayList<Producto> productosCargados = cargarProductos();
		
		if(productosCargados.size() > 0)
			tienda.getListaProductos().addAll(productosCargados);
		
		
//		guardarRecursoBancoEnBinario(tienda);
		
		//cargar archivo empleados
		ArrayList<Empleado> empleadosCargados = cargarEmpleados();
		
		if(empleadosCargados.size() > 0)
			tienda.getListaEmpleados().addAll(empleadosCargados);
		
		//cargar archivo clientes
		ArrayList<Cliente> clientesCargados = cargarClientes();
		
		if(clientesCargados.size() > 0)
			tienda.getListaClientes().addAll(clientesCargados);
		//cargar archivo prestamo
		
	}
	
	
	
	/**
	 * Este metodo guarda los productos
	 * @param listaProductos
	 * @throws IOException
	 */
	public static void guardarProductos(ArrayList<Producto> listaProductos) throws IOException {
		// TODO Auto-generated method stub
		String contenido = "";
		
		for(Producto producto:listaProductos) {
			contenido+= producto.getNombreProducto()+"@@"+producto.getCodigo()+"@@"+producto.getPrecio()+"@@"+producto.getCantidadExistente()
		     +"\n";
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PRODUCTOS, contenido, true);
		
	}
	
	/**
	 * 
	 * @param listaClientes
	 * @throws IOException
	 */
	public static void guardarClientes(ArrayList<Cliente> listaClientes) throws IOException {
		// TODO Auto-generated method stub
		String contenido = "";
		
		for(Cliente cliente:listaClientes) {
			
			contenido+= cliente.getNombre()+"@@"+cliente.getCedula()+"@@"+cliente.getTelefono()+"@@"+cliente.getEmail()
		     +"\n";
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_CLIENTES, contenido, true);
		
	}
	
	/**
	 * 
	 * @param listaEmpleados
	 * @throws IOException
	 */
	public static void guardarEmpleados(ArrayList<Empleado> empleados) throws IOException {
		// TODO Auto-generated method stub
		String contenido = "";
		
		for(Empleado empleado:empleados) 
		{
			contenido+= empleado.getNombre()+"@@"+empleado.getCedula()+"@@"+empleado.getTelefono()+"@@"+empleado.getEmail()
		     +"\n";
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_EMPLEADOS, contenido, true);
		
	}
	
	public static void guardarProveedores(ArrayList<Proveedor> listaProveedores) throws IOException {
		// TODO Auto-generated method stub
		String contenido = "";
		
		for(Proveedor proveedor:listaProveedores) {
			contenido+= proveedor.getNombre()+"@@"+proveedor.getCedula()+"@@"+proveedor.getTelefono()+"@@"+proveedor.getEmail()
		     +"\n";
		}
		ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PROVEEDORES, contenido, true);
		
	}
//	----------------------LOADS------------------------
	
	/**
	 * @param productos
	 * @return un Arraylist de productos con los datos obtenidos del archivo de texto indicado
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static ArrayList<Producto> cargarProductos() throws FileNotFoundException, IOException {
		
		ArrayList<Producto> productos = new ArrayList<Producto>();
		
		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_PRODUCTOS);
		String linea="";
		
		for (int i = 0; i < contenido.size(); i++) {
			linea = contenido.get(i);
			Producto producto = new Producto();
			producto.setNombreProducto(linea.split("@@")[0]);
			producto.setCodigo(linea.split("@@")[1]);
			producto.setPrecio(linea.split("@@")[2]);
			producto.setCantidadExistente(linea.split("@@")[3]);
			
			productos.add(producto);
		}
		return productos;
	}
	
	/**
	 * 
	 * @param tipoPersona
	 * @param ruta
	 * @return un Arraylist de personas con los datos obtenidos del archivo de texto indicado
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static ArrayList<Cliente> cargarClientes() throws FileNotFoundException, IOException 
	{
		ArrayList<Cliente> clientes =new ArrayList<Cliente>();
		
		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_CLIENTES);
		String linea="";
		
		for (int i = 0; i < contenido.size(); i++)
		{
			linea = contenido.get(i);
			Cliente cliente = new Cliente();
			cliente.setNombre(linea.split("@@")[0]);
			cliente.setCedula(linea.split("@@")[1]);
			cliente.setTelefono(linea.split("@@")[2]);
			cliente.setEmail(linea.split("@@")[3]);
			
			clientes.add(cliente);
		}
		return clientes;
	}
	
	public static ArrayList<Empleado> cargarEmpleados() throws FileNotFoundException, IOException 
	{
		ArrayList<Empleado> empleados =new ArrayList<Empleado>();
		
		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_EMPLEADOS);
		String linea="";
		
		for (int i = 0; i < contenido.size(); i++)
		{
			linea = contenido.get(i);
			Empleado empleado = new Empleado();
			empleado.setNombre(linea.split("@@")[0]);
			empleado.setCedula(linea.split("@@")[1]);
			empleado.setTelefono(linea.split("@@")[2]);
			empleado.setEmail(linea.split("@@")[3]);
			
			empleados.add(empleado);
		}
		return empleados;
	}
	
	
	public static ArrayList<Proveedor> cargarProveedores() throws FileNotFoundException, IOException 
	{
		ArrayList<Proveedor> listaProveedores =new ArrayList<Proveedor>();
		
		ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_PROVEEDORES);
		String linea="";
		
		for (int i = 0; i < contenido.size(); i++)
		{
			linea = contenido.get(i);
			Proveedor proveedor = new Proveedor();
			proveedor.setNombre(linea.split("@@")[0]);
			proveedor.setCedula(linea.split("@@")[1]);
			proveedor.setTelefono(linea.split("@@")[2]);
			proveedor.setEmail(linea.split("@@")[3]);
			
			listaProveedores.add(proveedor);
		}
		return listaProveedores;
	}
	

	/**
	 * @param TiendaLog.txt
	 * @param mensajeLog
	 * @param nivel
	 * @param accion
	 */
	public static void guardaRegistroLog(String mensajeLog, int nivel, String accion)
	{
		
		ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
	}


	/**
	 * 
	 * @param usuario
	 * @param contraseña
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws UsuarioException
	 */
	 public static boolean iniciarSesion(String usuario, String contraseña) throws FileNotFoundException, IOException, UsuarioException {
		
		if(validarUsuario(usuario,contraseña)) {
			return true;
		}else {
			throw new UsuarioException("Usuario no existe");
		}
		
	}
	
	 /**
	  * 
	  * @param usuario
	  * @param contraseña
	  * @return
	  * @throws FileNotFoundException
	  * @throws IOException
	  */
	private static boolean validarUsuario(String usuario, String contraseña) throws FileNotFoundException, IOException 
	{
		ArrayList<Usuario> usuarios = Persistencia.cargarUsuarios(RUTA_ARCHIVO_USUARIOS);
		
		for (int indiceUsuario = 0; indiceUsuario < usuarios.size(); indiceUsuario++) 
		{
			Usuario usuarioAux = usuarios.get(indiceUsuario);
			if(usuarioAux.getUsuario().equalsIgnoreCase(usuario) && usuarioAux.getContraseña().equalsIgnoreCase(contraseña)) {
				return true;
			}
		}
		return false;
	}

	public static ArrayList<Usuario> cargarUsuarios(String ruta) throws FileNotFoundException, IOException {
		ArrayList<Usuario> usuarios =new ArrayList<Usuario>();
		
		ArrayList<String> contenido = ArchivoUtil.leerArchivo(ruta);
		String linea="";
		
		for (int i = 0; i < contenido.size(); i++) {
			linea = contenido.get(i);
			
			Usuario usuario = new Usuario();
			usuario.setUsuario(linea.split("@@")[0]);
			usuario.setContraseña(linea.split("@@")[1]);
			
			usuarios.add(usuario);
		}
		return usuarios;
	}
	
	
	public static Tienda cargarModelo() throws IOException{

		Object obhecto = ArchivoUtil.leerArchivo("");

		Tienda tienda = (Tienda) obhecto;
		return tienda;
	}
	
	
	
//	----------------------SAVES------------------------
	
//	/**
//	 * Guarda en un archivo de texto todos la información de las personas almacenadas en el ArrayList
//	 * @param objetos
//	 * @param ruta
//	 * @throws IOException
//	 */
//	
//	public static void guardarObjetos(ArrayList<Cliente> listaClientes, String ruta) throws IOException  {
//		String contenido = "";
//		
//		for(Cliente clienteAux:listaClientes) {
//			contenido+= clienteAux.getNombre()+"@@"+clienteAux.getCedula()+clienteAux.getTelefono()+"@@"+clienteAux.getEmail()+"@@"+clienteAux.getTelefono()+"\n";
//		}
//		ArchivoUtil.guardarArchivo(ruta, contenido, true);
//	}
//
//	public static void guardarProductoTxt(ArrayList<Producto> listaproductos, String ruta) throws IOException {
//		
//	}
	
	
	
	//------------------------------------SERIALIZACIÓN  y XML
	
	
	public static Tienda cargarRecursoTiendaBinario() {
		
		Tienda tienda = null;
		try {
			tienda = (Tienda)ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_TIENDA_BINARIO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tienda;
	}
	
	public static void guardarRecursoTiendaBinario(Tienda tienda) {
		
		try {
			ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_MODELO_TIENDA_BINARIO, tienda);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static Tienda cargarRecursoTiendaXML() {
		
		Tienda tienda = null;
		
		try {
			tienda = (Tienda)ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_TIENDA_XML);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tienda;

	}

	
	
	public static void guardarRecursoTiendaXML(Tienda tienda) {
		
		try {
			ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_TIENDA_XML,tienda);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	



}
