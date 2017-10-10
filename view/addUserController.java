package de.dpma.azubidpma.view;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import de.dpma.azubidpma.view.*;
import de.dpma.azubidpma.AzubiMain;
import de.dpma.azubidpma.model.Benutzer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class addUserController implements Initializable {
static Logger log = Logger.getLogger(AzubiMain.class.getName());
AzubiMain azb = new AzubiMain();
public static Stage stage;

@FXML
private TextField vorname = new TextField();
@FXML
private TextField nachname = new TextField();
@FXML
private ComboBox<String> berufsbild = new ComboBox();
@FXML
private ComboBox<String> ausbildungsjahr = new ComboBox();


@FXML
public ComboBox<String> berufsbild1;
ObservableList<String> berufsbildList = FXCollections.observableArrayList("IT", "KFB", "VFA", "FAMI", "Schreiner", "Elektroniker");
@FXML
public ComboBox<String> ausbildungsjahr1;
ObservableList<String> ausbildungjahrList = FXCollections.observableArrayList("1", "2", "3", "4");
@FXML
public void abortButton(ActionEvent event) {
try {
	log.info("abortButton clicked");
	((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	
} catch (Exception e) {
	e.printStackTrace();
}
	
}
public void initialize(URL location, ResourceBundle resources) {
		vorname.setPromptText("vorname");
		nachname.setPromptText("nachname");
		berufsbild.setPromptText("Bitte Auswählen");
		ausbildungsjahr.setPromptText("Bitte Auswählen");
		berufsbild.setItems(berufsbildList);
		ausbildungsjahr.setItems(ausbildungjahrList);
}
	
@FXML
public void saveButton(ActionEvent event) {
	Benutzer user = new Benutzer();
	user.setName(vorname.getText() + " " + nachname.getText());
	user.setAusbildungsjahr(Integer.parseInt(ausbildungsjahr.getValue()));
	user.setBerufsbild(berufsbild.getValue());
	String name = (vorname.getText() + " " + nachname.getText());
	try {
		mainController.manageUsersDAO.addUser(user);
		List<Benutzer> lastUser = mainController.manageUsersDAO.allUsers();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	Alert alert = new Alert(AlertType.INFORMATION);
	alert.setTitle("Benutzer anlegen");
	alert.setHeaderText("Benutzer angelegt!");
	alert.setContentText("Der Benutzer '" +  name + "' wurde angelegt");

	alert.showAndWait();
}

}
