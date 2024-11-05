package com.foodapp.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.foodapp.dao.OrdersDAO;
import com.foodapp.model.Orders;

public class OrdersDAOimpl implements OrdersDAO
{
	 private static Connection con;

		static 
			{
				try
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_project","root","root");

				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
			}

		private PreparedStatement pstmt;
		private ResultSet rs;
		private Statement stmt;
		private int maxoid;
		private ResultSet resultset;
		private Orders uoid;
		private List<Orders> ohlist = new ArrayList<Orders>();

		@Override
		public int insertOrder(Orders o) {
			String query = "INSERT INTO orders (userId, restaurantId, totalAmount, status, paymentOption) VALUES (?, ?, ?, ?, ?)";
			int r = -1;
			
	        try
	        {
	        	pstmt = con.prepareStatement(query);
	            pstmt.setInt(1, o.getUserId());
	            pstmt.setInt(2, o.getRestaurantId());
	            pstmt.setFloat(3, o.getTotalAmount());
	            pstmt.setString(4, o.getStatus());
	            pstmt.setString(5, o.getPaymentOption());
	            r = pstmt.executeUpdate();
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return r;


		}

		@Override
		public Orders fetchOnOrderId(int orderId) {
			 String query = "SELECT * FROM orders WHERE orderId = ?";
			 Orders foid = null;
		        try
		        {
		             pstmt = con.prepareStatement(query);
		            pstmt.setInt(1, orderId);
		            rs = pstmt.executeQuery();
		            if (rs.next()) {
		                foid = new Orders(
		                    rs.getInt("orderId"),
		                    rs.getInt("userId"),
		                    rs.getInt("restaurantId"),
		                    rs.getFloat("totalAmount"),
		                    rs.getString("status"),
		                    rs.getDate("date"),
		                    rs.getString("paymentOption")
		                );
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        return foid;
		}

		@Override
		public int updateOrder(int orderId, String status) {
			String query = "UPDATE orders SET status = ? WHERE orderId = ?";
	        try {
	             pstmt = con.prepareStatement(query); 
	            pstmt.setString(1, status);
	            pstmt.setInt(2, orderId);
	            return pstmt.executeUpdate();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return -1;
		}

		@Override
		public int getMaxOfOrderId() 
		{
			String max = "select max(orderId) from orders";
			try
			{
				stmt = con.createStatement();
				rs = stmt.executeQuery(max);
				if(rs.next())
				{
					maxoid = rs.getInt(1);
					System.out.println(maxoid);
				}
				else
				{
					System.out.println("in else" + maxoid);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return maxoid;
		}

		@Override
		public List<Orders> fetchOnUserId(int id) 
		{
		
			String fetchByUserId = "select * from orders where userId = ?";
			try
			{
				pstmt = con.prepareStatement(fetchByUserId);
				pstmt.setInt(1, id);
				resultset = pstmt.executeQuery();
				 while (resultset.next()) {
		               ohlist.add( new Orders(
		                		resultset.getInt("orderId"),
		                		resultset.getInt("userId"),
		                		resultset.getInt("restaurantId"),
		                		resultset.getFloat("totalAmount"),
		                		resultset.getString("status"),
		                		resultset.getDate("date"),
		                		resultset.getString("paymentOption")));
		            }
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			return ohlist;
		}
		
		

}
