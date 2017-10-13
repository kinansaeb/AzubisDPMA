package de.dpma.azubidpma.view;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import de.dpma.azubidpma.AzubiMain;
import de.dpma.azubidpma.model.Benutzer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class editUserController implements Initializable {
	static Logger log = Logger.getLogger(AzubiMain.class.getName());
	AzubiMain azb = new AzubiMain();
	public static Stage stage;

	@FXML
	private TextField id = new TextField();
	@FXML
	private TextField name = new TextField();
	@FXML
	private ComboBox berufsbild = new ComboBox();
	@FXML
	private ComboBox ausbildungsjahr = new ComboBox();

	@FXML
	public ComboBox<String> berufsbild1;
	ObservableList<String> berufsbildList = FXCollections.observableArrayList("IT", "KFB", "VFA", "FAMI", "Schreiner",
			"Elektroniker");

	@FXML
	public ComboBox<String> ausbildungsjahr1;
	ObservableList<String> ausbildungsjahrList = FXCollections.observableArrayList("1", "2", "3", "4");

	private Benutzer user;

	public Benutzer getUser() {
		return user;
	}

	public void setUser(Benutzer user) {
		this.user = user;
	}

	@FXML
	public void abbortButton(ActionEvent event) {
		((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
	}

	@FXML
	public void saveButton(ActionEvent event) {

		user.setName(name.getText());
		user.setAusbildungsjahr(Integer.parseInt(ausbildungsjahr.getValue().toString()));
		user.setBerufsbild(berufsbild.getValue().toString());

		MainController.manageUsersDAO.editUser(user);

		((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Benutzer anlegen");
		alert.setHeaderText("Benutzer angelegt!");
		alert.setContentText("Der Benutzer '" + user.getName() + "' wurde angelegt");

		alert.showAndWait();
		AzubiMain.getMainController().initializeButton(event);
	}

	public void initialize(URL location, ResourceBundle resources) {
		berufsbild.setItems(berufsbildList);
		ausbildungsjahr.setItems(ausbildungsjahrList);
		name.setUserData(user);

	}
}