package it.exolab.tesina.mybank.repository;

import java.util.List; 

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import it.exolab.tesina.mybank.model.Staff;
import it.exolab.tesina.mybank.model.dto.StaffDTO;



public interface StaffRepository extends CrudRepository<Staff, Integer> {

	 public Staff findByEmailAndPassword(String email , String password);

//	public void save(Staff model, Integer id);
	 
	 public Staff findByEmailAndPasswordAndOtpCode(String email, String password, String OTP);
	
	 @Modifying
	 @Query("SELECT s.id FROM Staff AS s WHERE s.roleId = 3 ")
	 public List<Integer> findbyStaffMinimum();
	
	
}
