package com.adria.activiti.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity

public class Superior {

	@Id
	@GeneratedValue
	private Long id_superior;

	private String username;

	
	public Superior() {
		// TODO Auto-generated constructor stub
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}


	public Long getId_superior() {
		return id_superior;
	}


	public void setId_superior(Long id_superior) {
		this.id_superior = id_superior;
	}



}
