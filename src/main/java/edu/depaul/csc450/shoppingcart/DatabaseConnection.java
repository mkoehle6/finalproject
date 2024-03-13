package edu.depaul.csc450.shoppingcart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {

	static Connection connection = null;
	
	public Connection getConnection() {
		
		String url = "jdbc:mysql://localhost:3306/shopping_cart";
		String username = "shopping_cart_user";
		String password = "user1234";
		
	
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;
	}
}
