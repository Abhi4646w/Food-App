package com.foodapp.dao;

import java.util.List;

import com.foodapp.model.Menu;

public interface MenuDAO
{
	int insertMenu(Menu m);
	List<Menu> getAllRestMenu(int restid);
	Menu getMenuById(int id);
	int deleteMenuById(int id);
	int updateMenuById(int id,String isAvailable);

}
