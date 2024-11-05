package com.tap.controller;

import java.io.IOException;
import java.util.List;

import com.foodapp.dao.OrdersDAO;
import com.foodapp.dao.RestaurantDAO;
import com.foodapp.daoimpl.OrdersDAOimpl;
import com.foodapp.daoimpl.RestaurantDAOimpl;
import com.foodapp.model.Orders;
import com.foodapp.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class OrderHistory extends HttpServlet
{

	private HttpSession session;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		session = req.getSession();
		User u = (User) session.getAttribute("object"); 
		System.out.println("In OrderHistory Servlet");
		System.out.println(u.getEmail());
		System.out.println(u.getUser_id());
		OrdersDAO o = new OrdersDAOimpl();
		List<Orders> ohlist = o.fetchOnUserId(u.getUser_id());
		for(Orders O : ohlist)
		{
			System.out.println(O);
		}
		session.setAttribute("orderHistory", ohlist);
		resp.sendRedirect("orderhistory.jsp");
	}
	
}
