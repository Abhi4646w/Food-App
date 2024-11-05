package com.tap.controller;


import java.io.IOException;
import java.util.List;

import com.foodapp.dao.RestaurantDAO;
import com.foodapp.daoimpl.RestaurantDAOimpl;
import com.foodapp.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class HomeServlet extends HttpServlet
{
	private HttpSession session;
	private List<Restaurant> restaurantlist;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		RestaurantDAO r = new RestaurantDAOimpl();
		 restaurantlist = r.getAllRest();
		session = req.getSession();
		System.out.println(req.getParameter("username")); 
        System.out.println(req.getParameter("password"));
		session.setAttribute("reslist", restaurantlist);
		req.getRequestDispatcher("home1.jsp").forward(req, resp);
	}
	
}
