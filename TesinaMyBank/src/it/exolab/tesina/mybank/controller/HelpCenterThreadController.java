package it.exolab.tesina.mybank.controller;

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

import it.exolab.tesina.mybank.model.HTTPResponse;
import it.exolab.tesina.mybank.model.HelpCenterThread;
import it.exolab.tesina.mybank.model.Staff;
import it.exolab.tesina.mybank.service.AccountService;
import it.exolab.tesina.mybank.service.HelpCenterThreadService;
import it.exolab.tesina.mybank.service.StaffService;

@CrossOrigin
@Controller
@RequestMapping(value = "helpCenterThread")
public class HelpCenterThreadController {

	private AccountService accountService;
	private HelpCenterThreadService helpCenterThreadService;
	private StaffService staffService;
	
	@Autowired(required = true)
	public void setHelpCenterThreadService(HelpCenterThreadService helpCenterThreadService) {
		this.helpCenterThreadService = helpCenterThreadService;
	}
	
//	@Autowired(required = true )
//	public void setAccountService(AccountService accountService) {
//		this.accountService = accountService;
//	}
	
	@Autowired(required = true )
	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}

	@RequestMapping(value = "insert", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse register(@RequestBody HelpCenterThread helpCenterThread) {
		HTTPResponse response = new HTTPResponse();
		if (helpCenterThread != null) {
			this.helpCenterThreadService.insert(helpCenterThread);
			response.setData(helpCenterThread);
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
			this.helpCenterThreadService.find(id);
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

	@RequestMapping(value = "findAll", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse findAll(@RequestBody Integer id) {
		HTTPResponse response = new HTTPResponse();
		List<HelpCenterThread> transazioni = this.helpCenterThreadService.findbyFromAccountId(id);
		if (transazioni.size() > 0) {
			response.setData(transazioni);
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
			this.helpCenterThreadService.delete(id);
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

	// inizio lato spring e jsp x staff
	@RequestMapping(value = "helpcenterThreadList/{helpcenterId}", method = RequestMethod.GET)
	public ModelAndView helpcenterThreadList(@PathVariable String helpcenterId, Model model, HttpSession session) {
		ModelAndView ret = new ModelAndView("admin/helpcenterThreadList");
		// da creare
		// session=util.sessionCleanerFromTransactions(session);
		Staff staff = (Staff) session.getAttribute("staff");
		if (staff != null) {
			List<HelpCenterThread> helpCenterThreadList = helpCenterThreadService.findByHelpCenterId(Integer.valueOf(helpcenterId));
			ret.addObject("helpCenterThreadList", helpCenterThreadList);
		}
		return ret;
	}

	@RequestMapping(value = "helpcenterThreadHistory/{helpcenterId}", method = RequestMethod.GET)
	public ModelAndView helpcenterThreadHistory(@PathVariable String helpcenterId, Model model, HttpSession session) {
		ModelAndView ret = new ModelAndView("admin/helpcenterThreadHistory");
		// da creare
		// session=util.sessionCleanerFromTransactions(session);
		Staff staff = (Staff) session.getAttribute("staff");
		if (staff != null) {
			List<HelpCenterThread> helpCenterThreadList = helpCenterThreadService.findByHelpCenterId(Integer.valueOf(helpcenterId));
			ret.addObject("helpCenterThreadList", helpCenterThreadList);
		}
		return ret;
	}

}
