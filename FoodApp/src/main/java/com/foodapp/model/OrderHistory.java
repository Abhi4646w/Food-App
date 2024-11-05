package com.foodapp.model;

import java.sql.Date;

public class OrderHistory 
{
	private int orderHistoryId;
	private int userId;
	private int orderId;
	private Date date;
	private float total;
	private String status;
	public int getOrderHistoryId() {
		return orderHistoryId;
	}
	public void setOrderHistoryId(int orderHistoryId) {
		this.orderHistoryId = orderHistoryId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public OrderHistory(int orderHistoryId, int userId, int orderId, Date date2, float total, String status) {
		super();
		this.orderHistoryId = orderHistoryId;
		this.userId = userId;
		this.orderId = orderId;
		this.date = date2;
		this.total = total;
		this.status = status;
	}
	public OrderHistory(int userId, int orderId, float total, String status) {
		super();
		this.userId = userId;
		this.orderId = orderId;
		this.total = total;
		this.status = status;
	}
	public OrderHistory() {
		super();
	}
	@Override
	public String toString() {
		return "OrderHistory [orderHistoryId=" + orderHistoryId + ", userId=" + userId + ", orderId=" + orderId
				+ ", date=" + date + ", total=" + total + ", status=" + status + "]";
	}
	

}
