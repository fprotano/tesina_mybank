package it.exolab.tesina.mybank.model.dto;

import java.sql.Timestamp;






public class AccountDTO {
	
	private Integer id;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private String iban;
	private Double balance;
	private String email;
	private String password;
	private String name;
	private String surname;
	private Timestamp nextOtpCodeAfterDate;
	private String otpCode;
	private Timestamp otpCodeExpiresAt;
	private String creditCardNo;
	private String creditCardCin;
	private String creditCardExpiresAt;
	
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
		return "AccountDTO [id=" + id + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", iban=" + iban
				+ ", balance=" + balance + ", email=" + email + ", password=" + password + ", name=" + name
				+ ", surname=" + surname + ", nextOtpCodeAfterDate=" + nextOtpCodeAfterDate + ", otpCode=" + otpCode
				+ ", otpCodeExpiresAt=" + otpCodeExpiresAt + ", creditCardNo=" + creditCardNo + ", creditCardCin="
				+ creditCardCin + ", creditCardExpiresAt=" + creditCardExpiresAt + "]";
	}
	
	
}
