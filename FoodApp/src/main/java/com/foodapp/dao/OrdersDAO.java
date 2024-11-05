package com.foodapp.dao;

import java.util.List;

import com.foodapp.model.Orders;

public interface OrdersDAO 
{
	int insertOrder(Orders o);
    Orders fetchOnOrderId(int orderId);
    int updateOrder(int orderId,String status);
    int getMaxOfOrderId();
    List<Orders> fetchOnUserId(int id);
}
