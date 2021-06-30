package it.exolab.tesina.mybank.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import it.exolab.tesina.mybank.exception.InvalidEmail;
import it.exolab.tesina.mybank.exception.InvalidPassword;
import it.exolab.tesina.mybank.exception.MaxLengthError;
import it.exolab.tesina.mybank.exception.MinLengthError;
import it.exolab.tesina.mybank.exception.RequiredFieldError;
import it.exolab.tesina.mybank.exception.UniqueFieldError;
import it.exolab.tesina.mybank.factory.InternalTransactionFactory;
import it.exolab.tesina.mybank.factory.TransactionUniqueIdFactory;
import it.exolab.tesina.mybank.model.Account;
import it.exolab.tesina.mybank.model.HTTPResponse;
import it.exolab.tesina.mybank.model.InternalTransaction;
import it.exolab.tesina.mybank.model.Payment;
import it.exolab.tesina.mybank.model.TransactionUniqueId;
import it.exolab.tesina.mybank.service.AccountService;
import it.exolab.tesina.mybank.service.InternalTransactionService;
import it.exolab.tesina.mybank.service.PaymentService;
import it.exolab.tesina.mybank.service.StaffService;
import it.exolab.tesina.mybank.service.TransactionUniqueIdService;
@CrossOrigin
@Controller
@RequestMapping(value = "internalTransaction")

public class InternalTransactionController {
	private AccountService accountService;
	private StaffService staffService;
	private PaymentService paymentservice;
	private InternalTransactionService internalTransactionService;
	private TransactionUniqueIdService transactionUniqueIdService;
	private TransactionUniqueIdFactory transactionUniqueIdFactory;
	private InternalTransactionFactory itf = new InternalTransactionFactory();
	
	@Autowired(required = true)
	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}
	
	@Autowired(required = true)
	public void setPaymentService(PaymentService paymentservice) {
		this.paymentservice = paymentservice;
	}
	
	@Autowired(required = true)
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@Autowired(required = true)
	public void setExternalTransactionService(InternalTransactionService internalTransactionService) {
		this.internalTransactionService = internalTransactionService;
	}
	@Autowired(required = true)
	public void setTransactionUniqueIdService(TransactionUniqueIdService transactionUniqueIdService) {
		this.transactionUniqueIdService = transactionUniqueIdService;
	}

	@RequestMapping(value = "insert", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse insert(@RequestBody Payment payment, HttpSession session, HTTPResponse response) throws RequiredFieldError, MaxLengthError, MinLengthError, InvalidEmail, InvalidPassword, UniqueFieldError {
		Account accountPayed = accountService.findByEmail(payment.getEmail());
		InternalTransaction internalTransaction = new InternalTransaction();
		Account account = accountService.findByEmail(payment.getAccount().getEmail());
			internalTransaction = itf.fillInternalTransaction(internalTransaction, payment, account);
			internalTransaction.setToAccountId(accountPayed.getId());
			if(account.getBalance() - payment.getAmount() > 0) {
				accountPayed.setBalance(accountPayed.getBalance() + payment.getAmount());
				accountService.update(accountPayed);
				account.setBalance(account.getBalance() - payment.getAmount());
				accountService.update(account);
				internalTransactionService.insert(internalTransaction);
				paymentservice.deleteByTransactionId(payment.getTransactionId());
				return new HTTPResponse(payment);
			} else {
				return new HTTPResponse("Errore, pagamento non effettuato.", "01");
			}
			
	}

	@RequestMapping(value = "findOne", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse findOne(@RequestBody Integer id) {
		HTTPResponse response = new HTTPResponse();
		if (id != null) {
			this.internalTransactionService.find(id);
			response.setData(id);
			response.setSuccess(true);
			return response;
		} else {
			response.setSuccess(false);
			response.setErr("Errore");
			response.setErr_code("01");
			return response;
		}
	}

	@RequestMapping(value = "findAll", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse findAll() {
		HTTPResponse response = new HTTPResponse();
		List<InternalTransaction> transazioni = this.internalTransactionService.findAll();
		response.setData(transazioni);
		response.setSuccess(true);
		return response;

	}

	@RequestMapping(value = "delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse delete(@RequestBody Integer id) {
		HTTPResponse response = new HTTPResponse();
		if (id != null) {
			this.internalTransactionService.delete(id);
			response.setData(id);
			response.setSuccess(true);
			return response;
		} else {
			response.setSuccess(false);
			response.setErr("Errore");
			response.setErr_code("01");
			return response;

		}
	}
}
