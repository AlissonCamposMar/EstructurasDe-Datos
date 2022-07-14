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

public class BuscarTareaEnActividadTest {

	@Test
	public void test() throws ValidacionIDProcesoException, ValidacionNombreActividadException, ActividadNoExisteException {
		GestorDeProcesos gestor = null;
		try {
			gestor = Main.crearSetPruebas();
		} catch (ValidacionIDProcesoException | ValidacionNombreActividadException
				| ValidacionTareaOpcionalException e) {
			fail("Error en la creacion del set de pruebas " + e.getMessage() );
			return;
		}
		
		
		Tarea resultado = gestor.obtenerProceso("p2").buscarTareaEnActividadNombre("Prensar", "Cortar metal");
			
		Assert.assertEquals("Cortar metal",resultado.getDescripcion());
		Assert.assertEquals(true,resultado.isObligatorio());
		Assert.assertEquals(13,resultado.getTiempo());
			
		
	}

}
