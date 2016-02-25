package com.adria.activiti.DTO;

public class DemandesDTO {

	private String userId;
	private String creationDate;
	private String dure;
	private String status;
	public DemandesDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public DemandesDTO(String creationDate, String dure) {
		super();
		this.creationDate = creationDate;
		this.dure = dure;
	}

	
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getDure() {
		return dure;
	}
	public void setDure(String dure) {
		this.dure = dure;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
