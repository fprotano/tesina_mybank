package it.exolab.tesina.mybank.model;

import java.sql.Timestamp;

public class ExternalTransaction {

	private int id;
	private Timestamp createdAt;
	private String customCode;
	private String transactionId;
	private double amount;
	private int toAccountId;
	private int transactionStatusId;
	private String transactionErrorReason;
	private int verifyAssignedTo;
	private String customerName;
	private String customerSurname;
	private String customerCreditCardNo;
	private String customerCreditCardCin;
	private String customerCreditCardExpiresAt;
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public int getToAccountId() {
		return toAccountId;
	}
	public void setToAccountId(int toAccountId) {
		this.toAccountId = toAccountId;
	}
	public int getTransactionStatusId() {
		return transactionStatusId;
	}
	public void setTransactionStatusId(int transactionStatusId) {
		this.transactionStatusId = transactionStatusId;
	}
	public String getTransactionErrorReason() {
		return transactionErrorReason;
	}
	public void setTransactionErrorReason(String transactionErrorReason) {
		this.transactionErrorReason = transactionErrorReason;
	}
	public int getVerifyAssignedTo() {
		return verifyAssignedTo;
	}
	public void setVerifyAssignedTo(int verifyAssignedTo) {
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
	public ExternalTransaction() {
		super();
	}
	@Override
	public String toString() {
		return "ExternalTransaction [id=" + id + ", createdAt=" + createdAt + ", customCode=" + customCode
				+ ", transactionId=" + transactionId + ", amount=" + amount + ", toAccountId=" + toAccountId
				+ ", transactionStatusId=" + transactionStatusId + ", transactionErrorReason=" + transactionErrorReason
				+ ", verifyAssignedTo=" + verifyAssignedTo + ", customerName=" + customerName + ", customerSurname="
				+ customerSurname + ", customerCreditCardNo=" + customerCreditCardNo + ", customerCreditCardCin="
				+ customerCreditCardCin + ", customerCreditCardExpiresAt=" + customerCreditCardExpiresAt + "]";
	}
	
	
	
}
