package de.dpma.azubidpma.view;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import de.dpma.azubidpma.AzubiMain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class addTermineController implements Initializable {
static Logger log = Logger.getLogger(AzubiMain.class.getName());
AzubiMain azb = new AzubiMain();
public static Stage stage;

@FXML
private ComboBox name = new ComboBox();
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


public void initialize(URL location, ResourceBundle resources) {
	kommentar.setPromptText("Falls gewünscht hier Kommentar eingeben...");	
	kategorie1.setItems(kategorie1List);
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