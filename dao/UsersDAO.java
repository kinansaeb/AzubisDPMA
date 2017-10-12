package de.dpma.azubidpma.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.dbutils.DbUtils;

import de.dpma.azubidpma.AzubiMain;
import de.dpma.azubidpma.model.Benutzer;
import de.dpma.azubidpma.model.Termin;
import javafx.stage.Stage;

public class UsersDAO {
	static Logger log = Logger.getLogger(AzubiMain.class.getName());
	final String SELECT_ALL_USERS = "SELECT * FROM KISAEB.BENUTZER"; // <---- Statement muss ge�ndert werden
	final String ADD_USER = "INSERT INTO KISAEB.BENUTZER (ID, NAME, BERUFSBILD, AUSBILDUNGSJAHR) VALUES(BENUTZER_SEQUENCE.nextVal, ?, ?, ?)";
	final String DELETE_USER = "DELETE FROM KISAEB.BENUTZER WHERE ID = ?";
	final String ALTER_USER = "UPDATE KISAEB.BENUTZER SET (NAME, BERUFSBILD, AUSBILDUNGSJAHR) VALUES(?, ?, ?) WHERE ID = ?";
	final String GET_USERNAME_BY_ID = "SELECT ID FROM KISAEB.BENUTZER WHERE BENUTZER_ID = ?";
//	
	
	
private final Connection con;
private ArrayList<Benutzer> benutzerListe = new ArrayList<>();

public ArrayList<Benutzer> getBenutzerListe() {
	return benutzerListe;
}

public void setBenutzerListe(ArrayList<Benutzer> benutzerListe) {
	this.benutzerListe = benutzerListe;
}

public UsersDAO(Connection con) {
	this.con = con;
}

public Benutzer getUser(int userID) {
	PreparedStatement stat = null;
	Benutzer benutzer = new Benutzer();
	try {
		stat = con.prepareStatement(GET_USERNAME_BY_ID);
		stat.setInt(1, userID);
		ResultSet result = stat.executeQuery();
		benutzer.setBerufsbild(result.getString("berufsbild"));
		benutzer.setAusbildungsjahr(result.getInt("ausbildungsjahr"));
		benutzer.setId(result.getInt("id"));
		benutzer.setName(result.getString("name"));
		return benutzer;
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		DbUtils.closeQuietly(stat);
	}
	return null;
	
}
public List<Benutzer> allUsers() {
	PreparedStatement stat = null;
	try {
		stat = con.prepareStatement(SELECT_ALL_USERS);
		ResultSet result = stat.executeQuery();
		benutzerListe.clear();
		while (result.next()) {
			Benutzer benutzer = new Benutzer();
			benutzer.setBerufsbild(result.getString("berufsbild"));
			benutzer.setAusbildungsjahr(result.getInt("ausbildungsjahr"));
			benutzer.setId(result.getInt("id"));
			benutzer.setName(result.getString("name"));
			benutzerListe.add(benutzer);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
        DbUtils.closeQuietly(stat);

	}
	return benutzerListe;
	
}
public void addUser(Benutzer user) {
	try {
		PreparedStatement stat = con.prepareStatement(ADD_USER);
//		stat.setInt(1, user.getId().getValue());
		stat.setString(1, user.getName().getValue());
		stat.setString(2, user.getBerufsbild().getValue());
		stat.setInt(3, user.getAusbildungsjahr().getValue());
		stat.executeUpdate();
		log.info("Neuer Benutzer angelegt");
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
	}

}

public void deleteUser(Benutzer user) {
	try {
		PreparedStatement stat = con.prepareStatement(DELETE_USER);
		stat.setInt(1, user.getId().getValue());
		stat.executeUpdate();
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
public void editUser(Benutzer user) {
	try {
		PreparedStatement stat = con.prepareStatement(ALTER_USER);
		stat.setString (1, user.getName().getValue());
		stat.setString(2, user.getBerufsbild().getValue());
		stat.setInt(3, user.getAusbildungsjahr().getValue());
		stat.setInt(4, user.getId().getValue());
		log.info("Benutzer Informationen ge�ndert");
	} catch (SQLException e) {
		e.printStackTrace();
	}
}

}
