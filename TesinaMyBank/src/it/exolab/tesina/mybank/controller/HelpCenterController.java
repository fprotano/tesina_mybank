package it.exolab.tesina.mybank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import it.exolab.tesina.mybank.service.HelpCenterService;


@Controller
@RequestMapping(value="helpCenter")
public class HelpCenterController {
	
	private HelpCenterService helpCenterService;
	
	@Autowired(required=true)
	public void setHelpCenterService(HelpCenterService helpCenterService) {
		this.helpCenterService = helpCenterService;
	}

}
