package it.exolab.tesina.mybank.model;

public class Payment {
	
	private int id;
	private Double amount;
	private String email, customCode, transactionId, urlAngular, urlServlet, urlBank;
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
	public String getUrlAngular() {
		return urlAngular;
	}
	public void setUrlAngular(String urlAngular) {
		this.urlAngular = urlAngular;
	}
	public String getUrlServlet() {
		return urlServlet;
	}
	public void setUrlServlet(String urlServlet) {
		this.urlServlet = urlServlet;
	}
	public String getUrlBank() {
		return urlBank;
	}
	public void setUrlBank(String urlBank) {
		this.urlBank = urlBank;
	}
	@Override
	public String toString() {
		return "Payment [id=" + id + ", amount=" + amount + ", email=" + email + ", customCode=" + customCode
				+ ", transactionId=" + transactionId + ", urlAngular=" + urlAngular + ", urlServlet=" + urlServlet
				+ ", urlBank=" + urlBank + "]";
	}
	public Payment() {
		super();
	}
	public Payment(Double amount, String email, String customCode, String transactionId, String urlAngular,
			String urlServlet, String urlBank) {
		super();
		this.amount = amount;
		this.email = email;
		this.customCode = customCode;
		this.transactionId = transactionId;
		this.urlAngular = urlAngular;
		this.urlServlet = urlServlet;
		this.urlBank = urlBank;
	}
	public Payment(Double amount, String email, String customCode, String urlAngular, String urlServlet) {
		super();
		this.amount = amount;
		this.email = email;
		this.customCode = customCode;
		this.urlAngular = urlAngular;
		this.urlServlet = urlServlet;
	}
	
	
	
	

}
