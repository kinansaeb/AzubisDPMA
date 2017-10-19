package de.dpma.azubidpma.view;

import java.util.logging.Logger;

import de.dpma.azubidpma.AzubiMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;

public class SyntaxController { 
static Logger log = Logger.getLogger(AzubiMain.class.getName());
public static Stage stage;

@FXML
private TextArea area1 = new TextArea();
@FXML
private TextArea area2 = new TextArea();


@FXML
public void copyButton1(ActionEvent event) {
	log.info("copyButton1 clicked");
	final Clipboard clipboard = Clipboard.getSystemClipboard();
	final ClipboardContent content = new ClipboardContent();
	content.putString("INSERT INTO KISAEB.BENUTZER (ID, NAME, BERUFSBILD, AUSBILDUNGSJAHR) VALUES(BENUTZER_SEQUENCE.nextVal, 'BENUTZERNAME', 'BERUFSBILD', 1);");
	clipboard.setContent(content);
}


@FXML
public void copyButton2(ActionEvent event) {
	log.info("copyButton2 clicked");
	final Clipboard clipboard = Clipboard.getSystemClipboard();
	final ClipboardContent content = new ClipboardContent();
	content.putString("INSERT INTO KISAEB.TERMINE (ID, BENUTZER_ID, NAME, KOMMENTAR, KATEGORIE, VON, BIS, REFERAT) VALUES(TERMIN_SEQUENCE.nextVal, 100,'BENUTZERNAME ', 'KOMMENTARTEXT', 'KATEGORIE',to_date('01.01.2017', 'DD.MM.YYYY') , to_date('02.02.2017', 'DD.MM.YYYY'), '4.3.1');");
	clipboard.setContent(content);
}

@FXML
public void copyButton3(ActionEvent event) {
	log.info("copyButton3 clicked");
	final Clipboard clipboard = Clipboard.getSystemClipboard();
	final ClipboardContent content = new ClipboardContent();
	content.putString("DELETE FROM KISAEB.BENUTZER WHERE ID IN (1,2,3,4,5);");
	clipboard.setContent(content);
}

@FXML
public void closeButton(ActionEvent event) {
	try {
		log.info("abortButton clicked");
		((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

	} catch (Exception e) {
		e.printStackTrace();
	}
}

	
}
