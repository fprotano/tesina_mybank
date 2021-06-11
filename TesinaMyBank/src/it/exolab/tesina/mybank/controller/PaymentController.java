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

	
	@RequestMapping(value = "inserisci", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String pay(@RequestBody Payment payment, HttpSession session) {
		session.setAttribute("payment", payment);
		return "account/login";

	}
}
