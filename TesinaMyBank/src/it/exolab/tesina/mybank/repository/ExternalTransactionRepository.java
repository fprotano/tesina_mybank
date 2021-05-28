package it.exolab.tesina.mybank.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import it.exolab.tesina.mybank.model.ExternalTransaction;



public interface ExternalTransactionRepository extends CrudRepository<ExternalTransaction, Integer> {

}


