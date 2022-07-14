package pruebasunitarias;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import exceptions.ActividadNoExisteException;
import exceptions.ValidacionIDProcesoException;
import exceptions.ValidacionNombreActividadException;
import exceptions.ValidacionTareaOpcionalException;
import model.Actividad;
import model.GestorDeProcesos;
import model.Proceso;
import model.Tarea;
import pruebasrapidas.Main;

public class IntercambiarActividadesConTareasTest {

	@Test
	public void test() throws ActividadNoExisteException {
		GestorDeProcesos gestor = null;
		try {
			gestor = Main.crearSetPruebas();
		} catch (ValidacionIDProcesoException | ValidacionNombreActividadException
				| ValidacionTareaOpcionalException e) {
			fail("Error en la creaci�n del set de pruebas " + e.getMessage() );
			return;
		}
		
		
		gestor.obtenerProceso("p2").intercambiarActividadesConTarea("Fundir", "Carrocer�a");
		
		Proceso p2 = gestor.obtenerProceso("p2");
		String nombresEsperados[] = {"Carrocer�a","Prensar","Fundir","Pintura", "Ensamble y montaje"}; 
		int i = 0;
		for (Actividad actividad : p2) {
			Assert.assertEquals(nombresEsperados[i], actividad.getNombre());
			i++;
		}
		Actividad fundir = gestor.obtenerProceso("p2").buscarActividadNombre("Fundir");
		String tareasEsperadas[] = {"Fundir bloque del motor","Fundir chasis","Fundir cilindro","Fundir pistones"}; 
		int j = 0;
		for (Tarea tarea : fundir) {
			Assert.assertEquals(tareasEsperadas[j], tarea.getDescripcion());
			j++;
		}
		
		
		Actividad carroceria = gestor.obtenerProceso("p2").buscarActividadNombre("Carrocer�a");
	
		String tareasEsperadas2[] = {"Hacer croquis de piezas","Seleccionar piezas","Soldar piezas","Enviar piezas a pintura"}; 
		int k = 0;
		for (Tarea tarea : carroceria) {
		Assert.assertEquals(tareasEsperadas2[k], tarea.getDescripcion());
		k++;
	}
}
}
