package pruebasunitarias;

import static org.junit.Assert.fail;

import org.junit.Test;

import exceptions.ValidacionIDProcesoException;
import exceptions.ValidacionNombreActividadException;
import exceptions.ValidacionTareaOpcionalException;
import model.GestorDeProcesos;
import pruebasrapidas.Main;

public class CrearProcesoIdExistenteTest {

	@Test
	public void test() {
		GestorDeProcesos gestor = null;
		try {
			gestor = Main.crearSetPruebas();
		} catch (ValidacionIDProcesoException | ValidacionNombreActividadException
				| ValidacionTareaOpcionalException e) {
			fail("Error en la creación del set de pruebas " + e.getMessage() );
			return;
		}
		
		try {
			gestor.crearProceso("p2", "Hamburgueser'ia");
			fail("No detectó el id repetido " );
		} catch (ValidacionIDProcesoException e) {
			
		}
	}

}
