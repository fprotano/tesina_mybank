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
import it.exolab.tesina.mybank.model.Payment;
import it.exolab.tesina.mybank.model.Staff;
import it.exolab.tesina.mybank.model.TransactionUniqueId;
import it.exolab.tesina.mybank.service.AccountService;
import it.exolab.tesina.mybank.service.ExternalTransactionService;
import it.exolab.tesina.mybank.service.PaymentService;
import it.exolab.tesina.mybank.service.PushService;
import it.exolab.tesina.mybank.service.StaffService;
import it.exolab.tesina.mybank.service.TransactionUniqueIdService;
import it.exolab.tesina.mybank.util.Util;

@CrossOrigin
@Controller
@RequestMapping(value = "externalTransaction")
public class ExternalTransactionController {

	private AccountService accountService;
	private StaffService staffService;
	private ExternalTransactionService externalTransactionService;
	private TransactionUniqueIdService transactionUniqueIdService;
	private PaymentService paymentService;
	private PushService pushService;
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

	@Autowired(required = true)
	public void setExternalTransactionService(ExternalTransactionService externalTransactionService) {
		this.externalTransactionService = externalTransactionService;
	}

	@Autowired
	public void setPaymentService(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@Autowired
	public void setPushService(PushService pushService) {
		this.pushService = pushService;
	}

	@Autowired(required = true)
	public void setTransactionUniqueIdService(TransactionUniqueIdService transactionUniqueIdService) {
		this.transactionUniqueIdService = transactionUniqueIdService;
	}

	@RequestMapping(value = "insert", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse insert(@RequestBody ExternalTransaction externalTransaction) {
		HTTPResponse response = new HTTPResponse();
		if (externalTransaction != null) {
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

	@RequestMapping(value = "findOne", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse findOne(@RequestBody Integer id) {
		HTTPResponse response = new HTTPResponse();
		if (id != null) {
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

	@RequestMapping(value = "findAll", method = RequestMethod.GET)
	@ResponseBody
	public HTTPResponse findAll() {
		HTTPResponse response = new HTTPResponse();
		List<ExternalTransaction> transazioni = this.externalTransactionService.findAll();
		response.setData(transazioni);
		response.setSuccess(true);
		return response;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse delete(@RequestBody Integer id) {
		HTTPResponse response = new HTTPResponse();
		if (id != null) {
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
		session = util.sessionCleanerFromTransactions(session);
		Staff staff = (Staff) session.getAttribute("staff");
		if (staff != null) {
			List<ExternalTransaction> transactions = externalTransactionService
					.findByStatePendingAssignedToId(staff.getId());
			ret.addObject("transactions", transactions);
		}
		return ret;
	}

	@RequestMapping(value = "transactionsHistory", method = RequestMethod.GET)
	public ModelAndView transactionsHistory(Model model, HttpSession session) {
		ModelAndView ret = new ModelAndView("admin/transactionsHistory");
		session = util.sessionCleanerFromTransactions(session);
		Staff staff = (Staff) session.getAttribute("staff");
		if (staff != null) {
			List<ExternalTransaction> transactions = externalTransactionService
					.findByStateProcessedAssignedToId(staff.getId());
			ret.addObject("transactions", transactions);
		}
		return ret;
	}

	@RequestMapping(value = "acceptTransaction/{transactionId}", method = RequestMethod.GET)
	public String acceptTransaction(@PathVariable String transactionId, HttpSession session, Model model,
			HttpServletResponse response) throws IOException {
		ExternalTransaction transaction = externalTransactionService.find(Integer.valueOf(transactionId));
		if (transaction != null) {
			transaction.setTransactionStatusId(3);
			// chiamata sendData a auction. if - se ok allora update transaction, else no
			boolean push = doSendToAuction(transaction);
			if (push) {
				externalTransactionService.update(transaction);
				session.setAttribute("transactionAccepted", 0);
			}
		} else {
			session.setAttribute("transactionAccepted", 1);
		}
		return "redirect:/externalTransaction/transactionsList";
	}

	// doppio path variabile: la chiamata ajax è costruita come
	// ${transactionId}+"/"+${refuseDescription}
	@RequestMapping(value = "refuseTransaction/{transactionId}/{refuseDescription}", method = RequestMethod.POST)
	public void refuseTransaction(@PathVariable String transactionId, @PathVariable String refuseDescription,
			HttpSession session, Model model, HttpServletResponse response) throws IOException {
		ExternalTransaction transaction = externalTransactionService.find(Integer.valueOf(transactionId));
		if (transaction != null) {
			transaction.setTransactionStatusId(2);
			transaction.setTransactionErrorReason(refuseDescription);
			// chiamata sendData a auction. if - se ok allora update transaction, else no
			boolean push = doSendToAuction(transaction);
			if (push) {
				externalTransactionService.update(transaction);
				session.setAttribute("transactionRefused", 0);
				response.getWriter().append("0");
			}
		} else {
			session.setAttribute("transactionRefused", 1);
			response.getWriter().append("1");
		}
	}

	@RequestMapping(value = "doExternalPayment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse doExternalPayment(@RequestBody ExternalPayment externalPayment) {
		HTTPResponse response = new HTTPResponse();

		System.out.println("springservlet stampa externalPayment!! :::" + externalPayment + "\nFINE STAMPA!!!::::");

		if (externalPayment != null) {

			// controllo che il customCode non sia presente. Se esiste
			// lo modifico (VOIDED+data) e faccio l'update
			if (externalTransactionService.findByCustomCode(externalPayment.getPayment().getCustomCode()) != null) {

			}

			// per prima cosa valorizzo l'Account vuoto dentro il payment dentro
			// externalPayment
			System.out.println(externalPayment.getPayment().getEmail());
			System.out.println(accountService.findByEmail(externalPayment.getPayment().getEmail()));

			externalPayment.getPayment()
					.setAccount(accountService.findByEmail(externalPayment.getPayment().getEmail()));

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

			System.out.println("crud:prima insert externalPayment" + externalPayment); // da cancellare
			System.out.println("crud:prima insert externalTransaction:::" + externalTransaction); // da cancellare
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

	// metodo chiamato da accept e refuse externalTransaction per mandare i dati ad
	// auction
	private boolean doSendToAuction(ExternalTransaction externalTransaction) {
		boolean ret = false;
		Payment payment = paymentService.findByTransactionId(externalTransaction.getTransactionId());
		String data = "";
		data = data.concat("pn[0]=transactionId&pv[0]=" + externalTransaction.getTransactionId() + "&");
		data = data.concat("pn[1]=amount&pv[1]=" + externalTransaction.getAmount() + "&");
		// non avrò mai una email nel caso dell'externalTransaction
		data = data.concat("pn[2]=sellerEmail&pv[2]=null&");
		if (externalTransaction.getAccount() != null) {
			data = data.concat("pn[3]=buyerEmail&pv[3]=" + externalTransaction.getAccount().getEmail() + "&");
		} else {
			data = data.concat("pn[3]=buyerEmail&pv[3]=null&");
		}
		data = data.concat("pn[4]=customCode&pv[4]=" + externalTransaction.getCustomCode() + "&");
		data = data.concat("pn[5]=transactionStatus&pv[5]=" + externalTransaction.getTransactionStatusId() + "&");
		data = data.concat("pn[6]=transactionDays&pv[6]=null");
		System.out.println("STAMPO DATA:::" + data + "\nSTAMPO PAYMENT:::" + payment);
		if (payment != null) {
			pushService.notifyTransaction(payment.getUrlNotify(), data);
			paymentService.deleteByTransactionId(payment.getTransactionId());
			System.out.println("Chiamata effettuata.");
			ret = true;
		} else {
			System.out.println("Non sono riuscito a mandare il messaggio.");
		}
		return ret;
	}

}
