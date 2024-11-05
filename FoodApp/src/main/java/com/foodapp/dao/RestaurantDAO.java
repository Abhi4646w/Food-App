package com.foodapp.dao;

import java.util.List;

import com.foodapp.model.Restaurant;

public interface RestaurantDAO
{
	int insertRestaurant(Restaurant rest);
	List<Restaurant> getAllRest();
	Restaurant getRestById(int id);
	int deleteRestById(int id);
	int updateRestById(int id,String isActive);

}
