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
@Table(name="staff")
public class Staff {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="created_at")
	private Timestamp createdAt;
	@Column(name="updated_at")
	private Timestamp updatedAt;
	private String email;
	private String password;
	private String name;
	private String surname;
	@Column(name="role_id")
	private Integer roleId;
	@Column(name="next_otp_code_after_date")
	private Timestamp nextOtpCodeAfterDate;
	@Column(name="otp_code")
	private String otpCode;
	@Column(name="otp_code_expires_at")
	private Timestamp otpCodeExpiresAt;
	
	
	
	@Fetch(value=FetchMode.JOIN)
	@ManyToOne(fetch=FetchType.EAGER,optional=false)
	@JoinColumn(name="role_id", nullable=false,insertable=false, updatable=false)
	//@Transient
	private Role role;
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
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
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
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
	@Override
	public String toString() {
		return "Staff [id=" + id + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", email=" + email
				+ ", password=" + password + ", name=" + name + ", surname=" + surname + ", roleId=" + roleId
				+ ", nextOtpCodeAfterDate=" + nextOtpCodeAfterDate + ", otpCode=" + otpCode + ", otpCodeExpiresAt="
				+ otpCodeExpiresAt + "]";
	}
	public Staff() {
		super();
	}
	
}
