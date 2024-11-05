package com.tap.controller;


import java.io.IOException;
import java.io.PrintWriter;

import com.foodapp.dao.UserDAO;
import com.foodapp.daoimpl.UserDAOimpl;
import com.foodapp.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@WebServlet("/InsertData")
public class InsertData extends HttpServlet 
{
	 private PrintWriter pw;
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
    	{
    		String username = request.getParameter("username");
    		String email = request.getParameter("email");
    		String password = request.getParameter("password");
    		String address = request.getParameter("address");
    		int mobile = Integer.parseInt(request.getParameter("mobile"));
    		User u = new User(username,email,password,address,mobile);
    		UserDAO o = new UserDAOimpl();
    		int x = o.insertUser(u);
    		pw = response.getWriter();
    		if(x == 1)
    		{
    			response.sendRedirect("success.html");
    		}
    		else
    		{
    			response.sendRedirect("failure.html");
    		}
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
	}
}
