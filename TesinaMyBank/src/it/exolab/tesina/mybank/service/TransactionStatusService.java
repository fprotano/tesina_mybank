package it.exolab.tesina.mybank.service;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;

import it.exolab.tesina.mybank.model.ExternalTransaction;
import it.exolab.tesina.mybank.model.dto.TransactionStatusDTO;
import it.exolab.tesina.mybank.repository.ExternalTransactionRepository;
import it.exolab.tesina.mybank.repository.TransactionStatusRepository;

public class TransactionStatusService {

private TransactionStatusRepository transactionStatusRepository;
	
	@Autowired(required=true)
	public void setTransactionStatusRepository(TransactionStatusRepository transactionStatusRepository) {
		this.transactionStatusRepository=transactionStatusRepository;
	}
	
	public List<TransactionStatusDTO> findAll(){
		return (List<TransactionStatusDTO>) transactionStatusRepository.findAll();	
	}
	
	public void insert(TransactionStatusDTO transactionStatus) {
		transactionStatusRepository.save(transactionStatus);
	}
	
	public void update(TransactionStatusDTO transactionStatus) {
		transactionStatusRepository.save(transactionStatus);
	}
	
	
	
	public void delete(int id) {
		transactionStatusRepository.delete(id);
	}
	
	
	
	public TransactionStatusDTO find(int id) {
		return transactionStatusRepository.findOne(id);
	}
}
