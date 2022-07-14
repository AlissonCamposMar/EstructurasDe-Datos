
package co.edu.uniquindio.dulcelandia.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.beans.PojoObservables;

import co.edu.uniquindio.dulcelandia.controllers.CrudClienteViewController;
import co.edu.uniquindio.dulcelandia.controllers.CrudProductoViewController;
import co.edu.uniquindio.dulcelandia.controllers.CrudVentaViewController;
import co.edu.uniquindio.dulcelandia.model.Producto;
import co.edu.uniquindio.dulcelandia.model.Tienda;

import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.swt.widgets.Button;

public class VentaView extends Composite {
	private DataBindingContext m_bindingContext;
	private Table table;
	CrudVentaViewController crudVentaViewController = new CrudVentaViewController();
	Tienda tienda= crudVentaViewController.getTienda();
	private TableViewer tableViewer;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public VentaView(Composite parent, int style) {
		super(parent, style);
		
		Group group = new Group(this, SWT.BORDER);
		group.setBounds(10, 10, 533, 83);
		
		Label lblInformacinDeLa = new Label(group, SWT.NONE);
		lblInformacinDeLa.setBounds(10, 10, 181, 15);
		lblInformacinDeLa.setText("Informaci\u00F3n de la Venta");
		
		Label lblFecha = new Label(group, SWT.NONE);
		lblFecha.setBounds(10, 31, 55, 15);
		lblFecha.setText("Fecha:");
		
		Label lblValor = new Label(group, SWT.NONE);
		lblValor.setBounds(10, 52, 55, 15);
		lblValor.setText("Valor:");
		
		Label lblHora = new Label(group, SWT.NONE);
		lblHora.setBounds(172, 31, 55, 15);
		lblHora.setText("Hora:");
		
		Label lblNewLabel = new Label(group, SWT.NONE);
		lblNewLabel.setBounds(172, 52, 95, 15);
		lblNewLabel.setText("Numero de venta:");
		
		Button btnNewButton = new Button(group, SWT.NONE);
		btnNewButton.setBounds(329, 26, 88, 25);
		btnNewButton.setText("Nueva Venta");
		
		Button btnNewButton_1 = new Button(group, SWT.NONE);
		btnNewButton_1.setBounds(424, 26, 99, 25);
		btnNewButton_1.setText("Eliminar Venta");
		
		Group group_1 = new Group(this, SWT.BORDER);
		group_1.setBounds(10, 99, 533, 114);
		
		Label lblCliente = new Label(group_1, SWT.NONE);
		lblCliente.setBounds(10, 10, 55, 15);
		lblCliente.setText("Cliente:");
		
		CCombo combo_Cliente = new CCombo(group_1, SWT.BORDER);
		combo_Cliente.setBounds(10, 31, 136, 21);
		CrudClienteViewController clienteController = new CrudClienteViewController();
		
		Label lblNewLabel_1 = new Label(group_1, SWT.NONE);
		lblNewLabel_1.setBounds(288, 10, 113, 15);
		lblNewLabel_1.setText("Empleado:");
		
		CCombo combo_Empleado = new CCombo(group_1, SWT.BORDER);
		combo_Empleado.setBounds(288, 31, 136, 21);
		
		Label lblProducto = new Label(group_1, SWT.NONE);
		lblProducto.setBounds(10, 58, 55, 15);
		lblProducto.setText("Producto");
		
		CCombo combo = new CCombo(group_1, SWT.BORDER);
		combo.setBounds(10, 79, 136, 21);
		
		Group grpListadoDeVentas = new Group(this, SWT.BORDER);
		grpListadoDeVentas.setText("Listado de Ventas:");
		grpListadoDeVentas.setBounds(10, 220, 533, 185);
		
		tableViewer = new TableViewer(grpListadoDeVentas, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(10, 18, 513, 108);
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNombreProducto = tableViewerColumn.getColumn();
		tblclmnNombreProducto.setWidth(125);
		tblclmnNombreProducto.setText("Nombre Producto");
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnPrecio = tableViewerColumn_1.getColumn();
		tblclmnPrecio.setWidth(125);
		tblclmnPrecio.setText("Codigo");
		
		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNewColumn = tableViewerColumn_2.getColumn();
		tblclmnNewColumn.setWidth(125);
		tblclmnNewColumn.setText("Precio");
		
		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnCantidadExistente = tableViewerColumn_3.getColumn();
		tblclmnCantidadExistente.setWidth(162);
		tblclmnCantidadExistente.setText("Cantidad Existente");
		
		Button btnAgregar = new Button(grpListadoDeVentas, SWT.NONE);
		btnAgregar.setText("Agregar Venta");
		btnAgregar.setBounds(135, 138, 104, 25);
		
		Button btnNewButton_2 = new Button(grpListadoDeVentas, SWT.NONE);
		btnNewButton_2.setBounds(265, 138, 116, 25);
		btnNewButton_2.setText("Actualizar Venta");
		m_bindingContext = initDataBindings();

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
		tableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		tableViewer.setContentProvider(listContentProvider);
		//
		IObservableList listaProductosTiendaObserveList = PojoProperties.list("listaProductos").observe(tienda);
		tableViewer.setInput(listaProductosTiendaObserveList);
		//
		return bindingContext;
	}
}
