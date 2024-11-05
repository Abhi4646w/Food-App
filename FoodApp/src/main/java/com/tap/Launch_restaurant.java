package com.tap;

import java.util.List;

import com.foodapp.dao.MenuDAO;
import com.foodapp.daoimpl.MenuDAOimpl;
import com.foodapp.model.Menu;
import com.foodapp.model.Restaurant;

public class Launch_restaurant {

	private static List<Menu> rlist;

	public static void main(String[] args)
	{
	   MenuDAO r = new MenuDAOimpl();
	   rlist = r.getAllRestMenu(2);
	  // System.out.println(rlist);
	   for(Menu res : rlist)
	   {
		   System.out.println(res);
	   }

	}

}
