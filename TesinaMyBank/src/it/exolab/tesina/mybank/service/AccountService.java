package it.exolab.tesina.mybank.service;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;

import it.exolab.tesina.mybank.exception.InvalidEmail;
import it.exolab.tesina.mybank.exception.InvalidPassword;
import it.exolab.tesina.mybank.exception.MaxLengthError;
import it.exolab.tesina.mybank.exception.RequiredFieldError;
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
	
	public List<Account> findAll(){
		return (List<Account>)this.accountRepository.findAll();
	}
	public void insert(Account model) {
		this.accountRepository.save(model);
	}
	public void update(Account model) {
		this.accountRepository.save(model);
	}
	public void delete(int id) {
		this.accountRepository.delete(id);;
	}
	public Account find(int id) {
		return this.accountRepository.findOne(id);
	} 
	public Account findByEmailAndPassword(String email, String password) {
		return this.accountRepository.findByEmailAndPassword(email, password);
	}
	

	
}
