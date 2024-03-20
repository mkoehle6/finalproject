package edu.depaul.csc450.shoppingcart;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public abstract class Product {
	
	private int productID;
	private String nameString;
	private String productTypeString;
	private double price;
	private int quantityInStock;
	private String imageName;
	private String description;
	

	public Product(int productID, String nameString, String productTypeString, double price, int quantityInStock,
			String imageName, String description) {
		this.productID = productID;
		this.nameString = nameString;
		this.productTypeString = productTypeString;
		this.price = price;
		this.quantityInStock = quantityInStock;
		this.imageName = imageName;
		this.description = description;
	}


	public int getProductID() {
		return productID;
	}


	public void setProductID(int productID) {
		this.productID = productID;
	}


	public String getNameString() {
		return nameString;
	}


	public void setNameString(String nameString) {
		this.nameString = nameString;
	}


	public String getProductTypeString() {
		return productTypeString;
	}


	public void setProductTypeString(String productTypeString) {
		this.productTypeString = productTypeString;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public double getQuantityInStock() {
		return quantityInStock;
	}


	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public SimpleStringProperty getNameProperty() {
		return new SimpleStringProperty(this, nameString);
	}
	
	public SimpleStringProperty getIDProperty() {	
		return new SimpleStringProperty(Integer.toString(productID));
	}
	
	public SimpleDoubleProperty getPriceProperty() {
		return new SimpleDoubleProperty(this.price);
	}
	
	public SimpleStringProperty getTypeProperty() {
		return new SimpleStringProperty(this, productTypeString);
	}
	
	public SimpleIntegerProperty getQtyProperty() {
		return new SimpleIntegerProperty(this.quantityInStock);
	}
		
	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return String.format(
				"Product [productID=%s, nameString=%s, productTypeString=%s, price=%s, quantityInStock=%s, imageName=%s, description=%s]",
				productID, nameString, productTypeString, price, quantityInStock, imageName, description);
	}

}
