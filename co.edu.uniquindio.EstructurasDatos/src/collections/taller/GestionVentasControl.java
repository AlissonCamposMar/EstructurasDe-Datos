package collections.taller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import collections.clases.*;

public class GestionVentasControl {
	
	class Comparacion implements Comparator {
		
		public int compare (Object x1, Object x2) {
			Producto p1 =  (Producto)x1;
			Producto p2 = (Producto)x2;
			return p1.getCodigo().compareTo(p2.getCodigo());
		}
	}
	
	
	
	
	
	//ArrayList
	List <Cliente> listaClientes = new ArrayList<>();
//	Set <Cliente> listaClientes2 = new TreeSet<>();
//	List <Cliente> listaClientes2 = new ArrayList<>();	
	
	//HashSet
	Set <Vendedor> listaVendores = new HashSet<>();
	
	TreeSet listaProductosAsociadosClientes = new TreeSet(new Comparacion());
	TreeSet listaProductosAsociadosVendedor = new TreeSet(new Comparacion());
	
	//HashMap
//	HashMap<String,Float> listaProductos = new HashMap<String,Float>();
	HashMap<String,Producto> listaProdcutos = new HashMap<>();
	
	
	Iterator<Cliente> iteratorCliente = getListaClientes().iterator();
	Iterator<Vendedor> iteratorVendedor = getListaVendores().iterator();
	Iterator<String> iteratorProducto= getListaProdcutos().keySet().iterator();

	
	public GestionVentasControl() {
	
		inicializarDatos();
	}

	private void inicializarDatos() {
		
		Cliente cliente = new Cliente();
		cliente.setNombres("Juan");
		cliente.setApellidos("Arias");
		cliente.setCedula("1094");
		cliente.setDescripcion("Concurrente");
		listaClientes.add(cliente);
		
//		listaClientes2.add(cliente);
//		
//		cliente = new Cliente();
//		cliente.setNombres("Juan");
//		cliente.setApellidos("Arias");
//		cliente.setCedula("1094");
//		cliente.setDescripcion("Concurrente");
//		listaClientes2.add(cliente);
//		
//		System.out.println("aca");
		
//		cliente = new Cliente();
//		cliente.setNombres("Juan");
//		cliente.setApellidos("Arias");
//		cliente.setCedula("8913");
//		cliente.setDescripcion("Concurrente");
//		listaClientes.add(cliente);
//		
//
//		listaClientes2.add(cliente);
//		
//		listaClientes.removeAll(listaClientes);
//		listaClientes.add(cliente);
		
		Vendedor vendedor = new Vendedor();
		vendedor.setNombres("Pedro");
		vendedor.setApellidos("Arias");
		vendedor.setCedula("8913");
		vendedor.setDescripcion("Fijo");
		listaVendores.add(vendedor);
//		listaVendores.add(vendedor);
		
		
		Producto producto;
		producto = new Producto();
		producto.setCodigo("001");
		producto.setNombre("Pala");
		producto.setDescripcion("Pala de acero");
		producto.setEstado("Disponible");
		listaProdcutos.put( producto.getCodigo(),producto);
		
		
		
		producto = new Producto();
		producto.setCodigo("002");
		producto.setNombre("Caja herramientas");
		producto.setDescripcion("-------");
		producto.setEstado("Disponible");
		listaProdcutos.put(producto.getCodigo(),producto);
		

		
		
//		listaProductosAsociadosClientes.add(producto);
//		
//		
//		producto = new Producto();
//		producto.setNombre("Pala");
//		producto.setCodigo("002");
//		producto.setDescripcion("Pala de acero");
//		listaProdcutos.put(producto, producto.getCodigo());
//		
//		listaProductosAsociadosClientes.add(producto);
//		
//		System.out.println("aca");
		
		
//		producto.setNombre("Pala");
//		producto.setCodigo("001");
//		producto.setDescripcion("Pala de acero2");
//		listaProdcutos.put(producto, producto.getCodigo());
//		
//		
//		producto = new Producto();
//		producto.setNombre("Pala");
//		producto.setCodigo("001");
//		producto.setDescripcion("Pala de acero");
//		listaProdcutos.put(producto, producto.getCodigo());
		
//		listaProdcutos.put(producto, producto.getCodigo());
	}

	
//------------------------------------------------------CLIENTE-------------------------------------------------------------------------	
//-------------------------------------------------------------------------------------------------------------------------------	
//-------------------------------------------------------------------------------------------------------------------------------	
	
	
	public void agregarCliente(String nombres, String apellidos, String cedula, String descripcion) {
		
		Cliente cliente = new Cliente();
		cliente.setNombres(nombres);
		cliente.setApellidos(apellidos);
		cliente.setCedula(cedula);
		cliente.setDescripcion(descripcion);
		listaClientes.add(cliente);
	}
	
	
	public boolean eliminarCliente(String cedulaCliente) {

		//Metodo Iterator, util para recorrer un arrayList
		Cliente cliente = null;
		cliente = obtenerCliente(cedulaCliente);
		
		if(cliente != null) {
			getListaClientes().remove(cliente);
		return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public Cliente obtenerCliente(String cedulaCliente) {
		
		Cliente cliente = null;
		iteratorCliente = getListaClientes().iterator();
		
		while (iteratorCliente.hasNext()){
			cliente = iteratorCliente.next();

			if(cliente.getCedula().equalsIgnoreCase(cedulaCliente))
			{
				return cliente;
			}
		}
		return cliente;
	}
	
//------------------------------------------------------VENDEDOR-------------------------------------------------------------------------	
//-------------------------------------------------------------------------------------------------------------------------------	
//-------------------------------------------------------------------------------------------------------------------------------	
	
	public void agregarVendedor(String nombres, String apellidos, String cedula, String descripcion) {
		
		Vendedor vendedor = new Vendedor();
		vendedor.setNombres(nombres);
		vendedor.setApellidos(apellidos);
		vendedor.setCedula(cedula);
		vendedor.setDescripcion(descripcion);
		
		listaVendores.add(vendedor);
		
	}
	
	
	public boolean eliminarVendedor(String cedulaVendedor) {

		//Metodo Iterator, util para recorrer un arrayList
		Vendedor vendedor = null;
		
		vendedor = obtenerVendedor(cedulaVendedor);
		
		if(vendedor != null) {
			getListaVendores().remove(vendedor);
			
			return true;
			
		} else {
			return false;
		}
		
	}
	
	
	public Vendedor obtenerVendedor(String cedulaVendedor) {
		
		Vendedor vendedor = null;
		iteratorVendedor = getListaVendores().iterator();
		
		while (iteratorVendedor.hasNext()){
			vendedor = iteratorVendedor.next();

			if(vendedor.getCedula().equalsIgnoreCase(cedulaVendedor))
			{
				return vendedor;
			}
		}
		return vendedor;
	}
	
//-----------------------------------------------------PRODUCTO--------------------------------------------------------------------------	
//-------------------------------------------------------------------------------------------------------------------------------	
//-------------------------------------------------------------------------------------------------------------------------------	
	
	
	
	
	public void agregarProducto(String nombre, String codigo, String descripcion) {
		
		Producto producto = new Producto();
		producto.setNombre(nombre);
		producto.setCodigo(codigo);
		producto.setDescripcion(descripcion);
		
		System.out.println(listaProdcutos);
		
		listaProdcutos.put(producto.getCodigo(),producto );
		
		System.out.println(listaProdcutos);
	}


	public boolean eliminarProducto(String codigoProducto) {

		//Metodo Iterator, util para recorrer un arrayList
		Producto producto = null;
		producto = obtenerProducto(codigoProducto);
		
		if(producto != null) {
			getListaProdcutos().remove(producto,codigoProducto);
			
		return true;
		
		} else {
			return false;
			
		}
	}

	
	public Producto obtenerProducto(String codigoProducto) {
		
		Producto producto = null;
		
		for( Iterator it = getListaProdcutos().keySet().iterator(); it.hasNext();) { 

			producto = (Producto)it.next();
			
			if(producto.getCodigo().equals(codigoProducto))
			{
				return producto;
			}
		}
		
		iteratorProducto= getListaProdcutos().keySet().iterator();
		
		while(iteratorProducto.hasNext()){
			String codigo = iteratorProducto.next();
			if(codigo.equals(codigoProducto))
			{
				return producto;
			}
		}  
		return producto;
	}
	
	


	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public Set<Vendedor> getListaVendores() {
		return listaVendores;
	}

	public void setListaVendores(Set<Vendedor> listaVendores) {
		this.listaVendores = listaVendores;
	}

	
	public HashMap<String, Producto> getListaProdcutos() {
		return listaProdcutos;
	}

	public void setListaProdcutos(HashMap<String, Producto> listaProdcutos) {
		this.listaProdcutos = listaProdcutos;
	}

	public TreeSet getListaProductosAsociadosClientes() {
		return listaProductosAsociadosClientes;
	}

	public void setListaProductosAsociadosClientes(TreeSet listaProductosAsociadosClientes) {
		this.listaProductosAsociadosClientes = listaProductosAsociadosClientes;
	}

	public TreeSet getListaProductosAsociadosVendedor() {
		return listaProductosAsociadosVendedor;
	}

	public void setListaProductosAsociadosVendedor(TreeSet listaProductosAsociadosVendedor) {
		this.listaProductosAsociadosVendedor = listaProductosAsociadosVendedor;
	}
	
	
	
	
	

//	public List<Vendedor> getListaVendores() {
//		return listaVendores;
//	}
//
//	public void setListaVendores(List<Vendedor> listaVendores) {
//		this.listaVendores = listaVendores;
//	}
//
//	public List<Producto> getListaProdcutos() {
//		return listaProdcutos;
//	}
//
//	public void setListaProdcutos(List<Producto> listaProdcutos) {
//		this.listaProdcutos = listaProdcutos;
//	}


}
