package de.dpma.azubidpma.view;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import de.dpma.azubidpma.AzubiMain;
import de.dpma.azubidpma.dao.UsersDAO;
import de.dpma.azubidpma.model.Termin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class AddTermineController implements Initializable {
static Logger log = Logger.getLogger(AzubiMain.class.getName());
AzubiMain azb = new AzubiMain();
public static Stage stage;

@FXML
private ComboBox namenListe = new ComboBox();
@FXML
private ComboBox kategorie = new ComboBox();
@FXML
private TextField referat = new TextField();
@FXML
private DatePicker von = new DatePicker();
@FXML
private DatePicker bis = new DatePicker();
@FXML
private TextArea kommentar = new TextArea();

@FXML
public ComboBox<String> kategorie1;
ObservableList<String> kategorie1List = FXCollections.observableArrayList("Fachbereich", "Urlaub", "Berufschule", "Krank", "Vermisst");

@FXML
public ComboBox<String> namen1;
ObservableList<String> namen1Liste = FXCollections.observableArrayList(getBenutzerListe); // <-----

@FXML

public void initialize(URL location, ResourceBundle resources) {
	kommentar.setPromptText("Falls gew�nscht hier Kommentar eingeben...");	
	kategorie1.setItems(kategorie1List);
	von.setPromptText("Datum-Von");
	bis.setPromptText("Datum-Bis");
	UsersDAO usersDao = MainController.manageUsersDAO;
	usersDao.getBenutzerListe();
	namenListe.setItems(namen1Liste);
}

@FXML
public void saveButton(ActionEvent event) {
	Termin terminXL = new Termin();
	terminXL.setUserNameT(namenListe.getSelectionModel().getSelectedItem().toString());
	terminXL.setReferat(referat.getText());
	terminXL.setKategorie(kategorie.getSelectionModel().getSelectedItem().toString());
	terminXL.setVon(von.getValue().toString());
	terminXL.setBis(bis.getValue().toString());
	terminXL.setKommentar(kommentar.getText());
	try {
		MainController.manageTermineDAO.addTermin(terminXL);
		List<Termin> lastTermin = MainController.manageTermineDAO.allTermine();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
}
@FXML
public void abortButton(ActionEvent event) {
	try {
	log.info("abortButton clicked");
	((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	
} catch (Exception e) {
	e.printStackTrace();
}
}
}