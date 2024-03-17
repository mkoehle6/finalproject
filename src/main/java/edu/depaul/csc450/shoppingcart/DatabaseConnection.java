package edu.depaul.csc450.shoppingcart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;


public class DatabaseConnection {

	static Connection connection = null;
	private static Logger logger = (Logger) Logger.getLogger(DatabaseConnection.class.getName());  
	private static DatabaseConnection instance;
	
	public static DatabaseConnection getInstance() {
		if(instance == null)
			instance = new DatabaseConnection();
		
		return instance;
	}
	
	public Connection connect() {
		
		String url = "jdbc:mysql://localhost:3306/shopping_cart";
		
		String username = "user";
		String password = "user!5555";
		
	
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.severe(e.getMessage());
		}
		
		return connection;
	}
	
	public void close() {
		
	}
}
