package com.foodapp.model;

public class Restaurant
{
	private int restaurantId;
	private String name;
	private String cuisiveType;
	private int deliveryTime;
	private String isActive;
	private int ratings;
	private String imgPath;
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCuisiveType() {
		return cuisiveType;
	}
	public void setCuisiveType(String cuisiveType) {
		this.cuisiveType = cuisiveType;
	}
	public int getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(int deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public int getRatings() {
		return ratings;
	}
	public void setRatings(int ratings) {
		this.ratings = ratings;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public Restaurant(int restaurantId, String name, String cuisiveType, int deliveryTime, String isActive, int ratings,
			String imgPath) {
		super();
		this.restaurantId = restaurantId;
		this.name = name;
		this.cuisiveType = cuisiveType;
		this.deliveryTime = deliveryTime;
		this.isActive = isActive;
		this.ratings = ratings;
		this.imgPath = imgPath;
	}
	public Restaurant(String name, String cuisiveType, int deliveryTime, String isActive, int ratings, String imgPath) {
		super();
		this.name = name;
		this.cuisiveType = cuisiveType;
		this.deliveryTime = deliveryTime;
		this.isActive = isActive;
		this.ratings = ratings;
		this.imgPath = imgPath;
	}
	
	
	public Restaurant() {
		super();
	}
	@Override
	public String toString() {
		return  restaurantId + "    " + name + "    " + cuisiveType
				+ "    " + deliveryTime + "    " + isActive + "    " + ratings + "    "
				+ imgPath ;
	}
	
	

}
