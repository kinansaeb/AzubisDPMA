package de.dpma.azubidpma.view;

import de.dpma.azubidpma.AzubiMain;
import de.dpma.azubidpma.dao.TerminDAO;
import de.dpma.azubidpma.dao.UsersDAO;
import de.dpma.azubidpma.dao.DbCon;
import de.dpma.azubidpma.model.Benutzer;
import de.dpma.azubidpma.model.Termin;
import de.dpma.azubidpma.*;

import java.io.File;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import java.sql.Connection;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Alert.AlertType;

public class MainController implements Initializable {
	static Logger log = Logger.getLogger(AzubiMain.class.getName());
	AzubiMain azb = new AzubiMain();

	public static Stage stage;
	public String exportPath;
	public String fileName;
	public String sqlImportQuery;
	public String absolutePath;
	public String dateiFormat;
	public static Connection con;
	public static UsersDAO manageUsersDAO = new UsersDAO(AzubiMain.getCon());
	public static TerminDAO manageTermineDAO = new TerminDAO(AzubiMain.getCon());
	
	@FXML
	private TextArea importResultArea = new TextArea();
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
	private ComboBox<String> dateiFormate1 = new ComboBox();
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
	private TableColumn<Termin, String> referat = new TableColumn<Termin, String>();
	@FXML
	private TextArea massImportArea = new TextArea();
	@FXML
	private TextField filePathField = new TextField();
	@FXML
	private TextField fileNameField = new TextField();
	@FXML
	private CheckBox terminBox = new CheckBox();
	@FXML
	private CheckBox userBox = new CheckBox();

	
	
	
	//Export Area 
	@FXML
	public void directoryChooserButton(ActionEvent event) {
		log.info("directoryChooserButton clicked");
		DirectoryChooser dC = new DirectoryChooser();
		File sD = dC.showDialog(stage);
		if(sD == null) {
			filePathField.setText("No directory selected");
		}else{
			filePathField.setText(sD.getAbsolutePath());
			exportPath = (sD.getAbsolutePath());
			System.out.println(exportPath + " was choosen as export path");
			
		}
		
		
	}
	@FXML
	public void exportButton(ActionEvent event) {
		
		log.info("exportButton clicked");
		try {	
			fileName = fileNameField.getText();
			exportPath = filePathField.getText();
			dateiFormat = dateiFormate1.getValue().toString();
			absolutePath = (exportPath +  "\" + fileName + "." + dateiFormat);
			System.out.println(absolutePath);
			
			if (dateiFormat == "XLS") {
				
			}
			
		} catch (Exception ex)  {
					ex.printStackTrace();
	}
	}


	
	@FXML
	public void refreshButton(ActionEvent event) {

		List<Benutzer> user = null;
		user = MainController.manageUsersDAO.allUsers();
		List<Termin> TerminXL = null;
		try {
			TerminXL = MainController.manageTermineDAO.allTermine();
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

		List<Termin> terminListe = null;
		try {
			terminListe = MainController.manageTermineDAO.allTermine();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<Termin> terminList = FXCollections.observableArrayList();
		terminList = FXCollections.observableArrayList(terminListe);
		terminTbl.setItems(terminList);
		idT.setCellValueFactory(cellData -> cellData.getValue().convertIdT());
		kategorie.setCellValueFactory(cellData -> cellData.getValue().getKategorie());
		kommentar.setCellValueFactory(cellData -> cellData.getValue().getKommentar());
		von.setCellValueFactory(cellData -> cellData.getValue().getVon());
		bis.setCellValueFactory(cellData -> cellData.getValue().getBis());
		userNameT.setCellValueFactory(cellData -> cellData.getValue().getUserNameT());
		referat.setCellValueFactory(cellData -> cellData.getValue().getReferat());
	}

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
			connection = DriverManager.getConnection("jdbc:oracle:thin:@pgbtu-cluster-scan.dpma.de:1521/pgbtu.dpma.de",
					"kisaeb", "pass13word12");
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
			alert1.setContentText("Failure! You are not connected to the database."
					+ "Check the console output log for more information!");
			alert1.showAndWait();
		}
	}

	@FXML
	public void initializeButton(ActionEvent event) {
		log.info("initializeButton clicked");
		List<Benutzer> user = null;
		user = MainController.manageUsersDAO.allUsers();
		List<Termin> TerminXL = null;
		try {
			TerminXL = MainController.manageTermineDAO.allTermine();
		} catch (SQLException e) {
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
	public void clearResultAreaButton(ActionEvent event) {
		log.info("clearResultAreaButton clicked");
		importResultArea.setText("");
	}
	@FXML
	public void massImportButton(ActionEvent event) {
		log.info("massImportButton clicked");
		con = AzubiMain.getCon();
		String query = importArea.getText();
		String[] moreQuerys = null;
			if (query.contains(";")){
			moreQuerys = query.split(";");
		}
		try {
			PreparedStatement stmt = null;
			if (moreQuerys == null || moreQuerys.length == 0 || moreQuerys[0].isEmpty()){
			 stmt = con.prepareStatement(query);
			 
			 stmt.executeUpdate();
			 con.commit();
			 importResultArea.setText("SQL Statement was commited \n");

			}
			else{
				for (int i = 0; i < moreQuerys.length; i++){
					 stmt = con.prepareStatement(moreQuerys[i]);
					 stmt.executeUpdate();
					 con.commit();
					 importResultArea.setText("SQL Statement was commited \n");
					 
				}
			}
		} catch (SQLException e) {
			log.info("massImport SQL Exception...");
			 importResultArea.setText("SQL Statement was not commited \n");
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("SQL Error");
			alert.setHeaderText("There was an error!");
			alert.setContentText("There was an error! Check your SQL syntax or refresh database connection.");
			alert.showAndWait();
			
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

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML

	public void deleteUserButton(ActionEvent event) throws SQLException {
		try {
			log.info("deleteUserButton clicked");
			Benutzer user = userTbl.getSelectionModel().getSelectedItem();
			int selectedIndexDelete = userTbl.getSelectionModel().getSelectedIndex();
			userTbl.getItems().remove(selectedIndexDelete);
			MainController.manageUsersDAO.deleteUser(user);
			log.info("deleted from db");
		
			log.info("Benutzer erfolgreich gelöscht");
		} catch (SQLException e) {
			e.printStackTrace();

		}

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
			editUserController euc = fxmlLoader.getController();
			euc.setUser(user);
			int selectedIndexEdit = userTbl.getSelectionModel().getSelectedIndex();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void deleteTerminButton(ActionEvent event) {
		log.info("deleteTermineButton clicked");
		Termin terminXL = terminTbl.getSelectionModel().getSelectedItem();
		Termin selectedIndexDeleteT = terminTbl.getSelectionModel().getSelectedItem();
		terminTbl.getItems().remove(selectedIndexDeleteT);
		MainController.manageTermineDAO.deleteTermin(terminXL);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Termin löschen");
		alert.setHeaderText("Termin wurde gelöscht!");
		alert.setContentText("Der Termin wurde erfolgreich gelöscht.");

		alert.showAndWait();
		log.info("Termin erfolgreich gelöscht");

	}
	// Einstellungen Buttons & Code



	// Initialize Code zum setzen der ComboBox Items und Tabellen Items
	public void initialize(URL location, ResourceBundle resources) {
		berufsbild.setItems(berufsbildList);
		ausbildungsjahr.setItems(ausbildungjahrList);
		berufsbildT.setItems(berufsbildTList);
		ausbildungsjahrT.setItems(ausbildungjahrTList);
		kategorieT.setItems(kategorieTList);
		dateiFormate1.setItems(dateiFormateListe);
		List<Termin> TerminXL = null;
		try {
			TerminXL = MainController.manageTermineDAO.allTermine();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<Benutzer> user = null;
		user = MainController.manageUsersDAO.allUsers();
		ObservableList<Termin> terminList = FXCollections.observableArrayList();
		terminList = FXCollections.observableArrayList(TerminXL);
		terminTbl.setItems(terminList);
		idT.setCellValueFactory(cellData -> cellData.getValue().convertIdT());
		kategorie.setCellValueFactory(cellData -> cellData.getValue().getKategorie());
		kommentar.setCellValueFactory(cellData -> cellData.getValue().getKommentar());
		von.setCellValueFactory(cellData -> cellData.getValue().getVon());
		bis.setCellValueFactory(cellData -> cellData.getValue().getBis());
		userNameT.setCellValueFactory(cellData -> cellData.getValue().getUserNameT());
		referat.setCellValueFactory(cellData -> cellData.getValue().getReferat());

		ObservableList<Benutzer> userList = FXCollections.observableArrayList();
		userList = FXCollections.observableArrayList(user);
		userTbl.setItems(userList);
		userName.setCellValueFactory(cellData -> cellData.getValue().getName());
		id.setCellValueFactory(cellData -> cellData.getValue().convertId());
		berufsJahr.setCellValueFactory(cellData -> cellData.getValue().convertAj());
		berufsGruppe.setCellValueFactory(cellData -> cellData.getValue().getBerufsbild());

	}

	
	@FXML
	public void openSyntaxButton(ActionEvent event) {
		log.info("openSyntaxButton clicked");
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("syntax.fxml"));
			log.info("Scene syntax.fxml wird initialisiert");
			Parent root = (Parent) fxmlLoader.load();
			stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setTitle("SQL Statements");
			stage.setResizable(false);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	// Listen für ComboBoxen für Termine & Benutzer & Export Area Dateiformate
	@FXML
	public ComboBox<String> berufsbild1;
	ObservableList<String> berufsbildList = FXCollections.observableArrayList("IT", "KFB", "VFA", "FAMI", "Schreiner",
			"Elektroniker");
	@FXML
	public ComboBox<String> ausbildungsjahr1;
	ObservableList<String> ausbildungjahrList = FXCollections.observableArrayList("1", "2", "3", "4");

	@FXML
	public ComboBox<String> berufsbildT1;
	ObservableList<String> berufsbildTList = FXCollections.observableArrayList("IT", "KFB", "VFA", "FAMI", "Schreiner",
			"Elektroniker");
	@FXML
	public ComboBox<String> ausbildungsjahrT1;
	ObservableList<String> ausbildungjahrTList = FXCollections.observableArrayList("1", "2", "3", "4");
	@FXML
	public ComboBox<String> kategorieT1;
	ObservableList<String> kategorieTList = FXCollections.observableArrayList("Krank", "Urlaub", "Fachbereich",
			"Berufschule", "Innung");
	
	@FXML
	public ComboBox<String> dateiFormate;
	ObservableList<String> dateiFormateListe = FXCollections.observableArrayList("PDF", "CSV", "XLS", "txt");
	
	
}

