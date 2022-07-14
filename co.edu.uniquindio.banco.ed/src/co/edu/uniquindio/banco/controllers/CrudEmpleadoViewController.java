package co.edu.uniquindio.banco.controllers;

import org.eclipse.swt.widgets.Text;

import co.edu.uniquindio.banco.model.Banco;
import co.edu.uniquindio.banco.model.Empleado;

public class CrudEmpleadoViewController {

	ModelFactoryController modelFactoryController;
	Banco banco;
	
	public CrudEmpleadoViewController() {
		modelFactoryController = ModelFactoryController.getInstance();
		banco = modelFactoryController.getBanco();
	}
	
	public Banco getBanco() {
		return banco;
	}


	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public String  crearEmpleado(String nombre, String apellido, String cedula, String fechaNacimiento,
							  String telefono, String correo, String direccion, String codigo, String salario) {
		
		return modelFactoryController.crearEmpleado(nombre, apellido, cedula, fechaNacimiento, telefono, correo, direccion, codigo, salario);
	}

	public Boolean eliminarEmpleado(Empleado empleado) {
		
		return modelFactoryController.eliminarEmpleado(empleado);
	}

	
}
