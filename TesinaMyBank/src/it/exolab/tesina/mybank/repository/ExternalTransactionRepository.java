package it.exolab.tesina.mybank.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import it.exolab.tesina.mybank.model.ExternalTransaction;
import it.exolab.tesina.mybank.model.dto.ExternalTransactionDTO;



public interface ExternalTransactionRepository extends CrudRepository<ExternalTransactionDTO, Integer> {

	
}


