package com.gcu.business;

import java.util.ArrayList;
import java.util.List;

import com.gcu.model.OrderModel;

public class OrdersBusinessService implements OrdersBusinessServiceInterface {

	public void test() {
		System.out.println("Hello from the OrdersBusinessService");
	}

	@Override
	public List<OrderModel> getOrders() {

		List<OrderModel> orders = new ArrayList<OrderModel>();
		orders.add(new OrderModel(0L, "00000001", "Item 1", 1.00f, 1));
		orders.add(new OrderModel(1L, "00000002", "Item 2", 2.00f, 2));
		orders.add(new OrderModel(2L, "00000003", "Item 3", 3.00f, 3));
		orders.add(new OrderModel(3L, "00000004", "Item 4", 4.00f, 4));
		orders.add(new OrderModel(4L, "00000005", "Item 5", 5.00f, 5));
		
		
		return orders;
	}

}
