package com.foodapp.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.foodapp.dao.OrderHistoryDAO;
import com.foodapp.model.OrderHistory;

public class OrderHistoryDAOimpl implements OrderHistoryDAO 
{
	 private static Connection con;
		
		private PreparedStatement pstmt;
		private ResultSet resultset;
		 private static final String INSERT = "insert into orderhistory(userId,orderId,total,status) values(?,?,?,?)";
		 private static final String FETCH_BY_USERID = "select * from orderhistory where userId = ?";
		 private static final String UPDATE = "update orderhistory set status = ? where orderHistoryId = ?";
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

		@Override
		public List<OrderHistory> fetchOrdersOnUserId(int userid) {
			List<OrderHistory> orderHistorylist = new ArrayList<>();
			try
			{
				pstmt = con.prepareStatement(FETCH_BY_USERID);
				pstmt.setInt(1, userid);
				resultset = pstmt.executeQuery();
				while(resultset.next())
				{
					orderHistorylist.add(new OrderHistory(resultset.getInt(1),resultset.getInt(2),resultset.getInt(3),
							resultset.getDate(4),resultset.getFloat(5),resultset.getString(6)));
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			 

			return orderHistorylist;
		}

		@Override
		public int insertOrderHistory(OrderHistory h) {
			// TODO Auto-generated method stub
			int x=-1;
			try
			{
				pstmt = con.prepareStatement(INSERT);
				pstmt.setInt(1, h.getUserId());
				pstmt.setInt(2, h.getOrderId());
				pstmt.setFloat(3, h.getTotal());
				pstmt.setString(4, h.getStatus());
				x = pstmt.executeUpdate();
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			 

			return x;
		}

		@Override
		public int updateOrderHistory(int OrderHistoryId, String status) {
			int update = -1;
			try
			{
				pstmt = con.prepareStatement(UPDATE);
				pstmt.setString(1, status);
				pstmt.setInt(2, OrderHistoryId);
				update = pstmt.executeUpdate();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			 
			return update;
		}

}
