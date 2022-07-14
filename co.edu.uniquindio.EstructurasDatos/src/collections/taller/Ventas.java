package collections.taller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;

import collections.SWTResourceManager;
import collections.clases.Cliente;
import collections.clases.Producto;
import collections.clases.Vendedor;

import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.custom.CCombo;

public class Ventas {

	protected Shell shell;
	private Text txtNombre;
	private Text txtApellido;
	private Text txtCedula;
	private Text txtDescripcion;
	private Table tableClientes;
	
	private Table tableVendedores;
	private Text txtNombreVendedor;
	private Text txtApellidoVendedor;
	private Text txtCedulaVendedor;
	private Text txtDescripcionVendedor;
	
	
	
	private Table tableProductos;
	private Text txtNombreProducto;
	private Text txtCodigoProducto;
	private Group grpInformacinDelVendedor;
	private Label lblNombreYApellidos2;
	private Label lblApelldos2;
	private Label lblCdula2;
	private Label lblDescripcin2;
	private Label NombreProducto;
	private Label lblCodigoProducto;
	private Label lblDescripcinProducto;

	StyledText txtDescripcionProducto;

	TableItem clienteSeleccionado;
	TableItem vendedorSeleccionado;
	TableItem productoSeleccionado;
	
	
	TableItem clienteSeleccionadoVenta;
	TableItem vendedorSeleccionadoVenta;
	TableItem productoSeleccionadoVenta;
	TableItem productoVendidoSeleccionadoVenta;

	

	GestionVentasControl gestionVentasControl;
	private Table tableProductosVenta;
	private Table tableClientesVenta;
	private Table tableVendedorVentas;
	private Table tableProductosVendidos;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Ventas window = new Ventas();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		
		gestionVentasControl = new GestionVentasControl();
		cargarDatos();
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(838, 510);
		shell.setText("Gestión de Ventas");
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite.setBounds(0, 0, 812, 461);
		
		TabFolder tabFolder = new TabFolder(composite, SWT.NONE);
		tabFolder.setBounds(10, 10, 792, 441);
		
		TabItem tbtmCrudCliente = new TabItem(tabFolder, SWT.NONE);
		tbtmCrudCliente.setText("CRUD Cliente");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmCrudCliente.setControl(composite_1);
		
		Group grpListaDeClientes = new Group(composite_1, SWT.NONE);
		grpListaDeClientes.setText("Lista de Clientes");
		grpListaDeClientes.setBounds(10, 237, 576, 123);
		
		tableClientes = new Table(grpListaDeClientes, SWT.BORDER | SWT.FULL_SELECTION);
		tableClientes.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				Boolean flagClienteEliminado = false;
				
				 TableItem[] selection = tableClientes.getSelection();
				 clienteSeleccionado = selection[0];
				 
				 txtNombre.setText(clienteSeleccionado.getText(0));
				 txtApellido.setText(clienteSeleccionado.getText(1));
				 txtCedula.setText(clienteSeleccionado.getText(2));
				 txtDescripcion.setText(clienteSeleccionado.getText(3));
				 
			}
		});
		tableClientes.setBounds(10, 22, 556, 91);
		tableClientes.setHeaderVisible(true);
		tableClientes.setLinesVisible(true);
		
		TableColumn tblclmnNombre = new TableColumn(tableClientes, SWT.NONE);
		tblclmnNombre.setWidth(137);
		tblclmnNombre.setText("Nombre(s)");
		
		TableColumn tblclmnApellido = new TableColumn(tableClientes, SWT.NONE);
		tblclmnApellido.setWidth(147);
		tblclmnApellido.setText("Apellido(s)");
		
		TableColumn tblclmnCdula = new TableColumn(tableClientes, SWT.NONE);
		tblclmnCdula.setWidth(123);
		tblclmnCdula.setText("C\u00E9dula");
		
		TableColumn tblclmnDescripcin = new TableColumn(tableClientes, SWT.NONE);
		tblclmnDescripcin.setWidth(142);
		tblclmnDescripcin.setText("Descripci\u00F3n");
		
		Button btnAgregarCliente = new Button(composite_1, SWT.NONE);
		btnAgregarCliente.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				
				if(txtNombre.getText().equalsIgnoreCase("") || txtApellido.getText().equalsIgnoreCase("") ||txtCedula.getText().equalsIgnoreCase(""))
				{

					JOptionPane.showMessageDialog(null, "Faltan campos por diligenciar");
				}
				else
				{
					gestionVentasControl.agregarCliente(txtNombre.getText(), txtApellido.getText(),txtCedula.getText(),txtDescripcion.getText());

					TableItem item1 = new TableItem(tableClientes, SWT.NONE);
					item1.setText(new String[] {txtNombre.getText(), txtApellido.getText(),txtCedula.getText(),txtDescripcion.getText() });

					txtNombre.setText("");
					txtApellido.setText("");
					txtCedula.setText("");
					txtDescripcion.setText("");
				}
			}
		});
		btnAgregarCliente.setBounds(471, 155, 115, 25);
		btnAgregarCliente.setText("Agregar Cliente");
		
		Button btnActualizarCliente = new Button(composite_1, SWT.NONE);
		btnActualizarCliente.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				
				if(clienteSeleccionado != null)
				{
					String cedulaCliente = (String) clienteSeleccionado.getText(2);
					
					Cliente cliente = gestionVentasControl.obtenerCliente(cedulaCliente);
					
					cliente.setNombres(txtNombre.getText());
					cliente.setApellidos(txtApellido.getText());
					cliente.setCedula(txtCedula.getText());
					cliente.setDescripcion(txtDescripcion.getText());
					
					
					clienteSeleccionado.setText(new String[] {cliente.getNombres(),cliente.getApellidos(), cliente.getCedula(), cliente.getDescripcion()});
					txtNombre.setText("");
					txtApellido.setText("");
					txtCedula.setText("");
					txtDescripcion.setText("");
				}

			}
		});
		btnActualizarCliente.setBounds(471, 192, 115, 25);
		btnActualizarCliente.setText("Actualizar Cliente");
		
		Button btnEliminarCliente = new Button(composite_1, SWT.NONE);
		btnEliminarCliente.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(clienteSeleccionado != null)
				{
					String cedulaCliente = (String) clienteSeleccionado.getText(2);
					 if(gestionVentasControl.eliminarCliente(cedulaCliente))
					 {
						 //eliminar el objeto de la tabla
						 tableClientes.remove(tableClientes.getSelectionIndices());
						 txtNombre.setText("");
						 txtApellido.setText("");
						 txtCedula.setText("");
						 txtDescripcion.setText("");
					 }
					 else
					 {
						 
					 }
				}
			}
		});
		btnEliminarCliente.setBounds(455, 366, 131, 25);
		btnEliminarCliente.setText("Eliminar Cliente");
		
		Group grpInformacinDelCliente = new Group(composite_1, SWT.NONE);
		grpInformacinDelCliente.setText("Informaci\u00F3n del cliente");
		grpInformacinDelCliente.setBounds(10, 21, 437, 196);
		
		Label lblNombreYApellidos = new Label(grpInformacinDelCliente, SWT.NONE);
		lblNombreYApellidos.setBounds(10, 28, 69, 15);
		lblNombreYApellidos.setText("Nombre(s):");
		
		txtNombre = new Text(grpInformacinDelCliente, SWT.BORDER);
		txtNombre.setBounds(91, 25, 336, 21);
		
		Label lblApelldos = new Label(grpInformacinDelCliente, SWT.NONE);
		lblApelldos.setBounds(10, 67, 69, 15);
		lblApelldos.setText("Apellido(s):");
		
		txtApellido = new Text(grpInformacinDelCliente, SWT.BORDER);
		txtApellido.setBounds(91, 64, 336, 21);
		
		Label lblCdula = new Label(grpInformacinDelCliente, SWT.NONE);
		lblCdula.setBounds(30, 109, 55, 15);
		lblCdula.setText("C\u00E9dula:");
		
		txtCedula = new Text(grpInformacinDelCliente, SWT.BORDER);
		txtCedula.setBounds(91, 106, 336, 21);
		
		Label lblDescripcin = new Label(grpInformacinDelCliente, SWT.NONE);
		lblDescripcin.setBounds(10, 145, 69, 15);
		lblDescripcin.setText("Descripci\u00F3n:");
		
		txtDescripcion = new Text(grpInformacinDelCliente, SWT.BORDER);
		txtDescripcion.setBounds(91, 142, 336, 21);
		
		TabItem tbtmCrudVendedor = new TabItem(tabFolder, SWT.NONE);
		tbtmCrudVendedor.setText("CRUD Vendedor");
		
		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		tbtmCrudVendedor.setControl(composite_2);
		
		
		
		Group grpListaDeVendedores = new Group(composite_2, SWT.NONE);
		grpListaDeVendedores.setText("Lista de Vendedores");
		grpListaDeVendedores.setBounds(10, 237, 576, 123);
		
		tableVendedores = new Table(grpListaDeVendedores, SWT.BORDER | SWT.FULL_SELECTION);
		tableVendedores.setBounds(10, 22, 556, 91);
		tableVendedores.setHeaderVisible(true);
		tableVendedores.setLinesVisible(true);
		
		tableVendedores.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				Boolean flagClienteEliminado = false;
				
				 TableItem[] selection = tableVendedores.getSelection();
				 vendedorSeleccionado = selection[0];
				 
				 txtNombreVendedor.setText(vendedorSeleccionado.getText(0));
				 txtApellidoVendedor.setText(vendedorSeleccionado.getText(1));
				 txtCedulaVendedor.setText(vendedorSeleccionado.getText(2));
				 txtDescripcionVendedor.setText(vendedorSeleccionado.getText(3));
				 
			}
		});
		
		
		TableColumn tblclmnNombre2 = new TableColumn(tableVendedores, SWT.NONE);
		tblclmnNombre2.setWidth(137);
		tblclmnNombre2.setText("Nombre(s)");
		
		TableColumn tblclmnApellido2 = new TableColumn(tableVendedores, SWT.NONE);
		tblclmnApellido2.setWidth(147);
		tblclmnApellido2.setText("Apellido(s)");
		
		TableColumn tblclmnCdul2 = new TableColumn(tableVendedores, SWT.NONE);
		tblclmnCdul2.setWidth(123);
		tblclmnCdul2.setText("C\u00E9dula");
		
		TableColumn tblclmnDescripcin2 = new TableColumn(tableVendedores, SWT.NONE);
		tblclmnDescripcin2.setWidth(142);
		tblclmnDescripcin2.setText("Descripci\u00F3n");
		
		Button btnAgregarVendedor= new Button(composite_2, SWT.NONE);
		btnAgregarVendedor.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				
				if(txtNombreVendedor.getText().equalsIgnoreCase("") || txtApellidoVendedor.getText().equalsIgnoreCase("") ||txtCedulaVendedor.getText().equalsIgnoreCase(""))
				{

					JOptionPane.showMessageDialog(null, "Faltan campos por diligenciar");
				}
				else
				{
					gestionVentasControl.agregarVendedor(txtNombreVendedor.getText(), txtApellidoVendedor.getText(),txtCedulaVendedor.getText(),txtDescripcionVendedor.getText());

					TableItem item1 = new TableItem(tableVendedores, SWT.NONE);
					item1.setText(new String[] {txtNombreVendedor.getText(), txtApellidoVendedor.getText(),txtCedulaVendedor.getText(),txtDescripcionVendedor.getText() });

					txtNombreVendedor.setText("");
					txtApellidoVendedor.setText("");
					txtCedulaVendedor.setText("");
					txtDescripcionVendedor.setText("");
				}
				
				
			}
		});
		btnAgregarVendedor.setBounds(471, 155, 115, 25);
		btnAgregarVendedor.setText("Agregar Vendedor");
		
		Button btnActualizarVendedor = new Button(composite_2, SWT.NONE);
		btnActualizarVendedor.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				
				if(vendedorSeleccionado != null)
				{
					String cedulaVendedor = (String) vendedorSeleccionado.getText(2);
					
					Vendedor vendedor = gestionVentasControl.obtenerVendedor(cedulaVendedor);
					
					vendedor.setNombres(txtNombreVendedor.getText());
					vendedor.setApellidos(txtApellidoVendedor.getText());
					vendedor.setCedula(txtCedulaVendedor.getText());
					vendedor.setDescripcion(txtDescripcionVendedor.getText());
					
					vendedorSeleccionado.setText(new String[] {vendedor.getNombres(),vendedor.getApellidos(), vendedor.getCedula(), vendedor.getDescripcion()});
					txtNombreVendedor.setText("");
					txtApellidoVendedor.setText("");
					txtCedulaVendedor.setText("");
					txtDescripcionVendedor.setText("");
				}
			}
		});
		btnActualizarVendedor.setBounds(471, 192, 115, 25);
		btnActualizarVendedor.setText("Actualizar Vendedor");
		
		Button btnEliminarVendedor = new Button(composite_2, SWT.NONE);
		btnEliminarVendedor.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(vendedorSeleccionado != null)
				{
					String cedulaVendedor = (String) vendedorSeleccionado.getText(2);
					if(gestionVentasControl.eliminarVendedor(cedulaVendedor))
					{
						//eliminar el objeto de la tabla
						tableVendedores.remove(tableVendedores.getSelectionIndices());
						txtNombreVendedor.setText("");
						txtApellidoVendedor.setText("");
						txtCedulaVendedor.setText("");
						txtDescripcionVendedor.setText("");
					}
					else
					{

					}
				}
			}
		});
		btnEliminarVendedor.setBounds(455, 366, 131, 25);
		btnEliminarVendedor.setText("Eliminar Vendedor");
		
		grpInformacinDelVendedor = new Group(composite_2, SWT.NONE);
		grpInformacinDelVendedor.setText("Informaci\u00F3n del vendedor");
		grpInformacinDelVendedor.setBounds(10, 21, 437, 196);
		
		lblNombreYApellidos2 = new Label(grpInformacinDelVendedor, SWT.NONE);
		lblNombreYApellidos2.setBounds(10, 28, 69, 15);
		lblNombreYApellidos2.setText("Nombre(s):");
		
		txtNombreVendedor = new Text(grpInformacinDelVendedor, SWT.BORDER);
		txtNombreVendedor.setBounds(91, 25, 336, 21);
		
		lblApelldos2 = new Label(grpInformacinDelVendedor, SWT.NONE);
		lblApelldos2.setBounds(10, 67, 69, 15);
		lblApelldos2.setText("Apellido(s):");
		
		txtApellidoVendedor = new Text(grpInformacinDelVendedor, SWT.BORDER);
		txtApellidoVendedor.setBounds(91, 64, 336, 21);
		
		lblCdula2 = new Label(grpInformacinDelVendedor, SWT.NONE);
		lblCdula2.setBounds(30, 109, 55, 15);
		lblCdula2.setText("C\u00E9dula:");
		
		txtCedulaVendedor = new Text(grpInformacinDelVendedor, SWT.BORDER);
		txtCedulaVendedor.setBounds(91, 106, 336, 21);
		
		lblDescripcin2 = new Label(grpInformacinDelVendedor, SWT.NONE);
		lblDescripcin2.setBounds(10, 145, 69, 15);
		lblDescripcin2.setText("Descripci\u00F3n:");
		
		txtDescripcionVendedor = new Text(grpInformacinDelVendedor, SWT.BORDER);
		txtDescripcionVendedor.setBounds(91, 142, 336, 21);

		
		
		
		
		
		TabItem tbtmCrudProducto = new TabItem(tabFolder, SWT.NONE);
		tbtmCrudProducto.setText("CRUD Producto");
		
		Composite composite_3 = new Composite(tabFolder, SWT.NONE);
		tbtmCrudProducto.setControl(composite_3);
		
		Group grpListaDeProductos = new Group(composite_3, SWT.NONE);
		grpListaDeProductos.setText("Lista de Productos");
		grpListaDeProductos.setBounds(10, 237, 576, 123);
		
		tableProductos = new Table(grpListaDeProductos, SWT.BORDER | SWT.FULL_SELECTION);
		tableProductos.setBounds(10, 22, 556, 91);
		tableProductos.setHeaderVisible(true);
		tableProductos.setLinesVisible(true);
		
		
		tableProductos.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				Boolean flagClienteEliminado = false;
				
				 TableItem[] selection = tableProductos.getSelection();
				 productoSeleccionado = selection[0];
				 
				 txtNombreProducto.setText(productoSeleccionado.getText(0));
				 txtCodigoProducto.setText(productoSeleccionado.getText(1));
				 txtDescripcionProducto.setText(productoSeleccionado.getText(2));
				 
			}
		});
		
		
		TableColumn tblclmnNombre3 = new TableColumn(tableProductos, SWT.NONE);
		tblclmnNombre3.setWidth(137);
		tblclmnNombre3.setText("Nombre");
		
		TableColumn tblclmnApellido3 = new TableColumn(tableProductos, SWT.NONE);
		tblclmnApellido3.setWidth(101);
		tblclmnApellido3.setText("C\u00F3digo");
		
		TableColumn tblclmnDescripcin3 = new TableColumn(tableProductos, SWT.NONE);
		tblclmnDescripcin3.setWidth(172);
		tblclmnDescripcin3.setText("Descripci\u00F3n");
		
		TableColumn tblclmnNewColumn = new TableColumn(tableProductos, SWT.NONE);
		tblclmnNewColumn.setWidth(141);
		tblclmnNewColumn.setText("Estado");
		
		Button btnAgregarProducto= new Button(composite_3, SWT.NONE);
		btnAgregarProducto.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				
				if(txtNombreProducto.getText().equalsIgnoreCase("") || txtCodigoProducto.getText().equalsIgnoreCase("") ||txtDescripcionProducto.getText().equalsIgnoreCase(""))
				{

					JOptionPane.showMessageDialog(null, "Faltan campos por diligenciar");
				}
				else
				{
					gestionVentasControl.agregarProducto(txtNombreProducto.getText(), txtCodigoProducto.getText(),txtDescripcionProducto.getText());

					TableItem item1 = new TableItem(tableProductos, SWT.NONE);
					item1.setText(new String[] {txtNombreProducto.getText(), txtCodigoProducto.getText(),txtDescripcionProducto.getText()});

					txtNombreProducto.setText("");
					txtCodigoProducto.setText("");
					txtDescripcionProducto.setText("");
					updateTableProdutos();
					
				}
				
				
				
			}
		});
		btnAgregarProducto.setBounds(471, 155, 115, 25);
		btnAgregarProducto.setText("Agregar Producto");
		
		Button btnActualizarProducto = new Button(composite_3, SWT.NONE);
		btnActualizarProducto.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				
				if(productoSeleccionado != null)
				{
					String codigoProducto = (String) productoSeleccionado.getText(1);
					
					Producto producto = gestionVentasControl.obtenerProducto(codigoProducto);
					
					producto.setNombre(txtNombreProducto.getText());
					producto.setCodigo(txtCodigoProducto.getText());
					producto.setDescripcion(txtDescripcionProducto.getText());
					
					
					productoSeleccionado.setText(new String[] {producto.getNombre(),producto.getCodigo(), producto.getDescripcion()});
					txtNombreProducto.setText("");
					txtCodigoProducto.setText("");
					txtDescripcionProducto.setText("");
				}
			}
		});
		btnActualizarProducto.setBounds(471, 192, 115, 25);
		btnActualizarProducto.setText("Actualizar Producto");
		
		Button btnEliminarProducto = new Button(composite_3, SWT.NONE);
		btnEliminarProducto.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				
				if(productoSeleccionado != null)
				{
					String codigoProducto = (String) productoSeleccionado.getText(1);
					 if(gestionVentasControl.eliminarProducto(codigoProducto))
					 {
						 //eliminar el objeto de la tabla
						 tableProductos.remove(tableProductos.getSelectionIndices());
						 txtNombreProducto.setText("");
						 txtCodigoProducto.setText("");
						 txtDescripcionProducto.setText("");
					 }
					 else
					 {
						 
					 }
				}
			}
		});
		btnEliminarProducto.setBounds(455, 366, 131, 25);
		btnEliminarProducto.setText("Eliminar Producto");
		
		Group grpInformacinDeProducto = new Group(composite_3, SWT.NONE);
		grpInformacinDeProducto.setText("Informaci\u00F3n del producto");
		grpInformacinDeProducto.setBounds(10, 21, 437, 196);
		
		NombreProducto = new Label(grpInformacinDeProducto, SWT.NONE);
		NombreProducto.setBounds(10, 28, 69, 15);
		NombreProducto.setText("Nombre");
		
		txtNombreProducto = new Text(grpInformacinDeProducto, SWT.BORDER);
		txtNombreProducto.setBounds(91, 25, 336, 21);
		
		lblCodigoProducto = new Label(grpInformacinDeProducto, SWT.NONE);
		lblCodigoProducto.setBounds(10, 67, 69, 15);
		lblCodigoProducto.setText("C\u00F3digo:");
		
		txtCodigoProducto = new Text(grpInformacinDeProducto, SWT.BORDER);
		txtCodigoProducto.setBounds(91, 64, 336, 21);
		
		lblDescripcinProducto = new Label(grpInformacinDeProducto, SWT.NONE);
		lblDescripcinProducto.setBounds(10, 105, 69, 15);
		lblDescripcinProducto.setText("Descripci\u00F3n:");
		
		txtDescripcionProducto = new StyledText(grpInformacinDeProducto, SWT.BORDER);
		txtDescripcionProducto.setBounds(91, 105, 336, 57);

		
		
		TabItem tbtmVenderProducto = new TabItem(tabFolder, SWT.NONE);
		tbtmVenderProducto.setText("Vender Producto");
		
		Composite composite_4 = new Composite(tabFolder, SWT.NONE);
		tbtmVenderProducto.setControl(composite_4);
		
		Group grpInformacinDelCliente_1 = new Group(composite_4, SWT.NONE);
		grpInformacinDelCliente_1.setText("Informaci\u00F3n del Cliente y el Vendedor");
		grpInformacinDelCliente_1.setBounds(11, 21, 763, 171);
		
		Label lblSeleccionesElCliente = new Label(grpInformacinDelCliente_1, SWT.NONE);
		lblSeleccionesElCliente.setBounds(10, 20, 116, 15);
		lblSeleccionesElCliente.setText("Seleccione el cliente:");
		
		Label lblSeleccioneElVendedor = new Label(grpInformacinDelCliente_1, SWT.NONE);
		lblSeleccioneElVendedor.setBounds(403, 20, 134, 15);
		lblSeleccioneElVendedor.setText("Seleccione el vendedor:");
		
		tableClientesVenta = new Table(grpInformacinDelCliente_1, SWT.BORDER | SWT.FULL_SELECTION);
		tableClientesVenta.setBounds(10, 41, 349, 120);
		tableClientesVenta.setHeaderVisible(true);
		tableClientesVenta.setLinesVisible(true);
		
		
		tableClientesVenta.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				Boolean flagClienteEliminado = false;
				
				 TableItem[] selection = tableClientesVenta.getSelection();
				 clienteSeleccionadoVenta = selection[0];
			}
		});
		
		
		TableColumn tblclmnNombre_3 = new TableColumn(tableClientesVenta, SWT.NONE);
		tblclmnNombre_3.setWidth(136);
		tblclmnNombre_3.setText("Nombre");
		
		TableColumn tblclmnApellidos = new TableColumn(tableClientesVenta, SWT.NONE);
		tblclmnApellidos.setWidth(100);
		tblclmnApellidos.setText("Apellidos");
		
		TableColumn tblclmnCdula_1 = new TableColumn(tableClientesVenta, SWT.NONE);
		tblclmnCdula_1.setWidth(107);
		tblclmnCdula_1.setText("C\u00E9dula");
		
		tableVendedorVentas = new Table(grpInformacinDelCliente_1, SWT.BORDER | SWT.FULL_SELECTION);
		tableVendedorVentas.setBounds(404, 41, 349, 120);
		tableVendedorVentas.setHeaderVisible(true);
		tableVendedorVentas.setLinesVisible(true);
		
		
		tableVendedorVentas.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				Boolean flagClienteEliminado = false;
				
				 TableItem[] selection = tableVendedorVentas.getSelection();
				 vendedorSeleccionadoVenta = selection[0];
			}
		});
		
		
		
		TableColumn tblclmnNombre_4 = new TableColumn(tableVendedorVentas, SWT.NONE);
		tblclmnNombre_4.setWidth(145);
		tblclmnNombre_4.setText("Nombre");
		
		TableColumn tblclmnApellidos_1 = new TableColumn(tableVendedorVentas, SWT.NONE);
		tblclmnApellidos_1.setWidth(100);
		tblclmnApellidos_1.setText("Apellidos");
		
		TableColumn tblclmnCdula_2 = new TableColumn(tableVendedorVentas, SWT.NONE);
		tblclmnCdula_2.setWidth(97);
		tblclmnCdula_2.setText("C\u00E9dula");
		
		Group grpListaDeProdutos = new Group(composite_4, SWT.NONE);
		grpListaDeProdutos.setText("Lista de Produtos");
		grpListaDeProdutos.setBounds(11, 222, 362, 181);
		
		tableProductosVenta = new Table(grpListaDeProdutos, SWT.BORDER | SWT.FULL_SELECTION);
		tableProductosVenta.setBounds(10, 29, 342, 142);
		tableProductosVenta.setHeaderVisible(true);
		tableProductosVenta.setLinesVisible(true);
		
		
		tableProductosVenta.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
			
				Boolean flagClienteEliminado = false;
				
				 TableItem[] selection = tableProductosVenta.getSelection();
				 productoSeleccionadoVenta = selection[0];
			}
		});
		
		
		
		TableColumn tblclmnNombre_1 = new TableColumn(tableProductosVenta, SWT.NONE);
		tblclmnNombre_1.setWidth(110);
		tblclmnNombre_1.setText("Nombre");
		
		TableColumn tblclmnCdigo = new TableColumn(tableProductosVenta, SWT.NONE);
		tblclmnCdigo.setWidth(127);
		tblclmnCdigo.setText("C\u00F3digo");
		
		TableColumn tblclmnEstado = new TableColumn(tableProductosVenta, SWT.NONE);
		tblclmnEstado.setWidth(100);
		tblclmnEstado.setText("Estado");
		
		Group grpListaDeProductos_1 = new Group(composite_4, SWT.NONE);
		grpListaDeProductos_1.setText("Lista de productos vendidos");
		grpListaDeProductos_1.setBounds(432, 222, 342, 181);
		
		tableProductosVendidos = new Table(grpListaDeProductos_1, SWT.BORDER | SWT.FULL_SELECTION);
		tableProductosVendidos.setBounds(10, 29, 322, 133);
		tableProductosVendidos.setHeaderVisible(true);
		tableProductosVendidos.setLinesVisible(true);
		
		
		tableProductosVendidos.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				Boolean flagClienteEliminado = false;
				
				 TableItem[] selection = tableProductosVendidos.getSelection();
				 productoVendidoSeleccionadoVenta = selection[0];
			}
		});
		
		
		
		TableColumn tblclmnNombre_2 = new TableColumn(tableProductosVendidos, SWT.NONE);
		tblclmnNombre_2.setWidth(175);
		tblclmnNombre_2.setText("Nombre");
		
		TableColumn tblclmnCdigo_1 = new TableColumn(tableProductosVendidos, SWT.NONE);
		tblclmnCdigo_1.setWidth(139);
		tblclmnCdigo_1.setText("C\u00F3digo");
		
		Button button = new Button(composite_4, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(clienteSeleccionadoVenta != null )
				{
					if(vendedorSeleccionadoVenta != null )
					{
						if(productoSeleccionadoVenta != null )
						{
							
								String codigoProducto = (String) productoSeleccionadoVenta.getText(1);
								Producto producto  = gestionVentasControl.obtenerProducto(codigoProducto);
								
								boolean valor = gestionVentasControl.getListaProductosAsociadosClientes().add(producto);
								
								if(valor == true) 
								{
									gestionVentasControl.getListaProductosAsociadosVendedor().add(producto);
									
									TableItem item1 = new TableItem(tableProductosVendidos, SWT.NONE);
									item1.setText(new String[] {producto.getNombre(), producto.getCodigo()});

									
									producto.setEstado("Vendido");
									
									
									productoSeleccionadoVenta.setText(new String[] {producto.getNombre(),producto.getCodigo(), producto.getEstado()});
									
										String codigoProductoVenta = (String) productoSeleccionadoVenta.getText(1);
//										 if(gestionVentasControl.eliminarProducto(codigoProducto))
//										 {
////											 tableProductosVenta.remove(tableProductosVenta.getSelectionIndices());
//											 
//										 }
//										 else
//										 {
//											 
//										 }
									
									System.out.println("Producto agregado");
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Este producto se encuentra vendido");
								}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Por favor debe seleccionar un Producto");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Por favor debe seleccionar un Vendedor");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Por favor debe seleccionar un Cliente");
				}
				
				
				
				
			}
		});
		button.setBounds(379, 295, 47, 25);
		button.setText(">>");
		
		Button button_1 = new Button(composite_4, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				if(clienteSeleccionadoVenta != null )
				{
					if(vendedorSeleccionadoVenta != null )
					{
						if(productoSeleccionadoVenta != null )
						{
							if(productoVendidoSeleccionadoVenta != null )
							{
								String codigoProducto = (String) productoVendidoSeleccionadoVenta.getText(1);
								Producto producto  = gestionVentasControl.obtenerProducto(codigoProducto);

								gestionVentasControl.getListaProductosAsociadosClientes().remove(producto);
								gestionVentasControl.getListaProductosAsociadosVendedor().remove(producto);

								tableProductosVendidos.remove(tableProductosVendidos.getSelectionIndices());
								producto.setEstado("Disponible");
								productoSeleccionadoVenta.setText(new String[] {producto.getNombre(),producto.getCodigo(), producto.getEstado()});


								//								updateTableProdutos();

								String codigoProductoVenta = (String) productoSeleccionadoVenta.getText(1);

								System.out.println("Producto removido");

							}
							else
							{
								JOptionPane.showMessageDialog(null, "Por favor debe seleccionar un Producto");
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Por favor debe seleccionar un cliente");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Por favor debe seleccionar un Vendedor");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Por favor debe seleccionar un Cliente");
				}


			}


		});
		button_1.setBounds(378, 335, 47, 25);
		button_1.setText("<<");
		
		


	}



	private void cargarDatos() {
		
		int index= 0;
		
		List<String> listaClientesCombo = new ArrayList<>();
		
//		revisar aca como hago para 
		if(gestionVentasControl.getListaClientes().size() > 0)
		{
			
			for (Cliente cliente : gestionVentasControl.getListaClientes()) {
				TableItem item1 = new TableItem(tableClientes, SWT.NONE);
				item1.setText(new String[] {cliente.getNombres(), cliente.getApellidos(),cliente.getCedula(),cliente.getDescripcion() });
				
				TableItem item2 = new TableItem(tableClientesVenta, SWT.NONE);
				item2.setText(new String[] {cliente.getNombres(), cliente.getApellidos(),cliente.getCedula() });
			}

		}
		
		if(gestionVentasControl.getListaVendores().size() > 0)
		{
			for (Vendedor vendedor : gestionVentasControl.getListaVendores()) {
				TableItem item1 = new TableItem(tableVendedores, SWT.NONE);
				item1.setText(new String[] {vendedor.getNombres(), vendedor.getApellidos(),vendedor.getCedula(),vendedor.getDescripcion() });
				
				
				TableItem item2 = new TableItem(tableVendedorVentas, SWT.NONE);
				item2.setText(new String[] {vendedor.getNombres(), vendedor.getApellidos(),vendedor.getCedula()});
			}

			
		}
		
		if(gestionVentasControl.getListaProdcutos().size() > 0)
		{
			for( Iterator it = gestionVentasControl.getListaProdcutos().keySet().iterator(); it.hasNext();) { 

				String codigoProducto = (String)it.next();
				Producto producto = gestionVentasControl.getListaProdcutos().get(codigoProducto);

				TableItem item1 = new TableItem(tableProductos, SWT.NONE);
				item1.setText(new String[] {producto.getNombre(), producto.getCodigo(),producto.getDescripcion(),producto.getEstado()});
				
				TableItem item2 = new TableItem(tableProductosVenta, SWT.NONE);
				item2.setText(new String[] {producto.getNombre(), producto.getCodigo(),producto.getEstado()});
			}
		}
		
		
	}
	
	
	private void updateTableProdutos() {
		
		
		
//		tableProductos.clearAll();
		tableProductos.removeAll();
//		tableProductosVenta.clearAll();
		
		
//		tableProductosVenta.clear(tableProductosVenta.getSelectionIndex()); //clean the value
//		tableProductosVenta.remove(tableProductosVenta.getSelectionIndex());//delete the line
		
		
//		 TableItem[] selection = tableProductosVenta.getSelection();
//		 productoSeleccionadoVenta = null;
		
//		tableProductos.remove(0);
//		tableProductosVenta.removeAll();
		
		
		
		if(gestionVentasControl.getListaProdcutos().size() > 0)
		{
			for( Iterator it = gestionVentasControl.getListaProdcutos().keySet().iterator(); it.hasNext();) { 

				String codigoProducto = (String)it.next();
				Producto producto = gestionVentasControl.getListaProdcutos().get(codigoProducto);

				TableItem item1 = new TableItem(tableProductos, SWT.NONE);
				item1.setText(new String[] {producto.getNombre(), producto.getCodigo(),producto.getDescripcion(),producto.getEstado()});
				
				TableItem item2 = new TableItem(tableProductosVenta, SWT.NONE);
				item2.setText(new String[] {producto.getNombre(), producto.getCodigo(),producto.getEstado()});
			}
		}
		
	}
}
