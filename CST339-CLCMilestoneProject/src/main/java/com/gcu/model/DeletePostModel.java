package com.gcu.model;

public class DeletePostModel
{
	private int id; 
	
	public DeletePostModel() {}
	
	public DeletePostModel(int id) {
		this.id = id;
	}
	
	public int getId() { return id; }
	
	public void setId(int id) { this.id = id; }
}
