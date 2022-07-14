package gui;

import java.util.Vector;

import estructuras.Cola;
import exceptions.ValidacionIDProcesoException;
import exceptions.ValidacionNombreActividadException;
import exceptions.ValidacionTareaOpcionalException;
import model.Actividad;
import model.GestorDeProcesos;
import model.Proceso;
import model.ProcesoActividad;
import model.Tarea;

public class App {

	private GestorDeProcesos gestor;
	private Vector<ProcesoGUI> procesos;
	private Vector<ActividadGUI> actividades;
	private Vector<TareaGUI> tareas;

	private VentanaPrincipal ventana;
	public static final App instancia = new App();

	public App() {
		try {
			gestor = crearSetPruebas();
		} catch (ValidacionIDProcesoException | ValidacionNombreActividadException
				| ValidacionTareaOpcionalException e) {
			e.printStackTrace();
		}
		procesos = new Vector<ProcesoGUI>();
		actividades = new Vector<ActividadGUI>();
		tareas = new Vector<TareaGUI>();
		llenarProcesos();

	}

	private void llenarProcesos() {
		for (Proceso proceso : gestor) {
			procesos.add(new ProcesoGUI(proceso));
		}
		llenarActividades(procesos.isEmpty() ? null : procesos.get(0));
	}

	private void llenarActividades(ProcesoGUI procesoGUI) {
		actividades.clear();
		if (procesoGUI != null) {
			for (Actividad actividad : procesoGUI.getProceso()) {
				actividades.add(new ActividadGUI(actividad));
			}
		}
		llenarTareas( actividades.isEmpty() ? null : actividades.get(0) );
	}

	private void llenarTareas(ActividadGUI actividadGUI) {
		tareas.clear();
		if (actividadGUI != null) {
			for (Tarea tarea : actividadGUI.getActividad()) {
				tareas.add(new TareaGUI(tarea));
			}
		}
	}

	public static void main(String[] args) {
		instancia.iniciar();

	}

	private void iniciar() {
		ventana = new VentanaPrincipal();
	}

	/**
	 * @param id
	 * @param nombre
	 * @throws ValidacionIDProcesoException
	 * @see model.GestorDeProcesos#crearProceso(java.lang.String, java.lang.String)
	 */
	public void crearProceso(String id, String nombre) throws ValidacionIDProcesoException {
		gestor.crearProceso(id, nombre);
		procesos.add(new ProcesoGUI(obtenerProceso(id)));
	}

	/**
	 * @param nombreActividad
	 * @return
	 * @see model.GestorDeProcesos#buscarActividad(java.lang.String)
	 */
	public Cola<ProcesoActividad> buscarActividad(String nombreActividad) {
		return gestor.buscarActividad(nombreActividad);
	}

	/**
	 * @param id
	 * @return
	 * @see model.GestorDeProcesos#obtenerProceso(java.lang.String)
	 */
	public Proceso obtenerProceso(String id) {
		return gestor.obtenerProceso(id);
	}

	/**
	 * Metodo que permite obtener el valor del atributo procesos
	 * 
	 * @return El valor del atributo procesos
	 */
	public Vector<ProcesoGUI> getProcesos() {
		return procesos;
	}

	/**
	 * Metodo que permite obtener el valor del atributo actividades
	 * 
	 * @return El valor del atributo actividades
	 */
	public Vector<ActividadGUI> getActividades() {
		return actividades;
	}

	/**
	 * Metodo que permite obtener el valor del atributo tareas
	 * 
	 * @return El valor del atributo tareas
	 */
	public Vector<TareaGUI> getTareas() {
		return tareas;
	}

	/**
	 * Permite crear una actividad en un proceso
	 * 
	 * @param proceso     Proceso en el que se va a crear la actividad
	 * @param nombre      Nombre de la actividad
	 * @param descripcion Descripcion de la actividad
	 * @param obligatorio Indicador de si la actividad es obligatoria
	 * @throws ValidacionNombreActividadException
	 */
	public void crearActividad(ProcesoGUI proceso, String nombre, String descripcion, boolean obligatorio)
			throws ValidacionNombreActividadException {
		proceso.getProceso().crearActividadAlFinal(nombre, descripcion, obligatorio);
		actividades.add(new ActividadGUI(proceso.getProceso().buscarActividadNombre(nombre)));
	}

	/**
	 * Permite crear una tarea
	 * 
	 * @param actividadGUI Actividad en la que se desea crear la tarea
	 * @param descripcion  Descripcion de la tarea
	 * @param tiempo       Tiempo de la tarea
	 * @param obligatorio  Indicador de si la tarea es o no obligatoria
	 * @param posicion 
	 * @throws ValidacionTareaOpcionalException
	 */
	public void crearTarea(ActividadGUI actividadGUI, String descripcion, int tiempo, boolean obligatorio, String posicion)
			throws ValidacionTareaOpcionalException {
		if( posicion == null || posicion.trim().equals("") ) {
			actividadGUI.getActividad().crearTareaAlFinal(descripcion, obligatorio, tiempo);
		} else {
			actividadGUI.getActividad().crearTarea(descripcion, obligatorio, tiempo,Integer.parseInt(posicion));
		}
		tareas.add(new TareaGUI(actividadGUI.getActividad().buscarTareaDescripcion(descripcion)));
	}

	public void seleccionarProceso(ProcesoGUI procesoGUI) {
		llenarActividades(procesoGUI);
	}

	public void seleccionarActividad(ActividadGUI actividadGUI) {
		llenarTareas(actividadGUI);
	}

	public static GestorDeProcesos crearSetPruebas()
			throws ValidacionIDProcesoException, ValidacionNombreActividadException, ValidacionTareaOpcionalException {

		GestorDeProcesos gestor = new GestorDeProcesos();

		/*
		 * Se crean los procesos Desayunos Sopresa y Emsabladora de Autom�viles
		 */

		gestor.crearProceso("p1", "Desayunos Sorpresa");
		gestor.crearProceso("p2", "Ensambladora de Autom�viles");

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

		gestor.obtenerProceso("p2").crearActividadAlFinal("Fundir", "Identificar qu� piezas se necesitan", true);
		gestor.obtenerProceso("p2").crearActividadAlFinal("Prensar", "Recoger metal enrrollado", true);
		gestor.obtenerProceso("p2").crearActividadAlFinal("Carrocer�a", "Recoger piezas met�licas", true);
		gestor.obtenerProceso("p2").crearActividadAlFinal("Pintura", "Pintar las piezas de la carrocer�a", true);
		gestor.obtenerProceso("p2").crearActividadAlFinal("Ensamble y montaje", "Unir todas las partes del autom�vil",
				true);

		/*
		 ******* CREAR TAREAS**** PROCESO DESAYUNOS SORPRESA***** Se crean las tareas para la
		 * actividad Hacer sandwich
		 */

		gestor.obtenerProceso("p1").buscarActividadNombre("Hacer sandwich").crearTareaAlFinal("Coger dos panes", true,
				1);
		gestor.obtenerProceso("p1").buscarActividadNombre("Hacer sandwich").crearTareaAlFinal("Adicionar mantequilla",
				false, 1);
		gestor.obtenerProceso("p1").buscarActividadNombre("Hacer sandwich").crearTareaAlFinal("Adicionar mortadela",
				true, 1);
		gestor.obtenerProceso("p1").buscarActividadNombre("Hacer sandwich").crearTareaAlFinal("Adicionar jam�n", false,
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
		gestor.obtenerProceso("p1").buscarActividadNombre("Preparar jugo").crearTareaAlFinal("Adicionar az�car", false,
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
		gestor.obtenerProceso("p1").buscarActividadNombre("Seleccionar fruta").crearTareaAlFinal("Picar mel�n", false,
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
		gestor.obtenerProceso("p1").buscarActividadNombre("Decorar").crearTareaAlFinal("A�adir escarcha", true, 2);
		gestor.obtenerProceso("p1").buscarActividadNombre("Decorar").crearTareaAlFinal("Decorar la caja", true, 2);

		/*
		 ******* CREAR TAREAS**** *** PROCESO ENSAMBLADORA DE AUTOM�VILES***** Se crean las
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
		 * Se crean las tareas para la actividad Carrocer�a
		 */
		gestor.obtenerProceso("p2").buscarActividadNombre("Carrocer�a").crearTareaAlFinal("Hacer croquis de piezas",
				false, 12);
		gestor.obtenerProceso("p2").buscarActividadNombre("Carrocer�a").crearTareaAlFinal("Seleccionar piezas", true,
				10);
		gestor.obtenerProceso("p2").buscarActividadNombre("Carrocer�a").crearTareaAlFinal("Soldar piezas", true, 16);
		gestor.obtenerProceso("p2").buscarActividadNombre("Carrocer�a").crearTareaAlFinal("Enviar piezas a pintura",
				true, 14);

		/*
		 * Se crean las tareas para la actividad Pintura
		 */
		gestor.obtenerProceso("p2").buscarActividadNombre("Pintura").crearTareaAlFinal("Sellar y verificar fugas", true,
				2);
		gestor.obtenerProceso("p2").buscarActividadNombre("Pintura").crearTareaAlFinal("Lijar carrocer�a", true, 5);
		gestor.obtenerProceso("p2").buscarActividadNombre("Pintura").crearTareaAlFinal("Pintar toda la carrocer�a",
				true, 3);
		gestor.obtenerProceso("p2").buscarActividadNombre("Pintura").crearTareaAlFinal("Aplicar brillo", false, 1);

		/*
		 * Se crean las tareas para la actividad Ensamble y montaje
		 */
		gestor.obtenerProceso("p2").buscarActividadNombre("Ensamble y montaje")
				.crearTareaAlFinal("Juntar piezas seg�n tama�o", false, 3);
		gestor.obtenerProceso("p2").buscarActividadNombre("Ensamble y montaje")
				.crearTareaAlFinal("Ensamblar piezas grandes", true, 2);
		gestor.obtenerProceso("p2").buscarActividadNombre("Ensamble y montaje")
				.crearTareaAlFinal("Montar parte el�ctrica", true, 11);
		gestor.obtenerProceso("p2").buscarActividadNombre("Ensamble y montaje").crearTareaAlFinal("Montar accesorios",
				true, 1);
		gestor.obtenerProceso("p2").buscarActividadNombre("Ensamble y montaje")
				.crearTareaAlFinal("Poner sillas, volante y pedales", true, 2);

		return gestor;
	}
}
