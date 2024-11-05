package com.foodapp.dao;

import java.util.List;

import com.foodapp.model.OrderHistory;

public interface OrderHistoryDAO 
{
	List<OrderHistory>fetchOrdersOnUserId(int userid);
	int insertOrderHistory(OrderHistory h);
	int updateOrderHistory(int OrderHistoryId,String status);

}
