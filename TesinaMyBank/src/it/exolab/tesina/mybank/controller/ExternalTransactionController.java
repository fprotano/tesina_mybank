package it.exolab.tesina.mybank.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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

import it.exolab.tesina.mybank.factory.StaffAssignFactory;
import it.exolab.tesina.mybank.factory.TransactionUniqueIdFactory;
import it.exolab.tesina.mybank.model.ExternalPayment;
import it.exolab.tesina.mybank.model.ExternalTransaction;
import it.exolab.tesina.mybank.model.HTTPResponse;
import it.exolab.tesina.mybank.model.Staff;
import it.exolab.tesina.mybank.model.TransactionUniqueId;
import it.exolab.tesina.mybank.service.AccountService;
import it.exolab.tesina.mybank.service.ExternalTransactionService;
import it.exolab.tesina.mybank.service.StaffService;
import it.exolab.tesina.mybank.service.TransactionUniqueIdService;
import it.exolab.tesina.mybank.util.Util;

@CrossOrigin
@Controller
@RequestMapping(value="externalTransaction")
public class ExternalTransactionController {
	
	private AccountService accountService;
	private StaffService staffService;
	private ExternalTransactionService externalTransactionService;
	private TransactionUniqueIdService transactionUniqueIdService;
	private TransactionUniqueIdFactory transactionUniqueIdFactory = new TransactionUniqueIdFactory();
	private Util util = new Util();
	private StaffAssignFactory staffAssignFactory = new StaffAssignFactory();
	
	@Autowired(required = true)
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	@Autowired(required = true)
	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}
	@Autowired(required=true)
	public void setExternalTransactionService(ExternalTransactionService externalTransactionService) {
		this.externalTransactionService = externalTransactionService;
	}
	@Autowired(required = true)
	public void setTransactionUniqueIdService(TransactionUniqueIdService transactionUniqueIdService) {
		this.transactionUniqueIdService = transactionUniqueIdService;
	}
	
	@RequestMapping(value="insert", method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse insert(@RequestBody ExternalTransaction externalTransaction) {
		HTTPResponse response = new HTTPResponse();
		if(externalTransaction!=null) {
			TransactionUniqueId transactionUniqueId = new TransactionUniqueId();
			transactionUniqueId.setTransactionId(transactionUniqueIdFactory.CreateTransactionUniqueId());
			transactionUniqueIdService.insert(transactionUniqueId);
			externalTransaction.setTransactionId(transactionUniqueId.getTransactionId());
			externalTransactionService.insert(externalTransaction);
			response.setData(externalTransaction);
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
			this.externalTransactionService.find(id);
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
			List<ExternalTransaction> transazioni = this.externalTransactionService.findAll();
			response.setData(transazioni);
			response.setSuccess(true);
			return response;
	}
	
	@RequestMapping(value="delete", method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse delete(@RequestBody Integer id) {
		HTTPResponse response = new HTTPResponse();
		if(id!=null) {
			this.externalTransactionService.delete(id);
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
	
	@RequestMapping(value = "transactionsList", method = RequestMethod.GET)
	public ModelAndView transactionsList(Model model, HttpSession session) {
		ModelAndView ret = new ModelAndView("admin/transactionsList");
		session=util.sessionCleanerFromTransactions(session);
		Staff staff = (Staff) session.getAttribute("staff");
		if (staff != null) {
			List<ExternalTransaction> transactions = externalTransactionService.findByStatePendingAssignedToId(staff.getId());
			ret.addObject("transactions", transactions);
		}
		return ret;
	}
	
	@RequestMapping(value = "transactionsHistory", method = RequestMethod.GET)
	public ModelAndView transactionsHistory(Model model, HttpSession session) {
		ModelAndView ret = new ModelAndView("admin/transactionsHistory");
		session=util.sessionCleanerFromTransactions(session);
		Staff staff = (Staff) session.getAttribute("staff");
		if (staff != null) {
			List<ExternalTransaction> transactions = externalTransactionService.findByStateProcessedAssignedToId(staff.getId());
			ret.addObject("transactions", transactions);
		}
		return ret;
	}
	
	@RequestMapping(value = "acceptTransaction/{transactionId}", method = RequestMethod.GET)
	public String acceptTransaction(@PathVariable String transactionId, HttpSession session, Model model,
			HttpServletResponse response) throws IOException {
		ExternalTransaction transaction=externalTransactionService.find(Integer.valueOf(transactionId));
		if (transaction != null) {
			transaction.setTransactionStatusId(3);
			externalTransactionService.update(transaction);
			session.setAttribute("transactionAccepted", 0);
		} else {
			session.setAttribute("transactionAccepted", 1);
		}
		return "redirect:/externalTransaction/transactionsList";
	}
	
	// doppio path variabile: la chiamata ajax � costruita come ${transactionId}+"/"+${refuseDescription}
	@RequestMapping(value = "refuseTransaction/{transactionId}/{refuseDescription}", method = RequestMethod.POST)
	public void refuseTransaction(@PathVariable String transactionId, @PathVariable String refuseDescription, HttpSession session, Model model,
			HttpServletResponse response) throws IOException {
		ExternalTransaction transaction=externalTransactionService.find(Integer.valueOf(transactionId));
		if (transaction != null) {
			transaction.setTransactionStatusId(2);
			transaction.setTransactionErrorReason(refuseDescription);
			externalTransactionService.update(transaction);
			session.setAttribute("transactionRefused", 0);
			response.getWriter().append("0");
		} else {
			session.setAttribute("transactionRefused", 1);
			response.getWriter().append("1");
		}
	}
	
	@RequestMapping(value="testExternalPayment", method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse testExternalPayment(@RequestBody ExternalPayment externalPayment) {
		HTTPResponse response = new HTTPResponse();
		
		System.out.println("springservlet stampa externalPayment!! :::"+externalPayment+"\nFINE STAMPA!!!::::");
		
		if(externalPayment!=null) {
			// per prima cosa valorizzo l'Account vuoto dentro il payment dentro externalPayment
			System.out.println(externalPayment.getPayment().getEmail());
			System.out.println(accountService.findByEmail(externalPayment.getPayment().getEmail()));
			
			
			
			
			externalPayment.getPayment().setAccount(accountService.findByEmail(externalPayment.getPayment().getEmail()));
			
			TransactionUniqueId transactionUniqueId = new TransactionUniqueId();
			ExternalTransaction externalTransaction = new ExternalTransaction();
			transactionUniqueId.setTransactionId(transactionUniqueIdFactory.CreateTransactionUniqueId());
			transactionUniqueIdService.insert(transactionUniqueId);
			externalPayment.getPayment().setTransactionId(transactionUniqueId.getTransactionId());
			
			
			externalTransaction.setTransactionId(transactionUniqueId.getTransactionId());
			externalTransaction.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
			externalTransaction.setCustomCode(externalPayment.getPayment().getCustomCode());
			externalTransaction.setTransactionStatusId(1);
			externalTransaction.setVerifyAssignedTo(staffAssignFactory.assignToValidator(staffService));
			externalTransaction.setToAccountId(externalPayment.getPayment().getAccount().getId());
			externalTransaction.setAmount(externalPayment.getPayment().getAmount());
			
			externalTransaction.setCustomerName(externalPayment.getCustomerName());
			externalTransaction.setCustomerSurname(externalPayment.getCustomerSurname());
			externalTransaction.setCustomerCreditCardNo(externalPayment.getCustomerCreditCardNo());
			externalTransaction.setCustomerCreditCardCin(externalPayment.getCustomerCreditCardCin());
			externalTransaction.setCustomerCreditCardExpiresAt(externalPayment.getCustomerCreditCardExpiresAt());
			
			System.out.println("crud:prima insert externalPayment"+externalPayment);
			System.out.println("crud:prima insert externalTransaction:::"+externalTransaction);
			externalTransactionService.insert(externalTransaction);
			
			response.setData(externalPayment);
			response.setSuccess(true);
			return response;
		} else {
			response.setSuccess(false);
			response.setErr("Errore");
			response.setErr_code("01");
			return response;
		}
	}
	
	// da deprecare 
//	@RequestMapping(value="findAllByStaffId", method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public HTTPResponse findAll(@RequestBody Integer id) {
//			HTTPResponse response = new HTTPResponse();
//			List<ExternalTransaction> transazioni = this.externalTransactionService.findAllByStaffId(id);
//			if(transazioni.size()>0) {
//				response.setData(transazioni);
//				response.setSuccess(true);
//				return response;
//			} else {
//				response.setSuccess(false);
//				response.setErr("Errore");
//				response.setErr_code("01");
//				return response;
//			}
//	}

}
