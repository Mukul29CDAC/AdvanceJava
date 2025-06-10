package com.cdac.object;

public class Product {
	private int catId;
	private int prodId;
	private String prodName;
	private int prodPrice;
	private String prodImg;
	
	public Product() {
		super();
	}
	public Product(int catId, int prodId, String prodName, int prodPrice, String prodImg) {
		super();
		this.catId = catId;
		this.prodId = prodId;
		this.prodName = prodName;
		this.prodPrice = prodPrice;
		this.prodImg = prodImg;
	}
	public int getCatId() {
		return catId;
	}
	public void setCatId(int catId) {
		this.catId = catId;
	}
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(int prodPrice) {
		this.prodPrice = prodPrice;
	}
	public String getProdImg() {
		return prodImg;
	}
	public void setProdImg(String prodImg) {
		this.prodImg = prodImg;
	}
	@Override
	public String toString() {
		return "Product [catId=" + catId + ", prodId=" + prodId + ", prodName=" + prodName + ", prodPrice=" + prodPrice
				+ ", prodImg=" + prodImg + "]";
	}
	
	
	
}
