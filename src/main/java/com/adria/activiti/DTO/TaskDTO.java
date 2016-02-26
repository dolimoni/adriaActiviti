package com.adria.activiti.DTO;

public class TaskDTO {
	private String dure;
	private String firstName;
	private String id;
	public String getDure() {
		return dure;
	}
	public void setDure(String dure) {
		this.dure = dure;
	}
	public TaskDTO(String dure, String firstName, String id) {
		super();
		this.dure = dure;
		this.firstName = firstName;
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public TaskDTO(String dure, String firstName) {
		super();
		this.dure = dure;
		this.firstName = firstName;
	}
	public TaskDTO() {
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
