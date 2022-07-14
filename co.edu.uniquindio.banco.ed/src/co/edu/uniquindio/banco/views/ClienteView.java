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
import co.edu.uniquindio.banco.model.Banco;
import co.edu.uniquindio.banco.model.Cliente;
import co.edu.uniquindio.banco.model.Empleado;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.jface.databinding.viewers.ObservableSetContentProvider;
import org.eclipse.core.databinding.observable.set.IObservableSet;

public class ClienteView extends Composite {
	private DataBindingContext m_bindingContext;
	
	
	CrudClienteViewController crudClienteViewController = new CrudClienteViewController();
	
	//Para hacer uso del databinding
	Banco banco = crudClienteViewController.getBanco();
	
	
	
	private Table table;
	private Text text_Nombre;
	private Text text_Apellidos;
	private Text text_Cedula;
	private Text text_FechaNacimiento;
	private TableViewer tableViewerClientes;
	private Text text_Telefono;
	private Text text_Correo;
	private Text text_Direccion;
	private Text text_Busqueda;
	
	
	private String busquedad = "";
	
	Cliente clienteSeleccionado;
	

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ClienteView(Composite parent, int style) {
		super(parent, style);
		
		Group grpListaDeClientes = new Group(this, SWT.NONE);
		grpListaDeClientes.setText("Lista de Clientes");
		grpListaDeClientes.setBounds(10, 10, 881, 306);
		
		tableViewerClientes = new TableViewer(grpListaDeClientes, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewerClientes.getTable();
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				clienteSeleccionado = (Cliente) table.getItem(table.getSelectionIndex()).getData();
				
				text_Nombre.setText(clienteSeleccionado.getNombre());
				text_Apellidos.setText(clienteSeleccionado.getApellido());
				text_Cedula.setText(clienteSeleccionado.getCedula());
				text_FechaNacimiento.setText(clienteSeleccionado.getFechaNacimiento());
				text_Telefono.setText(clienteSeleccionado.getTelefono());
				text_Correo.setText(clienteSeleccionado.getCorreo());
				text_Direccion.setText(clienteSeleccionado.getDireccion());
				
			}
		});
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(10, 112, 855, 176);
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewerClientes, SWT.NONE);
		TableColumn tblclmnNombre = tableViewerColumn.getColumn();
		tblclmnNombre.setAlignment(SWT.CENTER);
		tblclmnNombre.setWidth(182);
		tblclmnNombre.setText("Nombre");
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewerClientes, SWT.NONE);
		TableColumn tblclmnApellido = tableViewerColumn_1.getColumn();
		tblclmnApellido.setAlignment(SWT.CENTER);
		tblclmnApellido.setWidth(200);
		tblclmnApellido.setText("Apellido");
		
		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewerClientes, SWT.NONE);
		TableColumn tblclmnCedula = tableViewerColumn_2.getColumn();
		tblclmnCedula.setAlignment(SWT.CENTER);
		tblclmnCedula.setWidth(181);
		tblclmnCedula.setText("Cedula");
		
		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(tableViewerClientes, SWT.NONE);
		TableColumn tblclmnDireccion = tableViewerColumn_3.getColumn();
		tblclmnDireccion.setAlignment(SWT.CENTER);
		tblclmnDireccion.setWidth(305);
		tblclmnDireccion.setText("Direccion");
		
		Button btnEliminarCliente = new Button(grpListaDeClientes, SWT.NONE);
		btnEliminarCliente.setBounds(617, 32, 142, 30);
		btnEliminarCliente.setText("Eliminar cliente");
		
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
				tableViewerClientes.refresh();
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
		
		
		tableViewerClientes.addFilter(new ViewerFilter() {
			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {

				Cliente clienteBusquedad = (Cliente) element;

				try {

					return  
							clienteBusquedad.getNombre().contains(busquedad) ||
							clienteBusquedad.getNombre().toLowerCase().contains(busquedad.toLowerCase()) ||
							clienteBusquedad.getNombre().toUpperCase().contains(busquedad.toUpperCase()) ||

							clienteBusquedad.getApellido().contains(busquedad) ||
							clienteBusquedad.getApellido().toLowerCase().contains(busquedad.toLowerCase()) ||
							clienteBusquedad.getApellido().toUpperCase().contains(busquedad.toUpperCase()) ||

							clienteBusquedad.getCedula().contains(busquedad) ||
							clienteBusquedad.getCedula().toLowerCase().contains(busquedad.toLowerCase()) ||
							clienteBusquedad.getCedula().toUpperCase().contains(busquedad.toUpperCase()) ||

							clienteBusquedad.getDireccion().contains(busquedad) ||
							clienteBusquedad.getDireccion().toLowerCase().contains(busquedad.toLowerCase()) ||
							clienteBusquedad.getDireccion().toUpperCase().contains(busquedad.toUpperCase()) ;

				} catch (Exception e) {
					// TODO: handle exception
					return false;
				}
			}
		});
		
		
		
		
		
		Button btnNuevoCliente = new Button(grpListaDeClientes, SWT.NONE);
		btnNuevoCliente.setBounds(436, 32, 142, 30);
		btnNuevoCliente.setText("Nuevo cliente");
		
		Label label = new Label(grpListaDeClientes, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(10, 79, 847, 2);
		
		Label label_2 = new Label(grpListaDeClientes, SWT.SEPARATOR | SWT.VERTICAL);
		label_2.setBounds(395, 25, 2, 46);
		
		Group grpInformacinDetalleCliente = new Group(this, SWT.NONE);
		grpInformacinDetalleCliente.setText("Informaci\u00F3n Detalle Cliente");
		grpInformacinDetalleCliente.setBounds(10, 322, 881, 292);
		
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
		lblDireccin.setBounds(20, 193, 70, 20);
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
		text_Correo.setBounds(562, 148, 309, 26);
		
		text_Direccion = new Text(grpInformacinDetalleCliente, SWT.BORDER);
		text_Direccion.setBounds(96, 187, 775, 26);
		
		Button btnAgergarCliente = new Button(grpInformacinDetalleCliente, SWT.NONE);
		btnAgergarCliente.setBounds(247, 250, 162, 30);
		btnAgergarCliente.setText("Agergar cliente");
		
		Button btnActualizarCliente = new Button(grpInformacinDetalleCliente, SWT.NONE);
		btnActualizarCliente.setBounds(443, 250, 162, 30);
		btnActualizarCliente.setText("Actualizar cliente");
		
		Label label_1 = new Label(grpInformacinDetalleCliente, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(20, 231, 851, 2);
		m_bindingContext = initDataBindings();

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableSetContentProvider setContentProvider = new ObservableSetContentProvider();
		IObservableMap[] observeMaps = PojoObservables.observeMaps(setContentProvider.getKnownElements(), Cliente.class, new String[]{"nombre", "apellido", "cedula", "direccion"});
		tableViewerClientes.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		tableViewerClientes.setContentProvider(setContentProvider);
		//
		IObservableSet listaClientesBancoObserveSet = PojoProperties.set("listaClientes").observe(banco);
		tableViewerClientes.setInput(listaClientesBancoObserveSet);
		//
		return bindingContext;
	}
}
