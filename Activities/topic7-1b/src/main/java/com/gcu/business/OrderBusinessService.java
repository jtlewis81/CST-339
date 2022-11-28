package com.gcu.business;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gcu.data.entity.OrderEntity;
import com.gcu.data.repository.OrdersRepository;
import com.gcu.model.OrderModel;

@Service
public class OrderBusinessService
{
	@Autowired
	private OrdersRepository ordersRepository;
	
	public OrderBusinessService(OrdersRepository usersRepository)
	{
		this.ordersRepository = usersRepository;
	}
	
	// return a list of userModel converted from userEntity objects
	public List<OrderModel> getAllOrders()
	{
		List<OrderEntity> ordersEntity = ordersRepository.findAll();
		List<OrderModel> ordersDomain = new ArrayList<OrderModel>();
		for(OrderEntity order : ordersEntity)
		{
			ordersDomain.add(new OrderModel(order.getId(), order.getOrderNo(), order.getProductName(), order.getPrice(), order.getQuantity()));
		}
		return ordersDomain;
	}

}
