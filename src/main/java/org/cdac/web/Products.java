package org.cdac.web;

public class Products {
	private int categoryId;
   private  int productId;
    private float productPrice;

public Products() {
	
}


public Products(int categoryId,int productId,float productPrice) {
	this.categoryId=categoryId;
	this.productId = productId;
	this.productPrice=productPrice;
}

public int getCategoryId() {
	return categoryId;
}

public void setCategoryId(int categoryId) {
	this.categoryId = categoryId;
}

public int getProductId() {
	return productId;
}

public void setProductId(int productId) {
	this.productId = productId;
}

public float getProductPrice() {
	return productPrice;
}

public void setProductPrice(float productPrice) {
	this.productPrice = productPrice;
}
}
