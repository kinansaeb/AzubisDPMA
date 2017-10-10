package de.dpma.azubidpma.view;

import de.dpma.azubidpma.AzubiMain;
import de.dpma.azubidpma.dao.TerminDAO;
import de.dpma.azubidpma.dao.UsersDAO;
import de.dpma.azubidpma.dao.dbCon;
import de.dpma.azubidpma.model.Benutzer;
import de.dpma.azubidpma.model.Termin;
import de.dpma.azubidpma.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;

public class mainController implements Initializable {
	static Logger log = Logger.getLogger(AzubiMain.class.getName());
	AzubiMain azb = new AzubiMain();

	public static  dbCon con = null;
	public static Stage stage;
	
	public String sqlImportQuery;
	
	public static UsersDAO manageUsersDAO = new UsersDAO(AzubiMain.con.getConnection());
	public static TerminDAO manageTermineDAO = new TerminDAO(AzubiMain.con.getConnection());
	
	
	@FXML
	private TextArea importArea = new TextArea();
	@FXML
	private TextField del_idFeld = new TextField();
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
	private TableView<Benutzer> userTbl = new TableView();
	@FXML
	private TableView<Termin> terminTbl = new TableView();
	@FXML
	private TableColumn<Benutzer, String> userName = new TableColumn<Benutzer, String>();
	@FXML
	private TableColumn<Benutzer, String> id = new TableColumn<Benutzer, String>();
	@FXML
	private TableColumn<Benutzer, String> berufsJahr = new TableColumn<Benutzer, String>();
	@FXML
	private TableColumn<Benutzer, String> berufsGruppe = new TableColumn<Benutzer, String>();
	@FXML
	private TableColumn<Termin, String> userNameT = new TableColumn<Termin, String>();
	@FXML
	private TableColumn<Termin, String> kategorie = new TableColumn<Termin, String>();
	@FXML
	private TableColumn<Termin, String> kommentar = new TableColumn<Termin, String>();
	@FXML
	private TableColumn<Termin, String> von = new TableColumn<Termin, String>();
	@FXML
	private TableColumn<Termin, String> bis = new TableColumn<Termin, String>();
	@FXML
	private TableColumn<Termin, String> idT = new TableColumn<Termin, String>();
	
	
	
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
	public void initializeButton(ActionEvent event) {
		log.info("initializeButton clicked");
		List<Benutzer> user = null;
		try {
			user = mainController.manageUsersDAO.allUsers();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Termin> TerminXL = null;
		try {
			TerminXL = mainController.manageTermineDAO.allTermine();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ObservableList<Benutzer> userList = FXCollections.observableArrayList();
		userList = FXCollections.observableArrayList(user);
		userTbl.setItems(userList);
		userName.setCellValueFactory(cellData -> cellData.getValue().getName());
		id.setCellValueFactory(cellData -> cellData.getValue().convertId());
		berufsJahr.setCellValueFactory(cellData -> cellData.getValue().convertAj());
		berufsGruppe.setCellValueFactory(cellData -> cellData.getValue().getBerufsbild());
		log.info("Benutzer hinzugefügt");
	
	}
	
	@FXML
	public void initializeTermineButton(ActionEvent event) {
		log.info("initializeTermine Button clicked");
		List<Termin> TerminXL = null;
		try {
			TerminXL = mainController.manageTermineDAO.allTermine();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<Termin> terminList = FXCollections.observableArrayList();
		terminList = FXCollections.observableArrayList(TerminXL);
		terminTbl.setItems(terminList);
		idT.setCellValueFactory(cellData -> cellData.getValue().convertIdT());
		kategorie.setCellValueFactory(cellData -> cellData.getValue().getKategorie());
		kommentar.setCellValueFactory(cellData -> cellData.getValue().getKategorie());
		von.setCellValueFactory(cellData -> cellData.getValue().getVon());
		bis.setCellValueFactory(cellData -> cellData.getValue().getBis());
		userNameT.setCellValueFactory(cellData -> cellData.getValue().getUserNameT());
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
	@FXML
	
	public void deleteUserButton(ActionEvent event) {
		log.info("deleteUserButton clicked");
		Benutzer user = userTbl.getSelectionModel().getSelectedItem();
		int selectedIndexDelete = userTbl.getSelectionModel().getSelectedIndex();
		userTbl.getItems().remove(selectedIndexDelete);
		mainController.manageUsersDAO.deleteUser(user);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Benutzer löschen");
		alert.setHeaderText("Benutzer wurde gelöscht!");
		alert.setContentText("Der Benutzer wurde erfolgreich gelöscht." );

		alert.showAndWait();
		log.info("Benutzer erfolgreich gelöscht");
	}
	
	@FXML
	private void editUserButton(ActionEvent event) {
		log.info("editUserButton clicked");
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("editUser.fxml"));
			log.info("Scene editUser.fxml wird initialisiert");
			Parent root = (Parent) fxmlLoader.load();
			stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setTitle("Benutzer ändern");
			stage.setResizable(false);
			stage.show();
			Benutzer user = userTbl.getSelectionModel().getSelectedItem();
			editUserController euc =  fxmlLoader.getController();
			euc.setUser(user);
			int selectedIndexEdit = userTbl.getSelectionModel().getSelectedIndex();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		//Termine Buttons & Code
	}
	@FXML
	public void addTermineButton(ActionEvent event) {
	log.info("addTerminButton clicked");
	try {
		FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("addTermine.fxml"));
		log.info("Scene addTermine.fxml wird initialisiert");
		Parent root = (Parent) fxmlLoader.load();
		stage = new Stage();
		stage.setScene(new Scene(root));
		stage.setTitle("Neuen Termin anlegen");
		stage.setResizable(false);
		stage.show();
	}catch (Exception e) {
		e.printStackTrace();
		}
	}
	//Einstellungen Buttons & Code
	
	@FXML 
	public void importButton(ActionEvent event) {
	sqlImportQuery = importArea.getText();
		
	
	
		
		
		
	
	}
	//Initialize Code zum setzen der ComboBox Items und Tabellen Items
	public void initialize(URL location, ResourceBundle resources) {
		berufsbild.setItems(berufsbildList);
		ausbildungsjahr.setItems(ausbildungjahrList);
		berufsbildT.setItems(berufsbildTList);
		ausbildungsjahrT.setItems(ausbildungjahrTList);
		kategorieT.setItems(kategorieTList);
		List<Benutzer> user = null;
		try {
			user = mainController.manageUsersDAO.allUsers();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		ObservableList<Benutzer> userList = FXCollections.observableArrayList();
		userList = FXCollections.observableArrayList(user);
		userTbl.setItems(userList);
		userName.setCellValueFactory(cellData -> cellData.getValue().getName());
		id.setCellValueFactory(cellData -> cellData.getValue().convertId());
		berufsJahr.setCellValueFactory(cellData -> cellData.getValue().convertAj());
		berufsGruppe.setCellValueFactory(cellData -> cellData.getValue().getBerufsbild());
		
		
		List<Termin> TerminXL = null;
		try {
			TerminXL = mainController.manageTermineDAO.allTermine();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ObservableList<Termin> terminList = FXCollections.observableArrayList();
		terminList = FXCollections.observableArrayList(TerminXL);
		terminTbl.setItems(terminList);
		idT.setCellValueFactory(cellData -> cellData.getValue().convertIdT());
		kategorie.setCellValueFactory(cellData -> cellData.getValue().getKategorie());
		kommentar.setCellValueFactory(cellData -> cellData.getValue().getKategorie());
		von.setCellValueFactory(cellData -> cellData.getValue().getVon());
		bis.setCellValueFactory(cellData -> cellData.getValue().getBis());
		userNameT.setCellValueFactory(cellData -> cellData.getValue().getUserNameT());
	}
	//Listen für ComboBoxen für Termine & Benutzer
	@FXML
	public ComboBox<String> berufsbild1;
	ObservableList<String> berufsbildList = FXCollections.observableArrayList("IT", "KFB", "VFA", "FAMI", "Schreiner", "Elektroniker");
	@FXML
	public ComboBox<String> ausbildungsjahr1;
	ObservableList<String> ausbildungjahrList = FXCollections.observableArrayList("1", "2", "3", "4");

	@FXML
	public ComboBox<String> berufsbildT1;
	ObservableList<String> berufsbildTList = FXCollections.observableArrayList("IT", "KFB", "VFA", "FAMI", "Schreiner", "Elektroniker");
	@FXML
	public ComboBox<String> ausbildungsjahrT1;
	ObservableList<String> ausbildungjahrTList = FXCollections.observableArrayList("1", "2", "3", "4");
	@FXML
	public ComboBox<String> kategorieT1;
	ObservableList<String> kategorieTList = FXCollections.observableArrayList("Krank", "Urlaub", "Fachbereich", "Berufschule", "Innung");




	
	
}
