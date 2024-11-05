package com.foodapp.model;

public class OrderItems 
{
	private int orderItemId;
	private int orderId;
	private int menuId;
	private int quantity;
	private double subTotal;
	public int getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
	}
	public OrderItems(int orderItemId, int orderId, int menuId, int quantity, double subTotal) {
		super();
		this.orderItemId = orderItemId;
		this.orderId = orderId;
		this.menuId = menuId;
		this.quantity = quantity;
		this.subTotal = subTotal;
	}
	public OrderItems(int orderId, int menuId, int quantity, double subTotal) {
		super();
		this.orderId = orderId;
		this.menuId = menuId;
		this.quantity = quantity;
		this.subTotal = subTotal;
	}
	public OrderItems() {
		super();
	}
	@Override
	public String toString() {
		return "OrderItems [orderItemId=" + orderItemId + ", orderId=" + orderId + ", menuId=" + menuId + ", quantity="
				+ quantity + ", subTotal=" + subTotal + "]";
	}
	

}
