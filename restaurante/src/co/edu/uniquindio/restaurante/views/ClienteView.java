package co.edu.uniquindio.restaurante.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import co.edu.uniquindio.restaurante.controllers.CrudClienteViewController;
import co.edu.uniquindio.restaurante.model.Restaurante;
import co.edu.uniquindio.restaurante.util.util;

import java.io.IOException;

import javax.swing.JOptionPane;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.beans.PojoObservables;

import co.edu.uniquindio.restaurante.model.Cliente;
import co.edu.uniquindio.restaurante.model.Empleado;

import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.beans.PojoProperties;

public class ClienteView extends Composite {
	private DataBindingContext m_bindingContext;
	
	CrudClienteViewController crudClienteViewController = new CrudClienteViewController();
	Restaurante restaurante = crudClienteViewController.getRestaurante();
	
	Cliente clienteSeleccionado;
	String busquedad = "";
	
	private Table table;
	private Text textNombre;
	private Text textApellido;
	private Text textCedula;
	private Text textEdad;
	private Text textBusqueda;
	private TableViewer tableViewer;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ClienteView(Composite parent, int style) {
		super(parent, style);
		
		Group grpListaDeEmpleados = new Group(this, SWT.NONE);
		grpListaDeEmpleados.setText("Lista de Clientes");
		grpListaDeEmpleados.setBounds(10, 0, 977, 326);
		
		tableViewer = new TableViewer(grpListaDeEmpleados, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				clienteSeleccionado = (Cliente) table.getItem(table.getSelectionIndex()).getData();
				textNombre.setText(clienteSeleccionado.getNombre());
				textApellido.setText(clienteSeleccionado.getApellido());
				textCedula.setText(clienteSeleccionado.getCedula());
				textEdad.setText(clienteSeleccionado.getEdad());				
			}
		});
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(0, 69, 773, 247);
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNombre = tableViewerColumn.getColumn();
		tblclmnNombre.setWidth(236);
		tblclmnNombre.setText("Nombre");
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnApellido = tableViewerColumn_1.getColumn();
		tblclmnApellido.setWidth(228);
		tblclmnApellido.setText("Apellido");
		
		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnCedula = tableViewerColumn_2.getColumn();
		tblclmnCedula.setWidth(190);
		tblclmnCedula.setText("Cedula");
		
		TableViewerColumn tableViewerColumn_4 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnEdad = tableViewerColumn_4.getColumn();
		tblclmnEdad.setWidth(115);
		tblclmnEdad.setText("Edad");
		
		Button btnNewButton = new Button(grpListaDeEmpleados, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				util.logging("OPERACION REALIZADA CON EXITO", "CAMPOS ELIMINADOS", "INFORMACION ELIMINADA");
				limpiarCamposTexto();								
			}
		});
		btnNewButton.setBounds(317, 28, 103, 25);
		btnNewButton.setText("Nuevo cliente");
		
		Button btnEliminarEmpleado = new Button(grpListaDeEmpleados, SWT.NONE);
		btnEliminarEmpleado.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Boolean flagEliminado = false;
				
				if (clienteSeleccionado != null) {
					
					int confirmacion = JOptionPane.showConfirmDialog(null, 
							"¿Esta seguro de eliminar el cliente seleccionado?", "Confirmación", 
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);					
					// 0: Si  1: No
					
					if (confirmacion == 0) flagEliminado = crudClienteViewController.eliminarCliente(clienteSeleccionado);
					if (flagEliminado == true) {						
						util.logging("OPERACION REALIZADA CON EXITO", "CLIENTE ELIMINADO", "INFORMACION ELIMINADA");
						JOptionPane.showMessageDialog(null, "Cliente eliminado con exito.");
						initDataBindings();
						limpiarCamposTexto();
					} else if (confirmacion == 0){
						JOptionPane.showMessageDialog(null, "Por favor, seleccione un cliente valido a eliminar.");
					}
				}								
			}
		});
		btnEliminarEmpleado.setText("Eliminar cliente");
		btnEliminarEmpleado.setBounds(426, 28, 117, 25);
		
		Button btnExportarEmpleados = new Button(grpListaDeEmpleados, SWT.NONE);
		btnExportarEmpleados.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			
				String texto;
				try {
					texto = util.linealizarArchivo("clientes.txt");
					util.exportarArchivo(texto);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}																
			}
		});
		btnExportarEmpleados.setText("Exportar");
		btnExportarEmpleados.setBounds(580, 28, 117, 25);
		
		Label lblBsqueda = new Label(grpListaDeEmpleados, SWT.NONE);
		lblBsqueda.setText("B\u00FAsqueda");
		lblBsqueda.setBounds(10, 33, 55, 15);
		
		textBusqueda = new Text(grpListaDeEmpleados, SWT.BORDER);
		textBusqueda.setBounds(70, 30, 175, 21);
		
		textBusqueda.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				Text source = (Text) e.getSource();
				busquedad = source.getText();
				tableViewer.refresh();
			}
		});
				
		textBusqueda.addSelectionListener(new SelectionAdapter(){
			public void widgetDefaultSelected(SelectionEvent e) {
				if (e.detail == SWT.CANCEL) {
					Text text = (Text) e.getSource();
					text.setText("a");
					text.setText("");
				}
			}			
		});
		
		tableViewer.addFilter(new ViewerFilter() {
			public boolean select (Viewer viewer, Object parentElement, Object element) {
				Cliente clienteBusqueda = (Cliente) element;
				
				try {
					return 
							clienteBusqueda.getNombre().contains(busquedad) ||
							clienteBusqueda.getNombre().toLowerCase().contains(busquedad.toLowerCase()) ||
							clienteBusqueda.getNombre().toUpperCase().contains(busquedad.toUpperCase()) ||
							
							clienteBusqueda.getApellido().contains(busquedad) ||
							clienteBusqueda.getApellido().toLowerCase().contains(busquedad.toLowerCase()) ||
							clienteBusqueda.getApellido().toUpperCase().contains(busquedad.toUpperCase()) ||
							
							clienteBusqueda.getCedula().contains(busquedad) ||
							clienteBusqueda.getCedula().toLowerCase().contains(busquedad.toLowerCase()) ||
							clienteBusqueda.getCedula().toUpperCase().contains(busquedad.toUpperCase()) ||
							
							clienteBusqueda.getEdad().equalsIgnoreCase(busquedad);					
				} catch (Exception e) {
					return false;
				}
			}
		});;
		
		Label label_1 = new Label(grpListaDeEmpleados, SWT.SEPARATOR | SWT.VERTICAL);
		label_1.setBounds(265, -1, 2, 70);
		
		Group grpInformacinDeDetalle = new Group(this, SWT.NONE);
		grpInformacinDeDetalle.setText("Informaci\u00F3n de Detalle");
		grpInformacinDeDetalle.setBounds(10, 337, 977, 243);
		
		Label lblNewLabel = new Label(grpInformacinDeDetalle, SWT.NONE);
		lblNewLabel.setBounds(10, 34, 44, 15);
		lblNewLabel.setText("Nombre");
		
		textNombre = new Text(grpInformacinDeDetalle, SWT.BORDER);
		textNombre.setBounds(62, 34, 175, 21);
		
		Label lblApellido = new Label(grpInformacinDeDetalle, SWT.NONE);
		lblApellido.setText("Apellido");
		lblApellido.setBounds(292, 34, 44, 15);
		
		textApellido = new Text(grpInformacinDeDetalle, SWT.BORDER);
		textApellido.setBounds(346, 34, 175, 21);
		
		Label lblCedula = new Label(grpInformacinDeDetalle, SWT.NONE);
		lblCedula.setText("Cedula");
		lblCedula.setBounds(10, 77, 44, 15);
		
		textCedula = new Text(grpInformacinDeDetalle, SWT.BORDER);
		textCedula.setBounds(62, 74, 175, 21);
		
		Label lblEdad = new Label(grpInformacinDeDetalle, SWT.NONE);
		lblEdad.setText("Edad");
		lblEdad.setBounds(292, 77, 32, 15);
		
		textEdad = new Text(grpInformacinDeDetalle, SWT.BORDER);
		textEdad.setBounds(346, 74, 175, 21);
		
		Label label = new Label(grpInformacinDeDetalle, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(0, 119, 967, 2);
		
		Button btnAgregarEmpleado = new Button(grpInformacinDeDetalle, SWT.NONE);
		btnAgregarEmpleado.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String notificacion = "";
				
				if (verificarCamposVacios() == true) {
					util.logging("OPERACION REALIZADA CON EXITO", "CLIENTE AGREGADO", "INFORMACION AÑADIDA");
					notificacion = crudClienteViewController.crearCliente(textNombre.getText(), textApellido.getText(), textCedula.getText(), textEdad.getText());					
					
					JOptionPane.showMessageDialog(null, notificacion);
					
					initDataBindings();
					limpiarCamposTexto();
					
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, ingrese todos los datos del campo de cliente.");
				}
				
			}
		});
		btnAgregarEmpleado.setText("Agregar cliente");
		btnAgregarEmpleado.setBounds(89, 171, 110, 25);
		
		Button btnEliminarEmpleado_1 = new Button(grpInformacinDeDetalle, SWT.NONE);
		btnEliminarEmpleado_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			
			Boolean flagActualizado = false;
			
			if (clienteSeleccionado != null) {				
				Cliente nuevoCliente = new Cliente();		
				nuevoCliente.setNombre(textNombre.getText());
				nuevoCliente.setApellido(textApellido.getText());
				nuevoCliente.setCedula(textCedula.getText());
				nuevoCliente.setEdad(textEdad.getText());				
				
				flagActualizado = crudClienteViewController.actualizarCliente(clienteSeleccionado, nuevoCliente);
				if (flagActualizado == true) {
					util.logging("OPERACION REALIZADA CON EXITO", "CLIENTE AGREGADO", "INFORMACION AÑADIDA");
					JOptionPane.showMessageDialog(null, "Cliente actualizado con exito.");
					initDataBindings();
					limpiarCamposTexto();
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, seleccione un cliente a actualizar.");
				}
			}	
			
			}
		});
		btnEliminarEmpleado_1.setText("Actualizar cliente");
		btnEliminarEmpleado_1.setBounds(223, 171, 120, 25);
		m_bindingContext = initDataBindings();

	}

	protected void limpiarCamposTexto() {
		// TODO Auto-generated method stub
		textNombre.setText("");
		textApellido.setText("");
		textCedula.setText("");
		textEdad.setText("");		
	}

	protected boolean verificarCamposVacios(){		
		if (textNombre.getText().equalsIgnoreCase("") || textApellido.getText().equalsIgnoreCase("") || 
				textCedula.getText().equalsIgnoreCase("") || textEdad.getText().equalsIgnoreCase("")) {
			return false;
		} 
		
		return true;
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap[] observeMaps = PojoObservables.observeMaps(listContentProvider.getKnownElements(), Cliente.class, new String[]{"nombre", "apellido", "cedula", "edad"});
		tableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		tableViewer.setContentProvider(listContentProvider);
		//
		IObservableList listaClientesRestauranteObserveList = PojoProperties.list("listaClientes").observe(restaurante);
		tableViewer.setInput(listaClientesRestauranteObserveList);
		//
		return bindingContext;
	}
}
