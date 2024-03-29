package pruebasunitarias;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import exceptions.ValidacionIDProcesoException;
import exceptions.ValidacionNombreActividadException;
import exceptions.ValidacionTareaOpcionalException;
import model.Actividad;
import model.GestorDeProcesos;
import model.Proceso;
import pruebasrapidas.Main;

public class CrearActividadConPredecesoraTest {

	@Test
	public void test() throws ValidacionIDProcesoException, ValidacionNombreActividadException {
		GestorDeProcesos gestor = null;
		try {
			gestor = Main.crearSetPruebas();
		} catch (ValidacionIDProcesoException | ValidacionNombreActividadException
				| ValidacionTareaOpcionalException e) {
			fail("Error en la creacion del set de pruebas " + e.getMessage() );
			return;
		}
		gestor.obtenerProceso("p2").crearActividad("Limpiar", "Remover residuos de las partes prensadas del automovil", false, "Prensar");
		
		
		Proceso p2 = gestor.obtenerProceso("p2");
		String nombresEsperados[] = {"Fundir","Prensar","Limpiar","Carrocerķa","Pintura", "Ensamble y montaje"}; 
		int i = 0;
		for (Actividad actividad : p2) {
			Assert.assertEquals(nombresEsperados[i], actividad.getNombre());
			i++;
		}
		
		Assert.assertEquals(6,p2.obtenerNumeroActividades());
		
		
		
	}

}
