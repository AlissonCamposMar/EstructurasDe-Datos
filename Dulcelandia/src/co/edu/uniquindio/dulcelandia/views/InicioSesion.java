package co.edu.uniquindio.dulcelandia.views;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import co.edu.uniquindio.dulcelandia.controllers.ModelFactoryController;
import co.edu.uniquindio.dulcelandia.exceptions.UsuarioException;
import co.edu.uniquindio.dulcelandia.persistencia.Persistencia;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/*
 * public class InicioSesion extends ApplicationWindow {
 * private Text textUsuario;
	private Text textContrasenia;
 * 
 * ModelFactoryController modelFactoryController = ModelFactoryController.getInstance();
 * public InicioSesion() {
*		super(null);
*		
*	}
*
*
*
*
*
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		
		Composite composite = new Composite(container, SWT.BORDER);
		composite.setBounds(29, 10, 397, 332);
		
		Label lblUsuario = new Label(composite, SWT.NONE);
		lblUsuario.setBounds(163, 10, 92, 23);
		lblUsuario.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblUsuario.setText("Usuario:");
		
		textUsuario = new Text(composite, SWT.BORDER);
		textUsuario.setBounds(42, 39, 312, 21);
		
		Label lblContrasea = new Label(composite, SWT.NONE);
		lblContrasea.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblContrasea.setBounds(143, 95, 92, 23);
		lblContrasea.setText("Contrase\u00F1a:");
		
		textContrasenia = new Text(composite, SWT.BORDER);
		textContrasenia.setBounds(43, 125, 311, 21);
		
		Label label = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(10, 209, 373, 2);
		
		Button btnIniciarSesin = new Button(composite, SWT.NONE);
		btnIniciarSesin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String usuario = textUsuario.getText();
				String contraseña = textContrasenia.getText();
				
				boolean inicio;
				try {
					inicio = Persistencia.iniciarSesion(usuario, contraseña);
					if(inicio){
						Display display = Display.getDefault();
						Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable(){
							public void run(){
								Display display = Display.getDefault();
								Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {
									public void run(){
										try {
											TiendaView tiendaView = new TiendaView();
											tiendaView.setBlockOnOpen(true); 
											tiendaView.open();
											
											Display.getCurrent().dispose(); 
										} catch (Exception e){
											e.printStackTrace();
										}
										}
									});
								}
							});
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Archivo usuarios no existe");
				} catch (UsuarioException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnIniciarSesin.setBounds(61, 247, 269, 25);
		btnIniciarSesin.setText("Iniciar Sesi\u00F3n");

		return container;
	}
*
*
*
*	
*	public static void main(String args[]) {
*		try {
*			InicioSesion window = new InicioSesion();
*			window.setBlockOnOpen(true);
*			window.open();
*			Display.getCurrent().dispose();
*		} catch (Exception e) {
*			e.printStackTrace();
*		}
*	}
*
*	@Override
*	protected void configureShell(Shell newShell) {
*		super.configureShell(newShell);
*		newShell.setText("Inicio de Sesi\u00F3n");
*	}
*	
*
*
*	@Override
	protected Point getInitialSize() {
		return new Point(471, 406);
	}
}

*
*
*
 */
	
	

	
	



	



	

