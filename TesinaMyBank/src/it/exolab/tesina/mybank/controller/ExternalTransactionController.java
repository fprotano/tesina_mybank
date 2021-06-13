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

import it.exolab.tesina.mybank.factory.TransactionUniqueIdFactory;
import it.exolab.tesina.mybank.model.ExternalTransaction;
import it.exolab.tesina.mybank.model.HTTPResponse;
import it.exolab.tesina.mybank.model.Staff;
import it.exolab.tesina.mybank.model.TransactionUniqueId;
import it.exolab.tesina.mybank.service.ExternalTransactionService;
import it.exolab.tesina.mybank.service.TransactionUniqueIdService;
import it.exolab.tesina.mybank.util.Util;

@CrossOrigin
@Controller
@RequestMapping(value="externalTransaction")
public class ExternalTransactionController {
	
	private ExternalTransactionService externalTransactionService;
	private TransactionUniqueIdService transactionUniqueIdService;
	private TransactionUniqueIdFactory transactionUniqueIdFactory;
	private Util util = new Util();
	
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
	
	// doppio path variabile: la chiamata ajax è costruita come ${transactionId}+"/"+${refuseDescription}
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
