package it.exolab.tesina.mybank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import it.exolab.tesina.mybank.factory.TransactionUniqueIdFactory;
import it.exolab.tesina.mybank.model.HTTPResponse;
import it.exolab.tesina.mybank.model.InternalTransaction;
import it.exolab.tesina.mybank.model.TransactionUniqueId;
import it.exolab.tesina.mybank.service.InternalTransactionService;
import it.exolab.tesina.mybank.service.TransactionUniqueIdService;
@CrossOrigin
@Controller
@RequestMapping(value = "internalTransaction")

public class InternalTransactionController {

	private InternalTransactionService internalTransactionService;
	private TransactionUniqueIdService transactionUniqueIdService;
	private TransactionUniqueIdFactory transactionUniqueIdFactory;

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
	public HTTPResponse register(@RequestBody InternalTransaction internalTransaction) {
		HTTPResponse response = new HTTPResponse();
		if (internalTransaction != null) {
			TransactionUniqueId transactionUniqueId = new TransactionUniqueId();
			transactionUniqueId.setTransactionId(transactionUniqueIdFactory.CreateTransactionUniqueId());
			transactionUniqueIdService.insert(transactionUniqueId);
			internalTransaction.setTransactionId(transactionUniqueId.getTransactionId());
			this.internalTransactionService.insert(internalTransaction);
			response.setData(internalTransaction);
			response.setSuccess(true);
			return response;
		} else {
			response.setSuccess(false);
			response.setErr("Errore");
			response.setErr_code("01");
			return response;
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
