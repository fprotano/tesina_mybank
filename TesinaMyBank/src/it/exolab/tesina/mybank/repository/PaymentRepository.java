package it.exolab.tesina.mybank.repository;

import org.springframework.data.repository.CrudRepository;
import it.exolab.tesina.mybank.model.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Integer> {

}
