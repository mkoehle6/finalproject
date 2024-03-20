package edu.depaul.csc450.shoppingcart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProductTable {
	
	ObservableList<Product> list = FXCollections.observableArrayList();
	TableView<Product> tableView = new TableView<Product>();
	
	public ObservableList<Product> getProductsList() {
		
		DatabaseQuery dbQuery = new DatabaseQuery();
		list = dbQuery.populateProductTable();
		
		return list; 
	}
}
