package co.edu.uniquindio.banco.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import co.edu.uniquindio.banco.exceptions.ClienteException;
import co.edu.uniquindio.banco.exceptions.EmpleadoException;
import co.edu.uniquindio.banco.model.services.IBancoService;

public class Banco implements IBancoService, Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	class Comparacion implements Comparator {

		public int compare(Object x1, Object x2) {
			Cuenta c1 = (Cuenta) x1;
			Cuenta c2 = (Cuenta) x2;
			return c1.getNumeroCuenta().compareTo(c2.getNumeroCuenta());
		}
	}

//	HashSet
	public Set<Cliente> listaClientes = new HashSet<>();

//	TreeSet
	public Set<Empleado> listaEmpleados = new TreeSet<>();
	TreeSet listaTransaccionesAsociadas = new TreeSet(new Comparacion());

//	HashMap	
	HashMap<String, Cuenta> listaCuentas = new HashMap<>();
	HashMap<String, Transaccion> listaTransacciones = new HashMap<>();

//	Iterator
	Iterator<Cliente> iteratorCliente = getListaClientes().iterator();
	Iterator<Empleado> iteratorEmpleado = getListaEmpleados().iterator();
	Iterator<String> iteratorCuenta = getListaCuentas().keySet().iterator();

	public Iterator<Cliente> getIteratorCliente() {
		return iteratorCliente;
	}

	public void setIteratorCliente(Iterator<Cliente> iteratorCliente) {
		this.iteratorCliente = iteratorCliente;
	}

	public Banco() {

	}

	// CLIENTE
	public void crearCliente(String nombre, String apellido, String cedula, String direccion, String telefono,
			String correo, String fechaNacimiento) throws ClienteException {

		Empleado nuevoEmpleado = null;
		Empleado empleadoExistente = obtenerEmpleado(cedula);

		if (empleadoExistente != null)
			throw new ClienteException("El cliente con cédula " + cedula + " no se puede crear. Ya existe");
		else {
			nuevoEmpleado = new Empleado();
			nuevoEmpleado.setNombre(nombre);
			nuevoEmpleado.setApellido(apellido);
			nuevoEmpleado.setCedula(cedula);
			nuevoEmpleado.setDireccion(direccion);
			nuevoEmpleado.setTelefono(telefono);
			nuevoEmpleado.setCorreo(correo);
			nuevoEmpleado.setFechaNacimiento(fechaNacimiento);

			listaEmpleados.add(nuevoEmpleado);
		}

	}

	@Override
	public void actualizarCliente(String nombre, String apellido, String cedula, String direccion, String telefono,
			String correo, String fechaNacimiento) {
		Cliente cliente = obtenerCliente(cedula);

		if (cliente != null) {
			cliente.setNombre(nombre);
			cliente.setApellido(apellido);
			cliente.setCedula(cedula);
			cliente.setDireccion(direccion);
			cliente.setTelefono(telefono);
			cliente.setCorreo(correo);
			cliente.setFechaNacimiento(fechaNacimiento);
		}
	}

	@Override
	public boolean eliminarCliente(String cedula) {

		Cliente cliente = null;
		cliente = obtenerCliente(cedula);

		if (cliente != null) {
			getListaClientes().remove(cliente);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Cliente obtenerCliente(String cedulaCliente) {

		Cliente clienteEncontrado = null;
		iteratorCliente = getListaClientes().iterator();

		while (iteratorCliente.hasNext()) {
			clienteEncontrado = iteratorCliente.next();

			if (clienteEncontrado.getCedula().equalsIgnoreCase(cedulaCliente)) {
				return clienteEncontrado;
			}
		}

		return clienteEncontrado;
	}

	@Override
	public void crearEmpleado(String nombre, String apellido, String cedula, String direccion, String telefono,
			String correo, String fechaNacimiento, String codigo, Double salario) throws EmpleadoException {

		Empleado nuevoEmpleado = null;
		Empleado empleadoExistente = obtenerEmpleado(cedula);

		if (empleadoExistente != null)
			throw new EmpleadoException("El empleado con cédula " + cedula + " no se puede crear. Ya existe");
		else {
			nuevoEmpleado = new Empleado();
			nuevoEmpleado.setNombre(nombre);
			nuevoEmpleado.setApellido(apellido);
			nuevoEmpleado.setCedula(cedula);
			nuevoEmpleado.setDireccion(direccion);
			nuevoEmpleado.setTelefono(telefono);
			nuevoEmpleado.setCorreo(correo);
			nuevoEmpleado.setFechaNacimiento(fechaNacimiento);
			nuevoEmpleado.setCodigo(codigo);
			nuevoEmpleado.setSalario(salario);

			listaEmpleados.add(nuevoEmpleado);
		}

	}

	@Override
	public void actualizarEmpleado(String nombre, String apellido, String cedula, String direccion, String telefono,
			String correo, String fechaNacimiento, String codigo, Double salario) {

		Empleado empleado = obtenerEmpleado(cedula);

		if (empleado != null) {
			empleado.setNombre(nombre);
			empleado.setApellido(apellido);
			empleado.setCedula(cedula);
			empleado.setDireccion(direccion);
			empleado.setTelefono(telefono);
			empleado.setCorreo(correo);
			empleado.setFechaNacimiento(fechaNacimiento);
			empleado.setCodigo(codigo);
			empleado.setSalario(salario);
		}
	}

	@Override
	public Boolean eliminarEmpleado(String cedula) {

		Boolean flagEliminado = false;
		Empleado empleado = obtenerEmpleado(cedula);

		if (empleado != null) {
			getListaEmpleados().remove(empleado);
			flagEliminado = true;
		}

		return flagEliminado;
	}

	@Override
	public Empleado obtenerEmpleado(String cedula) {

		Empleado empleadoEncontrado = null;

		for (Empleado empleado : getListaEmpleados()) {
			if (empleado.getCedula().equalsIgnoreCase(cedula)) {
				empleadoEncontrado = empleado;
				break;
			}
		}

		return empleadoEncontrado;
	}

/////CUENTA

	public void crearCuenta(String numeroCuenta, Double saldo) {
		Cuenta cuenta = new Cuenta();
		cuenta.setNumeroCuenta(numeroCuenta);
		cuenta.setSaldo(saldo);

		System.out.println(listaCuentas);

		listaCuentas.put(cuenta.getNumeroCuenta(), cuenta);

		System.out.println(listaCuentas);

	}

	public boolean eliminarCuenta(String numeroCuenta) {

		Cuenta cuenta = null;
		cuenta = obtenerCuenta(numeroCuenta);

		if (cuenta != null) {
			getListaCuentas().remove(cuenta, numeroCuenta);

			return true;
		} else {
			return false;
		}
	}

	public Cuenta obtenerCuenta(String numeroCuenta) {

		Cuenta cuenta = null;

		for (Iterator it = getListaCuentas().keySet().iterator(); it.hasNext();) {

			cuenta = (Cuenta) it.next();

			if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {

				return cuenta;
			}
		}

		iteratorCuenta = getListaCuentas().keySet().iterator();

		while (iteratorCuenta.hasNext()) {
			String numCuenta = iteratorCuenta.next();

			if (numCuenta.equals(numeroCuenta)) {
				return cuenta;
			}
		}
		return cuenta;
	}

	@Override
	public void DepositarDineroCuenta(Integer numeroCuenta) {

	}

	@Override
	public void RetirarDineroCuenta(Integer cedula, Integer numeroCuenta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ConsultarSaldoCuenta(Integer numeroCuenta) {
		// TODO Auto-generated method stub

	}

	public Set<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(Set<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public Set<Empleado> getListaEmpleados() {
		return listaEmpleados;
	}

	public void setListaEmpleados(Set<Empleado> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}

	public HashMap<String, Cuenta> getListaCuentas() {
		return listaCuentas;
	}

	public void setListaCuentas(HashMap<String, Cuenta> listaCuentas) {
		this.listaCuentas = listaCuentas;
	}

	public HashMap<String, Transaccion> getListaTransacciones() {
		return listaTransacciones;
	}

	public Set<Cuenta> getListaTransaccionesAsociadas() {
		return listaTransaccionesAsociadas;
	}

	public void setListaTransaccionesAsociadas(TreeSet listaTransaccionesAsociadas) {
		this.listaTransaccionesAsociadas = listaTransaccionesAsociadas;
	}

	@Override

	public void crearTransaccion(String numeroCuenta, Double valor, Double saldo, String fecha, String hora) {

		Transaccion transaccion = new Transaccion();
		transaccion.setNumeroCuenta(numeroCuenta);
		transaccion.setSaldo(saldo);
		transaccion.setValor(valor);
		transaccion.setFecha(fecha);
		transaccion.setHora(hora);

		System.out.println(listaTransacciones);

		listaTransacciones.put(transaccion.getNumeroCuenta(), transaccion);

		System.out.println(listaTransacciones);

	}

}
