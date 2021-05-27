package it.exolab.tesina.mybank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Faq {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
@Column(name="question")
private String question;
@Column(name="answer")
private String answer;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
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
public Faq(int id, String question, String answer) {
	super();
	this.id = id;
	this.question = question;
	this.answer = answer;
}
public Faq() {
	super();
}
@Override
public String toString() {
	return "Faq [id=" + id + ", question=" + question + ", answer=" + answer + "]";
}

	
	
	
	
	
}
