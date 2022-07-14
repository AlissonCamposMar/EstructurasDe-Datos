package co.edu.uniquindio.banco.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import co.edu.uniquindio.banco.controllers.CrudClienteViewController;
import co.edu.uniquindio.banco.controllers.CrudCuentaViewController;
import co.edu.uniquindio.banco.controllers.CrudEmpleadoViewController;
import co.edu.uniquindio.banco.model.Banco;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class CuentaView extends Composite {
	
	CrudCuentaViewController crudCuentaViewController = new CrudCuentaViewController();
	CrudClienteViewController crudClienteViewController = new CrudClienteViewController();
	
	
	private Text text_NumCuenta;
	private Text text_Saldo;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CuentaView(Composite parent, int style) {
		super(parent, style);
		setLayout(null);
		
		Label lblNSaldo = new Label(this, SWT.NONE);
		lblNSaldo.setBounds(20, 158, 107, 35);
		lblNSaldo.setText("Saldo");
		
		Label lblNmeroCuenta = new Label(this, SWT.NONE);
		lblNmeroCuenta.setText("N\u00FAmero Cuenta:");
		lblNmeroCuenta.setBounds(20, 114, 107, 15);
		
		text_NumCuenta = new Text(this, SWT.BORDER);
		text_NumCuenta.setBounds(160, 111, 209, 21);
		
		text_Saldo = new Text(this, SWT.BORDER);
		text_Saldo.setBounds(159, 155, 215, 21);
		
		Label lblCrearCuenta = new Label(this, SWT.NONE);
		lblCrearCuenta.setBounds(296, 27, 98, 15);
		lblCrearCuenta.setText("Crear Cuenta");
		
		Label lblCliente_1 = new Label(this, SWT.NONE);
		lblCliente_1.setText("Cliente");
		lblCliente_1.setBounds(20, 210, 55, 15);
		
		Combo combo_Cliente = new Combo(this, SWT.NONE);
		combo_Cliente.setBounds(159, 207, 210, 23);
		
//		while(crudClienteViewController.getModelFactoryController().getBanco().getIteratorCliente().hasNext()) {
//			combo_Cliente.add(crudClienteViewController.getModelFactoryController().getBanco().listaClientes.);
//		}
//		
		Button btnCrearCuenta = new Button(this, SWT.NONE);
		btnCrearCuenta.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnCrearCuenta.setBounds(159, 265, 107, 25);
		btnCrearCuenta.setText("Crear Cuenta");
		
		Label lblCuentasCreadas = new Label(this, SWT.NONE);
		lblCuentasCreadas.setBounds(20, 339, 107, 15);
		lblCuentasCreadas.setText("Cuentas Creadas");
		
		Combo combo_CuentasCreadas = new Combo(this, SWT.NONE);
		combo_CuentasCreadas.setBounds(160, 331, 209, 23);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
