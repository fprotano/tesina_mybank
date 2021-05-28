package it.exolab.tesina.mybank.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import it.exolab.tesina.mybank.model.Account;
import it.exolab.tesina.mybank.model.HTTPResponse;
import it.exolab.tesina.mybank.service.AccountService;



@Controller
@RequestMapping(value="account")
public class AccountController {
	
	private AccountService accountService;
	
	@Autowired
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	


	
	@RequestMapping(value="list")
	public ModelAndView list() {
		ModelAndView ret = new ModelAndView("account/list");
		ret.addObject("items",this.accountService.findAll());
		return ret;
		
	}
	
	@RequestMapping(value="add")
	public ModelAndView add() {
		ModelAndView ret = new ModelAndView("account/add");
		ret.addObject("account", new Account());
		return ret;
		
	}
	@RequestMapping(value="add", method=RequestMethod.POST)
	public String insert(@ModelAttribute Account account) {
		accountService.insert(account);
		return "redirect:/prenotazioni/list";
		
	}
	
	
	

	@RequestMapping(value="edit/{id}")
	public ModelAndView edit(@PathVariable int id) {
		ModelAndView ret = new ModelAndView("account/edit");
		ret.addObject("account", this.accountService.find(id));
		
		return ret;
		
	}
	@RequestMapping(value="edit/{id}", method=RequestMethod.POST)
	public String update(@ModelAttribute Account account) {
		accountService.update(account);
		return "redirect:/account/list";
		
	}
	
	@RequestMapping(value="delete/{id}")
	public String delete(@PathVariable int id) {
		accountService.delete(id);
		return "redirect:/account/list";
		
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	 @ResponseBody
	 public HTTPResponse login(@RequestBody Account account) {
		 	HTTPResponse response = new HTTPResponse();
		 	Account account_searched = this.accountService.findByEmailAndPassword(account.getEmail(), account.getPassword());
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
	
	@RequestMapping(value="insert", method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse register(@RequestBody Account account) {
		HTTPResponse response = new HTTPResponse();
		if(account!=null) {
			this.accountService.insert(account);
			response.setData(account);
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
