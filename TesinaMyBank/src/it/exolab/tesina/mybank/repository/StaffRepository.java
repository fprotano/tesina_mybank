package it.exolab.tesina.mybank.repository;

import org.springframework.data.repository.CrudRepository;

import it.exolab.tesina.mybank.model.Staff;
import it.exolab.tesina.mybank.model.dto.StaffDTO;



public interface StaffRepository extends CrudRepository<StaffDTO, Integer> {

	 public StaffDTO findByEmailAndPassword(String email , String password);

	public void save(StaffDTO model, Integer id);
	
	
	
	
	
	
}
