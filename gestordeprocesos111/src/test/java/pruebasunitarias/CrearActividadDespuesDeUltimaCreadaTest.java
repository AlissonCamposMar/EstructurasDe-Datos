package pruebasunitarias;

/**
 * @author Alisson Campos Marin
 * @author Jenny Marcela Tellez
 * @author Johan Andrey Ortiz
 */
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

public class CrearActividadDespuesDeUltimaCreadaTest {

	@Test
	public void test() throws ValidacionIDProcesoException, ValidacionNombreActividadException {
		GestorDeProcesos gestor = null;
		try {
			gestor = Main.crearSetPruebas();
		} catch (ValidacionIDProcesoException | ValidacionNombreActividadException
				| ValidacionTareaOpcionalException e) {
			fail("Error en la creación del set de pruebas " + e.getMessage() );
			return;
		}
		gestor.obtenerProceso("p2").crearActividad("Limpiar", "Remover residuos de las partes prensadas del automóvil", false, "Prensar");
		
		gestor.obtenerProceso("p2").crearActividadDespuesUltima("Encerar", "Se encera todas las partes del automóvil", false);

		Proceso p2 = gestor.obtenerProceso("p2");
		String nombresEsperados[] = {"Fundir","Prensar","Limpiar","Encerar","Carrocería","Pintura", "Ensamble y montaje"}; 
		int i = 0;
		for (Actividad actividad : p2) {
			Assert.assertEquals(nombresEsperados[i], actividad.getNombre());
			i++;
		}
		
		Assert.assertEquals(7,p2.obtenerNumeroActividades());
		
	}

}
