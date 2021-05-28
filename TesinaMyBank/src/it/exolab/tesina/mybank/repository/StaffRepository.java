package it.exolab.tesina.mybank.repository;

import org.springframework.data.repository.CrudRepository;

import it.exolab.tesina.mybank.model.Staff;



public interface StaffRepository extends CrudRepository<Staff, Integer> {

	 public Staff findByEmailAndPassword(String email , String password);

	public void save(Integer id);
	
	
	
	
	
	
}
