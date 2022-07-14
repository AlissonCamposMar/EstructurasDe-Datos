package co.edu.uniquindio.dulcelandia.services;

import javax.swing.JOptionPane;

import co.edu.uniquindio.dulcelandia.exceptions.ClienteException;
import co.edu.uniquindio.dulcelandia.exceptions.EmpleadoException;
import co.edu.uniquindio.dulcelandia.exceptions.ProductoException;

public interface ITiendaServices {
	public void crearProducto (String nombreProducto, String codigo, String precio, 
			String cantidadExistencias)throws ProductoException;
	public boolean eliminarProducto (String codigo);
	
	public void crearCliente (String nombre, String cedula, String telefono, String email)throws ClienteException;
	public boolean eliminarCliente(String cedula);
	
	public void crearEmpleado (String nombre, String cedula, String telefono, String email)throws EmpleadoException;
	public boolean eliminarEmpleado(String cedula);
    
}
