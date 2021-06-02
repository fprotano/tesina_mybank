package it.exolab.tesina.mybank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import it.exolab.tesina.mybank.model.HTTPResponse;
import it.exolab.tesina.mybank.model.HelpCenterThread;
import it.exolab.tesina.mybank.service.HelpCenterThreadService;
@CrossOrigin
@Controller
@RequestMapping(value="helpCenterThread")
public class HelpCenterThreadController {
	
	private HelpCenterThreadService helpCenterThreadService;
	
	@Autowired(required=true)  
	public void setHelpCenterThreadService(HelpCenterThreadService helpCenterThreadService) {
		this.helpCenterThreadService = helpCenterThreadService;
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
	
	
	@RequestMapping(value="findAll", method=RequestMethod.GET)
	@ResponseBody
	public HTTPResponse findAll() {
			HTTPResponse response = new HTTPResponse();
			List<HelpCenterThread> transazioni = this.helpCenterThreadService.findAll();
			if(transazioni.size()>0) {
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
	
	
	@RequestMapping(value="delete", method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse delete(@RequestBody Integer id) {
		HTTPResponse response = new HTTPResponse();
		if(id!=null) {
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

	
	


}
