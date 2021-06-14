package it.exolab.tesina.mybank.model;

public class Payment {
	
	private int id;
	private Double amount;
	private String email, customCode, transactionId, urlUnDo, urlSuccess, urlNotify;
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
