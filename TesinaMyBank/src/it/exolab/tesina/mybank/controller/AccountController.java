package it.exolab.tesina.mybank.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.exolab.tesina.mybank.model.Account;
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
		accountService.save(account);
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
		accountService.save(account);
		return "redirect:/account/list";
		
	}
	
	@RequestMapping(value="delete/{id}")
	public String delete(@PathVariable int id) {
		accountService.delete(id);
		return "redirect:/account/list";
		
	}
	
	
}
