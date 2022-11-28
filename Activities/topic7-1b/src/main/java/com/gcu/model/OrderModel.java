package com.gcu.model;

public class OrderModel {

	// class attributes
	private String id;
	private String orderNo;
	private String productName;
	private float price;
	private int quantity;
	
	// constructors
	
	public OrderModel()
	{
		this.id = "";
		this.orderNo = "";
		this.productName = "";
		this.price = 0;
		this.quantity = 0;
	}
	
	public OrderModel(String id, String orderNo, String productName, float price, int quantity)
	{
		this.id = id;
		this.orderNo = orderNo;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
	}

	public String getId()
	{
		return id;
	}

	public String getOrderNo()
	{
		return orderNo;
	}

	public String getProductName()
	{
		return productName;
	}

	public float getPrice()
	{
		return price;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public void setOrderNo(String orderNo)
	{
		this.orderNo = orderNo;
	}

	public void setProductName(String productName)
	{
		this.productName = productName;
	}

	public void setPrice(float price)
	{
		this.price = price;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
	
	
}
