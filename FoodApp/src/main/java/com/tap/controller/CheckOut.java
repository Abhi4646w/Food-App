package com.tap.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.foodapp.dao.OrderHistoryDAO;
import com.foodapp.dao.OrdersDAO;
import com.foodapp.daoimpl.Cart;
import com.foodapp.daoimpl.OrderHistoryDAOimpl;
import com.foodapp.daoimpl.OrderItemsDAOimpl;
import com.foodapp.daoimpl.OrdersDAOimpl;
import com.foodapp.model.CartItem;
import com.foodapp.model.OrderHistory;
import com.foodapp.model.OrderItems;
import com.foodapp.model.Orders;
import com.foodapp.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CheckOut extends HttpServlet 
{
	private PrintWriter pw;
	private HttpSession session;
	private int quantity;
	private int menuId;
	private double price;




	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		
		
		session = req.getSession();
		User user = (User) session.getAttribute("object");
		Cart cart = (Cart) session.getAttribute("cart");
		float amount = 0.0f;
		
		
		
		List<OrderItems> orderItems = new ArrayList<>();
		List<CartItem>cartitemslist = new ArrayList();
		for (CartItem cartItem : cart.getItems().values()) {
			amount += cartItem.getPrice() * cartItem.getQuantity();
            cartitemslist.add(cartItem);
		}


		//inserting into orders
		        OrdersDAO od = new OrdersDAOimpl();
				int restaurantid =   (int) session.getAttribute("restaurantId");
				String paymentmethod = req.getParameter("paymentMethod");
				Orders o = new Orders(user.getUser_id(),restaurantid,amount,"pending",paymentmethod);
				System.out.println("orders"+od.insertOrder(o));

				
		//inserting into orderHistory
		OrderHistoryDAO oh = new OrderHistoryDAOimpl();
		OrderHistory oh1 = new OrderHistory(user.getUser_id(),od.getMaxOfOrderId(),amount,"delivered");
		System.out.println("orderHistory"+oh.insertOrderHistory(oh1));

		//inserting into orderItems
		OrderItemsDAOimpl oid = new OrderItemsDAOimpl();
		for(CartItem c : cartitemslist)
		{
			OrderItems oi = new OrderItems(od.getMaxOfOrderId(),c.getItemId(),c.getQuantity(),(c.getQuantity()*c.getPrice()));
			System.out.println("orderitems"+ oid.insertOrderItems(oi));
		}
		
		
		// get orderid 
		int OrderId = od.getMaxOfOrderId();
		session.setAttribute("OrderId", OrderId);

		resp.sendRedirect("orderplaced.jsp");
		
		
		
		//inserting into orderItems
		/*    OrderItemsDAO oid = new OrderItemsDAOimpl();
           int menuid =  (int) session.getAttribute("menuId");
           int quantity =  (int) session.getAttribute("quantity");
           pw.println(menuid);
           pw.println(quantity);
           OrderItems oi = new OrderItems(or.getOrderId(),menuid,quantity,amount);
           System.out.println("orderHistory"+ oid.insertOrderItems(oi)); */

		
		// OrderItems oi = new OrderItems(or.getOrderId(),menuId,quantity,price);
         //System.out.println("orderitems"+oid.insertOrderItems(oi));
	}
}
