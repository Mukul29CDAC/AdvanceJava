package com.cdac.object;

import java.util.ArrayList;
import java.util.Iterator;

public class AddToCartObject implements AddToCartInf{
	
	ArrayList<Product> cart=new ArrayList<Product>();
	
	@Override
	public boolean add(Product product) {
		return cart.add(product);
	}

	@Override
	public Iterator<Product> getAllProducts() {
		return cart.iterator();
	}

	@Override
	public boolean removeProducts(int id) {

		return cart.remove(id)!=null;
	}

	@Override
	public boolean removeAllProducts() {
		return cart.removeAll(cart);
	}

	
		
}
