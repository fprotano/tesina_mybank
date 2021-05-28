package it.exolab.tesina.mybank.repository;

import org.springframework.data.repository.CrudRepository;

import it.exolab.tesina.mybank.model.TransactionStatus;

public interface TransactionStatusRepository extends CrudRepository<TransactionStatus, Integer> {

}
