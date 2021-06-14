package it.exolab.tesina.mybank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="payment")
public class Payment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="amount")
	private Double amount;
	@Column(name="email")
	private String email;
	@Column(name="custom_code")
	private String customCode;
	@Column(name="transaction_id")
	private String transactionId;
	@Column(name="url_undo")
	private String urlUnDo;
	@Column(name="url_success")
	private String urlSuccess;
	@Column(name="url_notify")
	private String urlNotify;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getUrlUnDo() {
		return urlUnDo;
	}
	public void setUrlUnDo(String urlUnDo) {
		this.urlUnDo = urlUnDo;
	}
	public String getUrlSuccess() {
		return urlSuccess;
	}
	public void setUrlSuccess(String urlSuccess) {
		this.urlSuccess = urlSuccess;
	}
	public String getUrlNotify() {
		return urlNotify;
	}
	public void setUrlNotify(String urlNotify) {
		this.urlNotify = urlNotify;
	}
	public Payment(Double amount, String email, String customCode, String urlUnDo, String urlSuccess,
			String urlNotify) {
		super();
		this.amount = amount;
		this.email = email;
		this.customCode = customCode;
		this.urlUnDo = urlUnDo;
		this.urlSuccess = urlSuccess;
		this.urlNotify = urlNotify;
	}
	@Override
	public String toString() {
		return "Payment [id=" + id + ", amount=" + amount + ", email=" + email + ", customCode=" + customCode
				+ ", transactionId=" + transactionId + ", urlUnDo=" + urlUnDo + ", urlSuccess=" + urlSuccess
				+ ", urlNotify=" + urlNotify + "]";
	}
	
	
	
	
	
	

}
