package it.exolab.tesina.mybank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.exolab.tesina.mybank.model.ExternalTransaction;
import it.exolab.tesina.mybank.model.Staff;
import it.exolab.tesina.mybank.repository.StaffRepository;

public class StaffService {

	private StaffRepository staffRepository;
	
	
	@Autowired(required = true)
	public void setStaffRepository(StaffRepository staffRepository) {
		this.staffRepository=staffRepository;
		}
	
	public List<Staff> findAll() {
		return (List<Staff>) this.staffRepository.findAll();
	}
	public void insert(Staff model) {
		this.staffRepository.save(model);
	}
	public void update(Integer id) {
		this.staffRepository.save(id);
	}

	public void delete(Integer id) {
		this.staffRepository.delete(id);
	}
	public Staff findById(Integer id) {
		return this.staffRepository.findOne(id);
	}
	 public Staff findByEmailAndPassword(String email , String password) {
		 return this.staffRepository.findByEmailAndPassword(email, password);
	 }
	

	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
