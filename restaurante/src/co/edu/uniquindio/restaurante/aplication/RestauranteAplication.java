package co.edu.uniquindio.restaurante.aplication;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.widgets.Display;

import co.edu.uniquindio.restaurante.controllers.ModelFactoryController;
import co.edu.uniquindio.restaurante.util.util;
import co.edu.uniquindio.restaurante.views.InicioSesionView;
import co.edu.uniquindio.restaurante.views.RestauranteEmpleadoView;
import co.edu.uniquindio.restaurante.views.RestauranteView;

public class RestauranteAplication {
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {
			public void run() {
				try {		
					InicioSesionView inicioSesion = new InicioSesionView();
					inicioSesion.open();
					inicioSesion.setBlockOnOpen(true);
					inicioSesion.open();
					
					Display.getCurrent().dispose();				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
