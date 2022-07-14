package pruebasunitarias;

import static org.junit.Assert.fail;

import org.junit.Test;

import exceptions.ActividadNoExisteException;
import exceptions.ValidacionIDProcesoException;
import exceptions.ValidacionNombreActividadException;
import exceptions.ValidacionTareaOpcionalException;
import model.GestorDeProcesos;
import pruebasrapidas.Main;

public class IntercambiarActividadesSinTareasInexistenteTest2 {

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
			gestor.obtenerProceso("p1").intercambiarActividadesSinTarea("Escoger dulce", "Preparar postre");	
			fail("No detectó el id repetido " );
		} catch (ActividadNoExisteException e) {
			
		}
	}

}
