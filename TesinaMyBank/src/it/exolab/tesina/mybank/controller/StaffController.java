package it.exolab.tesina.mybank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import it.exolab.tesina.mybank.model.Account;
import it.exolab.tesina.mybank.model.HTTPResponse;
import it.exolab.tesina.mybank.model.Staff;
import it.exolab.tesina.mybank.service.ExternalTransactionService;
import it.exolab.tesina.mybank.service.StaffService;

@CrossOrigin
@Controller
@RequestMapping(value="staff")
public class StaffController {
	private StaffService staffService;
	private HTTPResponse response;

	@Autowired(required=true)
	public void setStaffService( StaffService staffService) {
		this.staffService = staffService;
	}
	
	

	@RequestMapping(value="login", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HTTPResponse login(@RequestBody Staff staff) {	
		 staff = this.staffService.findByEmailAndPassword(staff.getEmail(), staff.getPassword());
		response= new HTTPResponse(staff);
		return response;
	}
		
	//response= new HTTPResponse("err","errore");
	
	}


