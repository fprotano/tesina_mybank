package it.exolab.tesina.mybank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import it.exolab.mavenspring.model.HTTPResponse;
import it.exolab.mavenspring.model.User;
import it.exolab.tesina.mybank.service.ExternalTransactionService;

@CrossOrigin
@Controller
@RequestMapping(value="libro")
public class ExternalTransactionController {
	
	private ExternalTransactionService externalTransactionService;
	
	@Autowired(required=true)
	public void setExternalTransactionService(ExternalTransactionService externalTransactionService) {
		this.externalTransactionService = externalTransactionService;
	}
	
	
	@RequestMapping(value="login", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	 @ResponseBody
	 public HTTPResponse login(@RequestBody User user) {
		 	HTTPResponse response = new HTTPResponse();
		 	User user2 = this.userservice.findByEmailAndPassword(user.getEmail(), user.getPassword());
			if(user2!=null) {
		 	response.setData(user2);
		 	response.setSuccess(true);
			return response;
			} else {
				response.setSuccess(false);
				response.setErr("Credenziali errate!");
				response.setErr_code("01");
					return response;
			}
		}
	
	@RequestMapping(value="register2", method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse register(@RequestBody User user) {
		HTTPResponse response = new HTTPResponse();
		if(user.getNome().length()>=1 && user.getCognome().length()>=1 && user.getEmail().length()>=1 && user.getPassword().length()>=1) {
			this.userservice.save(user);
			response.setData(user);
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
