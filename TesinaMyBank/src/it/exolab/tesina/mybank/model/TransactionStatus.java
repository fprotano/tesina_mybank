package it.exolab.tesina.mybank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class TransactionStatus {
	@Id
	private Integer id;
	@Column(name="title")
	private String title;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public TransactionStatus() {
		super();
	}
	public TransactionStatus(Integer id, String title) {
		super();
		this.id = id;
		this.title = title;
	}
	@Override
	public String toString() {
		return "TransactionStatus [id=" + id + ", title=" + title + "]";
	}
	
	
	
	
	
	
	
	

}
