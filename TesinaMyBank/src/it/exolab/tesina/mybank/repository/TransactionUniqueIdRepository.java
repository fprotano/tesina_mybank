package it.exolab.tesina.mybank.repository;

import org.springframework.data.repository.CrudRepository;

import it.exolab.tesina.mybank.model.TransactionUniqueId;
import it.exolab.tesina.mybank.model.dto.TransactionUniqueIdDTO;

public interface TransactionUniqueIdRepository extends CrudRepository<TransactionUniqueId, Integer> {

}
