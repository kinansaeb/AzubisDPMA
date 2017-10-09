package de.dpma.azubidpma.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Properties;



public class DatabaseConnection {
	
//public Connection getConnection() {
//	Connection dbConnection = null;
//	Properties properties = new Properties();
//	InputStream input = null;	
//	try {
//		input = new FileInputStream("./src/config.properties");
//		String currentDir = new File(".").getAbsolutePath();
//		System.out.println("dfbn " + currentDir);
//		properties.load(input);
//		String jdbcDriver = "oracle.jdbc.OracleDriver";
//		int dbPort = 1521;
//		String jdbcUrl = String.format("jdbc:oracle:thin:@//%s:%s/%s", new Object[] {
//				properties.getProperty("hostname"), dbPort, properties.getProperty("dbService") } );
//				
//		Class.forName(jdbcDriver);
//		dbConnection = DriverManager.getConnection(jdbcUrl, properties.getProperty("dbUser"),
//				properties.getProperty("dbPw"));
//		dbConnection.setAutoCommit(false);
//		System.out.println("Datenbankverbindung erfolgreich");
//		
//		
//		
//	 } catch (SQLException | ClassNotFoundException | IOException e) {
//		throw new RuntimeException("getDbConnection", e);
//	}
//	 return dbConnection;
//}
}
