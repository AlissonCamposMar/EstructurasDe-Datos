package co.edu.uniquindio.restaurante.services;

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
import co.edu.uniquindio.restaurante.model.Transaccion;

public interface IModelFactoryService {
	public String crearEmpleado(String nombre, String apellido, String cedula, String edad) throws EmpleadoNoCreado;
	public boolean actualizarEmpleado(Empleado empleado, Empleado nuevoEmpleado) throws EmpleadoNoActualizado;
	public boolean eliminarEmpleado(Empleado empleado);
	public Empleado obtenerEmpleado(String cedula);
	
	public String crearCuenta(String id, String total, String cliente, String empleado) throws CuentaNoCreada;
	public boolean actualizarCuenta(Cuenta cuentaSeleccionada, Cuenta nuevaCuenta) throws CuentaNoActualizada;
	public boolean eliminarCuenta(Cuenta cuentaSeleccionada);
	public Cuenta obtenerCuenta(String cedula);
	
	
	public String crearCliente(String nombre, String apellido, String cedula, String edad) throws ClienteNoCreado;
	public boolean actualizarCliente(Cliente cliente, Cliente nuevoCliente) throws ClienteNoActualizado;
	public boolean eliminarCliente(Cliente empleadoCliente);
	public Cuenta obtenerCliente(String cedula);
	
	public String crearTransaccion(String tipo, String total, String cliente, String empleado) throws TransaccionNoCreada;
	public boolean actualizarTransaccion(Transaccion transaccionSeleccionada, Transaccion nuevaTransaccion) throws TransaccionNoActualizada;
	public boolean eliminarTransaccion(Transaccion transaccion);
	public Cuenta obtenerTransaccion(String cedula);
}
