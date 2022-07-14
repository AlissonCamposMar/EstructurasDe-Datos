package co.edu.uniquindio.dulcelandia.views;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import co.edu.uniquindio.dulcelandia.controllers.CrudProductoViewController;
import co.edu.uniquindio.dulcelandia.controllers.CrudProveedorViewController;
import co.edu.uniquindio.dulcelandia.model.Cliente;
import co.edu.uniquindio.dulcelandia.model.Proveedor;
import co.edu.uniquindio.dulcelandia.model.Tienda;

import org.eclipse.swt.widgets.Button;

import javax.swing.JOptionPane;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class ProveedorView extends Composite {
	private DataBindingContext m_bindingContext;
	
	private Table table;
	CrudProveedorViewController crudProveedorViewController = new CrudProveedorViewController();
	Tienda tienda= crudProveedorViewController.getTienda();

	private Text text_Busqueda;
	private Text text_Nombre;
	private Text text_Cedula;
	private Text text_Telefono;
	private Text text_Email;
	private TableViewer tableViewer;
	
	Proveedor proveedorSeleccionado;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ProveedorView(Composite parent, int style) {
		super(parent, style);
		
		Group grpProveedoresYProductos = new Group(this, SWT.NONE);
		grpProveedoresYProductos.setText("Proveedores y Productos ");
		grpProveedoresYProductos.setBounds(10, 10, 554, 217);
		
		tableViewer = new TableViewer(grpProveedoresYProductos, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				proveedorSeleccionado = (Proveedor) table.getItem(table.getSelectionIndex()).getData();
				text_Nombre.setText(proveedorSeleccionado.getNombre());
				text_Cedula.setText(proveedorSeleccionado.getCedula());
				text_Telefono.setText(proveedorSeleccionado.getTelefono());
				text_Email.setText(proveedorSeleccionado.getEmail());
					}
		});
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(10, 61, 534, 146);
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNombre = tableViewerColumn.getColumn();
		tblclmnNombre.setWidth(131);
		tblclmnNombre.setText("Nombre Proveedor ");
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn = tableViewerColumn_1.getColumn();
		tblclmnNewColumn.setWidth(131);
		tblclmnNewColumn.setText("Cedula");
		
		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn_1 = tableViewerColumn_2.getColumn();
		tblclmnNewColumn_1.setWidth(131);
		tblclmnNewColumn_1.setText("Telefono");
		
		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnCantidadProducto = tableViewerColumn_3.getColumn();
		tblclmnCantidadProducto.setWidth(137);
		tblclmnCantidadProducto.setText("Email");
		
		Label lblNewLabel = new Label(grpProveedoresYProductos, SWT.NONE);
		lblNewLabel.setBounds(10, 27, 55, 15);
		lblNewLabel.setText("Buscar");
		
		text_Busqueda = new Text(grpProveedoresYProductos, SWT.BORDER);
		text_Busqueda.setBounds(71, 24, 188, 21);
		
		Button btnNuevoProveedor = new Button(grpProveedoresYProductos, SWT.NONE);
		btnNuevoProveedor.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				limpiarCamposTexto();
				initDataBindings();
			}
		});
		btnNuevoProveedor.setBounds(315, 24, 104, 25);
		btnNuevoProveedor.setText("Nuevo Proveedor");
		
		Button btnEliminarProducto = new Button(grpProveedoresYProductos, SWT.NONE);
		btnEliminarProducto.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean bandera = false;
				if (proveedorSeleccionado!= null){
					bandera = crudProveedorViewController.eliminarProveedor(proveedorSeleccionado);
					if (bandera==true){
				 JOptionPane.showMessageDialog(null, ("Proveedor eliminado con exito"));
				 crudProveedorViewController.guardar();
				 crudProveedorViewController.registrarAccion("Registro de nuevo usuario", 1, "Eliminar proveedor");
				 initDataBindings();
				 limpiarCamposTexto();
				 
					}
				}
				else 
					JOptionPane.showMessageDialog(null, ("El cliente no se pudo eliminar de la tienda"));
			}
		});
		btnEliminarProducto.setBounds(437, 24, 107, 25);
		btnEliminarProducto.setText("Eliminar Producto");
		
		Group grpInformacion = new Group(this, SWT.NONE);
		grpInformacion.setText("Informacion de Proveedor");
		grpInformacion.setBounds(20, 233, 532, 156);
		
		Label lblNombre = new Label(grpInformacion, SWT.NONE);
		lblNombre.setText("Nombre");
		lblNombre.setBounds(10, 35, 55, 15);
		
		text_Nombre = new Text(grpInformacion, SWT.BORDER);
		text_Nombre.setBounds(105, 35, 125, 21);
		
		Label lblCedula = new Label(grpInformacion, SWT.NONE);
		lblCedula.setBounds(279, 35, 96, 15);
		lblCedula.setText("Cedula");
		
		text_Cedula = new Text(grpInformacion, SWT.BORDER);
		text_Cedula.setBounds(397, 35, 125, 21);
		
		Label lblNewLabel_1 = new Label(grpInformacion, SWT.NONE);
		lblNewLabel_1.setBounds(10, 69, 85, 15);
		lblNewLabel_1.setText("Telefono");
		
		text_Telefono = new Text(grpInformacion, SWT.BORDER);
		text_Telefono.setBounds(105, 66, 125, 21);
		
		text_Email = new Text(grpInformacion, SWT.BORDER);
		text_Email.setBounds(397, 66, 125, 21);
		
		Label lblEmail = new Label(grpInformacion, SWT.NONE);
		lblEmail.setBounds(277, 69, 112, 15);
		lblEmail.setText("Email");
		
		Label label = new Label(grpInformacion, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(10, 103, 512, 2);
		
		Button btnAgregarProducto = new Button(grpInformacion, SWT.NONE);
		btnAgregarProducto.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String notificacion="";
				if (verificarCamposVacios()==true){
						notificacion = crudProveedorViewController.crearProveedor(text_Nombre.getText(), text_Cedula.getText(),text_Telefono.getText(),text_Email.getText());
						System.out.println(notificacion);
						crudProveedorViewController.guardar();
						crudProveedorViewController.registrarAccion("Registro de nuevo usuario", 1, "Crear proveedor");
						initDataBindings();
						limpiarCamposTexto();
						
			}
			}
			
			protected boolean verificarCamposVacios() {
				// TODO Auto-generated method stub
				if (text_Nombre.getText().equalsIgnoreCase("") || text_Cedula.getText().equalsIgnoreCase("") || text_Telefono.getText().equalsIgnoreCase("")||text_Email.getText().equalsIgnoreCase("")){
				return false;
				}
				else {
					return true;
				}
			}
		});
		btnAgregarProducto.setText("Agregar Proveedor");
		btnAgregarProducto.setBounds(105, 111, 125, 25);
		
		Button btnActualizar = new Button(grpInformacion, SWT.NONE);
		btnActualizar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String notificacion="";
				
				notificacion = crudProveedorViewController.actualizarProveedor(text_Nombre.getText(), text_Cedula.getText(),text_Telefono.getText(),text_Email.getText());
				System.out.println(notificacion);
				
				initDataBindings();
				limpiarCamposTexto();
				crudProveedorViewController.guardar();
				crudProveedorViewController.registrarAccion("Registro de nuevo usuario", 1, "Actualizar Proveedor");
			}
		});
		btnActualizar.setBounds(279, 111, 124, 25);
		btnActualizar.setText("Actualizar Proveedor");
		m_bindingContext = initDataBindings();

	}
	protected void limpiarCamposTexto(){
		text_Nombre.setText("");
		text_Cedula.setText("");
		text_Telefono.setText("");
		text_Email.setText("");
	}
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap[] observeMaps = PojoObservables.observeMaps(listContentProvider.getKnownElements(), co.edu.uniquindio.dulcelandia.model.Proveedor.class, new String[]{"nombre", "nombreProducto", "precioProducto", "cantidadProducto"});
		tableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		tableViewer.setContentProvider(listContentProvider);
		//
		IObservableList listaProveedoresTiendaObserveList = PojoProperties.list("listaProveedores").observe(tienda);
		tableViewer.setInput(listaProveedoresTiendaObserveList);
		//
		return bindingContext;
	}
}
