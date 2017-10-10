package de.dpma.azubidpma;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

import de.dpma.azubidpma.dao.dbCon;
import de.dpma.azubidpma.view.mainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AzubiMain extends Application  {

	static Logger log = Logger.getLogger(AzubiMain.class.getName());
	public static Stage stage;
	public static  dbCon con = null;
	public static Connection connection = null;
	private static mainController mC;
	public static void main(String[] args) {
		log.info("Applikation wird gestartet");
		
		con = new dbCon();
		log.info("Datenbank Verbindung wird hergestellt");
		Application.launch(AzubiMain.class, args);

		
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(this.getClass().getResource("view/main.fxml")); 
		 stage.getIcons().add(new Image("file:img/user.ico")); 
		log.info("Scene wird initialisiert");
		AnchorPane content;
		content = (AnchorPane) loader.load();
		mC = loader.getController();
		Scene scene = new Scene(content);
		stage.setResizable(false);
		stage.setTitle("Abwesenheitsliste");
		stage.setScene(scene);
		stage.show();
	}
	
	public static mainController getMainController() {
		return mC;
	}
}
