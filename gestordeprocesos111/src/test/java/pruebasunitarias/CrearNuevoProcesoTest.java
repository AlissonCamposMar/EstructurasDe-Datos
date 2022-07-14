package pruebasunitarias;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import exceptions.ValidacionIDProcesoException;
import exceptions.ValidacionNombreActividadException;
import exceptions.ValidacionTareaOpcionalException;
import model.GestorDeProcesos;
import model.Proceso;
import pruebasrapidas.Main;

public class CrearNuevoProcesoTest {

	@Test
	public void test() throws ValidacionIDProcesoException {
		GestorDeProcesos gestor = null;
		try {
			gestor = Main.crearSetPruebas();
		} catch (ValidacionIDProcesoException | ValidacionNombreActividadException
				| ValidacionTareaOpcionalException e) {
			fail("Error en la creación del set de pruebas " + e.getMessage() );
			return;
		}
		gestor.crearProceso("p3", "Casa Banquetera");
		
		Proceso p3 = gestor.obtenerProceso("p3");
		Assert.assertEquals("p3", p3.getId());
		Assert.assertEquals("Casa Banquetera", p3.getNombre());
	}

}
