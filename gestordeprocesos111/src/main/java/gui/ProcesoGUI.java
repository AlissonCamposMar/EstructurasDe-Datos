package gui;

import model.Proceso;

public class ProcesoGUI {
	private Proceso proceso;

	public ProcesoGUI(Proceso proceso) {
		super();
		this.proceso = proceso;
	}

	/**
	 * Metodo que permite obtener el valor del atributo proceso
	 * @return El valor del atributo proceso
	 */
	public Proceso getProceso() {
		return proceso;
	}

	/**
	 * Metodo que permite asignar un valor al atributo proceso
	 * @param proceso Valor a ser asignado al atributo proceso
	 */
	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	@Override
	public String toString() {
		return proceso.getId() + " - "+ proceso.getNombre();
	}
	
	
}
