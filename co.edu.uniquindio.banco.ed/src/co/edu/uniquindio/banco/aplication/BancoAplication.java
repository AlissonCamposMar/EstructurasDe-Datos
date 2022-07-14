package co.edu.uniquindio.banco.aplication;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.widgets.Display;

import co.edu.uniquindio.banco.views.BancoView;

public class BancoAplication {

	public static void main(String[] args) {
		
		Display display = Display.getDefault();
		Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {
			public void run() {
				Display display = Display.getDefault();
				Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {
					public void run() {
						try {
							BancoView bancoView = new BancoView();
							bancoView.open();
							bancoView.setBlockOnOpen(true);
							bancoView.open();
							
							Display.getCurrent().dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
	}

}
