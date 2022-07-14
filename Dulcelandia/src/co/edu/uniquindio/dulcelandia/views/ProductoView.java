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
import co.edu.uniquindio.dulcelandia.model.Producto;

import javax.swing.JOptionPane;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.core.databinding.observable.list.IObservableList;
import co.edu.uniquindio.dulcelandia.controllers.CrudProductoViewController;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;


public class ProductoView extends Composite {
	private DataBindingContext m_bindingContext;
	
	private Table table;
	CrudProductoViewController crudProductoViewController = new CrudProductoViewController();
	Tienda tienda= crudProductoViewController.getTienda();

	private Text text_NombreProducto;
	private Text text_Codigo;
	private Text text_Precio;
	private Text text_CantidadExistente;
	private Text text_Busqueda;
	private TableViewer tableViewerProductos;
	Producto productoSeleccionado;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
     

	
	public ProductoView(Composite parent, int style) {
		super(parent, style);
		
		Group grpInformacin = new Group(this, SWT.NONE);
		grpInformacin.setText("Informaci\u00F3n de productos");
		grpInformacin.setBounds(10, 253, 580, 191);
		
		Label lblNombre = new Label(grpInformacin, SWT.NONE);
		lblNombre.setBounds(10, 22, 128, 15);
		lblNombre.setText("Nombre del producto:");
		
		text_NombreProducto = new Text(grpInformacin, SWT.BORDER);
		text_NombreProducto.setBounds(143, 19, 134, 21);
		
		Label lblCodigoDelProducto = new Label(grpInformacin, SWT.NONE);
		lblCodigoDelProducto.setBounds(297, 22, 110, 15);
		lblCodigoDelProducto.setText("Codigo del producto");
		
		text_Codigo = new Text(grpInformacin, SWT.BORDER);
		text_Codigo.setBounds(421, 19, 145, 21);
		
		Label lblPrecio = new Label(grpInformacin, SWT.NONE);
		lblPrecio.setBounds(10, 59, 55, 15);
		lblPrecio.setText("Precio:");
		
		text_Precio = new Text(grpInformacin, SWT.BORDER);
		text_Precio.setBounds(143, 59, 134, 21);
		
		Label lblCantidadExistencias = new Label(grpInformacin, SWT.NONE);
		lblCantidadExistencias.setBounds(297, 65, 110, 15);
		lblCantidadExistencias.setText("Cantidad Existencias");
		
		text_CantidadExistente = new Text(grpInformacin, SWT.BORDER);
		text_CantidadExistente.setBounds(420, 59, 146, 21);
		
		Label label = new Label(grpInformacin, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(1, 101, 569, 2);
		
		Button btnAgregarProducto = new Button(grpInformacin, SWT.NONE);
		btnAgregarProducto.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String notificacion="";
				if (verificarCamposVacios()==true){
						notificacion = crudProductoViewController.crearProducto(text_NombreProducto.getText(), text_Codigo.getText(),text_Precio.getText(),text_CantidadExistente.getText());
						System.out.println(notificacion);
						
						initDataBindings();
						limpiarCamposTexto();
						crudProductoViewController.guardar();
						crudProductoViewController.registrarAccion("Registro de nuevo usuario", 1, "Agregar producto");
			}
			}

			protected boolean verificarCamposVacios() {
				// TODO Auto-generated method stub
				if (text_NombreProducto.getText().equalsIgnoreCase("") || text_Codigo.getText().equalsIgnoreCase("") || text_Precio.getText().equalsIgnoreCase("")||text_CantidadExistente.getText().equalsIgnoreCase("")){
				
				return false;
				}
				else {
					return true;
				}
			}
		});
		btnAgregarProducto.setBounds(140, 125, 110, 25);
		btnAgregarProducto.setText("Agregar producto");
		
		Button btnActualizarProducto = new Button(grpInformacin, SWT.NONE);
		btnActualizarProducto.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String notificacion="";
			
						notificacion = crudProductoViewController.actualizarProducto(text_NombreProducto.getText(), text_Codigo.getText(),text_Precio.getText(),text_CantidadExistente.getText());
						System.out.println(notificacion);
						
						initDataBindings();
						limpiarCamposTexto();
						crudProductoViewController.guardar();
						crudProductoViewController.registrarAccion("Registro de nuevo usuario", 1, "Actualizar producto");
			
			}
			protected boolean verificarCamposVacios() {
				// TODO Auto-generated method stub
				if (text_NombreProducto.getText().equalsIgnoreCase("") || text_Codigo.getText().equalsIgnoreCase("") || text_Precio.getText().equalsIgnoreCase("")||text_CantidadExistente.getText().equalsIgnoreCase("")){
				
				return false;
				}
				else {
					return true;
				}
			}
		});
		btnActualizarProducto.setBounds(332, 125, 110, 25);
		btnActualizarProducto.setText("Actualizar producto");
		
		Group grpListaDeProductos = new Group(this, SWT.NONE);
		grpListaDeProductos.setText("Lista de productos");
		grpListaDeProductos.setBounds(10, 10, 587, 237);
		
		tableViewerProductos = new TableViewer(grpListaDeProductos, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewerProductos.getTable();
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				productoSeleccionado = (Producto) table.getItem(table.getSelectionIndex()).getData();
				text_NombreProducto.setText(productoSeleccionado.getNombreProducto());
				text_Codigo.setText(productoSeleccionado.getCodigo());
				text_Precio.setText(productoSeleccionado.getPrecio());
				text_CantidadExistente.setText(productoSeleccionado.getCantidadExistente());
					}
		});
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(10, 54, 560, 158);
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewerProductos, SWT.NONE);
		TableColumn tblclmnNombreDelProducto = tableViewerColumn.getColumn();
		tblclmnNombreDelProducto.setWidth(148);
		tblclmnNombreDelProducto.setText("Nombre del producto");
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewerProductos, SWT.NONE);
		TableColumn tblclmnCodigoDelProducto = tableViewerColumn_1.getColumn();
		tblclmnCodigoDelProducto.setWidth(137);
		tblclmnCodigoDelProducto.setText("Codigo del producto");
		
		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewerProductos, SWT.NONE);
		TableColumn tblclmnPrecio = tableViewerColumn_2.getColumn();
		tblclmnPrecio.setAlignment(SWT.CENTER);
		tblclmnPrecio.setWidth(134);
		tblclmnPrecio.setText("Precio");
		
		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(tableViewerProductos, SWT.NONE);
		TableColumn tblclmnCantidadExistencias = tableViewerColumn_3.getColumn();
		tblclmnCantidadExistencias.setWidth(139);
		tblclmnCantidadExistencias.setText("Cantidad Existencias");
		
		Button btnNuevoProducto = new Button(grpListaDeProductos, SWT.NONE);
		btnNuevoProducto.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				limpiarCamposTexto();
				}
		});
		
		btnNuevoProducto.setBounds(344, 20, 99, 25);
		btnNuevoProducto.setText("Nuevo producto");
		
		Button btnEliminarProducto = new Button(grpListaDeProductos, SWT.NONE);
		btnEliminarProducto.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean bandera = false;
				if (productoSeleccionado!= null){
					bandera = crudProductoViewController.eliminarProducto(productoSeleccionado);
					if (bandera==true){
				 JOptionPane.showMessageDialog(null, ("Producto eliminado con exito"));
				 crudProductoViewController.guardar();
				 crudProductoViewController.registrarAccion("Registro de nuevo usuario", 1, "Eliminar producto");
				 initDataBindings();
				 limpiarCamposTexto();
					}
				}
				else 
					JOptionPane.showMessageDialog(null, ("El producto no se pudo eliminar de la tienda"));
					}
			
		});
		btnEliminarProducto.setBounds(463, 20, 107, 25);
		btnEliminarProducto.setText("Eliminar producto");
		
		Label lblBsqueda = new Label(grpListaDeProductos, SWT.NONE);
		lblBsqueda.setBounds(10, 25, 55, 15);
		lblBsqueda.setText("B\u00FAsqueda:");
		
		text_Busqueda = new Text(grpListaDeProductos, SWT.BORDER);
		text_Busqueda.setBounds(71, 22, 252, 21);
		m_bindingContext = initDataBindings();
		
      
        	
        }
	protected void limpiarCamposTexto() {
		// TODO Auto-generated method stub
		text_NombreProducto.setText("");
		text_Codigo.setText("");
		text_Precio.setText("");
		text_NombreProducto.setText("");
		text_CantidadExistente.setText("");
	}
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap[] observeMaps = PojoObservables.observeMaps(listContentProvider.getKnownElements(), Producto.class, new String[]{"nombreProducto", "codigo", "precio", "cantidadExistente"});
		tableViewerProductos.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		tableViewerProductos.setContentProvider(listContentProvider);
		//
		IObservableList listaProductosTiendaObserveList = PojoProperties.list("listaProductos").observe(tienda);
		tableViewerProductos.setInput(listaProductosTiendaObserveList);
		//
		return bindingContext;
	}
}