package it.exolab.tesina.mybank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import it.exolab.tesina.mybank.service.InternalTransactionService;

@Controller
@RequestMapping(value="internal_transaction")

public class InternalTransactionController {
	
	private InternalTransactionService internalTransactionService;

@Autowired(required=true)
public void setExternalTransactionService(InternalTransactionService internalTransactionService) {
	this.internalTransactionService = internalTransactionService;
}
}
