package com.tap.controller;

import java.io.IOException;
import java.util.List;

import com.foodapp.dao.OrderItemsDAO;
import com.foodapp.daoimpl.OrderItemsDAOimpl;
import com.foodapp.model.OrderItems;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ShowItems extends HttpServlet 
{
	private List<OrderItems> itemsList;
	private HttpSession session;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		session = req.getSession();
       int orderId = Integer.parseInt(req.getParameter("orderId"));
       OrderItemsDAO oi = new OrderItemsDAOimpl(); 
       itemsList = oi.fetchOrderId(orderId); 
       for(OrderItems o : itemsList)
       {
    	   System.out.println(o);
       }
       session.setAttribute("itemsList", itemsList);
       req.getRequestDispatcher("showitems.jsp").forward(req, resp);
       
	}	
}
