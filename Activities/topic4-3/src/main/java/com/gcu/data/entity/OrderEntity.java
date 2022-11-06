package com.gcu.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("ORDERS")
public class OrderEntity {

	@Id
	private Long id;
	
	@Column("ORDER_NO")
	private String orderNo;
	
	@Column("PRODUCT_NAME")
	private String productName;
	
	@Column("PRICE")
	private float price;
	
	@Column("QUANTITY")
	private int quantity;
	
	// Default Constructor
	public OrderEntity() { }
	
	// Non-Default Constructor
	public OrderEntity(Long id, String orderNo, String productName, float price, int quantity)
	{
		this.id = id;
		this.orderNo = orderNo;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
	}
	
	// Getters
	public Long getId() { return id; }
	public String getOrderNo() { return orderNo; }
	public String getProductName() { return productName; }
	public float getPrice() { return price; }
	public int getQuantity() { return quantity; }
	
	// Setters
	public void setId(Long id) { this.id = id; }
	public void setOrderNo(String orderNo) { this.orderNo = orderNo; }
	public void setProductName(String productName) { this.productName = productName; }
	public void setPrice(float price) { this.price = price; }
	public void setQuantity(int quantity) { this.quantity = quantity; }
	
}
