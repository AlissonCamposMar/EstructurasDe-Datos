package co.edu.uniquindio.dulcelandia.views;

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
import co.edu.uniquindio.dulcelandia.persistencia.ArchivoUtil;
import co.edu.uniquindio.dulcelandia.model.Cliente;
import co.edu.uniquindio.dulcelandia.model.Producto;

import java.io.IOException;

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

public class ClienteView extends Composite{
	private DataBindingContext m_bindingContex;
	
	private Table table;
	CrudClienteViewController crudClienteViewController = new CrudClienteViewController();
	Tienda tienda= crudClienteViewController.getTienda();
	private Text textNombre;
	private Text textCedula;
	private Text textTelefono;
	private Text textEmail;
	private Text textBusqueda;
	private Table table_1;
	Cliente clienteSeleccionado;
	private TableViewer tableViewer;
	
	ClienteView(Composite parent, int style) {
		super(parent, style);
		
		Group grpInformacion = new Group(this, SWT.NONE);
		grpInformacion.setText("Informacion de clientes");
		grpInformacion.setBounds(10, 255, 580, 182);
		
		Label lblNombre = new Label(grpInformacion, SWT.NONE);
		lblNombre.setBounds(10, 28, 108, 15);
		lblNombre.setText("Nombre del Cliente:");
		
		textNombre = new Text(grpInformacion, SWT.BORDER);
		textNombre.setBounds(124, 25, 134, 21);
		
		Label lblCedulaDelCliente = new Label(grpInformacion, SWT.NONE);
		lblCedulaDelCliente.setBounds(10, 66, 108, 15);
		lblCedulaDelCliente.setText("Cedula del cliente:");
		
		textCedula = new Text(grpInformacion, SWT.BORDER);
		textCedula.setBounds(124, 65, 134, 21);
		
		Label lblTelefono = new Label(grpInformacion, SWT.NONE);
		lblTelefono.setBounds(289, 28, 74, 15);
		lblTelefono.setText("Telefono:");
		
		textTelefono = new Text(grpInformacion, SWT.BORDER);
		textTelefono.setBounds(413, 25, 124, 21);
		
		Label lblEmailDelCliente = new Label(grpInformacion, SWT.NONE);
		lblEmailDelCliente.setBounds(292, 67, 94, 15);
		lblEmailDelCliente.setText("Email del Cliente:");
		
		textEmail = new Text(grpInformacion, SWT.BORDER);
		textEmail.setBounds(413, 66, 124, 21);
		
		Label label = new Label(grpInformacion, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(0, 105, 580, 2);
		
		Button btnAgregarCliente = new Button(grpInformacion, SWT.NONE);
		btnAgregarCliente.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String notificacion="";
				if (verificarCamposVacios()==true){
						notificacion = crudClienteViewController.crearCliente(textNombre.getText(), textCedula.getText(),textTelefono.getText(),textEmail.getText());
						System.out.println(notificacion);
						crudClienteViewController.guardar();
						crudClienteViewController.registrarAccion("Registro de nuevo usuario", 1, "Crear cliente");
						initDataBindings();
						limpiarCamposTexto();
						
			}
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
		});
		btnAgregarCliente.setBounds(144, 132, 108, 25);
		btnAgregarCliente.setText("Agregar Cliente");
		
		Button btnNewButton = new Button(grpInformacion, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String notificacion="";
				
				notificacion = crudClienteViewController.actualizarCliente(textNombre.getText(), textCedula.getText(),textTelefono.getText(),textEmail.getText());
				System.out.println(notificacion);
				
				initDataBindings();
				limpiarCamposTexto();
				crudClienteViewController.guardar();
				crudClienteViewController.registrarAccion("Registro de nuevo usuario", 1, "Actualizar cliente");
			}
		});
		
		btnNewButton.setBounds(304, 132, 113, 25);
		btnNewButton.setText("Actualizar Cliente");
		
		Button btnExportar = new Button(grpInformacion, SWT.NONE);
		btnExportar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					String texto = ArchivoUtil.generarTexto("archivoClientes.txt");
					ArchivoUtil.exportarArchivo(texto);	
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}							
			}
		});
		
		btnExportar.setBounds(462, 132, 75, 25);
		btnExportar.setText("Exportar");
		
		Group grpListaDeClientes = new Group(this, SWT.NONE);
		grpListaDeClientes.setText("Lista de Clientes");
		grpListaDeClientes.setBounds(10, 10, 586, 239);
		
		tableViewer = new TableViewer(grpListaDeClientes, SWT.BORDER | SWT.FULL_SELECTION);
		table_1 = tableViewer.getTable();
		table_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				clienteSeleccionado = (Cliente) table_1.getItem(table_1.getSelectionIndex()).getData();
				textNombre.setText(clienteSeleccionado.getNombre());
				textCedula.setText(clienteSeleccionado.getCedula());
				textTelefono.setText(clienteSeleccionado.getTelefono());
				textEmail.setText(clienteSeleccionado.getEmail());
					}
		});
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);
		table_1.setBounds(10, 59, 566, 170);
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNombre = tableViewerColumn.getColumn();
		tblclmnNombre.setAlignment(SWT.CENTER);
		tblclmnNombre.setWidth(140);
		tblclmnNombre.setText("Nombre ");
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnCedula = tableViewerColumn_1.getColumn();
		tblclmnCedula.setAlignment(SWT.CENTER);
		tblclmnCedula.setWidth(140);
		tblclmnCedula.setText("Cedula");
		
		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnTelefono = tableViewerColumn_2.getColumn();
		tblclmnTelefono.setAlignment(SWT.CENTER);
		tblclmnTelefono.setWidth(140);
		tblclmnTelefono.setText("Telefono");
		
		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnCliente = tableViewerColumn_3.getColumn();
		tblclmnCliente.setAlignment(SWT.CENTER);
		tblclmnCliente.setWidth(140);
		tblclmnCliente.setText("Email");
		
		Label lblBusqueda = new Label(grpListaDeClientes, SWT.NONE);
		lblBusqueda.setBounds(10, 24, 63, 15);
		lblBusqueda.setText("Busqueda:");
		
		textBusqueda = new Text(grpListaDeClientes, SWT.BORDER);
		textBusqueda.setBounds(71, 21, 258, 21);
		
		Button btnNuevoCliente = new Button(grpListaDeClientes, SWT.NONE);
		btnNuevoCliente.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				limpiarCamposTexto();
				initDataBindings();
					}
		});
		
		btnNuevoCliente.setBounds(340, 20, 110, 25);
		btnNuevoCliente.setText("Nuevo Cliente");
		
		Button btnEliminarCliente = new Button(grpListaDeClientes, SWT.NONE);
		btnEliminarCliente.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean bandera = false;
				if (clienteSeleccionado!= null){
					bandera = crudClienteViewController.eliminarCliente(clienteSeleccionado);
					if (bandera==true){
				 JOptionPane.showMessageDialog(null, ("Cliente eliminado con exito"));
				 crudClienteViewController.guardar();
				 crudClienteViewController.registrarAccion("Registro de nuevo usuario", 1, "Eliminar cliente");
				 initDataBindings();
				 limpiarCamposTexto();
				 
					}
				}
				else 
					JOptionPane.showMessageDialog(null, ("El cliente no se pudo eliminar de la tienda"));
					}
		});
		
		btnEliminarCliente.setBounds(462, 20, 114, 25);
		btnEliminarCliente.setText("Eliminar Cliente");
		m_bindingContex = initDataBindings();
		// TODO Auto-generated constructor stub
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
		IObservableMap[] observeMaps = PojoObservables.observeMaps(listContentProvider.getKnownElements(), Cliente.class, new String[]{"nombre", "cedula", "telefono", "email"});
		tableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		tableViewer.setContentProvider(listContentProvider);
		//
		IObservableList listaClientesTiendaObserveList = PojoProperties.list("listaClientes").observe(tienda);
		tableViewer.setInput(listaClientesTiendaObserveList);
		//
		return bindingContext;
	}
}