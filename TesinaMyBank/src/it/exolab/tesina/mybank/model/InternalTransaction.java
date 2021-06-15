package it.exolab.tesina.mybank.model;
 

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
	@Fetch(value=FetchMode.JOIN)
	@OneToOne(fetch=FetchType.EAGER,optional=false)
	@JoinColumn(name="transaction_id", nullable=false,insertable=false, updatable=false)
	private TransactionUniqueId transactionUniqueId;
	
	@Column(name="amount")
	private double amount;
	
	@Column(name="from_account_id")
	private Integer fromAccountId ;
	@Fetch(value=FetchMode.JOIN)
    @ManyToOne(fetch=FetchType.EAGER,optional=false)
    @JoinColumn(name="to_account_id",nullable=false,insertable=false,updatable=false)
	private Account accountFrom;
	
	@Column(name="to_account_id")
	private Integer toAccountId;
	@Fetch(value=FetchMode.JOIN)
    @ManyToOne(fetch=FetchType.EAGER,optional=false)
    @JoinColumn(name="to_account_id",nullable=false,insertable=false,updatable=false)
	private Account accountTo;
	
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
	public Integer getFromAccountId() {
		return fromAccountId;
	} 
	public void setFromAccountId(Integer fromAccountId) {
		this.fromAccountId = fromAccountId;
	}
	public Integer getToAccountId() {
		return toAccountId; 
	}
	public void setToAccountId(Integer toAccountId) { 
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
