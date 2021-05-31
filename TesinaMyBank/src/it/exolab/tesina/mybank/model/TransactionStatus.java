package it.exolab.tesina.mybank.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
 
@Entity
@Table
public class TransactionStatus {
	@Id
	private Integer id;
	@Column(name="title")
	private String title;
	
	// OneToMany per ExternalTransaction
	@OneToMany(mappedBy="transactionStatus")
	private List<ExternalTransaction> externaltransaction;
	
	public List<ExternalTransaction> getExternaltransaction() {
		return externaltransaction;
	}
	public void setExternaltransaction(List<ExternalTransaction> externaltransaction) {
		this.externaltransaction = externaltransaction;
	}
	
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
