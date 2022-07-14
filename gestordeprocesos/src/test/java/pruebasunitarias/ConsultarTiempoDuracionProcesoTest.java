package pruebasunitarias;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import exceptions.ValidacionIDProcesoException;
import exceptions.ValidacionNombreActividadException;
import exceptions.ValidacionTareaOpcionalException;
import model.GestorDeProcesos;
import pruebasrapidas.Main;

public class ConsultarTiempoDuracionProcesoTest {

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
		
		
		 
		 Assert.assertEquals(129,gestor.obtenerProceso("p2").tiempoMinimo() );
		Assert.assertEquals(149,gestor.obtenerProceso("p2").tiempoMaximo());
		 
		 
	}

}
