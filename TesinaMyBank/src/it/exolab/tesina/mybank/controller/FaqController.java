package it.exolab.tesina.mybank.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
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

import it.exolab.tesina.mybank.model.Faq;
import it.exolab.tesina.mybank.model.HTTPResponse;
import it.exolab.tesina.mybank.model.Staff;
import it.exolab.tesina.mybank.service.FaqService;
import it.exolab.tesina.mybank.util.Util;

@CrossOrigin
@Controller
@RequestMapping(value="faq")
public class FaqController {

	private FaqService faqService;
	private Util util = new Util();
	
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
	
	@RequestMapping(value = "faqList", method = RequestMethod.GET)
	public ModelAndView faqsList(Model model, HttpSession session) {
		ModelAndView ret = new ModelAndView("admin/faqList");
		Staff staff = (Staff) session.getAttribute("staff");
		if (staff != null) {
			List<Faq> faqs = faqService.findAll();
			ret.addObject("faqs", faqs);
		}
		return ret;
	}
	
	@RequestMapping(value = "addFaq", method = RequestMethod.GET)
	public String addFaq(HttpSession session, Model model) {
		session=util.sessionCleanerFromHome(session);
		Faq newFaq = new Faq();
		model.addAttribute("newFaq", newFaq);
		return "admin/faqAdd";
	}
	
	@RequestMapping(value = "addFaq", method = RequestMethod.POST)
	public ModelAndView addFaq(Faq newFaq, HttpSession session) {
		ModelAndView ret = new ModelAndView("redirect:/faq/faqList");
		if (newFaq != null) {
			faqService.insert(newFaq);
			session.setAttribute("faqAdded", 0);
		//	ret.addObject("faqAdded", 0);
			return ret;
		} else {
			session.setAttribute("faqAdded", 1);
		//	ret.addObject("faqAdded", 1);
			return ret;
		}
	}
	
	@RequestMapping(value = "updateFaq/{faqId}", method = RequestMethod.GET)
	public ModelAndView updateFaq(@PathVariable String faqId, HttpSession session, Model model,
		HttpServletResponse response) throws IOException {
		Faq faqToUpdate = faqService.find(Integer.valueOf(faqId));
		// ritorno un ModelAndView composto da: stringa view, ossia indirizzo della pagina
		// secondo parametro nome del modello, terzo parametro il modello
		return new ModelAndView("admin/faqUpdate","faqToUpdate",faqToUpdate);
	}
	
	@RequestMapping(value = "updateFaq", method = RequestMethod.POST)
	public ModelAndView updateFaq(Faq faqToUpdate, HttpSession session) {
		ModelAndView ret = new ModelAndView("redirect:/faq/faqList");
		if (faqToUpdate != null) {
			faqService.update(faqToUpdate);
			session.setAttribute("faqUpdated", 0);
		//	ret.addObject("faqAdded", 0);
			return ret;
		} else {
			session.setAttribute("faqUpdated", 1);
		//	ret.addObject("faqAdded", 1);
			return ret;
		}
	}
	
	@RequestMapping(value = "deleteFaq/{faqId}", method = RequestMethod.GET)
	public String deleteFaq(@PathVariable String faqId, HttpSession session, Model model,
			HttpServletResponse response) throws IOException {
		Faq faqToDelete=faqService.find(Integer.valueOf(faqId));
		if (faqToDelete != null) {
			faqService.delete(faqToDelete.getId());
			session.setAttribute("faqDeleted", 0);
		} else {
			session.setAttribute("faqDeleted", 1);
		}
		return "redirect:/faq/faqList";
	}
	
}
