package co.edu.uniquindio.banco.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import co.edu.uniquindio.banco.model.services.ICuentaService;

public class Cuenta implements ICuentaService , Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String numeroCuenta;
	private Double saldo;
	HashMap<String, Transaccion> listaTransacciones = new HashMap<>();
	
	public Cuenta() {
		// TODO Auto-generated constructor stub
	}

	
	
	@Override
	public void retirarDinero(Double cantidad) {
		// TODO Auto-generated method stub
		
	}
	
	
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	
	

	public HashMap<String,Transaccion> getListaTransacciones() {
		return listaTransacciones;
	}

	public void setListaTransacciones(HashMap<String, Transaccion> listaTransacciones) {
		this.listaTransacciones = listaTransacciones;
	}



	@Override
	public void consultarDinero(String usuario, String contrasenia) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void consultarSaldo(String usuario, String contrasenia) {
		// TODO Auto-generated method stub
		
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
