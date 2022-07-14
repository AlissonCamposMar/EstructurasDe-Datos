package pruebasunitarias;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import exceptions.ActividadNoExisteException;
import exceptions.ValidacionIDProcesoException;
import exceptions.ValidacionNombreActividadException;
import exceptions.ValidacionTareaOpcionalException;
import model.GestorDeProcesos;
import model.Tarea;
import pruebasrapidas.Main;

public class BuscarTareaActividadActualTest {

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
		
		gestor.obtenerProceso("p2").buscarActividadNombre("Prensar");
		Tarea tarea = gestor.obtenerProceso("p2").buscarTareaEnActividadActual("Cortar metal");
		
		Assert.assertEquals("Cortar metal",tarea.getDescripcion());
		Assert.assertEquals(true,tarea.isObligatorio());
		Assert.assertEquals(13,tarea.getTiempo());
			
		
	}

}
