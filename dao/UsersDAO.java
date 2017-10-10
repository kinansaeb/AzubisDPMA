package de.dpma.azubidpma.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


import de.dpma.azubidpma.AzubiMain;
import de.dpma.azubidpma.model.Benutzer;
import javafx.stage.Stage;

public class UsersDAO {
	static Logger log = Logger.getLogger(AzubiMain.class.getName());
	final String SELECT_ALL_USERS = "SELECT * FROM KISAEB.BENUTZER"; // <---- Statement muss geändert werden
	final String ADD_USER = "INSERT INTO KISAEB.BENUTZER (ID, NAME, BERUFSBILD, AUSBILDUNGSJAHR) VALUES(BENUTZER_SEQUENCE.nextVal, ?, ?, ?)";
	final String DELETE_USER = "DELETE FROM KISAEB.BENUTZER WHERE ID = ?";
	final String ALTER_USER = "UPDATE KISAEB.BENUTZER set (NAME, BERUFSBILD, AUSBILDUNGSJAHR) where id = VALUES(?, ?, ?, ?)";
private final Connection con;

public UsersDAO(Connection con) {
	this.con = con;
}
public List<Benutzer> allUsers() throws SQLException {
	PreparedStatement stat = con.prepareStatement(SELECT_ALL_USERS);
	ResultSet result = stat.executeQuery();
	
	ArrayList<Benutzer> User = new ArrayList<>();
	while (result.next()) {
		Benutzer Users = new Benutzer();
		Users.setBerufsbild(result.getString("berufsbild"));
		Users.setAusbildungsjahr(result.getInt("ausbildungsjahr"));
		Users.setId(result.getInt("id"));
		Users.setName(result.getString("name"));
		User.add(Users);
	
	}
	return User;
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
		log.info("Benutzer Informationen geändert");
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
}
