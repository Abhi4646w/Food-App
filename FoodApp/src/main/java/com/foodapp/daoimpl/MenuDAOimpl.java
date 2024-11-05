package com.foodapp.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.foodapp.dao.MenuDAO;
import com.foodapp.model.Menu;

public class MenuDAOimpl implements MenuDAO 
{
	private static Connection con;
	private static final String INSERT = "insert into menu values(?,?,?,?,?,?,?)";
	private static final String FETCH_BY_REST_ID = "select * from menu where restaurantId = ?";
	ArrayList<Menu> rlist = new ArrayList<Menu>();
	private static final String FETCH_BY_MENU_ID = "select * from menu where menuId = ?";
    private static final String DELETE = "delete from menu where menuId = ?";
    private static final String UPDATE = "update menu set isAvailable = ? where menuId = ?";
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
	private ResultSet resultset;

	@Override
	public int insertMenu(Menu m) {
		int count = 0;
		try
		{	
				pstmt = con.prepareStatement(INSERT);
				pstmt.setInt(1, m.getMenuId());
				pstmt.setInt(2, m.getRestaurantId());
				pstmt.setString(3, m.getItemName());
				pstmt.setString(4, m.getDescription());
				pstmt.setFloat(5, m.getPrice());
				pstmt.setInt(6, m.getIsAvailable());
				pstmt.setString(7, m.getImgPath());
				count = pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<Menu> getAllRestMenu(int restid) {
		// TODO Auto-generated method stub
		try
		{
			System.out.println(restid);
			pstmt = con.prepareStatement(FETCH_BY_REST_ID);
			pstmt.setInt(1, restid);
			resultset = pstmt.executeQuery();
			while(resultset.next())
			{
				rlist.add(new Menu(resultset.getInt(1),resultset.getInt(2),resultset.getString(3),
						resultset.getString(4),resultset.getFloat(5),resultset.getInt(6),resultset.getString(7),resultset.getInt(8)));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return rlist;
	}

	@Override
	public Menu getMenuById(int id) {
		Menu m = null;
		// TODO Auto-generated method stub
		try
		{
			pstmt = con.prepareStatement(FETCH_BY_MENU_ID);
			pstmt.setInt(1, id);
			resultset = pstmt.executeQuery();
			while(resultset.next())
			{
				m = new Menu(resultset.getInt(1),resultset.getInt(2),resultset.getString(3),
						resultset.getString(4),resultset.getFloat(5),resultset.getInt(6),resultset.getString(7));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return m;
	}

	@Override
	public int deleteMenuById(int id) {
		int temp = -1;
		// TODO Auto-generated method stub
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
	public int updateMenuById(int id, String isAvailable) {
		// TODO Auto-generated method stub
		int up = -1;
		try
		{
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, isAvailable);
			pstmt.setInt(2, id);
			up = pstmt.executeUpdate();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return up;
	}

}
