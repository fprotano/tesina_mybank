package it.exolab.tesina.mybank.model.dto;

import java.sql.Timestamp;



public class HelpCenterThreadDTO  {
	 
	private Integer id;
	private Timestamp createdAt;
	private Integer helpCenterId;
	private String question;
	private String answer;
	
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
	public Integer getHelpCenterId() {
		return helpCenterId;
	}
	public void setHelpCenterId(Integer helpCenterId) {
		this.helpCenterId = helpCenterId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	@Override
	public String toString() {
		return "HelpCenterThreadDTO [id=" + id + ", createdAt=" + createdAt + ", helpCenterId=" + helpCenterId
				+ ", question=" + question + ", answer=" + answer + "]";
	}
	
}

