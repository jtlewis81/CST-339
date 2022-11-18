package com.gcu.data;

import java.util.List;
import com.gcu.data.entity.OrderEntity;

public interface DataAccessInterface <T>
{
	public List<T> findAll();
	public OrderEntity findById(String id);
	public boolean create(T t);
	public boolean update(T t);
	public boolean delete(T t);
}
