package pruebasunitarias;

import static org.junit.Assert.fail;

import org.junit.Test;

import exceptions.ActividadNoExisteException;
import exceptions.ValidacionIDProcesoException;
import exceptions.ValidacionNombreActividadException;
import exceptions.ValidacionTareaOpcionalException;
import model.GestorDeProcesos;
import pruebasrapidas.Main;

public class BuscarTareaActividadInexistenteTest {

	@Test
	public void test() throws ActividadNoExisteException {
		GestorDeProcesos gestor = null;
		try {
			gestor = Main.crearSetPruebas();
		} catch (ValidacionIDProcesoException | ValidacionNombreActividadException
				| ValidacionTareaOpcionalException e) {
			fail("Error en la creación del set de pruebas " + e.getMessage() );
			return;
		}
		
		try {
			gestor.obtenerProceso("p2").buscarTareaEnActividadNombre("Asolear", "Seleccionar piezas");
			fail("No detecto el id repetido " );
		} catch (ActividadNoExisteException e) {
			
		}
	}

}
