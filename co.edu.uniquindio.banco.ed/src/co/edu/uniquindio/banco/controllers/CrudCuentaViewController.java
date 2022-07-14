package co.edu.uniquindio.banco.controllers;

import co.edu.uniquindio.banco.model.Banco;

public class CrudCuentaViewController {
	
	ModelFactoryController modelFactoryController;
	static Banco banco;
	
	public CrudCuentaViewController () {
		modelFactoryController = ModelFactoryController.getInstance();
		banco = modelFactoryController.getBanco();
	}
	
	public static Banco getBanco() {
		return banco;
	}


	public void setBanco(Banco banco) {
		this.banco = banco;
	}
	
	public ModelFactoryController getModelFactoryController() {
		return modelFactoryController;
		
	}
	
    public void crearCuenta(String numeroCuenta, Double saldo) {
    	modelFactoryController.crearCuenta(numeroCuenta, saldo);
    }

}
