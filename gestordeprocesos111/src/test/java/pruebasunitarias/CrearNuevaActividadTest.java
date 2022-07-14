package pruebasunitarias;

/**
 * @author Alisson Campos Marin
 * @author Jenny Marcela Tellez
 * @author Johan Andrey Ortiz
 */
import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import exceptions.ValidacionIDProcesoException;
import exceptions.ValidacionNombreActividadException;
import exceptions.ValidacionTareaOpcionalException;
import model.Actividad;
import model.GestorDeProcesos;
import pruebasrapidas.Main;

public class CrearNuevaActividadTest {

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
		gestor.obtenerProceso("p2").crearActividadAlFinal("Control de Calidad", "Validación del funcionamiento adecuado del automóvil", false);
		
		Actividad actividad = gestor.obtenerProceso("p2").buscarActividadNombre("Control de Calidad");
		Assert.assertEquals(6,gestor.obtenerProceso("p2").obtenerNumeroActividades());
		Assert.assertEquals("Control de Calidad", actividad.getNombre());
		
		
	}

}
