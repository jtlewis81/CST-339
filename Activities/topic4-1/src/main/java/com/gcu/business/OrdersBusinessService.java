package com.gcu.business;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.gcu.data.DataAccessInterface;
import com.gcu.model.OrderModel;

public class OrdersBusinessService implements OrdersBusinessServiceInterface
{
	@Autowired
	private DataAccessInterface<OrderModel> service;
	
	@Override
	public void test()
	{
		System.out.println("Hello from the OrdersBusinessService test() method");
	}

	@Override
	public List<OrderModel> getOrders()
	{
		return service.findAll();
	}

	@Override
	public void init()
	{
		System.out.println("Hello from the OrdersBusinessService init() method");
		
	}

	@Override
	public void destroy()
	{
		System.out.println("Hello from the OrdersBusinessService destroy() method");
		
	}

}
