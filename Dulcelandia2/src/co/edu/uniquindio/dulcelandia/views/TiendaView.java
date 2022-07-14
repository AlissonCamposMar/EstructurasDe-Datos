package co.edu.uniquindio.dulcelandia.views;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class TiendaView extends ApplicationWindow {
	
	/**
	 * Create the application window.
	 */
	public TiendaView() {
		super(null);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		
		TabFolder tabFolder = new TabFolder(container, SWT.NONE);
		tabFolder.setBounds(10, 10, 681, 457);
		
		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("Producto");
		
		ProductoView compositeProductoView = new ProductoView(tabFolder, SWT.NONE);
		tbtmNewItem.setControl(compositeProductoView);

		
		TabItem tbtmNewItem_1 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_1.setText("Cliente");
		
		ClienteView compositeClienteView = new ClienteView(tabFolder, SWT.NONE);
		tbtmNewItem_1.setControl(compositeClienteView);
		
		TabItem tbtmEmpleado = new TabItem(tabFolder, SWT.NONE);
		tbtmEmpleado.setText("Empleado");
		
		EmpleadoView compositeEmpleadoView = new EmpleadoView(tabFolder, SWT.NONE);
		tbtmEmpleado.setControl(compositeEmpleadoView);
		
		TabItem tbtmVenta = new TabItem(tabFolder, SWT.NONE);
		tbtmVenta.setText("Venta");
		
		TabItem tbtmProveedor = new TabItem(tabFolder, SWT.NONE);
		tbtmProveedor.setText("Proveedor");
		
		ProveedorView compositeProveedorView = new ProveedorView(tabFolder, SWT.NONE);
		tbtmProveedor.setControl(compositeProveedorView);
		
		VentaView compositeVentaView = new VentaView(tabFolder, SWT.NONE);
		tbtmVenta.setControl(compositeVentaView);
		

		return container;
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	

	/**
	 * Create the status line manager.
	 * @return the status line manager
	 */
	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			TiendaView window = new TiendaView();
			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Configure the shell.
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Sistema de Tienda");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(717, 534);
	}
}
