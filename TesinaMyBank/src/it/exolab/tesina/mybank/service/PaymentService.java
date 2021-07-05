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

	public void update(Payment payment) {
		paymentRepository.save(payment);
	}

	public void insert(Payment payment) {
		paymentRepository.save(payment);
	}

	public Payment findById(Integer id) {
		return paymentRepository.findById(id);
	}

	public void deleteById(int id) {
		paymentRepository.deleteById(id);
	}

	public Payment findByTransactionId(String transactionId) {
		return (Payment) paymentRepository.findByTransactionId(transactionId);
	}

	public void deleteByTransactionId(String transactionId) {
		paymentRepository.deleteByTransactionId(transactionId);
	}

}
