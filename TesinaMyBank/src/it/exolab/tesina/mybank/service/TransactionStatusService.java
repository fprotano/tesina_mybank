package it.exolab.tesina.mybank.service;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;

import it.exolab.tesina.mybank.model.ExternalTransaction;
import it.exolab.tesina.mybank.model.TransactionStatus;
import it.exolab.tesina.mybank.model.dto.TransactionStatusDTO;
import it.exolab.tesina.mybank.repository.ExternalTransactionRepository;
import it.exolab.tesina.mybank.repository.TransactionStatusRepository;

public class TransactionStatusService {

private TransactionStatusRepository transactionStatusRepository;
	
	@Autowired(required=true)
	public void setTransactionStatusRepository(TransactionStatusRepository transactionStatusRepository) {
		this.transactionStatusRepository=transactionStatusRepository;
	}
	
	public List<TransactionStatus> findAll(){
		return (List<TransactionStatus>) transactionStatusRepository.findAll();	
	}
	
	public void insert(TransactionStatus transactionStatus) {
		transactionStatusRepository.save(transactionStatus);
	}
	
	public void update(TransactionStatus transactionStatus) {
		transactionStatusRepository.save(transactionStatus);
	}
	
	
	
	public void delete(int id) {
		transactionStatusRepository.delete(id);
	}
	
	
	
	public TransactionStatus find(int id) {
		return transactionStatusRepository.findOne(id);
	}
}
