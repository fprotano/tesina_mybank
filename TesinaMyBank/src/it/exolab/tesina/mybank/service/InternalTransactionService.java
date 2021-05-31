package it.exolab.tesina.mybank.service;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;

import it.exolab.tesina.mybank.model.InternalTransaction;
import it.exolab.tesina.mybank.model.dto.InternalTransactionDTO;
import it.exolab.tesina.mybank.repository.InternalTransactionRepository;


public class InternalTransactionService {
	
	private InternalTransactionRepository internalTransactionRepository;
	
	@Autowired(required=true)
	public void setInternalTransactionRepository(InternalTransactionRepository internalTransactionRepository) {
		this.internalTransactionRepository = internalTransactionRepository;
	}
	
	public List<InternalTransaction> findAll(){
		return (List<InternalTransaction>) internalTransactionRepository.findAll();
	}
	public void insert(InternalTransaction internalTransaction){
		this.internalTransactionRepository.save(internalTransaction);
	}
	
	public void delete(Integer id){
		this.internalTransactionRepository.delete(id);
	}
	public void update(InternalTransaction internalTransaction) {
		internalTransactionRepository.save(internalTransaction);
	}
	
	public InternalTransaction find(Integer id){
		return internalTransactionRepository.findOne(id);
	}

}
