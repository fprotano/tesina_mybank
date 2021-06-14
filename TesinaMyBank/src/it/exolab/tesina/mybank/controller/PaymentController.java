package it.exolab.tesina.mybank.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@CrossOrigin
@Controller
@RequestMapping(value = "payment")
public class PaymentController {

	
	@RequestMapping(value = "inserisci", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String pay(@RequestBody String model) {
		System.out.println("Sono arrivato nel metodo della banca ");
		System.out.println(model);
		return "redirect:/account/login";

	}
}
