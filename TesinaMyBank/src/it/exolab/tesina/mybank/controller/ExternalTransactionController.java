package it.exolab.tesina.mybank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
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
	

}
