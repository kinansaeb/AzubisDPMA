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



public class dbCon {

	public static Connection getConnection() {
		System.out.println("--- Oracle JDBC Connection Test");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		} catch (ClassNotFoundException e) {
			System.out.println("No Oracle Driver found...");
			e.printStackTrace();
			return null;
		}
		System.out.println("Oracle JDBC Driver Registered!");
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection( "jdbc:oracle:thin:@pgbtu-cluster-scan.dpma.de:1521/pgbtu.dpma.de", "kisaeb", "pass13word12");
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check console output.");
			e.printStackTrace();
			return null;
		}
		if (connection != null) {
			System.out.println("Database connection established!");
			
		} else {
			
		System.out.println("Failed to create Database connection!");
		}
		return connection;
	}

}
