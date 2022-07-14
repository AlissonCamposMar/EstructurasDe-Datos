package co.edu.uniquindio.banco.model.services;

public interface ICuentaService {

	public void retirarDinero (Double cantidad);
	
	public void consultarDinero(String usuario, String contrasenia);
	
	public void consultarSaldo(String usuario, String contrasenia);

	void crearTransaccion(String numeroCuenta, Double valor, Double saldo, String fecha, String hora);
	
	
}
