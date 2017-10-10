package de.dpma.azubidpma.model;

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
private StringProperty userNameT = new SimpleStringProperty();
private StringProperty kategorie = new SimpleStringProperty();
private StringProperty kommentar = new SimpleStringProperty();
private StringProperty von = new SimpleStringProperty();
private StringProperty bis = new SimpleStringProperty();


public Termin() {
	
}

public Termin(String userNameT, String kategorie, String kommentar, String von, String bis, int idT) {
	this.userNameT = new SimpleStringProperty(userNameT);
	this.kategorie = new SimpleStringProperty(kategorie);
	this.kommentar = new SimpleStringProperty(kommentar);
	this.bis = new SimpleStringProperty(bis);
	this.von = new SimpleStringProperty(von);
	this.idT = new SimpleIntegerProperty(idT);
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
	// TODO Auto-generated method stub
	
}

public void setKategorie(String string) {
	// TODO Auto-generated method stub
	
}

public void setKommentar(String string) {
	// TODO Auto-generated method stub
	
}

public void setVon(String string) {
	// TODO Auto-generated method stub
	
}

public void setBis(String string) {
	// TODO Auto-generated method stub
	
}

}
