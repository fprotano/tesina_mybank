package it.exolab.tesina.mybank.repository;

import org.springframework.data.repository.CrudRepository;

import it.exolab.tesina.mybank.model.Account;

public interface AccountRepository extends CrudRepository <Account, Integer>{

	public Account findByEmailAndPassword(String email, String password);
	
}
