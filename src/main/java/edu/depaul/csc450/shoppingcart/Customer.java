package edu.depaul.csc450.shoppingcart;


public class Customer {
	
	private int custID;
	private String firstName;
	private String lastName;
	private String username;

	private static Customer instance;
	
	public static Customer getInstance() {
		if(instance == null)
			instance = new Customer();
		
		return instance;
	}
	
	private Customer() { 
		
	}
	
	public void setCustomerData(String firstName, String lastName, String username) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
	}
	
	public int getCustID() {
		return custID;
	}

	public void setCustID(int custID) {
		this.custID = custID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
