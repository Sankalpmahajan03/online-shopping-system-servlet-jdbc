package org.cdac.web.entity;

public class Category {
int categoryId;
String categoryName;
String categoryDescription;
String categoryImage;


public Category() {
	
}

public Category(int categoryId,String categoryName,String categoryDescription,String categoryImage) {
	super();
	this.categoryId = categoryId;
	this.categoryName = categoryName;
	this.categoryDescription = categoryDescription;
	this.categoryImage=categoryImage;
}

public int getCategoryId() {
	return categoryId;
}

public void setCategoryId(int categoryId) {
	this.categoryId = categoryId;
}

public String getCategoryName() {
	return categoryName;
}

public void setCategoryName(String categoryName) {
	this.categoryName = categoryName;
}

public String getCategoryDescription() {
	return categoryDescription;
}

public void setCategoryDescription(String categoryDescription) {
	this.categoryDescription = categoryDescription;
}

public String getCategoryImage() {
	return categoryImage;
}

public void setCategoryImage(String categoryImage) {
	this.categoryImage = categoryImage;
}

}
