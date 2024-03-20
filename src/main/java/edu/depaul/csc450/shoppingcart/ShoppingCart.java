package edu.depaul.csc450.shoppingcart;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart implements IShoppingCart{

	private Product product;
	private Customer customer;
	private List<Product> pList = new ArrayList<Product>();
	private static ShoppingCart instance;
	
	public static ShoppingCart getInstance() {
		if(instance == null)
			instance = new ShoppingCart();
		
		return instance;
	}
	
	private ShoppingCart() {}

	@Override
	public void addProduct(Product product) {
		pList.add(product);
		
	}

	@Override
	public void removeProduct(Product product) {
		for(int i = 0; i < pList.size(); i++) {
			if(pList.get(i).equals(product)) {
				pList.remove(i);
			}
		}
	}

	@Override
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}
