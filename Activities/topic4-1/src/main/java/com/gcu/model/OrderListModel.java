package com.gcu.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="orders")
public class OrderListModel {
	
	private List<OrderModel> orders = new ArrayList<OrderModel>();
	
	@XmlElement
	public List<OrderModel> getOrders()
	{
		return this.orders;
	}
	
	public void setOrders(List<OrderModel> orders)
	{
		this.orders = orders;
	}
}
