package de.dpma.azubidpma.view;

import de.dpma.azubidpma.AzubiMain;
import de.dpma.azubidpma.dao.UsersDAO;
import de.dpma.azubidpma.dao.dbCon;
import de.dpma.azubidpma.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class mainController implements Initializable {
	static Logger log = Logger.getLogger(AzubiMain.class.getName());
	AzubiMain azb = new AzubiMain();

	public static  dbCon con = null;
	public static Stage stage;
	String dbOn;
	String dbOff;
	
	public static UsersDAO manageUsersDAO = new UsersDAO(azb.con.getConnection());
	
	@FXML
	private TextField benutzerSuchfeld = new TextField();
	@FXML
	private TextField terminSuchfeld = new TextField();
	@FXML
	private ComboBox<String> berufsbild = new ComboBox();
	@FXML
	private ComboBox<String> ausbildungsjahr = new ComboBox();
	@FXML
	private ComboBox<String> berufsbildT = new ComboBox();
	@FXML
	private ComboBox<String> ausbildungsjahrT = new ComboBox();
	@FXML
	private ComboBox<String> kategorieT = new ComboBox();
	
	
	
	@FXML
	public void testConnectionButton(ActionEvent event) {
		log.info("testConnectionButton clicked");
		System.out.println("--- Oracle JDBC Connection Test");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		} catch (ClassNotFoundException e) {
			System.out.println("No Oracle Driver found...");
			e.printStackTrace();
			return;
		}
		System.out.println("Oracle JDBC Driver Registered!");
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection( "jdbc:oracle:thin:@pgbtu-cluster-scan.dpma.de:1521/pgbtu.dpma.de", "kisaeb", "pass13word12");
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check console output.");
			e.printStackTrace();
			return;
		}
		if (connection != null) {
			System.out.println("Database connection established!");
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Connection Test");
			alert.setHeaderText("Database Connection Test Result:");
			alert.setContentText("Success! You are connected to the database");
			alert.showAndWait();
		} else {
			
		System.out.println("Failed to create Database connection!");
		Alert alert1 = new Alert(AlertType.INFORMATION);
		alert1.setTitle("Connection Test");
		alert1.setHeaderText("Database Connection Test Result:");
		alert1.setContentText("Failure! You are not connected to the database." + "Check the console output log for more information!");
		alert1.showAndWait();
		}
	}

	@FXML
	
	public void addUserButton(ActionEvent event) {
		log.info("addUserButton clicked");
	try {
		FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("addUser.fxml"));
		log.info("Scene addUser.fxml wird initialisiert");
		Parent root = (Parent) fxmlLoader.load();
		stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle("Neuen Benutzer anlegen");
		stage.setResizable(false);
		stage.show();
		
	}catch (Exception e) {
		e.printStackTrace();
	}
		
	}
	public void initialize(URL location, ResourceBundle resources) {
		berufsbild.setItems(berufsbildList);
		ausbildungsjahr.setItems(ausbildungjahrList);
		berufsbildT.setItems(berufsbildTList);
		ausbildungsjahrT.setItems(ausbildungjahrTList);
		kategorieT.setItems(kategorieTList);
	}
	@FXML
	public ComboBox<String> berufsbild1;
	ObservableList<String> berufsbildList = FXCollections.observableArrayList("IT", "KFB", "VFA", "FAMI", "Schreiner", "Elektroniker");
	@FXML
	public ComboBox<String> ausbildungsjahr1;
	ObservableList<String> ausbildungjahrList = FXCollections.observableArrayList("1.", "2.", "3.", "4.");

	@FXML
	public ComboBox<String> berufsbildT1;
	ObservableList<String> berufsbildTList = FXCollections.observableArrayList("IT", "KFB", "VFA", "FAMI", "Schreiner", "Elektroniker");
	@FXML
	public ComboBox<String> ausbildungsjahrT1;
	ObservableList<String> ausbildungjahrTList = FXCollections.observableArrayList("1.", "2.", "3.", "4.");
	@FXML
	public ComboBox<String> kategorieT1;
	ObservableList<String> kategorieTList = FXCollections.observableArrayList("Krank", "Urlaub", "Fachbereich", "Berufschule", "Innung");

}