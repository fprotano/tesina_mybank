package it.exolab.tesina.mybank.service;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;

import it.exolab.tesina.mybank.model.TransactionUniqueId;
import it.exolab.tesina.mybank.model.dto.TransactionUniqueIdDTO;
import it.exolab.tesina.mybank.repository.TransactionUniqueIdRepository;



public class TransactionUniqueIdService {
	
private TransactionUniqueIdRepository transactionUniqueIdRepository;
	
	@Autowired
	public void setTransactionUniqueIdRepository(TransactionUniqueIdRepository transactionUniqueIdRepository) {
		this.transactionUniqueIdRepository = transactionUniqueIdRepository;
	
	}
	public List<TransactionUniqueId> findAll(){
		return (List<TransactionUniqueId>)this.transactionUniqueIdRepository.findAll();
	}
	public void insert(TransactionUniqueId model) {
		this.transactionUniqueIdRepository.save(model);
	}
	public void update(TransactionUniqueId model) {
		this.transactionUniqueIdRepository.save(model);
	}
	public void delete(int id) {
		this.transactionUniqueIdRepository.delete(id);;
	}
	public TransactionUniqueId find(int id) {
		return this.transactionUniqueIdRepository.findOne(id);
	}
	
}
