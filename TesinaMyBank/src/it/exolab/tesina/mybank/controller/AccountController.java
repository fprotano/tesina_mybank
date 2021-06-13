package it.exolab.tesina.mybank.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import it.exolab.tesina.mybank.exception.EntityNotFoundError;
import it.exolab.tesina.mybank.exception.FieldError;
import it.exolab.tesina.mybank.exception.GenericError;
import it.exolab.tesina.mybank.exception.InvalidEmail;
import it.exolab.tesina.mybank.exception.InvalidPassword;
import it.exolab.tesina.mybank.exception.MaxLengthError;
import it.exolab.tesina.mybank.exception.MinLengthError;
import it.exolab.tesina.mybank.exception.RequiredFieldError;
import it.exolab.tesina.mybank.exception.UniqueFieldError;
import it.exolab.tesina.mybank.factory.OtpCodeFactory;
import it.exolab.tesina.mybank.model.Account;
import it.exolab.tesina.mybank.model.HTTPResponse;
import it.exolab.tesina.mybank.service.AccountService;

@CrossOrigin
@Controller
@RequestMapping(value = "account")
public class AccountController {
	private AccountService accountService;

	@Autowired
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	@RequestMapping(value = "login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse login(@RequestBody Account account, HTTPResponse response) {
		try {
			account = this.accountService.findByEmailAndPassword(account.getEmail(), account.getPassword());
			account.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
			Long duration = Long.valueOf(((14 * 60) + 59) * 200);
			Timestamp time = Timestamp.valueOf(LocalDateTime.now());
			account.setOtpCodeExpiresAt(new Timestamp(time.getTime() + duration));
			this.accountService.update(account);
			return new HTTPResponse(account);
		} catch (RequiredFieldError | MaxLengthError | MinLengthError | InvalidEmail | InvalidPassword
				| EntityNotFoundError e) {
			return new HTTPResponse(e.getDescription(e), String.valueOf(GenericError.getCode(e)));
		}
	}

	@RequestMapping(value = "registrazione", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse register(@RequestBody Account account, HTTPResponse response) {
		try {
			OtpCodeFactory.setCreatedUpdatedAndOtp(account);
			this.accountService.insert(account);
			return new HTTPResponse(account);
			
		} catch (RequiredFieldError | MaxLengthError | MinLengthError | InvalidEmail | InvalidPassword
				| UniqueFieldError e) {
			return response = new HTTPResponse(e.getDescription(e), String.valueOf(GenericError.getCode(e)));
		}
	}

	@RequestMapping(value = "findOne", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse findOne(@RequestBody Integer id, HTTPResponse response) {
		if (id != null) {
			this.accountService.find(id);
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

	@RequestMapping(value = "findAll", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse findAll() {
		HTTPResponse response = new HTTPResponse();
		List<Account> transazioni = this.accountService.findAll();
		response.setData(transazioni);
		response.setSuccess(true);
		return response;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse delete(@RequestBody Integer id) {
		HTTPResponse response = new HTTPResponse();
		if (id != null) {
			this.accountService.delete(id);
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

	// cofermaotp Angular
	@RequestMapping(value = "confermaOTP", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody

	public HTTPResponse confermaOTP(@RequestBody Account account, HTTPResponse response) {

		if (accountService.findByEmailAndPasswordAndOtp(account.getEmail(), account.getPassword(),
				account.getOtpCode()) != null
				&& !Timestamp.valueOf(LocalDateTime.now()).after(account.getOtpCodeExpiresAt())) {

			response.setData(account);
			response.setSuccess(true);
			return response;
		} else {
			response.setSuccess(false);
			response.setErr("Errore");
			response.setErr_code("01");
			return response;

		}
	}

	@RequestMapping(value ="passwordDimenticata", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HTTPResponse passwordDimenticata(@RequestBody Account account) {

		try {
			account = accountService.findByNameAndSurnameAndEmail(account.getName(), account.getSurname(),
					account.getEmail());
			accountService.update(account);
			System.out.println(account);
			return new HTTPResponse(account);
		} catch (RequiredFieldError | MaxLengthError | MinLengthError | InvalidEmail | InvalidPassword
				| EntityNotFoundError e) {
			return new HTTPResponse(e.getDescription(e), String.valueOf(GenericError.getCode(e)));
		}

	}

}
