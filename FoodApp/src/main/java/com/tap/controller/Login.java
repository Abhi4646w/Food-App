package com.tap.controller;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import com.foodapp.dao.UserDAO;
import com.foodapp.daoimpl.UserDAOimpl;
import com.foodapp.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
//@WebServlet("/Login")
public class Login extends HttpServlet 
{
	private RequestDispatcher rd;
	private ResultSet resultset;
	

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try
		{
			UserDAO user = new UserDAOimpl();
			User u = user.getUserId(req.getParameter("username"));
			HttpSession session = req.getSession();
			if(u!=null)
			{
				if(req.getParameter("password").equals(u.getPassword()))
				{
				
					int userId = u.getUser_id();
					System.out.println(u);
			     	System.out.println("user");
					System.out.println(userId);
					session.setAttribute("object", u);
					session.setAttribute("userId", u.getUser_id());
				    session.setAttribute("password", req.getParameter("password"));
				 //   session.setAttribute("username", u.getUsername());    
					req.getRequestDispatcher("HomeServlet").forward(req, resp);
					
				}
				else
				{
					resp.sendRedirect("invalidpassword.jsp");
				}
			}
			else
			{
				resp.sendRedirect("register1.jsp");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


}
