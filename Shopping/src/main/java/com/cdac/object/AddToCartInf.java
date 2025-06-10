package com.cdac.object;

import java.util.Iterator;

public interface AddToCartInf {
	boolean add(Product product);
	Iterator<Product> getAllProducts();
	boolean removeAllProducts();
	boolean removeProducts(int id);
}
