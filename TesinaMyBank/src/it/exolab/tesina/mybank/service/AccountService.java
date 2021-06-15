package it.exolab.tesina.mybank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.exolab.tesina.mybank.exception.EntityNotFoundError;
import it.exolab.tesina.mybank.exception.InvalidEmail;
import it.exolab.tesina.mybank.exception.InvalidPassword;
import it.exolab.tesina.mybank.exception.MaxLengthError;
import it.exolab.tesina.mybank.exception.MinLengthError;
import it.exolab.tesina.mybank.exception.RequiredFieldError;
import it.exolab.tesina.mybank.exception.UniqueFieldError;
import it.exolab.tesina.mybank.model.Account;
import it.exolab.tesina.mybank.model.dto.AccountDTO;
import it.exolab.tesina.mybank.repository.AccountRepository;
import it.exolab.tesina.mybank.util.Validator;

public class AccountService {

	private AccountRepository accountRepository;

	@Autowired
	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public List<Account> findAll() {
		return (List<Account>) this.accountRepository.findAll();
	}

	public void insert(Account model)
			throws RequiredFieldError, MaxLengthError, MinLengthError, InvalidEmail, InvalidPassword, UniqueFieldError {
		validateInsert(model);
		this.accountRepository.save(model);
	}

	public void update(Account model) throws RequiredFieldError, MaxLengthError, MinLengthError, InvalidEmail, InvalidPassword, UniqueFieldError{
		validateUpdate(model);
		this.accountRepository.save(model);
	}
	public void updatePassword(Account model) throws RequiredFieldError, MaxLengthError, MinLengthError, InvalidEmail, InvalidPassword, UniqueFieldError {
		validateUpdate(model);
		this.accountRepository.updateForNewPassword(model.getName(), model.getSurname(), model.getEmail(),model.getPassword());
		}

	public void delete(int id) {
		this.accountRepository.delete(id);
		;
	}

	public Account find(int id) {
		return this.accountRepository.findOne(id);
	}
	
	public Account findByEmail(String email) {
		return this.accountRepository.findByEmail(email);
	}

	public Account findByEmailAndPassword(String email, String password) {
		return this.accountRepository.findByEmailAndPassword(email, password);
	}

	public Account findByEmailAndPasswordAndOtp(String email, String password, String OTP) {
		return this.accountRepository.findByEmailAndPasswordAndOtpCode(email, password, OTP);
	}

	public Account findByNameAndSurnameAndEmail(String name, String surname, String email) {
		return this.accountRepository.findByNameAndSurnameAndEmail(name, surname, email);
	}

	private void validateInsert(Account model)
			throws RequiredFieldError, MaxLengthError, MinLengthError, InvalidEmail, InvalidPassword, UniqueFieldError {
		validateInsertOrUpdate(model);
		Account ret = this.accountRepository.findByEmailAndPassword(model.getEmail(), model.getPassword());
		if (ret != null)
			throw new UniqueFieldError("email");
	}

	private void validateInsertOrUpdate(Account model)
			throws RequiredFieldError, MaxLengthError, MinLengthError, InvalidEmail, InvalidPassword {
		Validator.required("email", model.getEmail());
		Validator.required("password", model.getPassword());
		Validator.required("name", model.getName());
		Validator.required("surname", model.getSurname());
		Validator.maxLength("email", model.getEmail(), 20);
		Validator.maxLength("password", model.getPassword(), 20);
		Validator.maxLength("name", model.getName(), 20);
		Validator.maxLength("surname", model.getSurname(), 20);
		Validator.minLength("email", model.getEmail(), 1);
		Validator.minLength("password", model.getPassword(), 1);
		Validator.minLength("name", model.getName(), 1);
		Validator.minLength("surname", model.getSurname(), 1);
		Validator.validateEmail("email", model.getEmail());
		Validator.validatePassword("password", model.getPassword());
	}

	private void validateUpdate(Account model)
		throws RequiredFieldError, MaxLengthError, MinLengthError, InvalidEmail, InvalidPassword, UniqueFieldError {
		validateInsertOrUpdate(model);
	Account	ret = this.accountRepository.findByNameAndSurnameAndEmail(model.getName(), model.getSurname(), model.getEmail());
		if(ret == null)
			throw new UniqueFieldError("Campo Già esistente");
	}

}
