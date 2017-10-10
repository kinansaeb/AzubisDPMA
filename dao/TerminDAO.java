package de.dpma.azubidpma.dao;

import de.dpma.azubidpma.AzubiMain;
import de.dpma.azubidpma.model.Termin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;


public class TerminDAO {
	static Logger log = Logger.getLogger(AzubiMain.class.getName());
	final String SELECT_ALL_TERMINE = "SELECT * FROM KISAEB.TERMINE";
	final String ADD_TERMIN = "INSERT INTO KISAEB.TERMINE (NAME, KATEGORIE, KOMMENTAR, VON, BIS)";
	final String DELETE_TERMIN = "DELETE FROM KISAEB.TERMINE WHERE ID = ?";

private final Connection con;

public TerminDAO(Connection con) {
	this.con = con;
}

public List<Termin> allTermine() throws SQLException {
	PreparedStatement stat = con.prepareStatement(SELECT_ALL_TERMINE);
	ResultSet result = stat.executeQuery();
	
	ArrayList<Termin> TerminXL = new ArrayList<>();
	while (result.next()) {
		Termin TerminXXL = new Termin();
		TerminXXL.setUserNameT(result.getString("userNameT"));
		TerminXXL.setKategorie(result.getString("kategorie"));
		TerminXXL.setKommentar(result.getString("kommentar"));
		TerminXXL.setVon(result.getString("von"));
		TerminXXL.setBis(result.getString("bis"));
		TerminXL.add(TerminXXL);
	}
	return TerminXL;
}

	
}
