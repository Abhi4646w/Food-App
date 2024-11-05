package com.foodapp.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.foodapp.dao.RestaurantDAO;
import com.foodapp.model.Restaurant;

public class RestaurantDAOimpl implements RestaurantDAO
{
	private static Scanner scan1 = new Scanner(System.in);
	private static Connection con;
	private static final String INSERT_QUERY = "insert into restaurant values(?,?,?,?,?,?,?)";
	private static final String FETCH_ALL = "select * from restaurant";
	ArrayList<Restaurant> restaurantlist = new ArrayList<Restaurant>();
	private static final String GET_REST_BY_ID = "select * from restaurant where restaurantId = ?";
	private static final String DELETE = "delete from restaurant where restaurantId = ?";
	private static final String UPDATE = "update restaurant set isActive = ? where restaurantId = ?";

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
	private Statement stmt;
	private ResultSet resultset;

	@Override
	public int insertRestaurant(Restaurant rest) {
		int inst = -1;
		try
		{
			pstmt = con.prepareStatement(INSERT_QUERY);
			pstmt.setInt(1, rest.getRestaurantId());
			pstmt.setString(2, rest.getName());
			pstmt.setString(3, rest.getCuisiveType());
			pstmt.setInt(4, rest.getDeliveryTime());
			pstmt.setString(5, rest.getIsActive());
			pstmt.setInt(6, rest.getRatings());
			pstmt.setString(7, rest.getImgPath());
			inst = pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			
		}
		
		return inst;
	}

	@Override
	public List<Restaurant> getAllRest() {
		try
		{
			stmt = con.createStatement();
			resultset = stmt.executeQuery(FETCH_ALL);
			while(resultset.next())
			{
				restaurantlist.add(new Restaurant(resultset.getInt(1),resultset.getString(2),resultset.getString(3),resultset.getInt(4)
						,resultset.getString(5),resultset.getInt(6),resultset.getString(7)));
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return restaurantlist;
	}

	@Override
	public Restaurant getRestById(int id) {
		Restaurant gid = null;
		try
		{
			pstmt = con.prepareStatement(GET_REST_BY_ID);
			pstmt.setInt(1, id);
			resultset= pstmt.executeQuery();
			if(resultset.next())
			{
				gid = new Restaurant(resultset.getInt(1),resultset.getString(2),resultset.getString(3),resultset.getInt(4)
						,resultset.getString(5),resultset.getInt(6),resultset.getString(7));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return gid;
	}

	@Override
	public int deleteRestById(int id) {
		int temp = -1;
		try
		{
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, id);
			temp = pstmt.executeUpdate();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public int updateRestById(int id,String isActive) {
		int temp2 = -1;
		try
		{
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, isActive);
			pstmt.setInt(2, id);
			temp2 = pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return temp2;
	}

}
