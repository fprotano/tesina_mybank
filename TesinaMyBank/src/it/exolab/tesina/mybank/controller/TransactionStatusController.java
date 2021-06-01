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

import it.exolab.tesina.mybank.model.HTTPResponse;
import it.exolab.tesina.mybank.model.TransactionStatus;
import it.exolab.tesina.mybank.service.TransactionStatusService;
@CrossOrigin
@Controller
@RequestMapping(value="transactionStatus")
public class TransactionStatusController {
	
	private TransactionStatusService transactionStatusService;
	
	@Autowired(required=true)  
	public void setTransactionStatusService(TransactionStatusService transactionStatusService) {
		this.transactionStatusService = transactionStatusService;
	}
	
	@RequestMapping(value = "insert", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse register(@RequestBody TransactionStatus transactionStatus) {
		HTTPResponse response = new HTTPResponse();
		if (transactionStatusService != null) {
			this.transactionStatusService.insert(transactionStatus);
			response.setData(transactionStatus);
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
			this.transactionStatusService.find(id);
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
	 
	
	@RequestMapping(value="findAll", method=RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse findAll() {
			HTTPResponse response = new HTTPResponse();
			List<TransactionStatus> transazioni = this.transactionStatusService.findAll();
			response.setData(transazioni);
			response.setSuccess(true);
			return response;
		
	}
	 
	
	@RequestMapping(value="delete", method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse delete(@RequestBody Integer id) {
		HTTPResponse response = new HTTPResponse();
		if(id!=null) {
			this.transactionStatusService.delete(id);
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
