package it.exolab.tesina.mybank.model.dto;

import java.sql.Timestamp;





public class ExternalTransactionDTO {
	
	private Integer id;
	private Timestamp createdAt;
	private String customCode;
	private String transactionId;
	private double amount;
	private Integer toAccountId;
	private Integer transactionStatusId;
	private String transactionErrorReason;
	private Integer verifyAssignedTo;
	private String customerName;
	private String customerSurname;
	private String customerCreditCardNo;
	private String customerCreditCardCin;
	private String customerCreditCardExpiresAt;
	
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
	public Integer getToAccountId() {
		return toAccountId;
	}
	public void setToAccountId(Integer toAccountId) {
		this.toAccountId = toAccountId;
	}
	public Integer getTransactionStatusId() {
		return transactionStatusId;
	}
	public void setTransactionStatusId(Integer transactionStatusId) {
		this.transactionStatusId = transactionStatusId;
	}
	public String getTransactionErrorReason() {
		return transactionErrorReason;
	}
	public void setTransactionErrorReason(String transactionErrorReason) {
		this.transactionErrorReason = transactionErrorReason;
	}
	public Integer getVerifyAssignedTo() {
		return verifyAssignedTo;
	}
	public void setVerifyAssignedTo(Integer verifyAssignedTo) {
		this.verifyAssignedTo = verifyAssignedTo;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerSurname() {
		return customerSurname;
	}
	public void setCustomerSurname(String customerSurname) {
		this.customerSurname = customerSurname;
	}
	public String getCustomerCreditCardNo() {
		return customerCreditCardNo;
	}
	public void setCustomerCreditCardNo(String customerCreditCardNo) {
		this.customerCreditCardNo = customerCreditCardNo;
	}
	public String getCustomerCreditCardCin() {
		return customerCreditCardCin;
	}
	public void setCustomerCreditCardCin(String customerCreditCardCin) {
		this.customerCreditCardCin = customerCreditCardCin;
	}
	public String getCustomerCreditCardExpiresAt() {
		return customerCreditCardExpiresAt;
	}
	public void setCustomerCreditCardExpiresAt(String customerCreditCardExpiresAt) {
		this.customerCreditCardExpiresAt = customerCreditCardExpiresAt;
	}
	@Override
	public String toString() {
		return "ExternalTransactionDTO [id=" + id + ", createdAt=" + createdAt + ", customCode=" + customCode
				+ ", transactionId=" + transactionId + ", amount=" + amount + ", toAccountId=" + toAccountId
				+ ", transactionStatusId=" + transactionStatusId + ", transactionErrorReason=" + transactionErrorReason
				+ ", verifyAssignedTo=" + verifyAssignedTo + ", customerName=" + customerName + ", customerSurname="
				+ customerSurname + ", customerCreditCardNo=" + customerCreditCardNo + ", customerCreditCardCin="
				+ customerCreditCardCin + ", customerCreditCardExpiresAt=" + customerCreditCardExpiresAt + "]";
	}
	
	
	
}
