package de.dpma.azubidpma.model;

import de.dpma.azubidpma.view.MainController;
import javafx.beans.*;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class Termin {
private IntegerProperty idT = new SimpleIntegerProperty();
private IntegerProperty userID = new SimpleIntegerProperty();
private StringProperty userNameT = new SimpleStringProperty();
private StringProperty kategorie = new SimpleStringProperty();
private StringProperty kommentar = new SimpleStringProperty();
private StringProperty von = new SimpleStringProperty();
private StringProperty bis = new SimpleStringProperty();
private StringProperty referat = new SimpleStringProperty();


public Termin() {
	
}

public Termin(String userNameT, String kategorie, String kommentar, String von, String bis, int idT, String referat, int userID) {
	this.userNameT = new SimpleStringProperty(userNameT);
	this.kategorie = new SimpleStringProperty(kategorie);
	this.kommentar = new SimpleStringProperty(kommentar);
	this.bis = new SimpleStringProperty(bis);
	this.von = new SimpleStringProperty(von);
	this.idT = new SimpleIntegerProperty(idT);
	this.referat = new SimpleStringProperty(referat);
	this.userID = new SimpleIntegerProperty(userID);
}

public IntegerProperty getIdT() {
	return idT;
}

public void setIdT(IntegerProperty idT) {
	this.idT = idT;
}

public IntegerProperty getId() {
	return idT;
}

public void setId(IntegerProperty idT) {
	this.idT = idT;
}
public StringProperty convertIdT() {
	StringProperty dump = new SimpleStringProperty();
	StringConverter<Number> converter = new NumberStringConverter();
	Bindings.bindBidirectional(dump, idT, converter);
	return dump;
}
public StringProperty getUserNameT() {
	return userNameT;
}

public void setUserNameT(StringProperty userNameT) {
	this.userNameT = userNameT;
}

public StringProperty getKategorie() {
	return kategorie;
}

public void setKategorie(StringProperty kategorie) {
	this.kategorie = kategorie;
}

public StringProperty getKommentar() {
	return kommentar;
}

public void setKommentar(StringProperty kommentar) {
	this.kommentar = kommentar;
}

public StringProperty getVon() {
	return von;
}

public void setVon(StringProperty von) {
	this.von = von;
}

public StringProperty getBis() {
	return bis;
}

public void setBis(StringProperty bis) {
	this.bis = bis;
}

public void setUserNameT(String string) {
	this.userNameT.setValue(string);
	
}

public void setKategorie(String string) {
	kategorie.setValue(string);
	
}

public void setKommentar(String string) {
	kommentar.setValue(string);
}

public void setVon(String string) {
	von.setValue(string);
}

public void setBis(String string) {
	bis.setValue(string);
}
public void setIdT(int integer){
	idT.setValue(integer);
}

public StringProperty getReferat() {
	return referat;
}

public void setReferat(StringProperty referat) {
	this.referat = referat;
}

public void setReferat(String string) {
referat.setValue(string);
	
}

public IntegerProperty getUserID() {
	return userID;
}

public void setUserID(IntegerProperty userID) {
	this.userID = userID;
}

public IntegerProperty getUserIDByName() {
		return MainController.manageUsersDAO.getUserByName(getUserNameT().getValue()).getId();
}

//public String getUserName() {
//	Benutzer benutzer = MainController.manageUsersDAO.getUser(this.getUserID().getValue());
//	return benutzer.getName().getValue();
//}

}
