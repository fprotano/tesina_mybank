package it.exolab.tesina.mybank.model.dto;

import java.sql.Timestamp;



public class HelpCenterDTO {
	
	private Integer id;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private Timestamp closedAt;
	private Integer fromAccountId;
	private String question;
	private Integer assignedToId;
	
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
	
	public HelpCenterDTO() {
		super();
	}
	
	@Override
	public String toString() {
		return "HelpCenterDTO [id=" + id + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", closedAt="
				+ closedAt + ", fromAccountId=" + fromAccountId + ", question=" + question + ", assignedToId="
				+ assignedToId + "]";
	}
	
}
