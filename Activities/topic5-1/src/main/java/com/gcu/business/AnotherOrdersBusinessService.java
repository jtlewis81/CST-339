package com.gcu.business;

import java.util.ArrayList;
import java.util.List;
import com.gcu.model.OrderModel;

public class AnotherOrdersBusinessService implements OrdersBusinessServiceInterface {

	@Override
	public void test() {
		System.out.println("Hello from the AnotherOrdersBusinessService test() method");
	}

	@Override
	public List<OrderModel> getOrders() {
		
		List<OrderModel> orders = new ArrayList<OrderModel>();
		orders.add(new OrderModel("0", "00000006", "Item 1", 1.00f, 10));
		orders.add(new OrderModel("1", "00000007", "Item 2", 2.00f, 20));
		orders.add(new OrderModel("2", "00000008", "Item 3", 3.00f, 30));
		orders.add(new OrderModel("3", "00000009", "Item 4", 4.00f, 40));
		orders.add(new OrderModel("4", "00000010", "Item 5", 5.00f, 50));
		
		
		return null;
	}

	@Override
	public OrderModel getOrderById(String id) {
		return null;
	}
	
	@Override
	public void init() {
		System.out.println("Hello from the AnotherOrdersBusinessService init() method");
	}

	@Override
	public void destroy() {
		System.out.println("Hello from the AnotherOrdersBusinessService destroy() method");		
	}


}
