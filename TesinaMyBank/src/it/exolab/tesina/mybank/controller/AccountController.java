package it.exolab.tesina.mybank.controller;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.naming.spi.ObjectFactory;

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
import it.exolab.tesina.mybank.factory.IbanFactory;
import it.exolab.tesina.mybank.factory.OtpCodeFactory;
import it.exolab.tesina.mybank.model.Account;
import it.exolab.tesina.mybank.model.HTTPResponse;
import it.exolab.tesina.mybank.model.dto.AccountDTO;
import it.exolab.tesina.mybank.service.AccountService;


@CrossOrigin
@Controller
@RequestMapping(value="account")
public class AccountController {
	
	private AccountService accountService;
	
	@Autowired
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	


	
	
	
	@RequestMapping(value="login", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	 @ResponseBody
	 public HTTPResponse login(@RequestBody AccountDTO account) {
		 	HTTPResponse response = new HTTPResponse();
		 	
		 	Account account_searched = this.accountService.findByEmailAndPassword(account.getEmail(), account.getPassword());
		 	account_searched.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
			if(account!=null) {
		 	response.setData(account_searched);
		 	response.setSuccess(true);
			return response;
			} else {
				response.setSuccess(false);
				response.setErr("Credenziali errate!");
				response.setErr_code("01");
					return response;
			}
		}
	
	
	@RequestMapping(value="registrazione", method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse register(@RequestBody Account account) {
		HTTPResponse response = new HTTPResponse();
		
			try {
				if(account!=null) {
					OtpCodeFactory.setCreatedUpdatedAndOtp(account);
				this.accountService.insert(account);
				response.setData(account);
				response.setSuccess(true);
				
				}
			
			} catch (RequiredFieldError e) {
				response.setSuccess(false);
				response.setErr("Errore Campo Obbligatorio");
				response.setErr_code("01");
					return response;
			} catch (MaxLengthError e) {
				response.setSuccess(false);
				response.setErr("Lunghezza massima superata");
				response.setErr_code("02");
					return response;
			
				
			} catch (MinLengthError e) {
				response.setSuccess(false);
				response.setErr("Lunghezza minima superata");
				response.setErr_code("03");
					return response;
			} catch (InvalidEmail e) {
				response.setSuccess(false);
				response.setErr("Lunghezza minima superata");
				response.setErr_code("03");
					return response;
			} catch (InvalidPassword e) {
				response.setSuccess(false);
				response.setErr("Lunghezza minima superata");
				response.setErr_code("03");
					return response;
				
			}
			return response;
			
		
	}
	
	@RequestMapping(value="findOne", method=RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse findOne(@RequestBody Integer id) {
		HTTPResponse response = new HTTPResponse();
		if(id!=null) {
			this.accountService.find(id);
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
			List<Account> transazioni = this.accountService.findAll();
			response.setData(transazioni);
			response.setSuccess(true);
			return response;
		
	}
	
	@RequestMapping(value="delete", method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse delete(@RequestBody Integer id) {
		HTTPResponse response = new HTTPResponse();
		if(id!=null) {
			this.accountService.delete(id);
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
