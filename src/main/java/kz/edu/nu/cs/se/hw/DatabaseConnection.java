package kz.edu.nu.cs.se.hw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
	protected static Connection initializeDatabase()
			throws SQLException, ClassNotFoundException
	{
		// Initialize all the information regarding
		// Database Connection

		String dbDriver = "com.mysql.jdbc.Driver";
		Class.forName(dbDriver);
		//CHANGE PASSWORD!
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?allowMultiQueries=true", "root", "qazxsw");
		Statement mySt  = con.createStatement();
		return con;
	}
}