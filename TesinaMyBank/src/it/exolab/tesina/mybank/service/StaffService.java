package it.exolab.tesina.mybank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.exolab.tesina.mybank.model.ExternalTransaction;
import it.exolab.tesina.mybank.model.dto.StaffDTO;
import it.exolab.tesina.mybank.repository.StaffRepository;

public class StaffService {

	private StaffRepository staffRepository;
	
	
	@Autowired(required = true)
	public void setStaffRepository(StaffRepository staffRepository) {
		this.staffRepository=staffRepository;
		}
	
	public List<StaffDTO> findAll() {
		return (List<StaffDTO>) this.staffRepository.findAll();
	}
	public void insert(StaffDTO model) {
		this.staffRepository.save(model);
	}
	public void update(StaffDTO model) {
		this.staffRepository.save(model);
	}

	public void delete(Integer id) {
		this.staffRepository.delete(id);
	}
	public StaffDTO findById(Integer id) {
		return this.staffRepository.findOne(id);
	}
	 public StaffDTO findByEmailAndPassword(String email , String password) {
		 return this.staffRepository.findByEmailAndPassword(email, password);
	 }
	

	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
