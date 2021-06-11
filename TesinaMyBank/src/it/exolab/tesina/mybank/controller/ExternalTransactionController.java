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
import it.exolab.tesina.mybank.model.ExternalTransaction;
import it.exolab.tesina.mybank.model.HTTPResponse;
import it.exolab.tesina.mybank.model.TransactionUniqueId;
import it.exolab.tesina.mybank.service.ExternalTransactionService;
import it.exolab.tesina.mybank.service.TransactionUniqueIdService;

@CrossOrigin
@Controller
@RequestMapping(value="externalTransaction")
public class ExternalTransactionController {
	
	private ExternalTransactionService externalTransactionService;
	private TransactionUniqueIdService transactionUniqueIdService;
	private TransactionUniqueIdFactory transactionUniqueIdFactory;
	
	@Autowired(required=true)
	public void setExternalTransactionService(ExternalTransactionService externalTransactionService) {
		this.externalTransactionService = externalTransactionService;
	}
	@Autowired(required = true)
	public void setTransactionUniqueIdService(TransactionUniqueIdService transactionUniqueIdService) {
		this.transactionUniqueIdService = transactionUniqueIdService;
	}
	
	@RequestMapping(value="insert", method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse insert(@RequestBody ExternalTransaction externalTransaction) {
		HTTPResponse response = new HTTPResponse();
		if(externalTransaction!=null) {
			TransactionUniqueId transactionUniqueId = new TransactionUniqueId();
			transactionUniqueId.setTransactionId(transactionUniqueIdFactory.CreateTransactionUniqueId());
			transactionUniqueIdService.insert(transactionUniqueId);
			externalTransaction.setTransactionId(transactionUniqueId.getTransactionId());
			externalTransactionService.insert(externalTransaction);
			response.setData(externalTransaction);
			response.setSuccess(true);
			return response;
		} else {
				response.setSuccess(false);
				response.setErr("Errore");
				response.setErr_code("01");
					return response;
		}
	}
	
	@RequestMapping(value="findOne", method=RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse findOne(@RequestBody Integer id) {
		HTTPResponse response = new HTTPResponse();
		if(id!=null) {
			this.externalTransactionService.find(id);
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
	
	@RequestMapping(value="findAll", method=RequestMethod.GET)
	@ResponseBody
	public HTTPResponse findAll() {
			HTTPResponse response = new HTTPResponse();
			List<ExternalTransaction> transazioni = this.externalTransactionService.findAll();
			response.setData(transazioni);
			response.setSuccess(true);
			return response;
		
	}
	
	@RequestMapping(value="delete", method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse delete(@RequestBody Integer id) {
		HTTPResponse response = new HTTPResponse();
		if(id!=null) {
			this.externalTransactionService.delete(id);
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
	
	//inizio query composite
	@RequestMapping(value="findAllByStaffId", method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse findAll(@RequestBody Integer id) {
			HTTPResponse response = new HTTPResponse();
			List<ExternalTransaction> transazioni = this.externalTransactionService.findAllByStaffId(id);
			if(transazioni.size()>0) {
			response.setData(transazioni);
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
