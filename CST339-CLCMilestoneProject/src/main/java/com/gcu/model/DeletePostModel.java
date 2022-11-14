package com.gcu.model;

public class DeletePostModel 
{
	private int Id; 
	
	public DeletePostModel() {}
	
	public DeletePostModel(int id) {
		Id = id;
	}
	
	public int getId() { return Id; }
	
	public void setId(int id) { Id = id; }
}
