package com.tap.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet("/displayServlet")
public class DisplayServlet extends HttpServlet
{
	private PrintWriter pw;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		pw = resp.getWriter();
		HttpSession session = req.getSession();
		pw.println(session.getAttribute("userId") + " " + session.getAttribute("email") + " " + session.getAttribute("password"));
		
		
	}
	

}
