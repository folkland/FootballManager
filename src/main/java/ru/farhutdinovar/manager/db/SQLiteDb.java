package ru.farhutdinovar.manager.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteDb {

	public static Connection connection;
	public static Statement statement;
	public static ResultSet resultSet;
	
	public static void connect() {
		connection = null;
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:fmanager.db");
			System.out.println("Data base connected");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Data base not connected");
		}
		
	}
}
