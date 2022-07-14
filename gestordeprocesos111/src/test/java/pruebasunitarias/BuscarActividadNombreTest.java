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

public class BuscarActividadNombreTest {

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
		Cola<ProcesoActividad> resultado = gestor.buscarActividad("Pintura");
		Assert.assertEquals(1,resultado.getLongitud());
		
		ProcesoActividad p = resultado.poll();
		Assert.assertEquals("p2",p.getProceso().getId());
		Assert.assertEquals("Pintura",p.getActividad().getNombre());
			
		
	}

}
