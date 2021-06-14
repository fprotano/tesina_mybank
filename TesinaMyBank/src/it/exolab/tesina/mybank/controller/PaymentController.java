package it.exolab.tesina.mybank.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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
import org.springframework.web.bind.annotation.ResponseBody;

import it.exolab.tesina.mybank.exception.EntityNotFoundError;
import it.exolab.tesina.mybank.exception.GenericError;
import it.exolab.tesina.mybank.exception.InvalidEmail;
import it.exolab.tesina.mybank.exception.InvalidPassword;
import it.exolab.tesina.mybank.exception.MaxLengthError;
import it.exolab.tesina.mybank.exception.MinLengthError;
import it.exolab.tesina.mybank.exception.RequiredFieldError;
import it.exolab.tesina.mybank.exception.UniqueFieldError;
import it.exolab.tesina.mybank.model.Account;
import it.exolab.tesina.mybank.model.HTTPResponse;
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
		return "redirect: http://localhost:4201/";
		

	}
	
	@RequestMapping(value = "fillPayment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse fillPayment(@RequestBody Account account) throws EntityNotFoundError {
		
			Payment payment = new Payment();
			payment = paymentService.findByEmail(account.getEmail());
			if(payment!=null) {
			return new HTTPResponse(payment);
		}	
			return new HTTPResponse("Nessun Pagamento trovato", "Errore 01");
	}
	
	
}
