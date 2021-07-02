package it.exolab.tesina.mybank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.exolab.tesina.mybank.model.ExternalTransaction;
import it.exolab.tesina.mybank.model.dto.ExternalTransactionDTO;
import it.exolab.tesina.mybank.repository.ExternalTransactionRepository;



public class ExternalTransactionService {

	
private ExternalTransactionRepository externalTransactionRepository;
	
	@Autowired(required=true)
	public void setExternalTransactionRepository(ExternalTransactionRepository externalTransactionRepository) {
		this.externalTransactionRepository=externalTransactionRepository;
	}
	
	public List<ExternalTransaction> findAll(){
		return (List<ExternalTransaction>) externalTransactionRepository.findAll();	
	}
	
	public void insert(ExternalTransaction externalTransaction) {
		externalTransactionRepository.save(externalTransaction);
	}
	
	public void update(ExternalTransaction externalTransaction) {
		externalTransactionRepository.save(externalTransaction);
	}
	
	public void delete(int id) {
		externalTransactionRepository.delete(id);
	}
	
	public ExternalTransaction find(int id) {
		return externalTransactionRepository.findOne(id);
	}
	
	public ExternalTransaction findByCustomCode(String customCode) {
		return externalTransactionRepository.findByCustomCode(customCode);
	}
	
	public List<ExternalTransaction> findByStatePendingAssignedToId(Integer id){
		return (List<ExternalTransaction>) externalTransactionRepository.findByStatePendingAssignedToId(id);
	}
	
	public List<ExternalTransaction> findByStateProcessedAssignedToId(Integer id){
		return (List<ExternalTransaction>) externalTransactionRepository.findByStateProcessedAssignedToId(id);
	}
	
	public List<ExternalTransaction> findBytoAccountId(Integer id){
		return (List<ExternalTransaction>) externalTransactionRepository.findBytoAccountId(id);
	}
}
