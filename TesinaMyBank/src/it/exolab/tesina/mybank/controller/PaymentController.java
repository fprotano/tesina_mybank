package it.exolab.tesina.mybank.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import it.exolab.tesina.mybank.model.Payment;

@CrossOrigin
@Controller
@RequestMapping(value = "payment")
public class PaymentController {

	
	@RequestMapping(value = "inserisci", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
	
	public String pay(@RequestBody String model) {
		System.out.println("Sono arrivato nel cazzo di metodo della banca ");
		System.out.println(model);
		return "redirect:/account/login";

	}
}
