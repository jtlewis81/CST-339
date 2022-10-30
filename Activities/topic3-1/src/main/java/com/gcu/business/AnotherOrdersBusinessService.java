package com.gcu.business;

import java.util.ArrayList;
import java.util.List;
import com.gcu.model.OrderModel;

public class AnotherOrdersBusinessService implements OrdersBusinessServiceInterface {

	public void test() {
		System.out.println("Hello from the AnotherOrdersBusinessService");
	}

	@Override
	public List<OrderModel> getOrders() {
		
		List<OrderModel> orders = new ArrayList<OrderModel>();
		orders.add(new OrderModel(0L, "00000006", "Item 1", 1.00f, 10));
		orders.add(new OrderModel(1L, "00000007", "Item 2", 2.00f, 20));
		orders.add(new OrderModel(2L, "00000008", "Item 3", 3.00f, 30));
		orders.add(new OrderModel(3L, "00000009", "Item 4", 4.00f, 40));
		orders.add(new OrderModel(4L, "00000010", "Item 5", 5.00f, 50));
		
		
		return null;
	}

}
