package co.edu.uniquindio.dulcelandia.views;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import co.edu.uniquindio.dulcelandia.model.Tienda;
import co.edu.uniquindio.dulcelandia.model.Cliente;
import co.edu.uniquindio.dulcelandia.model.Empleado;

import javax.swing.JOptionPane;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.beans.PojoProperties;
import co.edu.uniquindio.dulcelandia.controllers.CrudClienteViewController;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;

import co.edu.uniquindio.dulcelandia.controllers.CrudClienteViewController;
import co.edu.uniquindio.dulcelandia.controllers.CrudEmpleadoViewController;
import co.edu.uniquindio.dulcelandia.model.Tienda;

public class EmpleadoView extends Composite {
	private DataBindingContext m_bindingContex;
	
	private Table table;
	CrudEmpleadoViewController crudEmpleadoViewController = new CrudEmpleadoViewController();
	Tienda tienda= crudEmpleadoViewController.getTienda();
	Empleado empleadoSeleccionado;
	private Text textNombre;
	private Text textCedula;
	private Text textTelefono;
	private Text textEmail;
	private Text text_4;
	private TableViewer tableViewer;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public EmpleadoView(Composite parent, int style) {
		super(parent, style);
		
		Group grpInformacionDeEmpleados = new Group(this, SWT.NONE);
		grpInformacionDeEmpleados.setText("Informacion de empleados");
		grpInformacionDeEmpleados.setBounds(0, 240, 580, 174);
		
		Label lblNombreDelEmpleado = new Label(grpInformacionDeEmpleados, SWT.NONE);
		lblNombreDelEmpleado.setText("Nombre del Empleado:");
		lblNombreDelEmpleado.setBounds(10, 28, 122, 15);
		
		textNombre = new Text(grpInformacionDeEmpleados, SWT.BORDER);
		textNombre.setBounds(138, 25, 134, 21);
		
		Label lblCedulaDelEmpleado = new Label(grpInformacionDeEmpleados, SWT.NONE);
		lblCedulaDelEmpleado.setText("Cedula del Empleado:");
		lblCedulaDelEmpleado.setBounds(10, 66, 122, 15);
		
		textCedula = new Text(grpInformacionDeEmpleados, SWT.BORDER);
		textCedula.setBounds(138, 63, 134, 21);
		
		Label label_2 = new Label(grpInformacionDeEmpleados, SWT.NONE);
		label_2.setText("Telefono:");
		label_2.setBounds(289, 28, 74, 15);
		
		textTelefono = new Text(grpInformacionDeEmpleados, SWT.BORDER);
		textTelefono.setBounds(413, 25, 124, 21);
		
		Label lblEmailDelEmpleado = new Label(grpInformacionDeEmpleados, SWT.NONE);
		lblEmailDelEmpleado.setText("Email del Empleado:");
		lblEmailDelEmpleado.setBounds(292, 67, 115, 15);
		
		textEmail = new Text(grpInformacionDeEmpleados, SWT.BORDER);
		textEmail.setBounds(413, 63, 124, 21);
		
		Label label_4 = new Label(grpInformacionDeEmpleados, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_4.setBounds(0, 105, 580, 2);
		
		Button btnAgregarEmpleado = new Button(grpInformacionDeEmpleados, SWT.NONE);
		btnAgregarEmpleado.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String notificacion="";
				if (verificarCamposVacios()==true){
						notificacion = crudEmpleadoViewController.crearEmpleado(textNombre.getText(), textCedula.getText(),textTelefono.getText(),textEmail.getText());
						System.out.println(notificacion);
						
						initDataBindings();
						limpiarCamposTexto();
						crudEmpleadoViewController.guardar();
						crudEmpleadoViewController.registrarAccion("Registro de nuevo usuario", 1, "Crear empleado");
			}
			}
			
			
		});
		btnAgregarEmpleado.setText("Agregar Empleado");
		btnAgregarEmpleado.setBounds(144, 132, 108, 25);
		
		
		
		Group grpListaDeEmpleados = new Group(this, SWT.NONE);
		grpListaDeEmpleados.setText("Lista de Empleados");
		grpListaDeEmpleados.setBounds(0, 0, 586, 239);
		
		tableViewer = new TableViewer(grpListaDeEmpleados, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				empleadoSeleccionado = (Empleado) table.getItem(table.getSelectionIndex()).getData();
				textNombre.setText(empleadoSeleccionado.getNombre());
				textCedula.setText(empleadoSeleccionado.getCedula());
				textTelefono.setText(empleadoSeleccionado.getTelefono());
				textEmail.setText(empleadoSeleccionado.getEmail());
					}
		});
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(10, 59, 566, 170);
		
		Button btnActualizarEmpleado = new Button(grpInformacionDeEmpleados, SWT.NONE);
		btnActualizarEmpleado.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String notificacion="";
				
						notificacion = crudEmpleadoViewController.actualizarEmpleado(textNombre.getText(), textCedula.getText(),textTelefono.getText(),textEmail.getText());
						System.out.println(notificacion);
						
						initDataBindings();
						limpiarCamposTexto();
						crudEmpleadoViewController.guardar();
						crudEmpleadoViewController.registrarAccion("Registro de nuevo usuario", 1, "Actualizar empleado");
			
			
			}
		});
		btnActualizarEmpleado.setText("Actualizar Empleado");
		btnActualizarEmpleado.setBounds(304, 132, 113, 25);
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tableColumn = tableViewerColumn.getColumn();
		tableColumn.setWidth(140);
		tableColumn.setText("Nombre ");
		tableColumn.setAlignment(SWT.CENTER);
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tableColumn_1 = tableViewerColumn_1.getColumn();
		tableColumn_1.setWidth(140);
		tableColumn_1.setText("Cedula");
		tableColumn_1.setAlignment(SWT.CENTER);
		
		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tableColumn_2 = tableViewerColumn_2.getColumn();
		tableColumn_2.setWidth(140);
		tableColumn_2.setText("Telefono");
		tableColumn_2.setAlignment(SWT.CENTER);
		
		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tableColumn_3 = tableViewerColumn_3.getColumn();
		tableColumn_3.setWidth(140);
		tableColumn_3.setText("Email");
		tableColumn_3.setAlignment(SWT.CENTER);
		
		Label label_5 = new Label(grpListaDeEmpleados, SWT.NONE);
		label_5.setText("Busqueda:");
		label_5.setBounds(10, 24, 63, 15);
		
		text_4 = new Text(grpListaDeEmpleados, SWT.BORDER);
		text_4.setBounds(71, 21, 258, 21);
		
		Button btnNuevoEmpleado = new Button(grpListaDeEmpleados, SWT.NONE);
		btnNuevoEmpleado.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				limpiarCamposTexto();
				initDataBindings();
			}
		});
		btnNuevoEmpleado.setText("Nuevo Empleado");
		btnNuevoEmpleado.setBounds(340, 20, 110, 25);
		
		Button btnEliminarEmpleado = new Button(grpListaDeEmpleados, SWT.NONE);
		btnEliminarEmpleado.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				boolean bandera = false;
				if (empleadoSeleccionado!= null){
					bandera = crudEmpleadoViewController.eliminarEmpleado(empleadoSeleccionado);
					if (bandera==true){
				 JOptionPane.showMessageDialog(null, ("Empleado eliminado con exito"));
				 crudEmpleadoViewController.guardar();
				 crudEmpleadoViewController.registrarAccion("Registro de nuevo usuario", 1, "Eliminar empleado");
				 initDataBindings();
				 limpiarCamposTexto();
					}
				}
				else 
					JOptionPane.showMessageDialog(null, ("El Empleado no se pudo eliminar de la tienda"));
					}
		});
		btnEliminarEmpleado.setText("Eliminar Empleado");
		btnEliminarEmpleado.setBounds(462, 20, 114, 25);
		m_bindingContex = initDataBindings();

	}
	
	protected boolean verificarCamposVacios() {
		// TODO Auto-generated method stub
		if (textNombre.getText().equalsIgnoreCase("") || textCedula.getText().equalsIgnoreCase("") || textTelefono.getText().equalsIgnoreCase("")||textEmail.getText().equalsIgnoreCase("")){
		
		return false;
		}
		else {
			return true;
		}
	}
	
	
	protected void limpiarCamposTexto(){
		textNombre.setText("");
		textCedula.setText("");
		textTelefono.setText("");
		textEmail.setText("");
	}
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap[] observeMaps = PojoObservables.observeMaps(listContentProvider.getKnownElements(), Empleado.class, new String[]{"nombre", "cedula", "telefono", "email"});
		tableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		tableViewer.setContentProvider(listContentProvider);
		//
		IObservableList listaEmpleadosTiendaObserveList = PojoProperties.list("listaEmpleados").observe(tienda);
		tableViewer.setInput(listaEmpleadosTiendaObserveList);
		//
		return bindingContext;
	}
}
