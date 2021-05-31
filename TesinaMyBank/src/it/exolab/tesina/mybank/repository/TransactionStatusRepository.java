package it.exolab.tesina.mybank.repository;

import org.springframework.data.repository.CrudRepository;

import it.exolab.tesina.mybank.model.TransactionStatus;
import it.exolab.tesina.mybank.model.dto.TransactionStatusDTO;

public interface TransactionStatusRepository extends CrudRepository<TransactionStatus, Integer> {

}
