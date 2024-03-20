package edu.depaul.csc450.shoppingcart;

public interface IShoppingCart {
	public void addProduct(Product product);
	public void removeProduct(Product product);
	public void setCustomer(Customer customer);
}
