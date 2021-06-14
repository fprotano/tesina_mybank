package it.exolab.tesina.mybank.service;

import org.springframework.beans.factory.annotation.Autowired;

import it.exolab.tesina.mybank.model.Payment;
import it.exolab.tesina.mybank.repository.PaymentRepository;

public class PaymentService {
	
	private PaymentRepository paymentRepository;

	@Autowired
	public void setPaymentRepository(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}

	public void insert(Payment Payment) {
		paymentRepository.save(Payment);
	}

}
