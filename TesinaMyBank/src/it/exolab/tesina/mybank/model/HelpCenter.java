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
@Table(name="help_center")
public class HelpCenter {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="created_at")
	private Timestamp createdAt;
	@Column(name="updated_at")
	private Timestamp updatedAt;
	@Column(name="closed_at")
	private Timestamp closedAt;
	@Column(name="from_account_id")
	private Integer fromAccountId;
	@Fetch(value=FetchMode.JOIN)
	@ManyToOne(fetch=FetchType.EAGER,optional=false)
	@JoinColumn(name="from_account_id", nullable=false,insertable=false, updatable=false)
	private Account account;
	private String question;
	@Column(name="assigned_to_id")
	private Integer assignedToId;
	@Fetch(value=FetchMode.JOIN)
	@ManyToOne(fetch=FetchType.EAGER,optional=false)
	@JoinColumn(name="assigned_to_id", nullable=false,insertable=false, updatable=false)
	private Account accountTo;
	private Staff staff;
	
	
	public Account getAccountTo() {
		return accountTo;
	}
	public void setAccountTo(Account accountTo) {
		this.accountTo = accountTo;
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
	public Timestamp getClosedAt() {
		return closedAt;
	}
	public void setClosedAt(Timestamp closedAt) {
		this.closedAt = closedAt;
	}
	public Integer getFromAccountId() {
		return fromAccountId;
	}
	public void setFromAccountId(Integer fromAccountId) {
		this.fromAccountId = fromAccountId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Integer getAssignedToId() {
		return assignedToId;
	}
	public void setAssignedToId(Integer assignedToId) {
		this.assignedToId = assignedToId;
	}
	
	
	public HelpCenter(Integer id, Timestamp createdAt, Timestamp updatedAt, Timestamp closedAt, Integer fromAccountId,
			Account account, String question, Integer assignedToId, Staff staff) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.closedAt = closedAt;
		this.fromAccountId = fromAccountId;
		this.account = account;
		this.question = question;
		this.assignedToId = assignedToId;
		this.staff = staff;
	}
	
	public HelpCenter() {
		super();
	}
	
	@Override
	public String toString() {
		return "HelpCenter [id=" + id + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", closedAt="
				+ closedAt + ", fromAccountId=" + fromAccountId + ", account=" + account + ", question=" + question
				+ ", assignedToId=" + assignedToId + ", staff=" + staff + "]";
	}
	
	
	
	
	
}
