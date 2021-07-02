package it.exolab.tesina.mybank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.exolab.tesina.mybank.model.ExternalTransaction;

public interface ExternalTransactionRepository extends CrudRepository<ExternalTransaction, Integer> {

	@Modifying
	@Query("SELECT et FROM ExternalTransaction et WHERE transactionStatusId = 1 AND verifyAssignedTo = ?1 ")
	public List<ExternalTransaction> findByStatePendingAssignedToId(Integer id);

	@Modifying
	@Query("SELECT et FROM ExternalTransaction et WHERE (transactionStatusId = 2 OR transactionStatusId = 3) AND verifyAssignedTo = ?1 ")
	public List<ExternalTransaction> findByStateProcessedAssignedToId(Integer id);

	public List<ExternalTransaction> findBytoAccountId(Integer id);

	public ExternalTransaction findByCustomCode(String customCode);
}
