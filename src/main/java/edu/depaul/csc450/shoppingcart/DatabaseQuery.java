package edu.depaul.csc450.shoppingcart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class DatabaseQuery {

	private DatabaseConnection databaseConnection = null;
	private Connection connection = null;
	private PreparedStatement statement = null;
	
		public DatabaseQuery() {
			
			databaseConnection = new DatabaseConnection();
			connection = databaseConnection.getConnection();
			
		}
	
		public ResultSet databaseQuerey(String query) {
			ResultSet resultSet = null;
			
			try {
				statement = connection.prepareStatement(query);
				resultSet = statement.executeQuery();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			return resultSet;
			
		}
		
		public void databaseInsertCreateAccount(String[] userInfo) {
			
			String queryString = "INSERT INTO customer_db (cust_first_name, cust_last_name, cust_username, cust_password, cust_question, cust_answer)" + "VALUES(?,?,?,?,?,?)";

			
    		try {
				PreparedStatement statement = connection.prepareStatement(queryString);
				
				//statement.setInt(1, Integer.parseInt(userInfo[0]));
				statement.setString(1, userInfo[0]);
				statement.setString(2, userInfo[1]);
				statement.setString(3, userInfo[2]);
				statement.setString(4, userInfo[3]);
				statement.setString(5, userInfo[4]);
				statement.setString(6, userInfo[5]);

				boolean b = statement.execute();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
}
