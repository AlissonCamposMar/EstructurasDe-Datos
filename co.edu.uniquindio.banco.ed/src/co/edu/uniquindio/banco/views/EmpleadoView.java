package co.edu.uniquindio.banco.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import co.edu.uniquindio.banco.controllers.CrudClienteViewController;
import co.edu.uniquindio.banco.controllers.CrudEmpleadoViewController;
import co.edu.uniquindio.banco.model.Banco;
import co.edu.uniquindio.banco.model.Cliente;
import co.edu.uniquindio.banco.model.Empleado;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.jface.viewers.ComboViewer;

import javax.swing.JOptionPane;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.jface.databinding.viewers.ObservableSetContentProvider;
import org.eclipse.core.databinding.observable.set.IObservableSet;

public class EmpleadoView extends Composite {
	private DataBindingContext m_bindingContext;
	
	
	CrudEmpleadoViewController crudEmpleadoViewController = new CrudEmpleadoViewController();
	
	//Para hacer uso del databinding
	Banco banco = crudEmpleadoViewController.getBanco();
	
	
	
	private Table table;
	private Text text_Nombre;
	private Text text_Apellidos;
	private Text text_Cedula;
	private Text text_FechaNacimiento;
	private TableViewer tableViewerEmpleados;
	private Text text_Telefono;
	private Text text_Correo;
	private Text text_Direccion;
	private Text text_Busqueda;
	
	
	private String busquedad = "";
	
	Empleado empleadoSeleccionado;
	
	
	private Text text_Codigo;
	private Text text_Salario;
	

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public EmpleadoView(Composite parent, int style) {
		super(parent, style);
		
		Group grpListaDeClientes = new Group(this, SWT.NONE);
		grpListaDeClientes.setText("Lista de Empleados");
		grpListaDeClientes.setBounds(10, 10, 881, 268);
		
		tableViewerEmpleados = new TableViewer(grpListaDeClientes, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewerEmpleados.getTable();
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				empleadoSeleccionado =  (Empleado) table.getItem(table.getSelectionIndex()).getData();
				
				text_Nombre.setText(empleadoSeleccionado.getNombre());
				text_Apellidos.setText(empleadoSeleccionado.getApellido());
				text_Cedula.setText(empleadoSeleccionado.getCedula());
				text_FechaNacimiento.setText(empleadoSeleccionado.getFechaNacimiento());
				text_Telefono.setText(empleadoSeleccionado.getTelefono());
				text_Correo.setText(empleadoSeleccionado.getCorreo());
				text_Direccion.setText(empleadoSeleccionado.getDireccion());
				text_Codigo.setText(empleadoSeleccionado.getCodigo());
				text_Salario.setText(String.valueOf(empleadoSeleccionado.getSalario()));
				
			}
		});
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(10, 102, 855, 156);
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewerEmpleados, SWT.NONE);
		TableColumn tblclmnNombre = tableViewerColumn.getColumn();
		tblclmnNombre.setAlignment(SWT.CENTER);
		tblclmnNombre.setWidth(182);
		tblclmnNombre.setText("Nombre");
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewerEmpleados, SWT.NONE);
		TableColumn tblclmnApellido = tableViewerColumn_1.getColumn();
		tblclmnApellido.setAlignment(SWT.CENTER);
		tblclmnApellido.setWidth(200);
		tblclmnApellido.setText("Apellido");
		
		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewerEmpleados, SWT.NONE);
		TableColumn tblclmnCedula = tableViewerColumn_2.getColumn();
		tblclmnCedula.setAlignment(SWT.CENTER);
		tblclmnCedula.setWidth(181);
		tblclmnCedula.setText("Cedula");
		
		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(tableViewerEmpleados, SWT.NONE);
		TableColumn tblclmnDireccion = tableViewerColumn_3.getColumn();
		tblclmnDireccion.setAlignment(SWT.CENTER);
		tblclmnDireccion.setWidth(285);
		tblclmnDireccion.setText("Direccion");
		
		Button btnEliminarEmpleado = new Button(grpListaDeClientes, SWT.NONE);
		btnEliminarEmpleado.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				Boolean flagEliminado = false;
				
				if(empleadoSeleccionado != null)
				{
					flagEliminado = crudEmpleadoViewController.eliminarEmpleado(empleadoSeleccionado);
					
					if(flagEliminado == true){
						JOptionPane.showMessageDialog(null, "Cliente eliminado con éxito");
						initDataBindings();
						limpiarCamposTexto();
					}
					else
						JOptionPane.showMessageDialog(null, "El Cliente no se ha podido eliminar");
				}
			}
		});
		btnEliminarEmpleado.setBounds(617, 32, 142, 30);
		btnEliminarEmpleado.setText("Eliminar empleado");
		
		Label lblB = new Label(grpListaDeClientes, SWT.NONE);
		lblB.setBounds(10, 37, 87, 20);
		lblB.setText("B\u00FAsquedad:");
		
		text_Busqueda = new Text(grpListaDeClientes, SWT.BORDER);
		text_Busqueda.setBounds(103, 31, 250, 26);
		
		
		// Filter at every keystroke
		text_Busqueda.addModifyListener(new ModifyListener() {


			@Override
			public void modifyText(ModifyEvent e) {
				Text source = (Text) e.getSource();
				busquedad = source.getText();
				// Trigger update in the viewer
				tableViewerEmpleados.refresh();
			}
		});

		
		text_Busqueda.addSelectionListener(new SelectionAdapter() {
			public void widgetDefaultSelected(SelectionEvent e) {
				if (e.detail == SWT.CANCEL) {
					Text text = (Text) e.getSource();
					text.setText("");
					//
				}
			}
		});
		
		
		tableViewerEmpleados.addFilter(new ViewerFilter() {
			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {

				Empleado empleadoBusquedad = (Empleado) element;

				try {

					return  
							empleadoBusquedad.getNombre().contains(busquedad) ||
							empleadoBusquedad.getNombre().toLowerCase().contains(busquedad.toLowerCase()) ||
							empleadoBusquedad.getNombre().toUpperCase().contains(busquedad.toUpperCase()) ||

							empleadoBusquedad.getApellido().contains(busquedad) ||
							empleadoBusquedad.getApellido().toLowerCase().contains(busquedad.toLowerCase()) ||
							empleadoBusquedad.getApellido().toUpperCase().contains(busquedad.toUpperCase()) ||

							empleadoBusquedad.getCedula().contains(busquedad) ||
							empleadoBusquedad.getCedula().toLowerCase().contains(busquedad.toLowerCase()) ||
							empleadoBusquedad.getCedula().toUpperCase().contains(busquedad.toUpperCase()) ||

							empleadoBusquedad.getDireccion().contains(busquedad) ||
							empleadoBusquedad.getDireccion().toLowerCase().contains(busquedad.toLowerCase()) ||
							empleadoBusquedad.getDireccion().toUpperCase().contains(busquedad.toUpperCase()) ;

				} catch (Exception e) {
					// TODO: handle exception
					return false;
				}
			}
		});
		
		
		
		
		
		Button btnNuevoEmpleado = new Button(grpListaDeClientes, SWT.NONE);
		btnNuevoEmpleado.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				limpiarCamposTexto();
			}
		});
		btnNuevoEmpleado.setBounds(436, 32, 142, 30);
		btnNuevoEmpleado.setText("Nuevo empleado");
		
		Label label = new Label(grpListaDeClientes, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(10, 79, 855, 2);
		
		Label label_2 = new Label(grpListaDeClientes, SWT.SEPARATOR | SWT.VERTICAL);
		label_2.setBounds(395, 25, 2, 46);
		
		Group grpInformacinDetalleCliente = new Group(this, SWT.NONE);
		grpInformacinDetalleCliente.setText("Informaci\u00F3n Detalle Cliente");
		grpInformacinDetalleCliente.setBounds(10, 284, 881, 340);
		
		Label lblNombre = new Label(grpInformacinDetalleCliente, SWT.NONE);
		lblNombre.setBounds(20, 41, 70, 20);
		lblNombre.setText("Nombre:");
		
		text_Nombre = new Text(grpInformacinDetalleCliente, SWT.BORDER);
		text_Nombre.setBounds(96, 35, 321, 26);
		
		Label lblLabel = new Label(grpInformacinDetalleCliente, SWT.NONE);
		lblLabel.setBounds(454, 38, 90, 20);
		lblLabel.setText("Apellido(s):");
		
		text_Apellidos = new Text(grpInformacinDetalleCliente, SWT.BORDER);
		text_Apellidos.setBounds(550, 38, 321, 26);
		
		Label lblNewLabel = new Label(grpInformacinDetalleCliente, SWT.NONE);
		lblNewLabel.setBounds(20, 99, 70, 20);
		lblNewLabel.setText("C\u00E9dula:");
		
		text_Cedula = new Text(grpInformacinDetalleCliente, SWT.BORDER);
		text_Cedula.setBounds(96, 93, 321, 26);
		
		Label lblDireccin = new Label(grpInformacinDetalleCliente, SWT.NONE);
		lblDireccin.setBounds(20, 243, 70, 20);
		lblDireccin.setText("Direcci\u00F3n:");
		
		text_FechaNacimiento = new Text(grpInformacinDetalleCliente, SWT.BORDER);
		text_FechaNacimiento.setBounds(604, 93, 267, 26);
		
		Label lblTelfono = new Label(grpInformacinDetalleCliente, SWT.NONE);
		lblTelfono.setBounds(20, 148, 70, 20);
		lblTelfono.setText("Tel\u00E9fono:");
		
		Label lblFechaDeNacimiento = new Label(grpInformacinDetalleCliente, SWT.NONE);
		lblFechaDeNacimiento.setBounds(454, 99, 148, 20);
		lblFechaDeNacimiento.setText("Fecha de nacimiento:");
		
		Label lblCorreo = new Label(grpInformacinDetalleCliente, SWT.NONE);
		lblCorreo.setBounds(458, 148, 70, 20);
		lblCorreo.setText("Correo:");
		
		text_Telefono = new Text(grpInformacinDetalleCliente, SWT.BORDER);
		text_Telefono.setBounds(96, 148, 321, 26);
		
		text_Correo = new Text(grpInformacinDetalleCliente, SWT.BORDER);
		text_Correo.setBounds(550, 148, 321, 26);
		
		text_Codigo = new Text(grpInformacinDetalleCliente, SWT.BORDER);
		text_Codigo.setBounds(96, 189, 321, 26);
		
		text_Salario = new Text(grpInformacinDetalleCliente, SWT.BORDER);
		text_Salario.setBounds(550, 186, 321, 26);
		
		text_Direccion = new Text(grpInformacinDetalleCliente, SWT.BORDER);
		text_Direccion.setBounds(96, 237, 775, 26);
		
		Button btnAgergarEmpleado = new Button(grpInformacinDetalleCliente, SWT.NONE);
		btnAgergarEmpleado.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				String notificacion = "";
				
				if(verificarCamposVacios() == true)
				{
					notificacion = crudEmpleadoViewController.crearEmpleado(text_Nombre.getText(), text_Apellidos.getText(), text_Cedula.getText(),text_FechaNacimiento.getText(), text_Telefono.getText(), 
							                                 text_Correo.getText(), text_Direccion.getText(), text_Codigo.getText(), text_Salario.getText());
					JOptionPane.showMessageDialog(null,notificacion );
					
					initDataBindings();
					limpiarCamposTexto();
				}
				
			}
		});
		btnAgergarEmpleado.setBounds(247, 300, 162, 30);
		btnAgergarEmpleado.setText("Agregar empleado");
		
		Button btnActualizarEmpleado = new Button(grpInformacinDetalleCliente, SWT.NONE);
		btnActualizarEmpleado.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
			}
		});
		btnActualizarEmpleado.setBounds(443, 300, 162, 30);
		btnActualizarEmpleado.setText("Actualizar empleado");
		
		Label label_1 = new Label(grpInformacinDetalleCliente, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(20, 281, 851, 2);
		
		Label lblCdigo = new Label(grpInformacinDetalleCliente, SWT.NONE);
		lblCdigo.setBounds(20, 192, 70, 20);
		lblCdigo.setText("C\u00F3digo:");
		
		Label lblSalario = new Label(grpInformacinDetalleCliente, SWT.NONE);
		lblSalario.setBounds(458, 192, 70, 20);
		lblSalario.setText("Salario:");
		m_bindingContext = initDataBindings();

	}

	protected boolean verificarCamposVacios() {
		
		if(text_Nombre.getText().equalsIgnoreCase("") || text_Apellidos.getText().equalsIgnoreCase("") || text_Cedula.getText().equalsIgnoreCase("") ||
				text_FechaNacimiento.getText().equalsIgnoreCase("") || text_Telefono.getText().equalsIgnoreCase("") || text_Correo.getText().equalsIgnoreCase("") ||
				text_Direccion.getText().equalsIgnoreCase("") || text_Codigo.getText().equalsIgnoreCase("") || text_Salario.getText().equalsIgnoreCase(""))
		{
			return false;
		}
		else
			return true;
	}

	protected void limpiarCamposTexto() {
		text_Nombre.setText("");
		text_Apellidos.setText("");
		text_Cedula.setText("");
		text_FechaNacimiento.setText("");
		text_Telefono.setText("");
		text_Correo.setText("");
		text_Direccion.setText("");
		text_Codigo.setText("");
		text_Salario.setText("");
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableSetContentProvider setContentProvider = new ObservableSetContentProvider();
		IObservableMap[] observeMaps = PojoObservables.observeMaps(setContentProvider.getKnownElements(), Empleado.class, new String[]{"nombre", "apellido", "cedula", "direccion"});
		tableViewerEmpleados.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		tableViewerEmpleados.setContentProvider(setContentProvider);
		//
		IObservableSet listaEmpleadosBancoObserveSet = PojoProperties.set("listaEmpleados").observe(banco);
		tableViewerEmpleados.setInput(listaEmpleadosBancoObserveSet);
		//
		return bindingContext;
	}
}
