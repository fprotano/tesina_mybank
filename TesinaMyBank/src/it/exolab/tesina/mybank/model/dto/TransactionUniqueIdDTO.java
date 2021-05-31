package it.exolab.tesina.mybank.model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import it.exolab.tesina.mybank.model.TransactionUniqueId;



public class TransactionUniqueIdDTO {
	private String transactionId;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public TransactionUniqueIdDTO() {
		super();
	}

	public TransactionUniqueIdDTO(String transactionId) {
		super();
		this.transactionId = transactionId;
	}

	@Override
	public String toString() {
		return "TransactionUniqueIdDTO [transactionId=" + transactionId + "]";
	}
       
	
	
	}

