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
import javafx.scene.control.TextField;
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
ObservableList<String> berufsbildList = FXCollections.observableArrayList("IT", "KFB", "VFA", "FAMI", "Schreiner", "Elektroniker");

@FXML
public ComboBox<String> ausbildungsjahr1;
ObservableList<String> ausbildungsjahrList = FXCollections.observableArrayList("1", "2", "3", "4");

@FXML
public void abbortButton(ActionEvent event) {
	((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
}

public void initialize(URL location, ResourceBundle resources) {
	berufsbild.setItems(berufsbildList);
	ausbildungsjahr.setItems(ausbildungsjahrList);
	
}
}