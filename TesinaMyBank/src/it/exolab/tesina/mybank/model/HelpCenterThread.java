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
@Table(name="help_center_thread")
public class HelpCenterThread {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="created_at")
	private Timestamp createdAt;
	@Column(name="help_center_id")
	private Integer helpCenterId;
	private String question;
	private String answer;

	
//	@Fetch(value=FetchMode.JOIN)
//    @ManyToOne(fetch=FetchType.EAGER,optional=false)
//    @JoinColumn(name="help_center_id",nullable=false,insertable=false,updatable=false)
	private HelpCenter helpCenter;
	
	
	public HelpCenterThread() {
		
	}

	
	


	public HelpCenter getHelpCenter() {
		return helpCenter;
	}





	public void setHelpCenter(HelpCenter helpCenter) {
		this.helpCenter = helpCenter;
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


	public HelpCenterThread(Integer id, Timestamp createdAt, Integer helpCenterId, String question, String answer) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.helpCenterId = helpCenterId;
		this.question = question;
		this.answer = answer;
	}
	
	
}
