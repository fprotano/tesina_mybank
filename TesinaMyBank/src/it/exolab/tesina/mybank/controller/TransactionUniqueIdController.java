package it.exolab.tesina.mybank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import it.exolab.tesina.mybank.model.HTTPResponse;
import it.exolab.tesina.mybank.model.dto.TransactionUniqueIdDTO;
import it.exolab.tesina.mybank.service.TransactionUniqueIdService;
 
@Controller
@RequestMapping(value="transactionUniqueId")
public class TransactionUniqueIdController {
	
	private TransactionUniqueIdService transactionUniqueIdService;

	@Autowired
	public void setTransactionUniqueIdService(TransactionUniqueIdService transactionUniqueIdService) {
		this.transactionUniqueIdService = transactionUniqueIdService;
	}
	
	@RequestMapping(value="insert", method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse register(@RequestBody TransactionUniqueIdDTO transactionUniqueIdDto) {
		HTTPResponse response = new HTTPResponse();
		if(transactionUniqueIdDto!=null) {
			this.transactionUniqueIdService.insert(transactionUniqueIdDto);
			response.setData(transactionUniqueIdDto);
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
			this.transactionUniqueIdService.find(id);
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
			List<TransactionUniqueIdDTO> transazioni = this.transactionUniqueIdService.findAll();
			response.setData(transazioni);
			response.setSuccess(true);
			return response;
		
	}
	
	@RequestMapping(value="delete", method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse delete(@RequestBody Integer id) {
		HTTPResponse response = new HTTPResponse();
		if(id!=null) {
			this.transactionUniqueIdService.delete(id);
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
