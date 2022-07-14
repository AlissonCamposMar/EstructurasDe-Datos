package co.edu.uniquindio.restaurante.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import co.edu.uniquindio.restaurante.controllers.CrudTransaccionViewController;
import co.edu.uniquindio.restaurante.model.Cuenta;
import co.edu.uniquindio.restaurante.model.Restaurante;
import co.edu.uniquindio.restaurante.model.Transaccion;
import co.edu.uniquindio.restaurante.util.util;

import java.io.IOException;

import javax.swing.JOptionPane;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.beans.PojoObservables;

import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.beans.PojoProperties;

public class TransaccionView extends Composite {
	private DataBindingContext m_bindingContext;
	
	CrudTransaccionViewController crudTransaccionViewController = new CrudTransaccionViewController();
	Restaurante restaurante = crudTransaccionViewController.getRestaurante();
	
	Transaccion transaccionSeleccionada;
	String busquedad = "";
	
	private Table table;
	private Text textNombre;
	private Text textApellido;
	private Text textCedula;
	private Text textEdad;
	private Text textBusqueda;
	private TableViewer tableViewer;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TransaccionView(Composite parent, int style) {
		super(parent, style);
		
		Group grpListaDeEmpleados = new Group(this, SWT.NONE);
		grpListaDeEmpleados.setText("Lista de Transacciones");
		grpListaDeEmpleados.setBounds(10, 0, 977, 326);
		
		tableViewer = new TableViewer(grpListaDeEmpleados, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				transaccionSeleccionada = (Transaccion) table.getItem(table.getSelectionIndex()).getData();
				textNombre.setText(transaccionSeleccionada.getTipo());
				textApellido.setText(transaccionSeleccionada.getId());
				textCedula.setText(transaccionSeleccionada.getClienteAsociado());
				textEdad.setText(transaccionSeleccionada.getEmpleadoAsociado());
				
			}
		});
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(0, 69, 773, 247);
		
		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnValor = tableViewerColumn.getColumn();
		tblclmnValor.setWidth(236);
		tblclmnValor.setText("Tipo");
		
		TableViewerColumn tableViewerColumn_1 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnTipo = tableViewerColumn_1.getColumn();
		tblclmnTipo.setWidth(228);
		tblclmnTipo.setText("Id");
		
		TableViewerColumn tableViewerColumn_2 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnCliente = tableViewerColumn_2.getColumn();
		tblclmnCliente.setWidth(190);
		tblclmnCliente.setText("Cliente");
		
		TableViewerColumn tableViewerColumn_4 = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblclmnEmpleado = tableViewerColumn_4.getColumn();
		tblclmnEmpleado.setWidth(115);
		tblclmnEmpleado.setText("Empleado");
		
		Button btnNewButton = new Button(grpListaDeEmpleados, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				util.logging("OPERACION REALIZADA CON EXITO", "CAMPOS ELIMINADOS", "INFORMACION ELIMINADA");
				limpiarCamposTexto();								
			}
		});
		btnNewButton.setBounds(317, 28, 110, 25);
		btnNewButton.setText("Nueva transacci\u00F3n");
		
		Button btnEliminarEmpleado = new Button(grpListaDeEmpleados, SWT.NONE);
		btnEliminarEmpleado.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Boolean flagEliminado = false;
				
				if (transaccionSeleccionada != null) {
					int confirmacion = JOptionPane.showConfirmDialog(null, 
							"¿Esta seguro de eliminar la transaccion seleccionada?", "Confirmación", 
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);					
					// 0: Si  1: No
					
					if (confirmacion == 0) flagEliminado = crudTransaccionViewController.eliminarTransaccion(transaccionSeleccionada);
					if (flagEliminado) {
						util.logging("OPERACION REALIZADA CON EXITO", "TRANSACCION ELIMINADA", "INFORMACION ELIMINADA");
						JOptionPane.showMessageDialog(null, "Transaccion eliminada con exito.");
						initDataBindings();
						limpiarCamposTexto();
					} else if (confirmacion == 0){
						JOptionPane.showMessageDialog(null, "Por favor, seleccione una transaccion valida a eliminar.");
					}
				}								
			}
		});
		btnEliminarEmpleado.setText("Eliminar transacci\u00F3n");
		btnEliminarEmpleado.setBounds(435, 28, 119, 25);
		
		Button btnExportarEmpleados = new Button(grpListaDeEmpleados, SWT.NONE);
		btnExportarEmpleados.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String texto;
				try {
					texto = util.linealizarArchivo("transacciones.txt");
					util.exportarArchivo(texto);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		btnExportarEmpleados.setText("Exportar");
		btnExportarEmpleados.setBounds(580, 28, 117, 25);
		
		Label lblBsqueda = new Label(grpListaDeEmpleados, SWT.NONE);
		lblBsqueda.setText("B\u00FAsqueda");
		lblBsqueda.setBounds(10, 33, 55, 15);
		
		textBusqueda = new Text(grpListaDeEmpleados, SWT.BORDER);
		textBusqueda.setBounds(70, 30, 175, 21);
		
		textBusqueda.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				Text source = (Text) e.getSource();
				busquedad = source.getText();
				tableViewer.refresh();
			}
		});
				
		textBusqueda.addSelectionListener(new SelectionAdapter(){
			public void widgetDefaultSelected(SelectionEvent e) {
				if (e.detail == SWT.CANCEL) {
					Text text = (Text) e.getSource();
					text.setText("a");
					text.setText("");
				}
			}			
		});
		
		tableViewer.addFilter(new ViewerFilter() {
			public boolean select (Viewer viewer, Object parentElement, Object element) {
				Transaccion transaccionBusquedad = (Transaccion) element;
				
				try {
					return 
							transaccionBusquedad.getTipo().contains(busquedad) ||
							transaccionBusquedad.getTipo().toLowerCase().contains(busquedad.toLowerCase()) ||
							transaccionBusquedad.getTipo().toUpperCase().contains(busquedad.toUpperCase()) ||
							
							transaccionBusquedad.getId().equalsIgnoreCase(busquedad) ||
							
							transaccionBusquedad.getClienteAsociado().equalsIgnoreCase(busquedad) ||
							
							transaccionBusquedad.getEmpleadoAsociado().equalsIgnoreCase(busquedad);
												
				} catch (Exception e) {
					return false;
				}
			}
		});;
		
		Label label_1 = new Label(grpListaDeEmpleados, SWT.SEPARATOR | SWT.VERTICAL);
		label_1.setBounds(265, -1, 2, 70);
		
		Group grpInformacinDeDetalle = new Group(this, SWT.NONE);
		grpInformacinDeDetalle.setText("Informaci\u00F3n de Detalle");
		grpInformacinDeDetalle.setBounds(10, 337, 977, 243);
		
		Label lblNewLabel = new Label(grpInformacinDeDetalle, SWT.NONE);
		lblNewLabel.setBounds(10, 34, 44, 15);
		lblNewLabel.setText("Estado");
		
		textNombre = new Text(grpInformacinDeDetalle, SWT.BORDER);
		textNombre.setBounds(62, 34, 175, 21);
		
		Label lblEstado = new Label(grpInformacinDeDetalle, SWT.NONE);
		lblEstado.setText("Id");
		lblEstado.setBounds(292, 34, 44, 15);
		
		textApellido = new Text(grpInformacinDeDetalle, SWT.BORDER);
		textApellido.setBounds(360, 31, 175, 21);
		
		Label lblCliente = new Label(grpInformacinDeDetalle, SWT.NONE);
		lblCliente.setText("Cliente");
		lblCliente.setBounds(10, 77, 44, 15);
		
		textCedula = new Text(grpInformacinDeDetalle, SWT.BORDER);
		textCedula.setBounds(62, 74, 175, 21);
		
		Label lblEdad = new Label(grpInformacinDeDetalle, SWT.NONE);
		lblEdad.setText("Empleado");
		lblEdad.setBounds(292, 77, 64, 15);
		
		textEdad = new Text(grpInformacinDeDetalle, SWT.BORDER);
		textEdad.setBounds(360, 74, 175, 21);
		
		Label label = new Label(grpInformacinDeDetalle, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(0, 119, 967, 2);
		
		Button btnAgregarTransacción = new Button(grpInformacinDeDetalle, SWT.NONE);
		btnAgregarTransacción.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String notificacion = "";
				
				if (verificarCamposVacios() == true) {
					util.logging("OPERACION REALIZADA CON EXITO", "TRANSACCION AGREGADA", "INFORMACION AÑADIDA");
					
					notificacion = crudTransaccionViewController.crearTransaccion(textNombre.getText(), textApellido.getText(), textCedula.getText(), textEdad.getText());					
					
					JOptionPane.showMessageDialog(null, notificacion);
					
					initDataBindings();
					limpiarCamposTexto();
					
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, ingrese todos los datos del campo de transaccion.");
				}
				
			}
		});
		btnAgregarTransacción.setText("Agregar transacci\u00F3n");
		btnAgregarTransacción.setBounds(89, 171, 120, 25);
		
		Button btnEliminarTransacción = new Button(grpInformacinDeDetalle, SWT.NONE);
		btnEliminarTransacción.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			
			Boolean flagActualizado = false;
			
			if (transaccionSeleccionada != null) {
				Transaccion nuevaTransaccion = new Transaccion();		
				nuevaTransaccion.setTipo(textNombre.getText());
				nuevaTransaccion.setId(textApellido.getText());
				nuevaTransaccion.setClienteAsociado(textCedula.getText());
				nuevaTransaccion.setEmpleadoAsociado(textEdad.getText());				
				
				flagActualizado = crudTransaccionViewController.actualizarTransaccion(transaccionSeleccionada, nuevaTransaccion);
				if (flagActualizado == true) {
					util.logging("OPERACION REALIZADA CON EXITO", "TRANSACCION AGREGADA", "INFORMACION AÑADIDA");
					JOptionPane.showMessageDialog(null, "Transaccion actualizada con exito.");
					initDataBindings();
					limpiarCamposTexto();
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, seleccione una transaccion a actualizar.");
				}
			}	
			
			}
		});
		btnEliminarTransacción.setText("Actualizar transacci\u00F3n");
		btnEliminarTransacción.setBounds(223, 171, 128, 25);
		m_bindingContext = initDataBindings();

	}

	protected void limpiarCamposTexto() {
		// TODO Auto-generated method stub
		textNombre.setText("");
		textApellido.setText("");
		textCedula.setText("");
		textEdad.setText("");		
	}

	protected boolean verificarCamposVacios(){		
		if (textNombre.getText().equalsIgnoreCase("") || textApellido.getText().equalsIgnoreCase("") || 
				textCedula.getText().equalsIgnoreCase("") || textEdad.getText().equalsIgnoreCase("")) {
			return false;
		} 
		
		return true;
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap[] observeMaps = PojoObservables.observeMaps(listContentProvider.getKnownElements(), Transaccion.class, new String[]{"tipo", "id", "clienteAsociado", "empleadoAsociado"});
		tableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		tableViewer.setContentProvider(listContentProvider);
		//
		IObservableList listaTransaccionesRestauranteObserveList = PojoProperties.list("listaTransacciones").observe(restaurante);
		tableViewer.setInput(listaTransaccionesRestauranteObserveList);
		//
		return bindingContext;
	}
}
