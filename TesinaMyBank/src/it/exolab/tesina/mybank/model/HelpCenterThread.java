package it.exolab.tesina.mybank.model;

import java.sql.Timestamp;

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
	private Timestamp created_at;
	private Integer help_center_id;
	private String question;
	private String answer;

//	@Fetch(value=FetchMode.JOIN)
//    @ManyToOne(fetch=FetchType.EAGER,optional=false)
//    @JoinColumn(name="help_center_id",nullable=false,insertable=false,updatable=false)
//	private Help_center help_center;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	public Integer getHelp_center_id() {
		return help_center_id;
	}
	public void setHelp_center_id(Integer help_center_id) {
		this.help_center_id = help_center_id;
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
	public HelpCenterThread(Integer id, Timestamp created_at, Integer help_center_id, String question,
			String answer) {
		this.id = id;
		this.created_at = created_at;
		this.help_center_id = help_center_id;
		this.question = question;
		this.answer = answer;
	}
	
	public HelpCenterThread() {
		
	}
	
	
}
