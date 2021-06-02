package it.exolab.tesina.mybank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import it.exolab.tesina.mybank.model.ExternalTransaction;
import it.exolab.tesina.mybank.model.dto.ExternalTransactionDTO;



public interface ExternalTransactionRepository extends CrudRepository<ExternalTransaction, Integer> {
	
	public List<ExternalTransaction> findByVerifyAssignedTo(Integer id);
}


