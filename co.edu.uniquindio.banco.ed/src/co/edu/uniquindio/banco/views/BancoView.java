package co.edu.uniquindio.banco.views;

import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class BancoView extends ApplicationWindow {

	/**
	 * Create the application window.
	 */
	public BancoView() {
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
		tabFolder.setBounds(10, 10, 1171, 844);
		
		TabItem tbtmC = new TabItem(tabFolder, SWT.NONE);
		tbtmC.setText("Cliente");
		
		ClienteView compositeClienteView = new ClienteView(tabFolder, SWT.NONE);
		tbtmC.setControl(compositeClienteView);
		
		TabItem tbtmEmpleado = new TabItem(tabFolder, SWT.NONE);
		tbtmEmpleado.setText("Empleado");
		
		EmpleadoView compositeEmpleadoView = new EmpleadoView(tabFolder, SWT.NONE);
		tbtmEmpleado.setControl(compositeEmpleadoView);
		
		TabItem tbtmCuenta = new TabItem(tabFolder, SWT.NONE);
		tbtmCuenta.setText("Cuenta");
		
		CuentaView compositeCuentaView = new CuentaView(tabFolder, SWT.NONE);
		tbtmCuenta.setControl(compositeCuentaView);
		
		TabItem tbtmTransaccinBancaria = new TabItem(tabFolder, SWT.NONE);
		tbtmTransaccinBancaria.setText("Transacci\u00F3n Bancaria");

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
			BancoView window = new BancoView();
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
		newShell.setText("New Application");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(954, 728);
	}
}
