package it.exolab.tesina.mybank.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import com.google.gson.Gson;


import it.exolab.tesina.mybank.exception.EntityNotFoundError;
import it.exolab.tesina.mybank.exception.GenericError;
import it.exolab.tesina.mybank.exception.InvalidEmail;
import it.exolab.tesina.mybank.exception.InvalidPassword;
import it.exolab.tesina.mybank.exception.MaxLengthError;
import it.exolab.tesina.mybank.exception.MinLengthError;
import it.exolab.tesina.mybank.exception.RequiredFieldError;
import it.exolab.tesina.mybank.exception.UniqueFieldError;
import it.exolab.tesina.mybank.factory.TransactionUniqueIdFactory;
import it.exolab.tesina.mybank.model.Account;
import it.exolab.tesina.mybank.model.HTTPResponse;
import it.exolab.tesina.mybank.model.Payment;
import it.exolab.tesina.mybank.model.TransactionUniqueId;
import it.exolab.tesina.mybank.service.AccountService;
import it.exolab.tesina.mybank.service.PaymentService;
import it.exolab.tesina.mybank.service.PushService;
import it.exolab.tesina.mybank.service.TransactionUniqueIdService;

@CrossOrigin
@Controller
@RequestMapping(value = "payment")
public class PaymentController {
	PushService pushservice;
	PaymentService paymentService;
	TransactionUniqueIdService transactionUniqueIdService;
	TransactionUniqueIdFactory factory = new TransactionUniqueIdFactory();
	
	@Autowired
	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}
	@Autowired
	public void setPushService(PushService pushservice) {
		this.pushservice = pushservice;
	}

	@Autowired
	public void setTransactionUniqueIdService(TransactionUniqueIdService transactionUniqueIdService) {
		this.transactionUniqueIdService = transactionUniqueIdService;
	}
	
	
	@RequestMapping(value = "inserisci", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
	public String pay(@ModelAttribute Payment payment, HttpServletRequest request, HttpSession session) {
		payment.setEmail(request.getParameter("email"));
		payment.setAmount(Double.valueOf(request.getParameter("amount")));
		payment.setCustomCode(request.getParameter("customCode"));
		payment.setTransactionId(factory.CreateTransactionUniqueId());
		TransactionUniqueId tui = new TransactionUniqueId();
		tui.setTransactionId(payment.getTransactionId());
		transactionUniqueIdService.insert(tui);
		payment.setUrlSuccess(request.getParameter("urlSuccess"));
		payment.setUrlUnDo(request.getParameter("urlUnDo"));
		payment.setUrlNotify(request.getParameter("urlNotify"));
		paymentService.insert(payment);
		return "redirect: http://localhost:4201/init-payment/"+payment.getId();
		

	}
	

	

	@RequestMapping(value = "sendData", method = RequestMethod.POST,consumes = MediaType.ALL_VALUE)
	@ResponseBody
	public void doAuctionOrderPayment(@RequestBody Payment model, HttpServletResponse httpServletResponse, HTTPResponse response) throws IOException {
		System.out.println(model);
		String data = "";
		data = data.concat("pn[0]=transactionId&pv[0]=" + model.getTransactionId() + "&");
		data = data.concat("pn[1]=amount&pv[1]=" + model.getAmount() + "&");
		data = data.concat("pn[2]=sellerEmail&pv[2]=" + model.getEmail() + "&");
		data = data.concat("pn[3]=buyerEmail&pv[3]=" + model.getAccount().getEmail() + "&");
		data = data.concat("pn[4]=customCode&pv[4]=" + model.getCustomCode());
		System.out.println(data);
		pushservice.notifyTransaction(model.getUrlNotify(), data);
		
		
		
	}
	
	@RequestMapping(value = "sendDataRedirect", method = RequestMethod.POST,consumes = MediaType.ALL_VALUE)
	@ResponseBody
	public String doAuctionOrderPayment() throws IOException {
		
		return "redirect: http://localhost:4201/pagamento-eseguito";
		
		
		
	}
	
	@RequestMapping(value = "fillPayment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse fillPayment(@RequestBody Payment payment, HttpSession session, HTTPResponse response) {
		
			
			
			if(payment!=null) {
			payment = paymentService.findById(payment.getId());
			return new HTTPResponse(payment);
		}	
			return new HTTPResponse("Nessun Pagamento trovato", "Errore 01");
	}
	
	
}
