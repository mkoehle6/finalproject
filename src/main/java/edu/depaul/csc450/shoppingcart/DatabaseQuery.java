package edu.depaul.csc450.shoppingcart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
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
		String queryString = "SELECT * FROM customer_db WHERE cust_username = ? AND cust_password = ?";
		ResultSet resultSet = null;
		Customer customerInstance = null;

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
				customerInstance = Customer.getInstance();
				customerInstance.setCustomerData(fname, lname, username);
			}

		} catch (SQLException e) {
			logger.severe(e.getMessage());
		}

		
		return customerInstance;

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
		/*TODO return confirmation */
		boolean b = statement.execute();

	}
	
	public ObservableList<Product> populateProductTable() {
		logger.info("DatabaseQuery:databaseQuery()");
		String queryString = "SELECT * FROM products";
		ResultSet resultSet = null;
		Product product = null;
		ObservableList<Product> productList = FXCollections.observableArrayList();

		try {
			logger.info("DatabaseQuery:populateProductTable() try block");
			Statement statement = connection.createStatement();
			
			resultSet = statement.executeQuery(queryString);
			
			while(resultSet.next()) {
				int prodID = resultSet.getInt("prod_id");
				String prodName = resultSet.getString("prod_name");
				String prodType = resultSet.getString("prod_type");
				double prodCost = resultSet.getDouble("prod_cost");
				int prodQtyInStock = resultSet.getInt("prod_quantity");
				String imgName = resultSet.getString("prod_image");
				String description = resultSet.getString("prod_desc");
				if(prodType.equalsIgnoreCase("Food")) {								
					product = new FoodProduct(prodID, prodName, prodType, prodCost, prodQtyInStock, imgName, description);
				}
				productList.add(product);
			}

		} catch (SQLException e) {
			logger.severe(e.getMessage());
		}

		return productList;	
	}
	
}
