package it.exolab.tesina.mybank.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import it.exolab.tesina.mybank.factory.HelpCenterFactory;
import it.exolab.tesina.mybank.model.Account;
import it.exolab.tesina.mybank.model.HTTPResponse;
import it.exolab.tesina.mybank.model.HelpCenter;
import it.exolab.tesina.mybank.model.HelpCenterThread;
import it.exolab.tesina.mybank.model.Staff;
import it.exolab.tesina.mybank.service.HelpCenterService;
import it.exolab.tesina.mybank.service.HelpCenterThreadService;
import it.exolab.tesina.mybank.service.StaffService;

@CrossOrigin
@Controller
@RequestMapping(value = "helpCenter")
public class HelpCenterController {

	private HelpCenterService helpCenterService;
	private HelpCenterFactory helpCenterFactory;
	private HelpCenterThreadService helpCenterThreadService;

	@Autowired(required = true)
	public void setHelpCenterService(HelpCenterService helpCenterService) {
		this.helpCenterService = helpCenterService;
	}

	@Autowired(required = true) 
	public void setHelpCenterThreadService(HelpCenterThreadService helpCenterThreadService) {
		this.helpCenterThreadService = helpCenterThreadService;
	}

	@RequestMapping(value = "insert/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse insert(@RequestBody HelpCenter helpCenter, @PathVariable int id, StaffService staffService) {
		HTTPResponse response = new HTTPResponse();
		if (helpCenter != null) {
			helpCenterFactory.fillHelpCenter(helpCenter, id, staffService);
			helpCenterService.insert(helpCenter);
			response.setData(helpCenter);
			response.setSuccess(true);
			return response;
		} else {
			response.setSuccess(false);
			response.setErr("Errore");
			response.setErr_code("01");
			return response;
		}
	}

	@RequestMapping(value = "findOne", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse findOne(@RequestBody Integer id) {
		HTTPResponse response = new HTTPResponse();
		if (id != null) {
			this.helpCenterService.find(id);
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

	@RequestMapping(value = "findAll", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse findAll() {
		HTTPResponse response = new HTTPResponse();
		List<HelpCenter> helpcenters = this.helpCenterService.findAll();
		response.setData(helpcenters);
		response.setSuccess(true);
		return response;

	}

	@RequestMapping(value = "findAllThreads", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse findAllThreads() {
		HTTPResponse response = new HTTPResponse();
		List<HelpCenterThread> helpcentersthreads = this.helpCenterThreadService.findAll();
		System.out.println("CIAIOOOOOO" + helpcentersthreads);
		if (helpcentersthreads.size() > 0) {
			response.setData(helpcentersthreads);
			response.setSuccess(true);
			return response;
		} else {
			response.setSuccess(false);
			response.setErr("Errore");
			response.setErr_code("01");
			return response;
		}

	}
	
	@RequestMapping(value = "findAccountId/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse findAccountId(@RequestBody HelpCenter helpCenter, @PathVariable int id) {
		HTTPResponse response = new HTTPResponse();
		System.out.println(id);
		this.helpCenterService.findByFromAccountId(id);

		if (helpCenter != null) {
			response.setData(helpCenter);
			response.setSuccess(true);
			return response;
		} else {
			response.setSuccess(false);
			response.setErr("Errore");
			response.setErr_code("01");
			return response;
		}
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse delete(@RequestBody Integer id) {
		HTTPResponse response = new HTTPResponse();
		if (id != null) {
			this.helpCenterService.delete(id);
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

	//inizio lato spring e jsp x staff
	@RequestMapping(value = "helpcenterList", method = RequestMethod.GET)
	public ModelAndView helpcenterList(Model model, HttpSession session) {
		ModelAndView ret = new ModelAndView("admin/helpcenterList");
		// da creare
//		session=util.sessionCleanerFromTransactions(session);
		Staff staff = (Staff) session.getAttribute("staff");
		if (staff != null) {
			List<HelpCenter> helpCenterList = helpCenterService.findAll();
			ret.addObject("helpCenterList", helpCenterList);
		}
		return ret;
	}
	

}
	

