package co.edu.uniquindio.dulcelandia.aplication;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.swt.widgets.Display;
import org.eclipse.jface.databinding.swt.SWTObservables;

import co.edu.uniquindio.dulcelandia.views.TiendaView;

public class TiendaAplication {
	public static void main(String[] args){
		Display display = Display.getDefault();
		Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable(){
			public void run(){
				Display display = Display.getDefault();
				Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {
					public void run(){
						try {
							TiendaView tiendaView = new TiendaView();
							tiendaView.open();
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
}
