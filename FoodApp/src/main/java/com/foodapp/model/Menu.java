package com.foodapp.model;

public class Menu 
{
	private int menuId;
	  private int restaurantId;
	  private String itemName;
	  private String description;
	  private float price;
	  private int isAvailable;
	  private String imgPath;
	  private int ratings;
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(int isAvailable) {
		this.isAvailable = isAvailable;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
	public int getRatings() {
		return ratings;
	}
	public void setRatings(int ratings) {
		this.ratings = ratings;
	}
	public Menu(int menuId, int restaurantId, String itemName, String description, float price, int isAvailable,
			String imgPath) {
		super();
		this.menuId = menuId;
		this.restaurantId = restaurantId;
		this.itemName = itemName;
		this.description = description;
		this.price = price;
		this.isAvailable = isAvailable;
		this.imgPath = imgPath;
	}
	
	public Menu(int menuId, int restaurantId, String itemName, String description, float price, int isAvailable,
			String imgPath, int ratings) {
		super();
		this.menuId = menuId;
		this.restaurantId = restaurantId;
		this.itemName = itemName;
		this.description = description;
		this.price = price;
		this.isAvailable = isAvailable;
		this.imgPath = imgPath;
		this.ratings = ratings;
	}
	public Menu() {
		super();
	}
	@Override
	public String toString() {
		return  menuId + "     " + restaurantId + "     " + itemName + "     "
				+ description + "     " + price + "     " + isAvailable + "     " + imgPath ;
	}

}
