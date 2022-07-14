package co.edu.uniquindio.restaurante.services;

import co.edu.uniquindio.restaurante.model.Cliente;
import co.edu.uniquindio.restaurante.model.Cuenta;
import co.edu.uniquindio.restaurante.model.Empleado;
import co.edu.uniquindio.restaurante.model.Transaccion;
import co.edu.uniquindio.restaurante.exceptions.ClienteNoActualizado;
import co.edu.uniquindio.restaurante.exceptions.ClienteNoCreado;
import co.edu.uniquindio.restaurante.exceptions.CuentaNoActualizada;
import co.edu.uniquindio.restaurante.exceptions.CuentaNoCreada;
import co.edu.uniquindio.restaurante.exceptions.EmpleadoNoActualizado;
import co.edu.uniquindio.restaurante.exceptions.EmpleadoNoCreado;
import co.edu.uniquindio.restaurante.exceptions.TransaccionNoActualizada;
import co.edu.uniquindio.restaurante.exceptions.TransaccionNoCreada;

public interface IRestauranteServices {
	public void crearEmpleado(String nombre, String apellido, String cedula, String edad) throws EmpleadoNoCreado;
	public void actualizarEmpleado(Empleado empleado, Empleado nuevoEmpleado) throws EmpleadoNoActualizado;
	public boolean eliminarEmpleado(String cedula);
	public Empleado obtenerEmpleado(String cedula, boolean byPass);
	
	public void crearCuenta(String id, String total, String cliente, String empleado) throws CuentaNoCreada;
	public void actualizarCuenta(Cuenta cuentaSeleccionada, Cuenta nuevaCuenta) throws CuentaNoActualizada;
	public boolean eliminarCuenta(String clienteAsociado);
	public Cuenta obtenerCuenta(String cedula, boolean byPass);
	
	public void crearCliente(String nombre, String apellido, String cedula, String edad) throws ClienteNoCreado;
	public void actualizarCliente(Cliente cliente, Cliente nuevoCliente) throws ClienteNoActualizado;
	public boolean eliminarCliente(String cedula);
	public Cliente obtenerCliente(String cedula, boolean byPass);
	
	public void crearTransaccion(String tipo, String total, String cliente, String empleado) throws TransaccionNoCreada ;
	public void actualizarTransaccion(Transaccion transaccion, Transaccion nuevaTransaccion) throws TransaccionNoActualizada;
	public boolean eliminarTransaccion(String cedula);
	public Transaccion obtenerTransaccion(String cedula);
}
