package com.adria.activiti.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Demande {

	 	public Demande(Long id, Integer dure, Date creationDate, String status, Person person) {
		super();
		this.id = id;
		this.dure = dure;
		this.creationDate = creationDate;
		this.status = status;
		this.person = person;
	}
		@Id
	    @GeneratedValue
	    private Long id;
	 	
	 	private Integer dure;
	 	private Date creationDate;
	 	private String status;
	 	
	 	@ManyToOne
	 	@JoinColumn(name="id_person")
	 	private Person person;
	 	public Demande() {
			// TODO Auto-generated constructor stub
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Integer getDure() {
			return dure;
		}
		public void setDure(Integer dure) {
			this.dure = dure;
		}
		public Date getCreationDate() {
			return creationDate;
		}
		public void setCreationDate(Date creationDate) {
			this.creationDate = creationDate;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public Person getPerson() {
			return person;
		}
		public void setPerson(Person person) {
			this.person = person;
		}

}
