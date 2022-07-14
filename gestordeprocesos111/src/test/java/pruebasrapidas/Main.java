package pruebasrapidas;

import exceptions.ActividadNoExisteException;
import exceptions.ValidacionIDProcesoException;
import exceptions.ValidacionNombreActividadException;
import exceptions.ValidacionTareaOpcionalException;
import model.GestorDeProcesos;

/**
 * @author Alisson Campos Marin
 * @author Jenny Marcela Tellez
 * @author Johan Andrey Ortiz
 */

public class Main {

	public static void main(String[] args)
			throws ValidacionIDProcesoException, ValidacionNombreActividadException, ValidacionTareaOpcionalException, ActividadNoExisteException {

		GestorDeProcesos gestor = crearSetPruebas();

		/**
		 * 1. CREAR PROCESOS
		 */
		System.out.println("1. CREAR PROCESOS\n");
		
		// Crear un nuevo proceso 
		gestor.crearProceso("p3", "Casa Banquetera");
		System.out.println("Se creó el " + gestor.obtenerProceso("p3"));
		
		// Crear un nuevo proceso con id ya existente en otro procesos
		System.out.println("Se trata de crear el proceso Hamburgueseria con id p3");
		try {
			gestor.crearProceso("p3", "Hamburguesería");
		} catch (Exception e) {
			System.out.println("Se genero excepcion: " + e.getMessage());
		}
		
		/**
		 * 2. CREAR ACTIVIDADES
		 */
		System.out.println("\n2. CREAR ACTIVIDADES\n");
		// Crear una Actividad con un nombre ya existente
		try {
		gestor.obtenerProceso("p1").crearActividadAlFinal("Decorar", "Adicionar implementos decorativos", true);
		} catch (Exception e) {
			System.out.println("Se genero excepcion: " + e.getMessage());
		}
		
		// Crear una nueva actividad
		gestor.obtenerProceso("p2").crearActividadAlFinal("Control de Calidad", "Validación del funcionamiento adecuado del automovil", false);
		
		System.out.println("Se creó " + gestor.obtenerProceso("p2").buscarActividadNombre("Control de Calidad") +
				" para un total de " + gestor.obtenerProceso("p2").obtenerNumeroActividades()+ " actividades en el proceso");
		
		// Validar que la cantidad de actividades aumente al momento de crear una nueva actividad
		System.out.println("Hay " + gestor.obtenerProceso("p1").obtenerNumeroActividades()+ " actividades en el proceso Desayuno Sorpresa");
		gestor.obtenerProceso("p1").crearActividadAlFinal("Comprar peluche", "Seleccionar peluche", false);
		System.out.println("Se creó la "+gestor.obtenerProceso("p1").buscarActividadNombre("Comprar peluche"));
		System.out.println("Hay " + gestor.obtenerProceso("p1").obtenerNumeroActividades()+ " actividades en el proceso Desayuno Sorpresa");
	
		// Crear una nueva actividad indicando la actividad predecesora
		
		System.out.println("Actividad predecesora: Prensar");
		gestor.obtenerProceso("p2").crearActividad("Limpiar", "Remover residuos de las partes prensadas del automovil", false, "Prensar");
		System.out.println("Se creó la actividad en la lista "+gestor.obtenerProceso("p2"));
		System.out.println("Hay " + gestor.obtenerProceso("p2").obtenerNumeroActividades()+ " actividades en el proceso");
	
		//Crear nueva actividad después de la última actividad creada
		System.out.println("Se crea la Actividad Encerar despues de la actual");
		gestor.obtenerProceso("p2").crearActividadDespuesUltima("Encerar", "Se encera todas las partes del automovil", false);
		System.out.println(gestor.obtenerProceso("p2"));
		System.out.println("Hay " + gestor.obtenerProceso("p2").obtenerNumeroActividades()+ " actividades en el proceso");
	
			
		/**
		 * 3. CREAR TAREAS
		 */
		System.out.println("\n3. CREAR TAREAS\n");
		
		// Crear tarea nueva obligatoria al final
		
		gestor.obtenerProceso("p1").buscarActividadNombre("Preparar jugo").crearTareaAlFinal("Servir jugo", true, 1);
		System.out.println("Se creó la tarea Servir jugo al final de la lista \n"+gestor.obtenerProceso("p1").buscarActividadNombre("Preparar jugo"));
		System.out.println("Hay " + gestor.obtenerProceso("p1").buscarActividadNombre("Preparar jugo").obtenerNumeroTareas()+ " tareas en la actividad");
		
		// Crear tarea nueva opcional después de una tarea ya creada obligatoria
		
		gestor.obtenerProceso("p1").buscarActividadNombre("Preparar jugo").crearTarea("Quitar semillas", false, 1, 1);
		System.out.println("Se creó la tarea Quitar semillas en la posicion 1 despues de una tarea obligatoria\n"+gestor.obtenerProceso("p1").buscarActividadNombre("Preparar jugo"));
		System.out.println("Hay " + gestor.obtenerProceso("p1").buscarActividadNombre("Preparar jugo").obtenerNumeroTareas()+ " tareas en la actividad");
		
		// Crear tarea opcional seguida de otra tarea ya creada opcional
		
		System.out.println("Se trata de crear una tarea opcional (Adicionar Tocineta) seguida de otra tarea opcional(Adicionar Jamon)");
		try {
			gestor.obtenerProceso("p1").buscarActividadNombre("Hacer sandwich").crearTarea("Adicionar Tocineta", false, 1, 4);
			} catch (Exception e) {
				System.out.println("Se genero excepcion: " + e.getMessage());
			}
		
		// Crear tarea en una posición determinada
		
		System.out.println("Se crea tarea en posicion 3 del proceso p2 Actividad Ensamble y montaje");
		gestor.obtenerProceso("p2").buscarActividadNombre("Ensamble y montaje").crearTarea("Instalar sensores", true, 3, 3);
		System.out.println("Se creó la tarea Instalar sensores en la posicion 3 \n"+gestor.obtenerProceso("p2").buscarActividadNombre("Ensamble y montaje"));
		System.out.println("Hay " + gestor.obtenerProceso("p2").buscarActividadNombre("Ensamble y montaje").obtenerNumeroTareas()+ " tareas en la actividad");
		
		/**
		 * 4. BUSCAR ACTIVIDAD
		 */
		System.out.println("\n4. BUSCAR ACTIVIDAD\n");
		
		// Buscar actividad existente dado su nombre (Clase Gestion de procesos)
		
		System.out.println("Se busca la actividad pintura \n"+gestor.buscarActividad("Pintura"));
		
		// Buscar actividad inexistente dado su nombre  (Clase Gestion de procesos)
		
		System.out.println("Se busca la actividad Envasado \n"+gestor.buscarActividad("Envasado"));
		
		/**
		 * 5. BUSCAR TAREA
		 */
		System.out.println("\n5. BUSCAR TAREA\n");
		
		//Buscar tarea en una actividad existente
		
		System.out.println("Se busca la tarea Cortar metal en la Actividad Prensar");
		System.out.println(gestor.obtenerProceso("p2").buscarActividadNombre("Prensar").buscarTareaDescripcion("Cortar metal"));
		
		//Buscar tarea en una actividad inexistente
		
		System.out.println("Se busca la tarea Seleccionar piezas en la Actividad Asolear");
		try {
		gestor.obtenerProceso("p2").buscarTareaEnActividadNombre("Asolear", "Seleccionar piezas");
		} catch (Exception e) {
			System.out.println("Se genero excepcion: " + e.getMessage());
		}
		
		// Buscar tarea desde el inicio del proceso
		
		System.out.println("Se busca la tarea Elaborar platinas desde el inicio del proceso Ensambladora de Automoviles");
		System.out.println(gestor.obtenerProceso("p2").buscarTareaDesdeElInicio("Elaborar platinas"));
		
		// Buscar tarea en actividad actual
		
		System.out.println("Se busca la tarea Cortar metal en la actividad actual Prensar");
		gestor.obtenerProceso("p2").buscarActividadNombre("Prensar");
		System.out.println(gestor.obtenerProceso("p2").buscarTareaEnActividadActual("Cortar metal"));
		
		/**
		 * 6. CONSULTAR TIEMPO DURACION PROCESO
		 */
		System.out.println("\n6. CONSULTAR TIEMPO DURACION PROCESO\n");
		
		//Consultar tiempo duración proceso
		
		System.out.println("El " + gestor.obtenerProceso("p2")+ "\nTiempo minimo: "+ gestor.obtenerProceso("p2").tiempoMinimo()
				+ "\nTiempo maximo: "+ gestor.obtenerProceso("p2").tiempoMaximo());
		
		/**
		 * 7. INTERCAMBIAR ACTIVIDADES
		 */
		System.out.println("\n7. INTERCAMBIAR ACTIVIDADES\n");
		
		//Intercambiar dos actividades sin sus tareas
		
		System.out.println(gestor.obtenerProceso("p2"));
		System.out.println("Se intercambiaran las actividades Fundir y Carroceria del proceso p2");
		gestor.obtenerProceso("p2").intercambiarActividadesSinTarea("Fundir", "Carrocería");
		System.out.println(gestor.obtenerProceso("p2"));
		
		// Intercambiar dos actividades con sus tareas, indicando un nombre inexistente en una actividad
		System.out.println("Se intercambiaran las actividades Escoger dulce y Preparar postre (no existe) del proceso p1");
		try {
		gestor.obtenerProceso("p1").intercambiarActividadesSinTarea("Escoger dulce", "Preparar postre");	
		} catch (Exception e) {
			System.out.println("Se genero excepcion: " + e.getMessage());
		}
		
		/**
		 * OTROS METODOS EMPLEADOS A PARTE DE LOS REQUISITOS FUNCIONALES
		 */
		System.out.println("\nOTROS METODOS EMPLEADOS A PARTE DE LOS REQUISITOS FUNCIONALES\n");
		
		// Buscar tarea por descripcion
		
		System.out.println("Se busca la tarea Soldar Piezas");
		System.out.println(gestor.obtenerProceso("p2").buscarActividadNombre("Fundir").buscarTareaDescripcion("Soldar piezas"));
		
		// Buscar Actividad por nombre (Clase proceso)
		
		System.out.println("Se busca la actividad por nombre Prensar");
		System.out.println(gestor.obtenerProceso("p2").buscarActividadNombre("Prensar"));
		
		
		// Buscar proceso por id
		
		System.out.println("Se busca proceso por el id p1");
		System.out.println(gestor.obtenerProceso("p1"));
		
		}

	
	
	public static GestorDeProcesos crearSetPruebas()
			throws ValidacionIDProcesoException, ValidacionNombreActividadException, ValidacionTareaOpcionalException {

		GestorDeProcesos gestor = new GestorDeProcesos();

		/*
		 * Se crean los procesos Desayunos Sopresa y Emsabladora de Automóviles
		 */

		gestor.crearProceso("p1", "Desayunos Sorpresa");
		gestor.crearProceso("p2", "Ensambladora de Automóviles");

		/*
		 * Se crean las actividades de cada proceso
		 */
		gestor.obtenerProceso("p1").crearActividadAlFinal("Hacer sandwich", "Preparar un sandwich", false);
		gestor.obtenerProceso("p1").crearActividadAlFinal("Preparar jugo",
				"Escoger la fruta para preparar el jugo solicitado", true);
		gestor.obtenerProceso("p1").crearActividadAlFinal("Escoger dulces",
				"Escoger dulce para el desayuno seleccionado", false);
		gestor.obtenerProceso("p1").crearActividadAlFinal("Seleccionar fruta",
				"Adicionar la fruta que ira en el desayuno", true);
		gestor.obtenerProceso("p1").crearActividadAlFinal("Decorar", "Decorar la bandeja del desayuno", true);

		gestor.obtenerProceso("p2").crearActividadAlFinal("Fundir", "Identificar qué piezas se necesitan", true);
		gestor.obtenerProceso("p2").crearActividadAlFinal("Prensar", "Recoger metal enrrollado", true);
		gestor.obtenerProceso("p2").crearActividadAlFinal("Carrocería", "Recoger piezas metálicas", true);
		gestor.obtenerProceso("p2").crearActividadAlFinal("Pintura", "Pintar las piezas de la carrocería", true);
		gestor.obtenerProceso("p2").crearActividadAlFinal("Ensamble y montaje", "Unir todas las partes del automóvil",
				true);

		/**
		 ******* CREAR TAREAS**** 
		 **** PROCESO DESAYUNOS SORPRESA***** 
		 *Se crean las tareas para la actividad Hacer sandwich
		 */

		gestor.obtenerProceso("p1").buscarActividadNombre("Hacer sandwich").crearTareaAlFinal("Coger dos panes", true,
				1);
		gestor.obtenerProceso("p1").buscarActividadNombre("Hacer sandwich").crearTareaAlFinal("Adicionar mantequilla",
				false, 1);
		gestor.obtenerProceso("p1").buscarActividadNombre("Hacer sandwich").crearTareaAlFinal("Adicionar mortadela",
				true, 1);
		gestor.obtenerProceso("p1").buscarActividadNombre("Hacer sandwich").crearTareaAlFinal("Adicionar jamón", false,
				1);
		gestor.obtenerProceso("p1").buscarActividadNombre("Hacer sandwich").crearTareaAlFinal("Adicionar queso", true,
				1);
		gestor.obtenerProceso("p1").buscarActividadNombre("Hacer sandwich").crearTareaAlFinal("Adicionar lechuga",
				false, 1);

		/*
		 * Se crean las tareas para la actividad Preparar
		 */
		gestor.obtenerProceso("p1").buscarActividadNombre("Preparar jugo").crearTareaAlFinal("Lavar la fruta", true, 2);
		gestor.obtenerProceso("p1").buscarActividadNombre("Preparar jugo").crearTareaAlFinal("Cortar la fruta", true,
				3);
		gestor.obtenerProceso("p1").buscarActividadNombre("Preparar jugo").crearTareaAlFinal("Adicionar agua o leche",
				true, 1);
		gestor.obtenerProceso("p1").buscarActividadNombre("Preparar jugo").crearTareaAlFinal("Adicionar azúcar", false,
				1);
		gestor.obtenerProceso("p1").buscarActividadNombre("Preparar jugo").crearTareaAlFinal("Licuar la fruta", true,
				2);

		/*
		 * Se crean las tareas para la actividad Escoger dulces
		 */
		gestor.obtenerProceso("p1").buscarActividadNombre("Escoger dulces").crearTareaAlFinal("Comprar gancito", false,
				2);
		gestor.obtenerProceso("p1").buscarActividadNombre("Escoger dulces").crearTareaAlFinal("Hacer torta de banano",
				true, 3);
		gestor.obtenerProceso("p1").buscarActividadNombre("Escoger dulces").crearTareaAlFinal("Comprar barra de cereal",
				true, 1);
		gestor.obtenerProceso("p1").buscarActividadNombre("Escoger dulces").crearTareaAlFinal("Comprar chocorramo",
				true, 5);
		gestor.obtenerProceso("p1").buscarActividadNombre("Escoger dulces").crearTareaAlFinal("Comprar chocolatina",
				false, 2);
		gestor.obtenerProceso("p1").buscarActividadNombre("Escoger dulces").crearTareaAlFinal("Comprar chocobreak",
				true, 6);

		/*
		 * Se crean las tareas para la actividad Seleccionar fruta
		 */
		gestor.obtenerProceso("p1").buscarActividadNombre("Seleccionar fruta").crearTareaAlFinal("Picar melón", false,
				1);
		gestor.obtenerProceso("p1").buscarActividadNombre("Seleccionar fruta").crearTareaAlFinal("Picar fresa", true,
				3);
		gestor.obtenerProceso("p1").buscarActividadNombre("Seleccionar fruta").crearTareaAlFinal("Picar mango", false,
				1);
		gestor.obtenerProceso("p1").buscarActividadNombre("Seleccionar fruta").crearTareaAlFinal("Picar kiwi", true, 1);
		gestor.obtenerProceso("p1").buscarActividadNombre("Seleccionar fruta").crearTareaAlFinal("Juntar fruta", true,
				2);

		/*
		 * Se crean las tareas para la actividad Decorar
		 */
		gestor.obtenerProceso("p1").buscarActividadNombre("Decorar").crearTareaAlFinal("Comprar globos", true, 2);
		gestor.obtenerProceso("p1").buscarActividadNombre("Decorar").crearTareaAlFinal("Inflar globos con helio", false,
				1);
		gestor.obtenerProceso("p1").buscarActividadNombre("Decorar").crearTareaAlFinal("Agregar letrero", true, 1);
		gestor.obtenerProceso("p1").buscarActividadNombre("Decorar").crearTareaAlFinal("Añadir escarcha", true, 2);
		gestor.obtenerProceso("p1").buscarActividadNombre("Decorar").crearTareaAlFinal("Decorar la caja", true, 2);

		/*
		 ******* CREAR TAREAS**** *** PROCESO ENSAMBLADORA DE AUTOMÓVILES***** Se crean las
		 * tareas para la actividad Fundir
		 */

		gestor.obtenerProceso("p2").buscarActividadNombre("Fundir").crearTareaAlFinal("Fundir bloque del motor", true,
				5);
		gestor.obtenerProceso("p2").buscarActividadNombre("Fundir").crearTareaAlFinal("Fundir chasis", true, 3);
		gestor.obtenerProceso("p2").buscarActividadNombre("Fundir").crearTareaAlFinal("Fundir cilindro", true, 2);
		gestor.obtenerProceso("p2").buscarActividadNombre("Fundir").crearTareaAlFinal("Fundir pistones", true, 3);

		/*
		 * Se crean las tareas para la actividad Prensar
		 */
		gestor.obtenerProceso("p2").buscarActividadNombre("Prensar").crearTareaAlFinal("Estirar metal", true, 20);
		gestor.obtenerProceso("p2").buscarActividadNombre("Prensar").crearTareaAlFinal("Cortar metal", true, 13);
		gestor.obtenerProceso("p2").buscarActividadNombre("Prensar").crearTareaAlFinal("Elaborar platinas", true, 17);
		gestor.obtenerProceso("p2").buscarActividadNombre("Prensar").crearTareaAlFinal("Cortar platinas", false, 4);

		/*
		 * Se crean las tareas para la actividad Carrocería
		 */
		gestor.obtenerProceso("p2").buscarActividadNombre("Carrocería").crearTareaAlFinal("Hacer croquis de piezas",
				false, 12);
		gestor.obtenerProceso("p2").buscarActividadNombre("Carrocería").crearTareaAlFinal("Seleccionar piezas", true,
				10);
		gestor.obtenerProceso("p2").buscarActividadNombre("Carrocería").crearTareaAlFinal("Soldar piezas", true, 16);
		gestor.obtenerProceso("p2").buscarActividadNombre("Carrocería").crearTareaAlFinal("Enviar piezas a pintura",
				true, 14);

		/*
		 * Se crean las tareas para la actividad Pintura
		 */
		gestor.obtenerProceso("p2").buscarActividadNombre("Pintura").crearTareaAlFinal("Sellar y verificar fugas", true,
				2);
		gestor.obtenerProceso("p2").buscarActividadNombre("Pintura").crearTareaAlFinal("Lijar carrocería", true, 5);
		gestor.obtenerProceso("p2").buscarActividadNombre("Pintura").crearTareaAlFinal("Pintar toda la carrocería",
				true, 3);
		gestor.obtenerProceso("p2").buscarActividadNombre("Pintura").crearTareaAlFinal("Aplicar brillo", false, 1);

		/*
		 * Se crean las tareas para la actividad Ensamble y montaje
		 */
		gestor.obtenerProceso("p2").buscarActividadNombre("Ensamble y montaje")
				.crearTareaAlFinal("Juntar piezas según tamaño", false, 3);
		gestor.obtenerProceso("p2").buscarActividadNombre("Ensamble y montaje")
				.crearTareaAlFinal("Ensamblar piezas grandes", true, 2);
		gestor.obtenerProceso("p2").buscarActividadNombre("Ensamble y montaje")
				.crearTareaAlFinal("Montar parte eléctrica", true, 11);
		gestor.obtenerProceso("p2").buscarActividadNombre("Ensamble y montaje").crearTareaAlFinal("Montar accesorios",
				true, 1);
		gestor.obtenerProceso("p2").buscarActividadNombre("Ensamble y montaje")
				.crearTareaAlFinal("Poner sillas, volante y pedales", true, 2);

		return gestor;
	}

}
