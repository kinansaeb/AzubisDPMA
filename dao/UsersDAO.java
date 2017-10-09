package de.dpma.azubidpma.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.dpma.azubidpma.model.Benutzer;

public class UsersDAO {
final String SELECT_ALL_USERS = "SELECT * FROM ROOT.BENUTZER"; // <---- Statement muss geändert werden
final String ADD_USER = "INSERT INTO ROOT.BENUTZER (ID, NAME, BERUFSBILD, AUSBILDUNGSJAHR, KATEGORIE) VALUES(?, ?, ?, ?, ?)";

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
		Users.setKategorie(result.getString("kategorie"));
		Users.setId(result.getInt("id"));
		Users.setName(result.getString("name"));

		User.add(Users);
		
	}
	return User;
}
public void addUser(Benutzer user) {
	try {
		PreparedStatement stat = con.prepareStatement(ADD_USER);
		stat.setInt(1, user.getId().getValue());
		stat.setString(2, user.getName().getValue());
		stat.setString(3, user.getBerufsbild().getValue());
		stat.setInt(4, user.getAusbildungsjahr().getValue());
		stat.executeUpdate();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

}
