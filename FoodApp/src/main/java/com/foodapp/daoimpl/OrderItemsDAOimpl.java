package com.foodapp.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.foodapp.dao.OrderItemsDAO;
import com.foodapp.model.OrderItems;

public class OrderItemsDAOimpl implements OrderItemsDAO {

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

	private PreparedStatement ps = null;

	@Override
	public int insertOrderItems(OrderItems o) {
		 String query = "INSERT INTO orderitems (orderItemsId, orderId, menuId, quantity, subTotal) VALUES (?, ?, ?, ?, ?)";
	        try  {
	        	ps = con.prepareStatement(query);
	            ps.setInt(1, o.getOrderItemId());
	            ps.setInt(2, o.getOrderId());
	            ps.setInt(3, o.getMenuId());
	            ps.setInt(4, o.getQuantity());
	            ps.setDouble(5, o.getSubTotal());
	            return ps.executeUpdate();
	        } catch (Exception e) {
	            e.printStackTrace();
	            return 0;
	        }

	}

	@Override
	public List<OrderItems> fetchOrderId(int OrderId) {
		ArrayList<OrderItems> ods = new ArrayList<OrderItems>();
		 String query = "SELECT * FROM orderitems WHERE orderId = ?";
	        try {
	        	ps = con.prepareStatement(query);
	            ps.setInt(1, OrderId);
	            ResultSet rs = ps.executeQuery();
	            while(rs.next()) {
	                ods.add(new OrderItems(
	                    rs.getInt("orderItemsId"),
	                    rs.getInt("orderId"),
	                    rs.getInt("menuId"),
	                    rs.getInt("quantity"),
	                    rs.getFloat("subTotal")
	                ));
	            }
	            
	      } 
	            catch (Exception e) {
	            e.printStackTrace();
	        }
	        return ods;
	    }
}
