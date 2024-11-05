package com.tap.controller;


import java.io.IOException;
import java.util.List;

import com.foodapp.daoimpl.MenuDAOimpl;
import com.foodapp.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class MenuServlet extends HttpServlet 
{
	private HttpSession session;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		int restid = Integer.parseInt(req.getParameter("restaurantId"));
	    MenuDAOimpl m = new MenuDAOimpl();
	    List<Menu> menulist = m.getAllRestMenu(restid);
	    System.out.println(menulist);
	    session = req.getSession();
	    session.setAttribute("menulist", menulist);
	    session.setAttribute("restaurantId",restid);
	    req.getRequestDispatcher("menu.jsp").forward(req, resp);

	}
	

}
