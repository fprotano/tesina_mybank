package it.exolab.tesina.mybank.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.exolab.tesina.mybank.model.Staff;
import it.exolab.tesina.mybank.model.dto.StaffDTO;



public interface StaffRepository extends CrudRepository<Staff, Integer> {

	 public Staff findByEmailAndPassword(String email , String password);

//	public void save(Staff model, Integer id);
	 
	 public Staff findByEmailAndPasswordAndOtpCode(String email, String password, String OTP);
	
	
	
	
}
