package it.exolab.tesina.mybank.repository;

import org.springframework.data.repository.CrudRepository;

import it.exolab.tesina.mybank.model.InternalTransaction;
import it.exolab.tesina.mybank.model.dto.InternalTransactionDTO;

public interface InternalTransactionRepository extends CrudRepository<InternalTransactionDTO, Integer> {

}
