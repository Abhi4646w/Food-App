package com.foodapp.dao;

import java.util.List;

import com.foodapp.model.OrderItems;

public interface OrderItemsDAO 
{
	int insertOrderItems(OrderItems o);
	List<OrderItems> fetchOrderId(int OrderId);

}
