package com.foodapp.dao;

import java.sql.ResultSet;
import java.util.List;

import com.foodapp.model.User;

public interface UserDAO
{
	int insertUser(User user);
	List<User> getAllUsers();
	User getUserId(String email);
	int deleteUserById(int id);
	int updateUserById(String email,String password);

}
