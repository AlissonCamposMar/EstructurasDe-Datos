package co.edu.uniquindio.banco.model;

import java.io.Serializable;

public class Empleado extends Persona implements Serializable, Comparable<Empleado>{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Double salario;
	private String codigo;

	
	public Empleado() {
		
	}
	
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Double getSalario() {
		return salario;
	}


	public void setSalario(Double salario) {
		this.salario = salario;
	}


	@Override
	public int compareTo(Empleado e) {
		// TODO Auto-generated method stub
		return this.getCedula().compareTo(e.getCedula());
	}
	
}
