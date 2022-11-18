package com.gcu.business;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.gcu.data.OrdersDataService;
import com.gcu.data.entity.OrderEntity;
import com.gcu.model.OrderModel;

public class OrdersBusinessService implements OrdersBusinessServiceInterface
{
	@Autowired
	private OrdersDataService service;
	
	@Override
	public void test()
	{
		System.out.println("Hello from the OrdersBusinessService test() method");
	}

	@Override
	public List<OrderModel> getOrders()
	{
		List<OrderEntity> ordersEntity = service.findAll();
		List<OrderModel> ordersDomain = new ArrayList<OrderModel>();
		
		for(OrderEntity entity : ordersEntity)
		{
			ordersDomain.add(new OrderModel(entity.getId(), entity.getOrderNo(), entity.getProductName(), entity.getPrice(), entity.getQuantity()));
		}
		
		return ordersDomain;
	}
	
	@Override
	public OrderModel getOrderById(String id) {
		OrderEntity orderEntity = service.findById(id);
		return new OrderModel(orderEntity.getId(), orderEntity.getOrderNo(), orderEntity.getProductName(), orderEntity.getPrice(), orderEntity.getQuantity());
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
