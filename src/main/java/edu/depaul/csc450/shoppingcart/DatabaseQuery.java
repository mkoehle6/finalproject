package edu.depaul.csc450.shoppingcart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

  
public class DatabaseQuery {

	private static Logger logger = Logger.getLogger(DatabaseQuery.class.getName());
	
	private DatabaseConnection databaseConnection = null;
	private Connection connection = null;
	
	public DatabaseQuery() {

		logger.setLevel(Level.INFO);
		logger.addHandler(new ConsoleHandler());
		logger.info("DatabaseQuery constructor");
		databaseConnection = DatabaseConnection.getInstance();
		connection = databaseConnection.connect();

    }

	public Customer databaseQuery(String[] loginInfo) {
		
		logger.info("DatabaseQuery:databaseQuery()");
		// select customer from customer_db where cust_username = x and cust_pw = x
		String queryString = "SELECT * FROM customer_db WHERE cust_username = ? AND cust_password = ?";
		ResultSet resultSet = null;
		Customer customer = null;

		try {
			logger.info("DatabaseQuery:databaseQuery() try block");
			PreparedStatement statement = connection.prepareStatement(queryString);
			statement.setString(1, loginInfo[0]);
			statement.setString(2, loginInfo[1]);

			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				String fname = resultSet.getString("cust_first_name");
				String lname = resultSet.getString("cust_last_name");
				String username = resultSet.getString("cust_username");
				customer = new Customer(fname, lname, username);
			}

		} catch (SQLException e) {
			logger.severe(e.getMessage());
		}

		
		return customer;

	}

	public void databaseInsertCreateAccount(String[] userInfo) throws SQLException {

		logger.info("DatabaseQuery:databaseInsertCreateAccount()");
		String queryString = "INSERT INTO customer_db (cust_first_name, cust_last_name, cust_username, cust_password, cust_question, cust_answer)"
				+ "VALUES(?,?,?,?,?,?)";

		PreparedStatement statement = connection.prepareStatement(queryString);

		statement.setString(1, userInfo[0]);
		statement.setString(2, userInfo[1]);
		statement.setString(3, userInfo[2]);
		statement.setString(4, userInfo[3]);
		statement.setString(5, userInfo[4]);
		statement.setString(6, userInfo[5]);

		logger.info("DatabaseQuery:databaseInsertCreateAccount() statement.execute()");
		// return confirmation
		boolean b = statement.execute();

	}
	
	public ArrayList<Product> populateProductTable() {
		logger.info("DatabaseQuery:databaseQuery()");
		// select customer from customer_db where cust_username = x and cust_pw = x
		String queryString = "SELECT * FROM products";
		ResultSet resultSet = null;
		Product product = null;
		ArrayList<Product> productList = new ArrayList<Product>();
		//ObservableList<Product> productList = FXCollections.observableArrayList();

		try {
			logger.info("DatabaseQuery:populateProductTable() try block");
			Statement statement = connection.createStatement();
			
			resultSet = statement.executeQuery(queryString);
			
			while(resultSet.next()) {
				int prodID = resultSet.getInt("prod_id");
				String prodName = resultSet.getString("prod_name");
				String prodType = resultSet.getString("prod_type");
				String prodCost = resultSet.getString("prod_cost");
				int prodQtyInStock = resultSet.getInt("prod_quantity");
				product = new Product(prodID, prodName, prodType, prodCost, prodQtyInStock);
				productList.add(product);
			}

		} catch (SQLException e) {
			logger.severe(e.getMessage());
		}

		
		return productList;
	
	}
	
}
