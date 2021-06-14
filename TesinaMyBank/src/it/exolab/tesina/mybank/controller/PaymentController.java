package it.exolab.tesina.mybank.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.exolab.tesina.mybank.model.Payment;
import it.exolab.tesina.mybank.service.AccountService;
import it.exolab.tesina.mybank.service.PaymentService;

@CrossOrigin
@Controller
@RequestMapping(value = "payment")
public class PaymentController {
	
	PaymentService paymentService;
	
	@Autowired
	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	
	
	@RequestMapping(value = "inserisci", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
	public String pay(@ModelAttribute Payment payment, HttpServletRequest request, HttpSession session) {
		payment.setEmail(request.getParameter("email"));
		payment.setAmount(Double.valueOf(request.getParameter("amount")));
		payment.setCustomCode(request.getParameter("customCode"));
		payment.setUrlSuccess(request.getParameter("urlSuccess"));
		payment.setUrlUnDo(request.getParameter("urlUnDo"));
		payment.setUrlNotify(request.getParameter("urlNotify"));
		paymentService.insert(payment);
		session.setAttribute("payment", payment);
		return "redirect: http://localhost:59718/";
		

	}
}
