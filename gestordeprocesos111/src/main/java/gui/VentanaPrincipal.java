package gui;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import exceptions.ValidacionIDProcesoException;
import exceptions.ValidacionNombreActividadException;
import exceptions.ValidacionTareaOpcionalException;

public class VentanaPrincipal extends JFrame {
	private static final int ANCHO_COMPONENTE = 120;
	private static final int ANCHO_SEPARACION = 3;
	private static final int ALTO_COMPONENTE = 25;
	private static final int ALTO_SEPARACION = 10;
	private static final int POS_INICIAL_X = 10;
	private static final int POS_INICIAL_Y = 10;
	private JTextField idProceso;
	private JTextField nombreProceso;
	private JButton btnAdicionarProceso;
	private JComboBox<ProcesoGUI> cbProcesos;
	
	private JTextField nombreActividad;
	private JTextField descripcionActividad;
	private JCheckBox  obligatorioActividad; 
	private JButton btnAdicionarActividad;
	private JComboBox<ActividadGUI> cbActividades;
	
	
	private JTextField descripcionTarea;
	private JTextField tiempoTarea;
	private JCheckBox  obligatorioTarea;
	private JTextField posicionTarea;
	private JButton btnAdicionarTarea;
	
	private JList<TareaGUI> tareas;
	public VentanaPrincipal() {
		inicializarGUI();
		setVisible(true);
	}

	private void inicializarGUI() {

		getContentPane().setLayout(null);
		setSize(1115, 600);
		JLabel lbIdProceso = new JLabel("Id:");
		lbIdProceso.setBounds(10,
				10, 49, 25);
		getContentPane().add(lbIdProceso);
		idProceso = new JTextField();
		idProceso.setBounds(83,
				10, ANCHO_COMPONENTE, ALTO_COMPONENTE);
		getContentPane().add(idProceso);
		JLabel lbNombreProceso = new JLabel("Nombre:");
		lbNombreProceso.setBounds(228,
				10, 60, 25);
		getContentPane().add(lbNombreProceso);

		nombreProceso = new JTextField();
		nombreProceso.setBounds(298,
				10, ANCHO_COMPONENTE, ALTO_COMPONENTE);
		getContentPane().add(nombreProceso);

		btnAdicionarProceso = new JButton("Adicionar");
		btnAdicionarProceso.setBounds(519,
				10, ANCHO_COMPONENTE, ALTO_COMPONENTE);
		btnAdicionarProceso.addActionListener((e) -> {
			crearProceso();
		});
		getContentPane().add(btnAdicionarProceso);

		JLabel lbProcesos = new JLabel("Procesos:");
		lbProcesos.setBounds(POS_INICIAL_X + (ANCHO_COMPONENTE + ANCHO_SEPARACION) * 0,
				POS_INICIAL_Y + (ALTO_COMPONENTE + ALTO_SEPARACION) * 1, ANCHO_COMPONENTE, ALTO_COMPONENTE);
		getContentPane().add(lbProcesos);
		cbProcesos = new JComboBox<ProcesoGUI>(App.instancia.getProcesos());
		cbProcesos.setBounds(83,
				46, ANCHO_COMPONENTE, ALTO_COMPONENTE);
		cbProcesos.addActionListener( c->{ seleccionarProceso(); } );
		getContentPane().add(cbProcesos);
		
		JLabel lbNombreActividad = new JLabel("Nombre:");
		lbNombreActividad.setBounds(228,
				46, 60, 25);
		getContentPane().add(lbNombreActividad);
		
		nombreActividad = new JTextField();
		nombreActividad.setBounds(298,
				45, ANCHO_COMPONENTE, ALTO_COMPONENTE);
		getContentPane().add(nombreActividad);
		
		JLabel lbDescripcionActividad = new JLabel("Descripcion:");
		lbDescripcionActividad.setBounds(442,
				46, 73, 25);
		getContentPane().add(lbDescripcionActividad);
		
		descripcionActividad = new JTextField();
		descripcionActividad.setBounds(519,
				45, ANCHO_COMPONENTE, ALTO_COMPONENTE);
		getContentPane().add(descripcionActividad);
		
		obligatorioActividad = new JCheckBox("Obligatoria");
		obligatorioActividad.setBounds(659,
				45, 87, 25);
		getContentPane().add(obligatorioActividad);

		btnAdicionarActividad = new JButton("Adicionar");
		btnAdicionarActividad.setBounds(753,
				45, ANCHO_COMPONENTE, ALTO_COMPONENTE);
		btnAdicionarActividad.addActionListener((e) -> {
			crearActividad();
		});
		getContentPane().add(btnAdicionarActividad);
		
		JLabel lbActividades = new JLabel("Actividades:");
		lbActividades.setBounds(10,
				80, 68, 25);
		getContentPane().add(lbActividades);
		cbActividades = new JComboBox<>(App.instancia.getActividades());
		cbActividades.setBounds(83,
				80, ANCHO_COMPONENTE, ALTO_COMPONENTE);
		cbActividades.addActionListener( a->{seleccionarActividad();} );
		getContentPane().add(cbActividades);
		
		JLabel lbDescripcion = new JLabel("Descripcion:");
		lbDescripcion.setBounds(228,
				80, 81, 25);
		getContentPane().add(lbDescripcion);
		
		descripcionTarea = new JTextField();
		descripcionTarea.setBounds(308,
				81, ANCHO_COMPONENTE, ALTO_COMPONENTE);
		getContentPane().add(descripcionTarea); 
		
		JLabel lbTiempo = new JLabel("Tiempo:");
		lbTiempo.setBounds(447,
				82, ANCHO_COMPONENTE, ALTO_COMPONENTE);
		getContentPane().add(lbTiempo);
		
		tiempoTarea = new JTextField();
		tiempoTarea.setBounds(519,
				80, ANCHO_COMPONENTE, ALTO_COMPONENTE);
		getContentPane().add(tiempoTarea); 
		
		
		
		obligatorioTarea = new JCheckBox("Obligatoria");
		obligatorioTarea.setBounds(659,
				80, 87, 25);
		getContentPane().add(obligatorioTarea); 
		
		
		JLabel lbPosicion = new JLabel("Posicion:");
		lbPosicion.setBounds(763,
				80, 110, 25);
		getContentPane().add(lbPosicion);
		
		posicionTarea = new JTextField();
		posicionTarea.setBounds(824,
				80, ANCHO_COMPONENTE, ALTO_COMPONENTE);
		getContentPane().add(posicionTarea); 
		
		
		btnAdicionarTarea = new JButton("Adicionar");
		btnAdicionarTarea.setBounds(968,
				80, ANCHO_COMPONENTE, ALTO_COMPONENTE);
		btnAdicionarTarea.addActionListener((e) -> {
			crearTarea();
		});
		getContentPane().add(btnAdicionarTarea);

		
		JLabel lbTareas = new JLabel("Tareas");
		lbTareas.setBounds(POS_INICIAL_X + (ANCHO_COMPONENTE + ANCHO_SEPARACION) * 0,
				POS_INICIAL_Y + (ALTO_COMPONENTE + ALTO_SEPARACION) * 3, ANCHO_COMPONENTE, ALTO_COMPONENTE);
		getContentPane().add(lbTareas);
		tareas = new JList<>(App.instancia.getTareas());
		tareas.setBounds(10,
				150, 1078, 400);
		getContentPane().add(tareas);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		revalidate();
	}

	private void seleccionarActividad() {
		App.instancia.seleccionarActividad( (ActividadGUI) cbActividades.getSelectedItem() );
		tareas.updateUI();
		
	}

	private void seleccionarProceso() {
		App.instancia.seleccionarProceso( (ProcesoGUI) cbProcesos.getSelectedItem() );
		cbActividades.updateUI();
		tareas.updateUI();
	}

	private void crearTarea() {
		if (cbActividades.getSelectedIndex() == -1 ) {
			error("Debe seleccionar una actividad");
			return;
		}
		if( esVacio( descripcionTarea.getText() ) || esVacio( tiempoTarea.getText() )  ) {
			error("Los campos son obligatorios");
			return;
		}
		try {
			App.instancia.crearTarea( ((ActividadGUI )cbActividades.getSelectedItem()) , descripcionTarea.getText() , Integer.parseInt( tiempoTarea.getText() ) , obligatorioTarea.isSelected() , posicionTarea.getText() );
			if( !esVacio(posicionTarea.getText()) ) {
				seleccionarActividad();
			}
			tareas.updateUI();
			mensaje("La tarea se creo exitosamente");
		} catch (NumberFormatException | ValidacionTareaOpcionalException e) {
			error(e.getMessage());
		}
	}

	private void crearActividad() {
		if (cbProcesos.getSelectedIndex() == -1) {
			error("Debe seleccionar un proceso");
			return;
		}
		if( esVacio( nombreActividad.getText() ) || esVacio( descripcionActividad.getText() )  ) {
			error("Los campos son obligatorios");
			return;
		}
		try {
			App.instancia.crearActividad( ((ProcesoGUI )cbProcesos.getSelectedItem()) , nombreActividad.getText() , descripcionActividad.getText() , obligatorioActividad.isSelected() );
			cbActividades.updateUI();
			tareas.updateUI();
			mensaje("La actividad se creo exitosamente");
		} catch (ValidacionNombreActividadException e) {
			error(e.getMessage());
		}
	}

	private void crearProceso() {
		if( esVacio( idProceso.getText() ) || esVacio( nombreProceso.getText() )  ) {
			error("Los campos son obligatorios");
			return;
		}
		try {
			App.instancia.crearProceso(idProceso.getText(), nombreProceso.getText());
			cbProcesos.updateUI();
			cbActividades.updateUI();
			tareas.updateUI();
			mensaje("El proceso se creo exitosamente");
		} catch (ValidacionIDProcesoException e) {
			error( e.getMessage() );
		}
	}

	private boolean esVacio( String texto ) {
		return texto == null || texto.trim().equals("");
	}
	
	private void error(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}
	

	private void mensaje(String text) {
		JOptionPane.showMessageDialog(this, text, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
	}

}
