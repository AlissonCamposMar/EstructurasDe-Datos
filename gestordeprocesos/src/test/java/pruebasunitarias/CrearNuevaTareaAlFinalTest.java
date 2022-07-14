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

public class CrearNuevaTareaAlFinalTest {

	@Test
	public void test() throws ActividadNoExisteException, ValidacionTareaOpcionalException {
		GestorDeProcesos gestor = null;
		try {
			gestor = Main.crearSetPruebas();
		} catch (ValidacionIDProcesoException | ValidacionNombreActividadException
				| ValidacionTareaOpcionalException e) {
			fail("Error en la creacion del set de pruebas " + e.getMessage() );
			return;
		}
	
			gestor.obtenerProceso("p1").buscarActividadNombre("Preparar jugo").crearTareaAlFinal("Servir jugo", true, 1);
			Actividad p1 = gestor.obtenerProceso("p1").buscarActividadNombre("Preparar jugo");
			String nombresEsperados[] = {"Lavar la fruta","Cortar la fruta","Adicionar agua o leche","Adicionar azúcar", "Licuar la fruta","Servir jugo"}; 
			int i = 0;
			for (Tarea tarea : p1) {
				Assert.assertEquals(nombresEsperados[i], tarea.getDescripcion());
				i++;
			}
			Tarea tarea = gestor.obtenerProceso("p1").buscarTareaEnActividadNombre("Preparar jugo", "Servir jugo");
			Assert.assertEquals("Servir jugo", tarea.getDescripcion());
			Assert.assertEquals(6,p1.obtenerNumeroTareas());

		}
		   
	}


