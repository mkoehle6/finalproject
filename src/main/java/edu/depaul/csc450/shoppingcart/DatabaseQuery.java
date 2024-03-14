package edu.depaul.csc450.shoppingcart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

  
  
public class DatabaseQuery {

	private static Logger logger = Logger.getLogger(DatabaseQuery.class.getName());
	
	
	private DatabaseConnection databaseConnection = null;
	private Connection connection = null;
	private PreparedStatement statement = null;

	public DatabaseQuery() {

		logger.setLevel(Level.INFO);
		logger.addHandler(new ConsoleHandler());
		logger.info("DatabaseQuery constructor");
		databaseConnection = new DatabaseConnection();
		connection = databaseConnection.getConnection();

	}

	public ResultSet databaseQuery(String query) {
		
		logger.info("DatabaseQuery:databaseQuery()");
		ResultSet resultSet = null;

		try {
			logger.info("DatabaseQuery:databaseQuery() try block");
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.severe(e.getMessage());
		}

		return resultSet;

	}

	public void databaseInsertCreateAccount(String[] userInfo) throws SQLException {

		logger.info("DatabaseQuery:databaseInsertCreateAccount()");
		String queryString = "INSERT INTO customer_db (cust_first_name, cust_last_name, cust_username, cust_password, cust_question, cust_answer)"
				+ "VALUES(?,?,?,?,?,?)";

		PreparedStatement statement = connection.prepareStatement(queryString);

		// statement.setInt(1, Integer.parseInt(userInfo[0]));
		statement.setString(1, userInfo[0]);
		statement.setString(2, userInfo[1]);
		statement.setString(3, userInfo[2]);
		statement.setString(4, userInfo[3]);
		statement.setString(5, userInfo[4]);
		statement.setString(6, userInfo[5]);

		logger.info("DatabaseQuery:databaseInsertCreateAccount() statement.execute()");
		// return yes?
		boolean b = statement.execute();

	}
}
