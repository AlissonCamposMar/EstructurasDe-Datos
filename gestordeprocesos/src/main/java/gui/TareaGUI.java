package gui;

import model.Tarea;

public class TareaGUI {
	private Tarea tarea;

	public TareaGUI(Tarea tarea) {
		super();
		this.tarea = tarea;
	}

	/**
	 * Metodo que permite obtener el valor del atributo tarea
	 * 
	 * @return El valor del atributo tarea
	 */
	public Tarea getTarea() {
		return tarea;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return tarea.getDescripcion()+" - " + tarea.getTiempo() + " - " + (tarea.isObligatorio() ? "Obligatoria" : "Opcional");
	}

	
}
