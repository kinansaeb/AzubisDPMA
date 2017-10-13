package de.dpma.azubidpma.model;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class Benutzer {
	private IntegerProperty id = new SimpleIntegerProperty();
	private IntegerProperty ausbildungsjahr = new SimpleIntegerProperty();
	private StringProperty name = new SimpleStringProperty();
	private StringProperty berufsbild = new SimpleStringProperty();

	public Benutzer() {

	}

	public Benutzer(String name, String berufsbild, int ausbildungsjahr, int id) {
		this.name = new SimpleStringProperty(name);

		this.berufsbild = new SimpleStringProperty(berufsbild);
		this.ausbildungsjahr = new SimpleIntegerProperty(ausbildungsjahr);
		this.id = new SimpleIntegerProperty(id);
	}

	public IntegerProperty getId() {
		return id;
	}

	public StringProperty convertId() {
		StringProperty dump = new SimpleStringProperty();
		StringConverter<Number> converter = new NumberStringConverter();
		Bindings.bindBidirectional(dump, id, converter);
		return dump;
	}

	public StringProperty convertAj() {
		StringProperty dump = new SimpleStringProperty();
		StringConverter<Number> converter = new NumberStringConverter();
		Bindings.bindBidirectional(dump, ausbildungsjahr, converter);
		return dump;
	}

	public void setId(int id) {
		this.id.set(id);
	}

	public IntegerProperty getAusbildungsjahr() {
		return ausbildungsjahr;
	}

	public void setAusbildungsjahr(int ausbildungsjahr) {
		this.ausbildungsjahr.set(ausbildungsjahr);
	}

	public StringProperty getName() {
		return name;
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public StringProperty getBerufsbild() {
		return berufsbild;
	}

	public void setBerufsbild(String berufsbild) {
		this.berufsbild.set(berufsbild);
	}

}
