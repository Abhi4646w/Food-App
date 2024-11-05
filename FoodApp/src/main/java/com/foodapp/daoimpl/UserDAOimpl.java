package com.foodapp.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.foodapp.dao.UserDAO;
import com.foodapp.model.User;

public class UserDAOimpl implements UserDAO 
{
	private static Connection con;
	ArrayList<User> userlist = new ArrayList<User>();
	private static final String INSERT_QUERY = "insert into user(username,email,password,address,mobile) values(?,?,?,?,?)";
	private static final String GET_ALL_USERS = "select * from user";
	private static final String GET_BY_ID = "select * from user where email =?";
	private static final String DELETE_BY_ID = "delete from user where userId = ?";
	private static final String UPDATE_BY_ID = "update user set password = ? where email = ?";

	static
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_project", "root", "root");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet resultset;

	@Override
	public int insertUser(User user) {
		int x = -1;
		try
		{
		   pstmt = con.prepareStatement(INSERT_QUERY);
		   pstmt.setString(1, user.getUsername());
		   pstmt.setString(2, user.getEmail());
		   pstmt.setString(3, user.getPassword());
		   pstmt.setString(4, user.getAddress());
		   pstmt.setInt(5, user.getMobile());
		   x  = pstmt.executeUpdate();
		   
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return x;
	}

	@Override
	public List<User> getAllUsers() {
		try
		{
			stmt = con.createStatement();
			resultset = stmt.executeQuery(GET_ALL_USERS);
			while(resultset.next())
			{
				userlist.add(new User(resultset.getInt(1), resultset.getString(2),resultset.getString(3),resultset.getString(4),
						resultset.getString(5), resultset.getInt(6),resultset.getString(7)));
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return userlist;
	}

	@Override
	public User getUserId(String email) {
		User temp = null;
		resultset = null;
		try
		{
			pstmt = con.prepareStatement(GET_BY_ID);
			pstmt.setString(1,email);
			resultset = pstmt.executeQuery();
			while(resultset.next())
			{
				 temp = new User(resultset.getInt(1),resultset.getString(2),resultset.getString(3),resultset.getString(4),
							resultset.getString(5),resultset.getInt(6),resultset.getString(7));
			} 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return temp;
	}

	@Override
	public int deleteUserById(int id) {
		
		try
		{
			pstmt = con.prepareStatement(DELETE_BY_ID);
			pstmt.setInt(1, id);
			return  pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int updateUserById(String email, String password) {
		try
		{
			pstmt = con.prepareStatement(UPDATE_BY_ID);
			pstmt.setString(1, password);
			pstmt.setString(2, email);
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return 0;
	}

}
