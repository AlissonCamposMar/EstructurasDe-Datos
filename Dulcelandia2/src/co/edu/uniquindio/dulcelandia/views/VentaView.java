
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

import java.io.IOException;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.beans.PojoObservables;

import co.edu.uniquindio.dulcelandia.controllers.CrudClienteViewController;
import co.edu.uniquindio.dulcelandia.controllers.CrudProductoViewController;
import co.edu.uniquindio.dulcelandia.controllers.CrudVentaViewController;
import co.edu.uniquindio.dulcelandia.model.Producto;
import co.edu.uniquindio.dulcelandia.model.Tienda;
import co.edu.uniquindio.dulcelandia.persistencia.ArchivoUtil;

import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import co.edu.uniquindio.dulcelandia.model.Venta;
import org.eclipse.swt.widgets.Text;

public class VentaView extends Composite {
	private DataBindingContext m_bindingContext;
	private Table table;
	CrudVentaViewController crudVentaViewController = new CrudVentaViewController();
	Tienda tienda= crudVentaViewController.getTienda();
	private TableViewer tableViewer;
	private Text textEstadoVenta;
	private Text textNumVenta;
	private Text textEmpleadoVenta;
	private Text textClienteVenta;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public VentaView(Composite parent, int style) {
		super(parent, style);
		
		Group group = new Group(this, SWT.BORDER);
		group.setBounds(10, 10, 650, 83);
		
		Label lblInformacinDeLa = new Label(group, SWT.NONE);
		lblInformacinDeLa.setBounds(10, 10, 181, 15);
		lblInformacinDeLa.setText("Informaci\u00F3n de la Venta");
		
		Label lblEstadoVenta = new Label(group, SWT.NONE);
		lblEstadoVenta.setBounds(10, 31, 98, 15);
		lblEstadoVenta.setText("Estado de Venta");
		
		Label lblClienteVenta = new Label(group, SWT.NONE);
		lblClienteVenta.setBounds(220, 31, 98, 15);
		lblClienteVenta.setText("Codigo Cliente:");
		
		Label lblEmpleadoVenta = new Label(group, SWT.NONE);
		lblEmpleadoVenta.setBounds(220, 52, 110, 15);
		lblEmpleadoVenta.setText("Codigo Empleado:");
		
		Label lblNumVenta = new Label(group, SWT.NONE);
		lblNumVenta.setBounds(10, 52, 95, 15);
		lblNumVenta.setText("Numero de venta:");
		
		Button btnNewButton = new Button(group, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				limpiarCamposTexto();
				initDataBindings();
			}
		});
		btnNewButton.setBounds(443, 26, 88, 25);
		btnNewButton.setText("Nueva Venta");
		
		Button btnNewButton_1 = new Button(group, SWT.NONE);
		btnNewButton_1.setBounds(531, 26, 99, 25);
		btnNewButton_1.setText("Eliminar Venta");
		
		textEstadoVenta = new Text(group, SWT.BORDER);
		textEstadoVenta.setBounds(114, 31, 76, 15);
		
		textNumVenta = new Text(group, SWT.BORDER);
		textNumVenta.setBounds(115, 49, 76, 15);
		
		textEmpleadoVenta = new Text(group, SWT.BORDER);
		textEmpleadoVenta.setBounds(330, 52, 76, 15);
		
		textClienteVenta = new Text(group, SWT.BORDER);
		textClienteVenta.setBounds(324, 31, 76, 15);
		CrudClienteViewController clienteController = new CrudClienteViewController();
		
		Group grpListadoDeVentas = new Group(this, SWT.BORDER);
		grpListadoDeVentas.setText("Listado de Ventas:");
		grpListadoDeVentas.setBounds(10, 99, 650, 185);
		
		tableViewer = new TableViewer(grpListadoDeVentas, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(10, 18, 513, 108);
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnEstadoVenta = tableViewerColumn.getColumn();
		tblclmnEstadoVenta.setWidth(125);
		tblclmnEstadoVenta.setText("Estado Venta");
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnNumVenta = tableViewerColumn_1.getColumn();
		tblclmnNumVenta.setText("Codigo Venta");
		tblclmnNumVenta.setWidth(125);
		
		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnClienteVenta = tableViewerColumn_2.getColumn();
		tblclmnClienteVenta.setWidth(125);
		tblclmnClienteVenta.setText("Codigo Cliente");
		
		TableViewerColumn tableViewerColumn_3 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnEmpleadoVenta = tableViewerColumn_3.getColumn();
		tblclmnEmpleadoVenta.setWidth(162);
		tblclmnEmpleadoVenta.setText("Codigo Empleado");
		
		Button btnAgregar = new Button(grpListadoDeVentas, SWT.NONE);
		btnAgregar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnAgregar.setText("Agregar Venta");
		btnAgregar.setBounds(135, 138, 104, 25);
		
		Button btnNewButton_2 = new Button(grpListadoDeVentas, SWT.NONE);
		btnNewButton_2.setBounds(265, 138, 116, 25);
		btnNewButton_2.setText("Actualizar Venta");
		
		Button btnExportar = new Button(grpListadoDeVentas, SWT.NONE);
		btnExportar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					String texto = ArchivoUtil.generarTexto("archivoVentas.txt");
					ArchivoUtil.exportarArchivo(texto);	
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}							
			}
		});
		btnExportar.setBounds(401, 138, 75, 25);
		btnExportar.setText("Exportar");
		m_bindingContext = initDataBindings();

	}
	
	
	protected void limpiarCamposTexto(){
		textEstadoVenta.setText("");
		textNumVenta.setText("");
		textClienteVenta.setText("");
		textEmpleadoVenta.setText("");
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap[] observeMaps = PojoObservables.observeMaps(listContentProvider.getKnownElements(), Venta.class, new String[]{"estadoVenta", "numVenta", "clienteVenta", "empleadoVenta"});
		tableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		tableViewer.setContentProvider(listContentProvider);
		//
		IObservableList listaProductosTiendaObserveList = PojoProperties.list("listaProductos").observe(tienda);
		tableViewer.setInput(listaProductosTiendaObserveList);
		//
		return bindingContext;
	}
}
