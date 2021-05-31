package it.exolab.tesina.mybank.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="account")
public class Account {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="created_at")
	private Timestamp createdAt;
	@Column(name="updated_at")
	private Timestamp updatedAt;
	private String iban;
	private Double balance;
	private String email;
	private String password;
	private String name;
	private String surname;
	@Column(name="next_otp_code_after_date")
	private Timestamp nextOtpCodeAfterDate;
	@Column(name="otp_code")
	private String otpCode;
	@Column(name="otp_code_expires_at")
	private Timestamp otpCodeExpiresAt;
	@Column(name="credit_card_no")
	private String creditCardNo;
	@Column(name="credit_card_cid")
	private String creditCardCin;
	@Column(name="credit_card_expires_at")
	private String creditCardExpiresAt;
	
	// OneToMany per HelpCenter
	@OneToMany(mappedBy="account")
//	@Transient
	private List<HelpCenter> helpCenter;
	
	public List<HelpCenter> getHelpCenter() {
		return helpCenter;
	}
	public void setHelpCenter(List<HelpCenter> helpCenter) {
		this.helpCenter = helpCenter;
	}
	
	// OneToMany per ExternalTransaction
	@OneToMany(mappedBy="account")
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
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getIban() {
		return iban;
	}
	public void setIban(String iban) {
		this.iban = iban;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Timestamp getNextOtpCodeAfterDate() {
		return nextOtpCodeAfterDate;
	}
	public void setNextOtpCodeAfterDate(Timestamp nextOtpCodeAfterDate) {
		this.nextOtpCodeAfterDate = nextOtpCodeAfterDate;
	}
	public String getOtpCode() {
		return otpCode;
	}
	public void setOtpCode(String otpCode) {
		this.otpCode = otpCode;
	}
	public Timestamp getOtpCodeExpiresAt() {
		return otpCodeExpiresAt;
	}
	public void setOtpCodeExpiresAt(Timestamp otpCodeExpiresAt) {
		this.otpCodeExpiresAt = otpCodeExpiresAt;
	}
	public String getCreditCardNo() {
		return creditCardNo;
	}
	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}
	public String getCreditCardCin() {
		return creditCardCin;
	}
	public void setCreditCardCin(String creditCardCin) {
		this.creditCardCin = creditCardCin;
	}
	public String getCreditCardExpiresAt() {
		return creditCardExpiresAt;
	}
	public void setCreditCardExpiresAt(String creditCardExpiresAt) {
		this.creditCardExpiresAt = creditCardExpiresAt;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", iban=" + iban
				+ ", balance=" + balance + ", email=" + email + ", password=" + password + ", name=" + name
				+ ", surname=" + surname + ", nextOtpCodeAfterDate=" + nextOtpCodeAfterDate + ", otpCode=" + otpCode
				+ ", otpCodeExpiresAt=" + otpCodeExpiresAt + ", creditCardNo=" + creditCardNo + ", creditCardCin="
				+ creditCardCin + ", creditCardExpiresAt=" + creditCardExpiresAt + "]";
	}
	public Account() {
		super();
	}
	
}
