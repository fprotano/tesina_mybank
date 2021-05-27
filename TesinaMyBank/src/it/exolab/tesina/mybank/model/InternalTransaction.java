package it.exolab.tesina.mybank.model;

import java.security.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="internal_transaction")
public class InternalTransaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="created_at")
	private Timestamp createdAt;
	@Column(name="custom_code")
	private String customCode;
	@Column(name="transaction_id")
	private String transactionId;
	private double amount;
	@Column(name="from_account_id")
	private int fromAccountId;
	@Column(name="to_account_id")
	private int toAccountId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public String getCustomCode() {
		return customCode;
	}
	public void setCustomCode(String customCode) {
		this.customCode = customCode;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getFromAccountId() {
		return fromAccountId;
	} 
	public void setFromAccountId(int fromAccountId) {
		this.fromAccountId = fromAccountId;
	}
	public int getToAccountId() {
		return toAccountId; 
	}
	public void setToAccountId(int toAccountId) { 
		this.toAccountId = toAccountId;
	}
	
	@Override
	public String toString() {
		return "InternalTransaction [id=" + id + ", createdAt=" + createdAt + ", customCode=" + customCode
				+ ", transactionId=" + transactionId + ", amount=" + amount + ", fromAccountId=" + fromAccountId
				+ ", toAccountId=" + toAccountId + "]";
	}
	
	public InternalTransaction(){
		super();
	}
	
	
	
	

}
