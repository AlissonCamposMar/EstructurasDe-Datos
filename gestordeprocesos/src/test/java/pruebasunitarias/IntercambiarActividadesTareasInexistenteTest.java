package pruebasunitarias;

import static org.junit.Assert.fail;

import org.junit.Test;

import exceptions.ValidacionIDProcesoException;
import exceptions.ValidacionNombreActividadException;
import exceptions.ValidacionTareaOpcionalException;
import model.GestorDeProcesos;
import pruebasrapidas.Main;

public class IntercambiarActividadesTareasInexistenteTest {

	@Test
	public void test() {
		GestorDeProcesos gestor = null;
		try {
			gestor = Main.crearSetPruebas();
		} catch (ValidacionIDProcesoException | ValidacionNombreActividadException
				| ValidacionTareaOpcionalException e) {
			fail("Error en la creacion del set de pruebas " + e.getMessage() );
			return;
		}
		
		try {
			gestor.obtenerProceso("p1").crearActividadAlFinal("Decorar", "Adicionar implementos decorativos", true);
			fail("No detecto el id repetido " );
		} catch (ValidacionNombreActividadException e) {
			
		}
	}

}
