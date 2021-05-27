package it.exolab.tesina.mybank.model;

import java.sql.Timestamp;

public class ExternalTransaction {

	private int id;
	private Timestamp createdAt;
	private String customCode;
	private String transactionId;
	private double amount;
	private int toAccountId;
}
