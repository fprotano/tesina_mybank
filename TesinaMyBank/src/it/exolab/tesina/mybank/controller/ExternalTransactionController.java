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
import it.exolab.tesina.mybank.model.dto.ExternalTransactionDTO;
import it.exolab.tesina.mybank.service.ExternalTransactionService;

@CrossOrigin
@Controller
@RequestMapping(value="externalTransaction")
public class ExternalTransactionController {
	
	private ExternalTransactionService externalTransactionService;
	
	@Autowired(required=true)
	public void setExternalTransactionService(ExternalTransactionService externalTransactionService) {
		this.externalTransactionService = externalTransactionService;
	}
	
	
//	@RequestMapping(value="login", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//	 @ResponseBody
//	 public HTTPResponse login(@RequestBody User user) {
//		 	HTTPResponse response = new HTTPResponse();
//		 	User user2 = this.userservice.findByEmailAndPassword(user.getEmail(), user.getPassword());
//			if(user2!=null) {
//		 	response.setData(user2);
//		 	response.setSuccess(true);
//			return response;
//			} else {
//				response.setSuccess(false);
//				response.setErr("Credenziali errate!");
//				response.setErr_code("01");
//					return response;
//			}
//		}
//	
//	@RequestMapping(value="register2", method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public HTTPResponse register(@RequestBody User user) {
//		HTTPResponse response = new HTTPResponse();
//		if(user.getNome().length()>=1 && user.getCognome().length()>=1 && user.getEmail().length()>=1 && user.getPassword().length()>=1) {
//			this.userservice.save(user);
//			response.setData(user);
//			response.setSuccess(true);
//			return response;
//		} else {
//				response.setSuccess(false);
//				response.setErr("Errore");
//				response.setErr_code("01");
//					return response;
//		}
//	}
	
	
	
	@RequestMapping(value="insert", method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse register(@RequestBody ExternalTransactionDTO externalTransaction) {
		HTTPResponse response = new HTTPResponse();
		if(externalTransaction!=null) {
			this.externalTransactionService.insert(externalTransaction);
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
	
	@RequestMapping(value="findAll", method=RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse findAll() {
			HTTPResponse response = new HTTPResponse();
			List<ExternalTransactionDTO> transazioni = this.externalTransactionService.findAll();
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
	
	

}
