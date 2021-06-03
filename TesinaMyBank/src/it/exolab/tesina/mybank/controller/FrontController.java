package it.exolab.tesina.mybank.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.exolab.tesina.mybank.model.Staff;


@Controller
public class FrontController {

	@RequestMapping(value="index")
	public String index() {
		return "index";
		
	}
	
	@RequestMapping(value="register")
	public ModelAndView register() {
		ModelAndView ret = new ModelAndView("registrazione");
		ret.addObject("staff", new Staff());
		return ret;
		
	}
	@RequestMapping(value="register", method=RequestMethod.POST)
	public String doRegister(@Valid @ModelAttribute Staff staff, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("staff", staff);
			
			return "registrazione";
		}
		
		return "redirect:/index";
		
	}
}
