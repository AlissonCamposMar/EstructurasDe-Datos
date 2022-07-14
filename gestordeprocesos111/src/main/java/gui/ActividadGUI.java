package gui;

/**
 * @author Alisson Campos Marin
 * @author Jenny Marcela Tellez
 * @author Johan Andrey Ortiz
 */
import model.Actividad;

public class ActividadGUI {
	private Actividad actividad;

	public ActividadGUI(Actividad actividad) {
		super();
		this.actividad = actividad;
	}

	/**
	 * Metodo que permite obtener el valor del atributo actividad
	 * @return El valor del atributo actividad
	 */
	public Actividad getActividad() {
		return actividad;
	}

	@Override
	public String toString() {
		return  actividad.getNombre();
	}
	
	
}
