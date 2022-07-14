package co.edu.uniquindio.restaurante.views;

import java.io.IOException;

import javax.swing.JOptionPane;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
//import org.eclipse.wb.swt.SWTResourceManager;

import co.edu.uniquindio.restaurante.controllers.ModelFactoryController;
import co.edu.uniquindio.restaurante.util.util;

import org.eclipse.swt.widgets.Button;

public class InicioSesionView extends ApplicationWindow {
	private Text textUsuario;
	private Text textContraseña;

	ModelFactoryController modelFactoryController = new ModelFactoryController();
	
	
	/**
	 * Create the application window.
	 */
	public InicioSesionView() {
		super(null);
		
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		
		Composite composite = new Composite(container, SWT.BORDER);
		composite.setBounds(69, 48, 472, 358);
		
		Label lblUsuario = new Label(composite, SWT.NONE);
		lblUsuario.setBounds(81, 28, 79, 31);
		//lblUsuario.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblUsuario.setText("Usuario: ");
		
		textUsuario = new Text(composite, SWT.BORDER);
		textUsuario.setBounds(187, 33, 176, 21);
		
		Label lblContrasea = new Label(composite, SWT.NONE);
		lblContrasea.setBounds(55, 86, 105, 31);
		//lblContrasea.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblContrasea.setText("Contrase\u00F1a: ");
		
		textContraseña = new Text(composite, SWT.BORDER);
		textContraseña.setBounds(187, 91, 176, 21);
		
		Label lblAdasd = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		lblAdasd.setText("adasd");
		lblAdasd.setBounds(0, 160, 468, 21);
		
		Button btnIniciarSesin = new Button(composite, SWT.NONE);
		btnIniciarSesin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String usuario = textUsuario.getText();
				String contraseña = textContraseña.getText();
				
				Boolean inicio = false;
				
				try {
					inicio = modelFactoryController.iniciarSesion(usuario, contraseña);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
						
				
				if (inicio) {	
					util.logging("INICIO DE SESION EXITOSO", "SESION INICIADO", "USUARIO VALIDADO");
					
					ModelFactoryController modelFactoryController;
					modelFactoryController = ModelFactoryController.getInstance();
					modelFactoryController.start();
					util.crearLogger();
					
					modelFactoryController.setAutentificacion(usuario);
					
					if (usuario.equalsIgnoreCase("administrador")) {							
						RestauranteView restauranteView = new RestauranteView();
						restauranteView.open();
						restauranteView.setBlockOnOpen(true);
						restauranteView.open();
						
						Display.getCurrent().dispose();						
					} else {							
						RestauranteEmpleadoView restauranteView = new RestauranteEmpleadoView();
						restauranteView.open();
						restauranteView.setBlockOnOpen(true);
						restauranteView.open();
						
						Display.getCurrent().dispose();	
					}						
				} else {
					util.logging("INICIO DE SESION RECHAZADO", "SESION RECHAZADA", "USUARIO RECHAZADO");
					JOptionPane.showMessageDialog(null, "Por favor, digite una autentificación válida.");
				}				
			}
		});
		
		btnIniciarSesin.setBounds(186, 209, 105, 38);
		btnIniciarSesin.setText("Iniciar Sesi\u00F3n");
			

		return container;
	}

	/**
	 * Configure the shell.
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Inicio Sesion");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(635, 535);
	}
}