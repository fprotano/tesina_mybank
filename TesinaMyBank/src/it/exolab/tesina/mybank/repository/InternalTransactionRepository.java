package it.exolab.tesina.mybank.repository;

import org.springframework.data.repository.CrudRepository;

import it.exolab.tesina.mybank.model.InternalTransaction;

public interface InternalTransactionRepository extends CrudRepository<InternalTransaction, Integer> {

}
