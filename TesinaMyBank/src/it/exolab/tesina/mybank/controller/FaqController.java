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

import it.exolab.tesina.mybank.model.ExternalTransaction;
import it.exolab.tesina.mybank.model.Faq;
import it.exolab.tesina.mybank.model.HTTPResponse;
import it.exolab.tesina.mybank.repository.ExternalTransactionRepository;
import it.exolab.tesina.mybank.service.ExternalTransactionService;
import it.exolab.tesina.mybank.service.FaqService;

@CrossOrigin
@Controller
@RequestMapping(value="faq")
public class FaqController {

	private FaqService faqService;
	
	@Autowired(required=true)
	public void setFaqService(FaqService faqService) {
		this.faqService = faqService;
	}
	
	@RequestMapping(value="insert", method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse register(@RequestBody Faq faq) {
		HTTPResponse response = new HTTPResponse();
		if(faq!=null) {
			this.faqService.insert(faq);
			response.setData(faq);
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
			this.faqService.find(id);
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
			List<Faq> faqs = this.faqService.findAll();
			response.setData(faqs);
			response.setSuccess(true);
			return response;
		
	}
	
	@RequestMapping(value="delete", method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse delete(@RequestBody Integer id) {
		HTTPResponse response = new HTTPResponse();
		if(id!=null) {
			this.faqService.delete(id);
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
