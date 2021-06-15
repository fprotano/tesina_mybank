package it.exolab.tesina.mybank.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import it.exolab.tesina.mybank.model.Account;


public interface AccountRepository extends CrudRepository <Account, Integer>{
	@Transactional
	@Modifying
	@Query("UPDATE Account a SET a.password =?4"
			+ " WHERE a.name = ?1 AND a.surname =?2 AND a.email=?3")
	public void updateForNewPassword(String name,String surname,String email,String password);
	public Account findByEmailAndPassword(String email, String password);
	public Account findByEmailAndPasswordAndOtpCode(String email , String password , String OTP);
	public Account findByNameAndSurnameAndEmail(String name , String surname , String email);
	public Account findByEmail(String email);
}
