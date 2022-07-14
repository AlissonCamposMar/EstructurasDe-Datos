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

		setLayout(null);
		setSize(1300, 600);
		JLabel lbIdProceso = new JLabel("Id:");
		lbIdProceso.setBounds(POS_INICIAL_X + (ANCHO_COMPONENTE + ANCHO_SEPARACION) * 0,
				POS_INICIAL_Y + (ALTO_COMPONENTE + ALTO_SEPARACION) * 0, ANCHO_COMPONENTE, ALTO_COMPONENTE);
		add(lbIdProceso);
		idProceso = new JTextField();
		idProceso.setBounds(POS_INICIAL_X + (ANCHO_COMPONENTE + ANCHO_SEPARACION) * 1,
				POS_INICIAL_Y + (ALTO_COMPONENTE + ALTO_SEPARACION) * 0, ANCHO_COMPONENTE, ALTO_COMPONENTE);
		add(idProceso);
		JLabel lbNombreProceso = new JLabel("Nombre:");
		lbNombreProceso.setBounds(POS_INICIAL_X + (ANCHO_COMPONENTE + ANCHO_SEPARACION) * 2,
				POS_INICIAL_Y + (ALTO_COMPONENTE + ALTO_SEPARACION) * 0, ANCHO_COMPONENTE, ALTO_COMPONENTE);
		add(lbNombreProceso);

		nombreProceso = new JTextField();
		nombreProceso.setBounds(POS_INICIAL_X + (ANCHO_COMPONENTE + ANCHO_SEPARACION) * 3,
				POS_INICIAL_Y + (ALTO_COMPONENTE + ALTO_SEPARACION) * 0, ANCHO_COMPONENTE, ALTO_COMPONENTE);
		add(nombreProceso);

		btnAdicionarProceso = new JButton("Adicionar");
		btnAdicionarProceso.setBounds(POS_INICIAL_X + (ANCHO_COMPONENTE + ANCHO_SEPARACION) * 4,
				POS_INICIAL_Y + (ALTO_COMPONENTE + ALTO_SEPARACION) * 0, ANCHO_COMPONENTE, ALTO_COMPONENTE);
		btnAdicionarProceso.addActionListener((e) -> {
			crearProceso();
		});
		add(btnAdicionarProceso);

		JLabel lbProcesos = new JLabel("Procesos:");
		lbProcesos.setBounds(POS_INICIAL_X + (ANCHO_COMPONENTE + ANCHO_SEPARACION) * 0,
				POS_INICIAL_Y + (ALTO_COMPONENTE + ALTO_SEPARACION) * 1, ANCHO_COMPONENTE, ALTO_COMPONENTE);
		add(lbProcesos);
		cbProcesos = new JComboBox<ProcesoGUI>(App.instancia.getProcesos());
		cbProcesos.setBounds(POS_INICIAL_X + (ANCHO_COMPONENTE + ANCHO_SEPARACION) * 1,
				POS_INICIAL_Y + (ALTO_COMPONENTE + ALTO_SEPARACION) * 1, ANCHO_COMPONENTE, ALTO_COMPONENTE);
		cbProcesos.addActionListener( c->{ seleccionarProceso(); } );
		add(cbProcesos);
		
		JLabel lbNombreActividad = new JLabel("Nombre:");
		lbNombreActividad.setBounds(POS_INICIAL_X + (ANCHO_COMPONENTE + ANCHO_SEPARACION) * 2,
				POS_INICIAL_Y + (ALTO_COMPONENTE + ALTO_SEPARACION) * 1, ANCHO_COMPONENTE, ALTO_COMPONENTE);
		add(lbNombreActividad);
		
		nombreActividad = new JTextField();
		nombreActividad.setBounds(POS_INICIAL_X + (ANCHO_COMPONENTE + ANCHO_SEPARACION) * 3,
				POS_INICIAL_Y + (ALTO_COMPONENTE + ALTO_SEPARACION) * 1, ANCHO_COMPONENTE, ALTO_COMPONENTE);
		add(nombreActividad);
		
		JLabel lbDescripcionActividad = new JLabel("Descripcion:");
		lbDescripcionActividad.setBounds(POS_INICIAL_X + (ANCHO_COMPONENTE + ANCHO_SEPARACION) * 4,
				POS_INICIAL_Y + (ALTO_COMPONENTE + ALTO_SEPARACION) * 1, ANCHO_COMPONENTE, ALTO_COMPONENTE);
		add(lbDescripcionActividad);
		
		descripcionActividad = new JTextField();
		descripcionActividad.setBounds(POS_INICIAL_X + (ANCHO_COMPONENTE + ANCHO_SEPARACION) * 5,
				POS_INICIAL_Y + (ALTO_COMPONENTE + ALTO_SEPARACION) * 1, ANCHO_COMPONENTE, ALTO_COMPONENTE);
		add(descripcionActividad);
		
		obligatorioActividad = new JCheckBox("Obligatoria");
		obligatorioActividad.setBounds(POS_INICIAL_X + (ANCHO_COMPONENTE + ANCHO_SEPARACION) * 6,
				POS_INICIAL_Y + (ALTO_COMPONENTE + ALTO_SEPARACION) * 1, ANCHO_COMPONENTE, ALTO_COMPONENTE);
		add(obligatorioActividad);

		btnAdicionarActividad = new JButton("Adicionar");
		btnAdicionarActividad.setBounds(POS_INICIAL_X + (ANCHO_COMPONENTE + ANCHO_SEPARACION) * 7,
				POS_INICIAL_Y + (ALTO_COMPONENTE + ALTO_SEPARACION) * 1, ANCHO_COMPONENTE, ALTO_COMPONENTE);
		btnAdicionarActividad.addActionListener((e) -> {
			crearActividad();
		});
		add(btnAdicionarActividad);
		
		JLabel lbActividades = new JLabel("Actividades:");
		lbActividades.setBounds(POS_INICIAL_X + (ANCHO_COMPONENTE + ANCHO_SEPARACION) * 0,
				POS_INICIAL_Y + (ALTO_COMPONENTE + ALTO_SEPARACION) * 2, ANCHO_COMPONENTE, ALTO_COMPONENTE);
		add(lbActividades);
		cbActividades = new JComboBox<>(App.instancia.getActividades());
		cbActividades.setBounds(POS_INICIAL_X + (ANCHO_COMPONENTE + ANCHO_SEPARACION) * 1,
				POS_INICIAL_Y + (ALTO_COMPONENTE + ALTO_SEPARACION) * 2, ANCHO_COMPONENTE, ALTO_COMPONENTE);
		cbActividades.addActionListener( a->{seleccionarActividad();} );
		add(cbActividades);
		
		JLabel lbDescripcion = new JLabel("Descripcion:");
		lbDescripcion.setBounds(POS_INICIAL_X + (ANCHO_COMPONENTE + ANCHO_SEPARACION) * 2,
				POS_INICIAL_Y + (ALTO_COMPONENTE + ALTO_SEPARACION) * 2, ANCHO_COMPONENTE, ALTO_COMPONENTE);
		add(lbDescripcion);
		
		descripcionTarea = new JTextField();
		descripcionTarea.setBounds(POS_INICIAL_X + (ANCHO_COMPONENTE + ANCHO_SEPARACION) * 3,
				POS_INICIAL_Y + (ALTO_COMPONENTE + ALTO_SEPARACION) * 2, ANCHO_COMPONENTE, ALTO_COMPONENTE);
		add(descripcionTarea); 
		
		JLabel lbTiempo = new JLabel("Tiempo:");
		lbTiempo.setBounds(POS_INICIAL_X + (ANCHO_COMPONENTE + ANCHO_SEPARACION) * 4,
				POS_INICIAL_Y + (ALTO_COMPONENTE + ALTO_SEPARACION) * 2, ANCHO_COMPONENTE, ALTO_COMPONENTE);
		add(lbTiempo);
		
		tiempoTarea = new JTextField();
		tiempoTarea.setBounds(POS_INICIAL_X + (ANCHO_COMPONENTE + ANCHO_SEPARACION) * 5,
				POS_INICIAL_Y + (ALTO_COMPONENTE + ALTO_SEPARACION) * 2, ANCHO_COMPONENTE, ALTO_COMPONENTE);
		add(tiempoTarea); 
		
		
		
		obligatorioTarea = new JCheckBox("Obligatoria");
		obligatorioTarea.setBounds(POS_INICIAL_X + (ANCHO_COMPONENTE + ANCHO_SEPARACION) * 6,
				POS_INICIAL_Y + (ALTO_COMPONENTE + ALTO_SEPARACION) * 2, ANCHO_COMPONENTE, ALTO_COMPONENTE);
		add(obligatorioTarea); 
		
		
		JLabel lbPosicion = new JLabel("Posicion:");
		lbPosicion.setBounds(POS_INICIAL_X + (ANCHO_COMPONENTE + ANCHO_SEPARACION) * 7,
				POS_INICIAL_Y + (ALTO_COMPONENTE + ALTO_SEPARACION) * 2, ANCHO_COMPONENTE, ALTO_COMPONENTE);
		add(lbPosicion);
		
		posicionTarea = new JTextField();
		posicionTarea.setBounds(POS_INICIAL_X + (ANCHO_COMPONENTE + ANCHO_SEPARACION) * 8,
				POS_INICIAL_Y + (ALTO_COMPONENTE + ALTO_SEPARACION) * 2, ANCHO_COMPONENTE, ALTO_COMPONENTE);
		add(posicionTarea); 
		
		
		btnAdicionarTarea = new JButton("Adicionar");
		btnAdicionarTarea.setBounds(POS_INICIAL_X + (ANCHO_COMPONENTE + ANCHO_SEPARACION) * 9,
				POS_INICIAL_Y + (ALTO_COMPONENTE + ALTO_SEPARACION) * 2, ANCHO_COMPONENTE, ALTO_COMPONENTE);
		btnAdicionarTarea.addActionListener((e) -> {
			crearTarea();
		});
		add(btnAdicionarTarea);

		
		JLabel lbTareas = new JLabel("Tareas");
		lbTareas.setBounds(POS_INICIAL_X + (ANCHO_COMPONENTE + ANCHO_SEPARACION) * 0,
				POS_INICIAL_Y + (ALTO_COMPONENTE + ALTO_SEPARACION) * 3, ANCHO_COMPONENTE, ALTO_COMPONENTE);
		add(lbTareas);
		tareas = new JList<>(App.instancia.getTareas());
		tareas.setBounds(POS_INICIAL_X + (ANCHO_COMPONENTE + ANCHO_SEPARACION) * 0,
				POS_INICIAL_Y + (ALTO_COMPONENTE + ALTO_SEPARACION) * 4, ANCHO_COMPONENTE*5, ALTO_COMPONENTE*10);
		add(tareas);
		
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
