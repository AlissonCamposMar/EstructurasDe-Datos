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
import model.Tarea;
import pruebasrapidas.Main;

public class CrearTareaPosicionDeterminadaTest {

	@Test
	public void test() throws ValidacionTareaOpcionalException, ActividadNoExisteException {
		GestorDeProcesos gestor = null;
		try {
			gestor = Main.crearSetPruebas();
		} catch (ValidacionIDProcesoException | ValidacionNombreActividadException
				| ValidacionTareaOpcionalException e) {
			fail("Error en la creacion del set de pruebas " + e.getMessage());
			return;
		}
		gestor.obtenerProceso("p2").buscarActividadNombre("Ensamble y montaje").crearTarea("Instalar sensores", true, 3,
				3);
		Actividad p2 = gestor.obtenerProceso("p2").buscarActividadNombre("Ensamble y montaje");
		String nombresEsperados[] = {"Juntar piezas según tamaño","Ensamblar piezas grandes",
				"Montar parte eléctrica", "Instalar sensores","Montar accesorios",
				"Poner sillas, volante y pedales" };
	     int i = 0;
		for (Tarea tarea : p2) {
			Assert.assertEquals(nombresEsperados[i], tarea.getDescripcion());
			i++;
		}
		Tarea tarea = gestor.obtenerProceso("p2").buscarTareaEnActividadNombre("Ensamble y montaje",
				"Instalar sensores");
		Assert.assertEquals("Instalar sensores", tarea.getDescripcion());
		Assert.assertEquals(6, p2.obtenerNumeroTareas());
	}

}
