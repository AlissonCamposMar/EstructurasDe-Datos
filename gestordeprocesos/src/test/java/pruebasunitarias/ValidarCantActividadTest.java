package pruebasunitarias;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import exceptions.ValidacionIDProcesoException;
import exceptions.ValidacionNombreActividadException;
import exceptions.ValidacionTareaOpcionalException;
import model.GestorDeProcesos;
import pruebasrapidas.Main;

public class ValidarCantActividadTest {

	@Test
	public void test() throws ValidacionIDProcesoException, ValidacionNombreActividadException {
		GestorDeProcesos gestor = null;
		try {
			gestor = Main.crearSetPruebas();
		} catch (ValidacionIDProcesoException | ValidacionNombreActividadException
				| ValidacionTareaOpcionalException e) {
			fail("Error en la creacion del set de pruebas " + e.getMessage() );
			return;
		}
		
		Assert.assertEquals(5,gestor.obtenerProceso("p1").obtenerNumeroActividades());
		
		gestor.obtenerProceso("p1").crearActividadAlFinal("Comprar peluche", "Seleccionar peluche", false);
		
		Assert.assertEquals(6,gestor.obtenerProceso("p1").obtenerNumeroActividades());
		
		
		
	}

}
