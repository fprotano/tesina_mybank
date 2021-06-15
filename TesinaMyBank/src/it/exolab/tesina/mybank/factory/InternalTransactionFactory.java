package it.exolab.tesina.mybank.factory;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;



import it.exolab.tesina.mybank.model.Account;
import it.exolab.tesina.mybank.model.InternalTransaction;
import it.exolab.tesina.mybank.model.Payment;

public class InternalTransactionFactory {

	
	public InternalTransaction fillInternalTransaction(InternalTransaction internalTransaction, Payment payment, Account account){
		
		if(payment.getAmount()<=account.getBalance()) {
		internalTransaction.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
		internalTransaction.setCustomCode(payment.getCustomCode());
		internalTransaction.setTransactionId(payment.getTransactionId());
		internalTransaction.setAmount(payment.getAmount());
		internalTransaction.setFromAccountId(account.getId());
		internalTransaction.setToAccountId(2);
		}
		return internalTransaction;
		
	}
}
