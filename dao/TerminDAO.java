package de.dpma.azubidpma.dao;

import de.dpma.azubidpma.AzubiMain;
import de.dpma.azubidpma.model.Termin;
import de.dpma.azubidpma.view.MainController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

import org.apache.commons.dbutils.DbUtils;

public class TerminDAO {
	static Logger log = Logger.getLogger(AzubiMain.class.getName());
	final String SELECT_ALL_TERMINE = "SELECT * FROM KISAEB.TERMINE";
	final String ADD_TERMIN = "INSERT INTO KISAEB.TERMINE (ID, BENUTZER_ID, NAME, KOMMENTAR, KATEGORIE, VON, BIS, REFERAT) VALUES(TERMIN_SEQUENCE.nextVal, ?, ?, ?, ?, ?, ?, ?)";
	final String DELETE_TERMIN = "DELETE FROM KISAEB.TERMINE WHERE ID = ?";
	
	private final Connection con;

	public TerminDAO(Connection con) {
		this.con = con;
	}

	public List<Termin> allTermine() throws SQLException {
		PreparedStatement stat = null;
		// Statement statement = null;
		ArrayList<Termin> terminXL = new ArrayList<>();
		try {
			// stat = con.prepareStatement(SELECT_ALL_TERMINE);
			stat = con.prepareStatement("select * from kisaeb.termine");
			ResultSet result = stat.executeQuery();

			// statement = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,
			// ResultSet.CONCUR_READ_ONLY);
			// ResultSet result = statement.executeQuery(SELECT_ALL_TERMINE);
			log.info("trying to get DB information");
			while (result.next()) {
				System.out.println("success-");
				Termin terminXXL = new Termin();
				terminXXL.setUserNameT(result.getString("name"));
				terminXXL.setKategorie(result.getString("kategorie"));
				terminXXL.setKommentar(result.getString("kommentar"));
				terminXXL.setVon(result.getString("von"));
				terminXXL.setBis(result.getString("bis"));
				terminXXL.setIdT(result.getInt("id"));
				terminXXL.setReferat(result.getString("referat"));
				System.out.println(terminXXL.toString());
				terminXL.add(terminXXL);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.info("failed to get DB information");
		} finally {
			DbUtils.closeQuietly(stat);
		}
		return terminXL;

	}

	public void addTermin(Termin terminXL) {
		try {

			PreparedStatement stat = con.prepareStatement(ADD_TERMIN);
			stat.setInt(1, terminXL.getUserID().getValue());
			stat.setString(2, terminXL.getUserNameT().getValue());
			stat.setString(3, terminXL.getKommentar().getValue());
			stat.setString(4, terminXL.getKategorie().getValue());
			//TODO von und bis in einen Datum umwandeln um es in die Datenbank einzuspeichern
			stat.setString(5, terminXL.getVon().getValue());
			stat.setString(6, terminXL.getBis().getValue());
			stat.setString(7, terminXL.getReferat().getValue());

			stat.executeUpdate();
			log.info("Neuer Termin angelegt");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void deleteTermin(Termin terminXL) {
		PreparedStatement stat = null;
		try {
			stat = con.prepareStatement(DELETE_TERMIN);
			stat.setInt(1, terminXL.getIdT().getValue());
			stat.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DbUtils.closeQuietly(stat);
		}
	}
}