package com.tap.controller;


import java.io.IOException;

import com.foodapp.dao.MenuDAO;
import com.foodapp.daoimpl.Cart;
import com.foodapp.daoimpl.MenuDAOimpl;
import com.foodapp.model.CartItem;
import com.foodapp.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AddToCartServlet extends HttpServlet 
{
	private HttpSession session;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		session = req.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart == null)
		{
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		String action = req.getParameter("action");
		if("add".equals(action))
		{
			addItem(req,cart);
		}
		else if("update".equals(action))
		{
			updateItem(req,cart);
		}
		else if("delete".equals(action))
		{
			deleteItem(req,cart);
		}

        session.setAttribute("cart", cart);
        int menuid = Integer.parseInt(req.getParameter("menuId"));
        session.setAttribute("menuId", menuid);
        req.getRequestDispatcher("cart.jsp").forward(req, resp);
	}

	private void deleteItem(HttpServletRequest req, Cart cart) 
	{
		int menuid = Integer.parseInt(req.getParameter("menuId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		cart.removeItem(menuid);
	}

	private void updateItem(HttpServletRequest req, Cart cart)
	{
		System.out.println("enterred");
		System.out.println(req.getParameter("menuId"));
		System.out.println(req.getParameter("quantity"));
		int menuid = Integer.parseInt(req.getParameter("menuId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		cart.updateItem(menuid, quantity);
		
	}

	private void addItem(HttpServletRequest req, Cart cart) 
	{
		int menuid = Integer.parseInt(req.getParameter("menuId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		
		MenuDAO md = new MenuDAOimpl();
		Menu m = md.getMenuById(menuid);
		session = req.getSession();
		session.setAttribute("restaurantId", m.getRestaurantId());
		
		if(m!=null)
		{
			CartItem cartitem = new CartItem(m.getMenuId(),m.getRestaurantId(),m.getItemName(),quantity,m.getPrice());
			cart.addItem(cartitem);
			
		}
	

	}


}
