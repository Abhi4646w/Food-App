package com.tap.controller;


import java.io.IOException;
import java.io.PrintWriter;

import com.foodapp.dao.UserDAO;
import com.foodapp.daoimpl.UserDAOimpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@WebServlet("ResetPassword")
public class ResetPassword extends HttpServlet 
{
	
	private PrintWriter pw;
	private String email;
	private String cpassword;
	private String password;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		try
		{
			UserDAO user = new UserDAOimpl();
			pw = resp.getWriter();
			 password = req.getParameter("newpassword");
			 cpassword = req.getParameter("confirmpassword");
			if(password.equals(cpassword))
			{
				email = req.getParameter("email");
				int x = user.updateUserById(email, cpassword);
				if(x == 0)
				{
					pw.println("failure page");
				}
				else
				{
					Cookie c1 = new Cookie("email",email);
					Cookie c2 = new Cookie("password",password);
					c1.setMaxAge(20);
					c2.setMaxAge(30);
					
					resp.addCookie(c1);
					resp.addCookie(c2);
					resp.sendRedirect("login.html");
				}
				
			}
			else
			{
				pw.println("Password Mismath Please re-enter");
				resp.sendRedirect("resetpassword.html");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
	}
	

}
