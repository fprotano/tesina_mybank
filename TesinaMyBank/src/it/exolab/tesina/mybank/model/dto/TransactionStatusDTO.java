package it.exolab.tesina.mybank.model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import it.exolab.tesina.mybank.model.TransactionStatus;


public class TransactionStatusDTO extends TransactionStatus{
	private Integer id;
	private String title;
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public TransactionStatusDTO(Integer id, String title) {
		super();
		this.id = id;
		this.title = title;
	}
	public TransactionStatusDTO() {
		super();
	}
	@Override
	public String toString() {
		return "TransactionStatusDTO [id=" + id + ", title=" + title + "]";
	}

	
	
	
	
	
	
	
	
	
}
