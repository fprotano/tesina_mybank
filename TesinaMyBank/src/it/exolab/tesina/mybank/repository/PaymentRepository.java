package it.exolab.tesina.mybank.repository;

import org.springframework.data.annotation.Transient;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import it.exolab.tesina.mybank.model.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Integer> {

	public Payment findById(Integer id);

	public void deleteById(Integer id);

	public Payment findByTransactionId(String transactionId);
	
	@Modifying
	// @Transactional serve con delete e update, altrimenti da errore. 
	// Essenzialmente @T. chiama un proxy e crea una copia del metodo e dall'altra parte
	// istanzia un TransactionalInterceptor, per questioni di sicurezza
	@Transactional
	@Query("DELETE FROM Payment WHERE transactionId = ?1")
	public void deleteByTransactionId(String transactionId);
	
}
