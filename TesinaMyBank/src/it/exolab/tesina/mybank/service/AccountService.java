package it.exolab.tesina.mybank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.exolab.tesina.mybank.model.Account;
import it.exolab.tesina.mybank.repository.AccountRepository;



public class AccountService {
	
private AccountRepository accountRepository;
	
	@Autowired
	public void setAutoreRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	public List<Account> findAll(){
		return (List<Account>)this.accountRepository.findAll();
	}
	public void save(Account model) {
		this.accountRepository.save(model);
	}
	public void delete(int id) {
		this.accountRepository.delete(id);;
	}
	public Account find(int id) {
		return this.accountRepository.findOne(id);
	}
}
