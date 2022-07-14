package co.edu.uniquindio.restaurante.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Properties;

import co.edu.uniquindio.restaurante.exceptions.ClienteNoActualizado;
import co.edu.uniquindio.restaurante.exceptions.ClienteNoCreado;
import co.edu.uniquindio.restaurante.exceptions.CuentaNoActualizada;
import co.edu.uniquindio.restaurante.exceptions.CuentaNoCreada;
import co.edu.uniquindio.restaurante.exceptions.EmpleadoNoActualizado;
import co.edu.uniquindio.restaurante.exceptions.EmpleadoNoCreado;
import co.edu.uniquindio.restaurante.exceptions.TransaccionNoActualizada;
import co.edu.uniquindio.restaurante.exceptions.TransaccionNoCreada;
import co.edu.uniquindio.restaurante.services.IRestauranteServices;
import co.edu.uniquindio.restaurante.util.Persistencia;
import co.edu.uniquindio.restaurante.util.util;

public class Restaurante implements IRestauranteServices, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Empleado> listaEmpleados = new ArrayList<>();
	ArrayList<Cliente> listaClientes = new ArrayList<>();
	ArrayList<Cuenta> listaCuentas = new ArrayList<>();
	ArrayList<Transaccion> listaTransacciones = new ArrayList<>();
	
	public Restaurante(){
		
	}	
	
	public ArrayList<Empleado> getListaEmpleados() {
		return listaEmpleados;
	}

	public void setListaEmpleados(ArrayList<Empleado> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}

	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public ArrayList<Cuenta> getListaCuentas() {
		return listaCuentas;
	}

	public void setListaCuentas(ArrayList<Cuenta> listaCuentas) {
		this.listaCuentas = listaCuentas;
	}
	
	public ArrayList<Transaccion> getListaTransacciones() {
		return listaTransacciones;
	}

	public void setListaTransacciones(ArrayList<Transaccion> listaTransacciones) {
		this.listaTransacciones = listaTransacciones;
	}
	
	public Boolean verificarInicioSesion(String usuario, String contraseña) throws FileNotFoundException, IOException {
		Properties propiedades = new Properties();
		propiedades = Persistencia.leerPropiedades();
		boolean inicio = false;
		
		String ingresoUsuario = propiedades.getProperty("usuario");
		String ingresoContraseña = propiedades.getProperty("contraseña");
		String[] usuarioDiv = ingresoUsuario.split("@@");

		if ((usuario.equalsIgnoreCase(usuarioDiv[0]) || usuario.equalsIgnoreCase(usuarioDiv[1])) && 
				contraseña.equals(ingresoContraseña)) {
			inicio = true;
		}
		
		return inicio;
	}

	@Override
	public void crearEmpleado(String nombre, String apellido, String cedula, String edad) throws EmpleadoNoCreado {
		// TODO Auto-generated method stub
		Empleado empleadoExistente = null;
		Empleado empleadoNuevo = null;
		
		empleadoExistente = obtenerEmpleado(cedula, true);
		if (empleadoExistente != null) {
			throw new EmpleadoNoCreado("No ha sido posible crear el empleado.");
		} else {
			empleadoNuevo = new Empleado();
			
			empleadoNuevo.setNombre(nombre);
			empleadoNuevo.setApellido(apellido);
			empleadoNuevo.setCedula(cedula);
			empleadoNuevo.setEdad(edad);
			
			getListaEmpleados().add(empleadoNuevo);
			util.escribirArchivo("empleados.txt", nombre, apellido, cedula, edad);
		}
	}

	@Override
	public void actualizarEmpleado(Empleado empleado, Empleado nuevoEmpleado) throws EmpleadoNoActualizado {
		Boolean sonIguales  = null;
		
		sonIguales = validarDiferenciaPersona(empleado, nuevoEmpleado);
		if (sonIguales) {
			throw new EmpleadoNoActualizado("No es posible actualizar ya que el empleado existe.");
		} else {
			try {
				util.actualizarArchivo("empleados.txt", empleado.getCedula(), false,
						nuevoEmpleado.getNombre(), nuevoEmpleado.getApellido(), nuevoEmpleado.getCedula(), nuevoEmpleado.getEdad());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				empleado.setNombre(nuevoEmpleado.getNombre());
				empleado.setApellido(nuevoEmpleado.getApellido());
				empleado.setCedula(nuevoEmpleado.getCedula());
				empleado.setEdad(nuevoEmpleado.getEdad());		
			}											
		}				
	}

	@Override
	public boolean eliminarEmpleado(String cedula) {
		boolean flagEliminado = false;
		Empleado empleado = obtenerEmpleado(cedula, false);
		
		if (empleado != null) {
			getListaEmpleados().remove(empleado);
			
			try {
				util.actualizarArchivo("empleados.txt", empleado.getCedula(), true,
						empleado.getNombre(), empleado.getApellido(), empleado.getCedula(), empleado.getEdad());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			flagEliminado = true;
		}
		
		return flagEliminado;
	}

	@Override
	public Empleado obtenerEmpleado(String cedula, boolean byPass) {
		Empleado empleadoEncontrado = null;
		boolean verificarNoExistenciaTransaccion = verificarNoExistenciaTransaccion(cedula);
		
		if (byPass || verificarNoExistenciaTransaccion) {
			for (Empleado empleado : getListaEmpleados()) {
				if (empleado.getCedula().equalsIgnoreCase(cedula)){
					empleadoEncontrado = empleado;
					break;
				}
			}
		}		
		
		return empleadoEncontrado;
	}		

	@Override
	public void crearCuenta(String total, String id, String cliente, String empleado) throws CuentaNoCreada {
		// TODO Auto-generated method stub
		Cuenta cuentaExistente = null;
		Cuenta cuentaNueva = null;
		
		cuentaExistente = obtenerCuenta(id, true);
		if (cuentaExistente != null) {
			throw new CuentaNoCreada("No ha sido posible crear la cuenta.");
		} else {
			cuentaNueva = new Cuenta();
			
			cuentaNueva.setId(id);
			cuentaNueva.setTotal(total);
			cuentaNueva.setClienteAsociado(cliente);
			cuentaNueva.setEmpleadoAsociado(empleado);
			
			getListaCuentas().add(cuentaNueva);
			util.escribirArchivo("cuentas.txt", total, id, cliente, empleado);
		}
	}

	@Override
	public void actualizarCuenta(Cuenta cuentaSeleccionada, Cuenta nuevaCuenta) throws CuentaNoActualizada {
		Boolean sonIguales  = null;
		
		sonIguales = validarDiferenciaCuenta(cuentaSeleccionada, nuevaCuenta);
		if (sonIguales) {
			throw new CuentaNoActualizada("No es posible actualizar ya que la cuenta existe.");
		} else {
			try {
				util.actualizarArchivo("cuentas.txt", cuentaSeleccionada.getClienteAsociado(), false,
						nuevaCuenta.getId(), nuevaCuenta.getTotal(), nuevaCuenta.getClienteAsociado(), nuevaCuenta.getEmpleadoAsociado());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				cuentaSeleccionada.setId(nuevaCuenta.getId());
				cuentaSeleccionada.setTotal(nuevaCuenta.getTotal());
				cuentaSeleccionada.setClienteAsociado(nuevaCuenta.getClienteAsociado());
				cuentaSeleccionada.setEmpleadoAsociado(nuevaCuenta.getEmpleadoAsociado());	
			}										
		}	
		
	}

	@Override
	public boolean eliminarCuenta(String id) {
		boolean flagEliminado = false;
		Cuenta cuenta = obtenerCuenta(id, false);
		
		if (cuenta != null) {
			getListaCuentas().remove(cuenta);
			
			try {
				util.actualizarArchivo("cuentas.txt", cuenta.getClienteAsociado(), true,
						cuenta.getTotal(), cuenta.getId(), cuenta.getClienteAsociado(), cuenta.getEmpleadoAsociado());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			flagEliminado = true;
		}
		
		return flagEliminado;
	}

	@Override
	public Cuenta obtenerCuenta(String id, boolean byPass) {
		Cuenta cuentaEncontrada = null;
		boolean verificarNoExistenciaTransaccion = verificarNoExistenciaTransaccion(id);
		
		if (byPass || verificarNoExistenciaTransaccion) {
			for (Cuenta cuenta : getListaCuentas()) {
				if (cuenta.getId().equalsIgnoreCase(id)){
					cuentaEncontrada = cuenta;
					break;
				}
			}
		}		
		
		return cuentaEncontrada;
	}	

	@Override
	public void crearCliente(String nombre, String apellido, String cedula, String edad) throws ClienteNoCreado {
		// TODO Auto-generated method stub
		Cliente clienteExistente = null;
		Cliente clienteNuevo = null;
		
		clienteExistente = obtenerCliente(cedula, true);
		if (clienteExistente != null) {
			throw new ClienteNoCreado("No ha sido posible crear el cliente.");
		} else {
			clienteNuevo = new Cliente();
			
			clienteNuevo.setNombre(nombre);
			clienteNuevo.setApellido(apellido);
			clienteNuevo.setCedula(cedula);
			clienteNuevo.setEdad(edad);
			
			getListaClientes().add(clienteNuevo);
			util.escribirArchivo("clientes.txt", nombre, apellido, cedula, edad);
		}
	}

	@Override
	public void actualizarCliente(Cliente cliente, Cliente nuevoCliente) throws ClienteNoActualizado {
		Boolean sonIguales  = null;
		
		sonIguales = validarDiferenciaPersona(cliente, nuevoCliente);
		if (sonIguales) {
			throw new ClienteNoActualizado("No es posible actualizar ya que el cliente existe.");
		} else {
			try {
				util.actualizarArchivo("clientes.txt", cliente.getCedula(), false,
						nuevoCliente.getNombre(), nuevoCliente.getApellido(), nuevoCliente.getCedula(), nuevoCliente.getEdad());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				cliente.setNombre(nuevoCliente.getNombre());
				cliente.setApellido(nuevoCliente.getApellido());
				cliente.setCedula(nuevoCliente.getCedula());
				cliente.setEdad(nuevoCliente.getEdad());
			}											
		}				
	}

	@Override
	public boolean eliminarCliente(String cedula) {
		boolean flagEliminado = false;
		Cliente cliente = obtenerCliente(cedula, false);
		
		if (cliente != null) {
			getListaClientes().remove(cliente);
			
			try {
				util.actualizarArchivo("clientes.txt", cliente.getCedula(), true,
						cliente.getNombre(), cliente.getApellido(), cliente.getCedula(), cliente.getEdad());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			flagEliminado = true;
		}
		
		return flagEliminado;
	}

	@Override
	public Cliente obtenerCliente(String cedula, boolean byPass) {
		Cliente clienteEncontrado = null;
		boolean verificarNoExistenciaTransaccion = verificarNoExistenciaTransaccion(cedula);
		if (byPass || verificarNoExistenciaTransaccion) {
			for (Cliente cliente : getListaClientes()) {
				if (cliente.getCedula().equalsIgnoreCase(cedula)){
					clienteEncontrado = cliente;
					break;
				}
			}
		}
		
		return clienteEncontrado;
	}		
	
	public boolean verificarNoExistenciaTransaccion(String clave){
		boolean verificarNoExistencia = true;
		
		for(Transaccion transaccion : getListaTransacciones()) {
			if (transaccion.getClienteAsociado().equalsIgnoreCase(clave) || 
					transaccion.getEmpleadoAsociado().equalsIgnoreCase(clave) ||
						transaccion.getId().equalsIgnoreCase(clave)
					) {																
				verificarNoExistencia = false;
				break;
			}
		}
		
		return verificarNoExistencia;
	}

	@Override
	public void crearTransaccion(String tipo, String id, String cliente, String empleado) throws TransaccionNoCreada {
		// TODO Auto-generated method stub
		Transaccion transaccionExistente = null;
		Transaccion transaccionNueva = null;
				
		transaccionExistente = obtenerTransaccion(id);
		if (transaccionExistente != null) {
			throw new TransaccionNoCreada("No ha sido posible crear la cuenta.");
		} else {
			transaccionNueva = new Transaccion();
						
			transaccionNueva.setTipo(tipo);
			transaccionNueva.setId(id);
			transaccionNueva.setClienteAsociado(cliente);
			transaccionNueva.setEmpleadoAsociado(empleado);
					
			getListaTransacciones().add(transaccionNueva);
			util.escribirArchivo("transacciones.txt", tipo, id, cliente, empleado); 
		}
		
	}

	@Override
	public void actualizarTransaccion(Transaccion transaccion, Transaccion nuevaTransaccion) throws TransaccionNoActualizada {
		Boolean sonIguales  = null;
		
		sonIguales = validarDiferenciaTransaccion(transaccion, nuevaTransaccion);
		if (sonIguales) {
			throw new TransaccionNoActualizada("No es posible actualizar ya que la transaccion existe.");
		} else {
			try {
				util.actualizarArchivo("transacciones.txt", transaccion.getClienteAsociado(), false,
						nuevaTransaccion.getTipo(), nuevaTransaccion.getId(), nuevaTransaccion.getClienteAsociado(), nuevaTransaccion.getEmpleadoAsociado());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				transaccion.setTipo(nuevaTransaccion.getTipo());
				transaccion.setId(nuevaTransaccion.getId());
				transaccion.setClienteAsociado(nuevaTransaccion.getClienteAsociado());
				transaccion.setEmpleadoAsociado(nuevaTransaccion.getEmpleadoAsociado());	
			}							
		}	
		
	}

	@Override
	public boolean eliminarTransaccion(String cedula) {
		boolean flagEliminado = false;
		Transaccion transaccion = obtenerTransaccion(cedula);
		
		if (transaccion != null) {
			getListaTransacciones().remove(transaccion);
			
			try {
				util.actualizarArchivo("transacciones.txt", transaccion.getClienteAsociado(), true,
						transaccion.getTipo(), transaccion.getId(), transaccion.getClienteAsociado(), transaccion.getEmpleadoAsociado());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			flagEliminado = true;
		}
		
		return flagEliminado;
	}

	@Override
	public Transaccion obtenerTransaccion(String id) {
		Transaccion transaccionEncontrada = null;
		
		for (Transaccion transaccion: getListaTransacciones()) {
			if (transaccion.getId().equalsIgnoreCase(id)){
				transaccionEncontrada = transaccion;
				break;
			}
		}
		
		return transaccionEncontrada;
	}
	
	public Boolean validarDiferenciaPersona(Object args1, Object args2) {		
		boolean igualNombre = ((Persona) args1).getNombre().equalsIgnoreCase(((Persona) args2).getNombre());
		boolean igualApellido = ((Persona) args1).getApellido().equalsIgnoreCase(((Persona) args2).getApellido());;
		boolean igualCedula = ((Persona) args1).getCedula().equalsIgnoreCase(((Persona) args2).getCedula());;
		boolean igualEdad = ((Persona) args1).getEdad().equalsIgnoreCase(((Persona) args2).getEdad());;
		
		boolean sonIguales = false;
		if (igualNombre && igualApellido && igualCedula && igualEdad) {
			sonIguales = true;
		}
		
		return sonIguales;
	}
	
	public Boolean validarDiferenciaCuenta(Object args1, Object args2) {		
		boolean igualNombre = ((Cuenta) args1).getId().equalsIgnoreCase(((Cuenta) args2).getId());
		boolean igualApellido = ((Cuenta) args1).getTotal().equalsIgnoreCase(((Cuenta) args2).getTotal());;
		boolean igualCedula = ((Cuenta) args1).getClienteAsociado().equalsIgnoreCase(((Cuenta) args2).getClienteAsociado());;
		boolean igualEdad = ((Cuenta) args1).getEmpleadoAsociado().equalsIgnoreCase(((Cuenta) args2).getEmpleadoAsociado());;
		
		boolean sonIguales = false;
		if (igualNombre && igualApellido && igualCedula && igualEdad) {
			sonIguales = true;
		}
		
		return sonIguales;
	}
	
	public Boolean validarDiferenciaTransaccion(Object args1, Object args2) {		
		boolean igualNombre = ((Transaccion) args1).getTipo().equalsIgnoreCase(((Transaccion) args2).getTipo());
		boolean igualApellido = ((Transaccion) args1).getId().equalsIgnoreCase(((Transaccion) args2).getId());;
		boolean igualCedula = ((Transaccion) args1).getClienteAsociado().equalsIgnoreCase(((Transaccion) args2).getClienteAsociado());;
		boolean igualEdad = ((Transaccion) args1).getEmpleadoAsociado().equalsIgnoreCase(((Transaccion) args2).getEmpleadoAsociado());;
		
		boolean sonIguales = false;
		if (igualNombre && igualApellido && igualCedula && igualEdad) {
			sonIguales = true;
		}
		
		return sonIguales;
	}	
}
