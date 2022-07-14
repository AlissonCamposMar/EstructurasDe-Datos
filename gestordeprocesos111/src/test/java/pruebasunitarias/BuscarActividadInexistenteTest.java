package pruebasunitarias;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import estructuras.Cola;
import exceptions.ValidacionIDProcesoException;
import exceptions.ValidacionNombreActividadException;
import exceptions.ValidacionTareaOpcionalException;
import model.GestorDeProcesos;
import model.ProcesoActividad;
import pruebasrapidas.Main;

public class BuscarActividadInexistenteTest {

	@Test
	public void test() throws ValidacionIDProcesoException, ValidacionNombreActividadException {
		GestorDeProcesos gestor = null;
		try {
			gestor = Main.crearSetPruebas();
		} catch (ValidacionIDProcesoException | ValidacionNombreActividadException
				| ValidacionTareaOpcionalException e) {
			fail("Error en la creación del set de pruebas " + e.getMessage() );
			return;
		}
		Cola<ProcesoActividad> resultado = gestor.buscarActividad("Envasado");
		Assert.assertEquals(0,resultado.getLongitud());
			
	}

}
