package pruebasunitarias;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.ValidacionIDProcesoException;
import exceptions.ValidacionNombreActividadException;
import exceptions.ValidacionTareaOpcionalException;
import model.GestorDeProcesos;
import pruebasrapidas.Main;

public class CrearTareaOpcionaDespuesTareaOpcional {

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
			gestor.obtenerProceso("p1").buscarActividadNombre("Hacer sandwich").crearTarea("Adicionar Tocineta", false, 1, 4);
			
			fail( "No se puede crear consecutivo de tarea opcional");
			} catch (Exception e) {
			}
		
	}
	

}
