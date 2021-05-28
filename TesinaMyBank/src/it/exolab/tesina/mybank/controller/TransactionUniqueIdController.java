package it.exolab.tesina.mybank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.exolab.tesina.mybank.model.Account;
import it.exolab.tesina.mybank.model.TransactionUniqueId;
import it.exolab.tesina.mybank.service.TransactionUniqueIdService;

@Controller
@RequestMapping(value="transactionUniqueId")
public class TransactionUniqueIdController {
	
	private TransactionUniqueIdService transactionUniqueIdService;

	@Autowired
	public void setTransactionUniqueIdService(TransactionUniqueIdService transactionUniqueIdService) {
		this.transactionUniqueIdService = transactionUniqueIdService;
	}
	
	@RequestMapping(value="list")
	public ModelAndView list() {
		ModelAndView ret = new ModelAndView("transactionUniqueId/list");
		ret.addObject("items",this.transactionUniqueIdService.findAll());
		return ret;
		
	}
	
	@RequestMapping(value="add")
	public ModelAndView add() {
		ModelAndView ret = new ModelAndView("transactionUniqueId/add");
		ret.addObject("transactionUniqueId", new TransactionUniqueId());
		return ret;
		
	}
	@RequestMapping(value="add", method=RequestMethod.POST)
	public String insert(@ModelAttribute TransactionUniqueId transactionUniqueId) {
		transactionUniqueIdService.insert(transactionUniqueId);
		return "redirect:/transactionUniqueId/list";
		
	}
	
	
	

	@RequestMapping(value="edit/{id}")
	public ModelAndView edit(@PathVariable int id) {
		ModelAndView ret = new ModelAndView("transactionUniqueId/edit");
		ret.addObject("transactionUniqueId", this.transactionUniqueIdService.find(id));
		
		return ret;
		
	}
	@RequestMapping(value="edit/{id}", method=RequestMethod.POST)
	public String update(@ModelAttribute TransactionUniqueId transactionUniqueId) {
		transactionUniqueIdService.update(transactionUniqueId);
		return "redirect:/transactionUniqueId/list";
		
	}
	
	@RequestMapping(value="delete/{id}")
	public String delete(@PathVariable int id) {
		transactionUniqueIdService.delete(id);
		return "redirect:/transactionUniqueId/list";
		 
	}
	
}
