package pruebasunitarias;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import exceptions.ActividadNoExisteException;
import exceptions.ValidacionIDProcesoException;
import exceptions.ValidacionNombreActividadException;
import exceptions.ValidacionTareaOpcionalException;
import model.Actividad;
import model.GestorDeProcesos;
import model.Proceso;
import model.Tarea;
import pruebasrapidas.Main;

public class IntercambiarActividadesSinTareasTest {

	@Test
	public void test() throws ActividadNoExisteException {
		GestorDeProcesos gestor = null;
		try {
			gestor = Main.crearSetPruebas();
		} catch (ValidacionIDProcesoException | ValidacionNombreActividadException
				| ValidacionTareaOpcionalException e) {
			fail("Error en la creacion del set de pruebas " + e.getMessage() );
			return;
		}
gestor.obtenerProceso("p2").intercambiarActividadesSinTarea("Fundir", "Carrocería");
		
		Proceso p2 = gestor.obtenerProceso("p2");
		String nombresEsperados[] = {"Carrocería","Prensar","Fundir","Pintura", "Ensamble y montaje"}; 
		int i = 0;
		for (Actividad actividad : p2) {
			Assert.assertEquals(nombresEsperados[i], actividad.getNombre());
			i++;
		}
		
	}  

}
