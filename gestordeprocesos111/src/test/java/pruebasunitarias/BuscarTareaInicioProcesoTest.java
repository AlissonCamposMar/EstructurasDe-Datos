package pruebasunitarias;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import estructuras.Cola;
import exceptions.ActividadNoExisteException;
import exceptions.ValidacionIDProcesoException;
import exceptions.ValidacionNombreActividadException;
import exceptions.ValidacionTareaOpcionalException;
import model.GestorDeProcesos;
import model.Tarea;
import pruebasrapidas.Main;

public class BuscarTareaInicioProcesoTest {

	@Test
	public void test() throws ValidacionIDProcesoException, ValidacionNombreActividadException, ActividadNoExisteException {
		GestorDeProcesos gestor = null;
		try {
			gestor = Main.crearSetPruebas();
		} catch (ValidacionIDProcesoException | ValidacionNombreActividadException
				| ValidacionTareaOpcionalException e) {
			fail("Error en la creación del set de pruebas " + e.getMessage() );
			return;
		}
		
		
		Cola<Tarea> resultado = gestor.obtenerProceso("p2").buscarTareaDesdeElInicio("Elaborar platinas");
		
		Assert.assertEquals(1,resultado.getLongitud());
		
		Tarea tarea = resultado.poll();
		
		Assert.assertEquals("Elaborar platinas",tarea.getDescripcion());
		Assert.assertEquals(true,tarea.isObligatorio());
		Assert.assertEquals(17,tarea.getTiempo());
			
		
	}

}
