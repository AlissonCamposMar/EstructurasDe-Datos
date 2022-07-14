package co.edu.uniquindio.restaurante.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import co.edu.uniquindio.restaurante.model.Cliente;
import co.edu.uniquindio.restaurante.model.Cuenta;
import co.edu.uniquindio.restaurante.model.Empleado;
import co.edu.uniquindio.restaurante.model.Restaurante;
import co.edu.uniquindio.restaurante.model.Transaccion;

public class Persistencia {
	public static final String path = "persistencia/archivos/";
	public static final String RUTA_ARCHIVO_CLIENTES = "clientes.txt";
	public static final String RUTA_ARCHIVO_CUENTAS = "cuentas.txt";
	public static final String RUTA_ARCHIVO_EMPLEADOS = "empleados.txt";
	public static final String RUTA_ARCHIVO_TRANSACCIONES = "transacciones.txt";
	public static final String RUTA_ARCHIVO_PROPIEDADES = "config.properties";
	
	public static final String RUTA_ARCHIVO_MODELO_RESTAURANTE_BINARIO = "persistencia/model.dat";
	public static final String RUTA_ARCHIVO_MODELO_RESTAURANTE_XML = "persistencia/model.xml";
	
	static Properties propiedades = new Properties();
	
	public static Properties leerPropiedades() throws FileNotFoundException, IOException{
		propiedades.load(new FileReader(path+RUTA_ARCHIVO_PROPIEDADES));		
		return propiedades;  		    
	}
	
	public static void cargarDatosClientes(Restaurante restaurante) throws IOException {
		ArrayList<String> contenido = util.leerArchivo(path+RUTA_ARCHIVO_CLIENTES);
		String[] contenidoSplit;
		Cliente cliente;
		
		for (String linea : contenido) {
			cliente = new Cliente();			
			contenidoSplit = linea.split("@@");
						
			cliente.setNombre(contenidoSplit[0]);
			cliente.setApellido(contenidoSplit[1]);
			cliente.setCedula(contenidoSplit[2]);
			cliente.setEdad(contenidoSplit[3]);
			restaurante.getListaClientes().add(cliente);
		}
	}
	
	public static void cargarDatosEmpleados(Restaurante restaurante) throws IOException {
		ArrayList<String> contenido = util.leerArchivo(path+RUTA_ARCHIVO_EMPLEADOS);
		String[] contenidoSplit;
		Empleado empleado;
		
		for (String linea : contenido) {
			empleado = new Empleado();			
			contenidoSplit = linea.split("@@");
						
			empleado.setNombre(contenidoSplit[0]);
			empleado.setApellido(contenidoSplit[1]);
			empleado.setCedula(contenidoSplit[2]);
			empleado.setEdad(contenidoSplit[3]);
			restaurante.getListaEmpleados().add(empleado);
		}
	}
	
	public static void cargarDatosCuentas(Restaurante restaurante) throws IOException {
		ArrayList<String> contenido = util.leerArchivo(path+RUTA_ARCHIVO_CUENTAS);
		String[] contenidoSplit;
		Cuenta cuenta;
		
		for (String linea : contenido) {
			cuenta = new Cuenta();			
			contenidoSplit = linea.split("@@");
				
			cuenta.setId(contenidoSplit[0]);			
			cuenta.setTotal(contenidoSplit[1]);
			cuenta.setClienteAsociado(contenidoSplit[2]);
			cuenta.setEmpleadoAsociado(contenidoSplit[3]);			
			restaurante.getListaCuentas().add(cuenta);
		}
	}
	
	public static void cargarDatosTransacciones(Restaurante restaurante) throws IOException {
		ArrayList<String> contenido = util.leerArchivo(path+RUTA_ARCHIVO_TRANSACCIONES);
		String[] contenidoSplit;
		Transaccion transaccion;
		
		for (String linea : contenido) {
			transaccion = new Transaccion();			
			contenidoSplit = linea.split("@@");
			
			transaccion.setTipo(contenidoSplit[0]);
			transaccion.setId(contenidoSplit[1]);
			transaccion.setClienteAsociado(contenidoSplit[2]);
			transaccion.setEmpleadoAsociado(contenidoSplit[3]);			
			restaurante.getListaTransacciones().add(transaccion);
		}
	}
	
	public static Restaurante cargarRecursoRestauranteBinario() {
		
		Restaurante restaurante = null;
		
		try {
			restaurante = (Restaurante) util.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_RESTAURANTE_BINARIO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return restaurante;
	}
	
	public static void guardarRecursoRestauranteBinario(Restaurante restaurante) {
		
		try {
			util.salvarRecursoSerializado(RUTA_ARCHIVO_MODELO_RESTAURANTE_BINARIO, restaurante);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static Restaurante cargarRecursoRestauranteXML() {
		
		Restaurante restaurante = null;
		
		try {
			restaurante = (Restaurante) util.cargarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_RESTAURANTE_XML);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return restaurante;

	}

	
	
	public static void guardarRecursoRestauranteXML(Restaurante restaurante) {
		
		try {
			util.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_RESTAURANTE_XML, restaurante);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
