package prestamo.objetos.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import prestamo.objetos.MainApp;
import prestamo.objetos.controller.GestionPrestamoControl;
import prestamo.objetos.model.Cliente;

public class CrudClienteViewController {
	@FXML
	private TableView<Cliente> clienteTable;
	@FXML
	private TableColumn<Cliente, String> nombreColumn;
	@FXML
	private TableColumn<Cliente, String> apellidoColumn;
	@FXML
	private Label firstNameLabel;
	@FXML
	private Label lastNameLabel;
	@FXML
	private Label streetLabel;
	@FXML
	private Label postalCodeLabel;
	@FXML
	private Label cityLabel;
	@FXML
	private Label birthdayLabel;
	// Reference to the main application.
	private MainApp mainApp;

	GestionPrestamoControl gestionPrestamoControl;

	public CrudClienteViewController() {
		// TODO Auto-generated constructor stub
		gestionPrestamoControl = new GestionPrestamoControl();
	}



	@FXML
	private void initialize() {
		// Initialize the person table with the two columns.
		nombreColumn.setCellValueFactory(cellData -> cellData.getValue().getNombreProperty());
		apellidoColumn.setCellValueFactory(cellData -> cellData.getValue().getApellidoProperty());
	}


	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		// Add observable list data to the table
		clienteTable.setItems(gestionPrestamoControl.getClienteData());
	}


}
