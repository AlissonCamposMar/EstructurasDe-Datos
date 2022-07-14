package modelo;

import org.junit.Assert;
import org.junit.Test;

import model.Actividad;
import model.Tarea;

public class Ejemplo {

	@Test
	public void testCrearTareaAlFinal() {
		Actividad actividad = new Actividad("Prueba", "Actividad de prueba", true);
	//	actividad.crearTareaAlFinal("Tarea01", true, 12);
		
		Tarea tarea = actividad.buscarTareaDescripcion("Tarea01");
		
		Assert.assertNotNull(tarea);
		Assert.assertEquals("Tarea01", tarea.getDescripcion());
		Assert.assertEquals(true, tarea.isObligatorio());
		Assert.assertEquals(12, tarea.getTiempo());
		
	}


}
