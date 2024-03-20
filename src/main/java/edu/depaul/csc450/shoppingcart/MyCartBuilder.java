package edu.depaul.csc450.shoppingcart;

public class MyCartBuilder implements IShoppingCartBuilder {
	
	private ShoppingCart shoppingCartinstance;
	
	public MyCartBuilder() {
		
		shoppingCartinstance = ShoppingCart.getInstance();
	}
	
	
	
}
