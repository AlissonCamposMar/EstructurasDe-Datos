package co.edu.uniquindio.restaurante.controllers;

import co.edu.uniquindio.restaurante.util.Persistencia;

import java.io.FileNotFoundException;
import java.io.IOException;

import co.edu.uniquindio.restaurante.exceptions.ClienteNoActualizado;
import co.edu.uniquindio.restaurante.exceptions.ClienteNoCreado;
import co.edu.uniquindio.restaurante.exceptions.CuentaNoActualizada;
import co.edu.uniquindio.restaurante.exceptions.CuentaNoCreada;
import co.edu.uniquindio.restaurante.exceptions.EmpleadoNoActualizado;
import co.edu.uniquindio.restaurante.exceptions.EmpleadoNoCreado;
import co.edu.uniquindio.restaurante.exceptions.TransaccionNoActualizada;
import co.edu.uniquindio.restaurante.exceptions.TransaccionNoCreada;
import co.edu.uniquindio.restaurante.model.Cliente;
import co.edu.uniquindio.restaurante.model.Cuenta;
import co.edu.uniquindio.restaurante.model.Empleado;
import co.edu.uniquindio.restaurante.model.Restaurante;
import co.edu.uniquindio.restaurante.model.Transaccion;
import co.edu.uniquindio.restaurante.services.IModelFactoryService;

public class ModelFactoryController extends Thread implements IModelFactoryService{
	
	private Restaurante restaurante;
	String autentificacion;
	
	private static class SingletonHolder {
		private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
	}
	
	public static ModelFactoryController getInstance() {
		return SingletonHolder.eINSTANCE;
	}
	
	public ModelFactoryController() {
		try {
			inicializarDatos();						
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}					
	}
	
	public void run() {
		guardarResourceBinario();		
		cargarResourceBinario();
	}
	
	public String getAutentificacion() {
		return autentificacion;
	}
	
	public String setAutentificacion(String autentificacion) {
		return this.autentificacion = autentificacion;
	}
	
	private void guardarResourceBinario() {			
		Persistencia.guardarRecursoRestauranteBinario(restaurante);
		
	}
	
	private void cargarResourceBinario() {
		restaurante = Persistencia.cargarRecursoRestauranteBinario();		
	}
	
	public Boolean iniciarSesion(String usuario, String contraseña) throws FileNotFoundException, IOException {
		return restaurante.verificarInicioSesion(usuario, contraseña);
	}

	private void inicializarDatos() throws IOException{		
		restaurante = new Restaurante();
		
		Persistencia.cargarDatosClientes(restaurante);
		Persistencia.cargarDatosEmpleados(restaurante);
		Persistencia.cargarDatosCuentas(restaurante);
		Persistencia.cargarDatosTransacciones(restaurante);
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	@Override
	public String crearEmpleado(String nombre, String apellido, String cedula, String edad) {
		String mensaje = "";
		try {
			getRestaurante().crearEmpleado(nombre, apellido, cedula, edad);
			mensaje = "Empleado creado con exito.";
		} catch (EmpleadoNoCreado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mensaje = e.getMessage();
		}		
		return mensaje;
	}
	
	@Override
	public boolean actualizarEmpleado(Empleado empleado, Empleado nuevoEmpleado) {
		boolean flagActualizado = false;
		try {
			getRestaurante().actualizarEmpleado(empleado, nuevoEmpleado);
			flagActualizado = true;
		} catch (EmpleadoNoActualizado e) {
			e.printStackTrace();
			flagActualizado = false;
		}
		
		return flagActualizado;
	}

	@Override
	public boolean eliminarEmpleado(Empleado empleado) {
		return getRestaurante().eliminarEmpleado(empleado.getCedula());
	}

	@Override
	public Empleado obtenerEmpleado(String cedula) {
		// TODO Auto-generated method stub
		return null;
	}	
	
	@Override
	public String crearCuenta(String total, String id, String cliente, String empleado) {
		String mensaje = "";
		try {
			getRestaurante().crearCuenta(total, id, cliente, empleado);
			mensaje = "Cuenta creada con exito.";
		} catch (CuentaNoCreada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mensaje = e.getMessage();
		}		
		return mensaje;
	}
	
	@Override
	public boolean actualizarCuenta(Cuenta cuentaSeleccionada, Cuenta nuevaCuenta) {
		boolean flagActualizado = false;
		try {
			getRestaurante().actualizarCuenta(cuentaSeleccionada, nuevaCuenta);
			flagActualizado = true;
		} catch (CuentaNoActualizada e) {
			e.printStackTrace();
			flagActualizado = false;
		}
		
		return flagActualizado;
	}

	@Override
	public boolean eliminarCuenta(Cuenta cuenta) {
		return getRestaurante().eliminarCuenta(cuenta.getId());
	}

	@Override
	public Cuenta obtenerCuenta(String cedula) {
		return null;
	}

	public String crearCliente(String nombre, String apellido, String cedula, String edad) {
		String mensaje = "";
		try {
			getRestaurante().crearCliente(nombre, apellido, cedula, edad);
			mensaje = "Cliente creado con exito.";
		} catch (ClienteNoCreado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mensaje = e.getMessage();
		}		
		return mensaje;
	}

	public boolean actualizarCliente(Cliente cliente, Cliente nuevoCliente) {
		boolean flagActualizado = false;
		try {
			getRestaurante().actualizarCliente(cliente, nuevoCliente);
			flagActualizado = true;
		} catch (ClienteNoActualizado e) {
			e.printStackTrace();
			flagActualizado = false;
		}
		
		return flagActualizado;
	}
	
	public boolean eliminarCliente(Cliente empleadoCliente) {
		return getRestaurante().eliminarCliente(empleadoCliente.getCedula());
	}	
	
	@Override
	public Cuenta obtenerCliente(String cedula) {
		return null;
	}

	public String crearTransaccion(String estado, String id, String cliente, String empleado) {
		String mensaje = "";
		try {
			getRestaurante().crearTransaccion(estado, id, cliente, empleado);
			mensaje = "Transaccion creada con exito.";
		} catch (TransaccionNoCreada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mensaje = e.getMessage();
		}		
		return mensaje;
	}

	public boolean actualizarTransaccion(Transaccion transaccionSeleccionada, Transaccion nuevaTransaccion) {
		boolean flagActualizado = false;
		try {
			getRestaurante().actualizarTransaccion(transaccionSeleccionada, nuevaTransaccion);
			flagActualizado = true;
		} catch (TransaccionNoActualizada e) {
			e.printStackTrace();
			flagActualizado = false;
		}
		
		return flagActualizado;
	}
	
	@Override
	public boolean eliminarTransaccion(Transaccion transaccion) {
		return getRestaurante().eliminarTransaccion(transaccion.getId());
	}
	
	@Override
	public Cuenta obtenerTransaccion(String cedula) {
		return null;
	}
}
