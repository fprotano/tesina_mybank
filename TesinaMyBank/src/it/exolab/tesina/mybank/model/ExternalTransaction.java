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
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
@Entity
@Table(name="external_transaction")
public class ExternalTransaction {

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
	@ManyToOne(fetch=FetchType.EAGER,optional=false)
	@JoinColumn(name="transaction_id", nullable=false,insertable=false, updatable=false)
	private TransactionUniqueId transactionUniqueId;
	
	@Column(name="amount")
	private double amount;
	
	@Column(name="to_account_id")
	private Integer toAccountId;
	@Fetch(value=FetchMode.JOIN)
    @ManyToOne(fetch=FetchType.EAGER,optional=false)
    @JoinColumn(name="to_account_id",nullable=false,insertable=false,updatable=false)
	private Account account;
	
	@Column(name="transaction_status_id")
	private Integer transactionStatusId;
	@Fetch(value=FetchMode.JOIN)
	@ManyToOne(fetch=FetchType.EAGER,optional=false)
	@JoinColumn(name="transaction_status_id", nullable=false,insertable=false, updatable=false)
	private TransactionStatus transactionStatus;
	
	@Column(name="transaction_error_reason")
	private String transactionErrorReason;
	
	@Column(name="verify_assigned_to")
	private Integer verifyAssignedTo;
	@Fetch(value=FetchMode.JOIN)
	@ManyToOne(fetch=FetchType.EAGER,optional=false)
	@JoinColumn(name="verify_assigned_to", nullable=false,insertable=false, updatable=false)
	private Staff staff;
	
	@Column(name="customer_name")
	private String customerName;
	
	@Column(name="customer_surname")
	private String customerSurname;
	
	@Column(name="customer_credit_card_no")
	private String customerCreditCardNo;
	
	@Column(name="customer_credit_card_cin")
	private String customerCreditCardCin;
	
	@Column(name="customer_credit_card_expires_at")
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
	
	public TransactionUniqueId getTransactionUniqueId() {
		return transactionUniqueId;
	}
	public void setTransactionUniqueId(TransactionUniqueId transactionUniqueId) {
		this.transactionUniqueId = transactionUniqueId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public Integer getTransactionStatusId() {
		return transactionStatusId;
	}
	public void setTransactionStatusId(Integer transactionStatusId) {
		this.transactionStatusId = transactionStatusId;
	}
	
	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
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
	public ExternalTransaction() {
		super();
	}
	@Override
	public String toString() {
		return "ExternalTransaction [id=" + id + ", createdAt=" + createdAt + ", customCode=" + customCode
				+ ", transactionId=" + transactionId + ", transactionUniqueId=" + transactionUniqueId + ", amount="
				+ amount + ", account=" + account + ", transactionStatusId=" + transactionStatusId
				+ ", transactionStatus=" + transactionStatus + ", transactionErrorReason=" + transactionErrorReason
				+ ", verifyAssignedTo=" + verifyAssignedTo + ", staff=" + staff + ", customerName=" + customerName
				+ ", customerSurname=" + customerSurname + ", customerCreditCardNo=" + customerCreditCardNo
				+ ", customerCreditCardCin=" + customerCreditCardCin + ", customerCreditCardExpiresAt="
				+ customerCreditCardExpiresAt + "]";
	}
	
	
	
}
