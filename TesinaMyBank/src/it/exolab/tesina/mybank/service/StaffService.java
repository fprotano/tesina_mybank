package it.exolab.tesina.mybank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.exolab.tesina.mybank.exception.InvalidEmail;
import it.exolab.tesina.mybank.exception.InvalidPassword;
import it.exolab.tesina.mybank.exception.MaxLengthError;
import it.exolab.tesina.mybank.exception.RequiredFieldError;
import it.exolab.tesina.mybank.model.ExternalTransaction;
import it.exolab.tesina.mybank.model.Staff;
import it.exolab.tesina.mybank.model.dto.StaffDTO;
import it.exolab.tesina.mybank.repository.StaffRepository;
import it.exolab.tesina.mybank.util.Validator;

public class StaffService {

	private StaffRepository staffRepository;
	
	
	@Autowired(required = true)
	public void setStaffRepository(StaffRepository staffRepository) {
		this.staffRepository=staffRepository;
		}
	
	public List<Staff> findAll() {
		return (List<Staff>) this.staffRepository.findAll();
	}
	public void insert(Staff model){
		this.staffRepository.save(model);
	}
	public void update(Staff model) {
		this.staffRepository.save(model);
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
	 public Staff findByEmailAndPasswordAndOtpCode(String email , String password, String OTP) {
		 return this.staffRepository.findByEmailAndPasswordAndOtpCode(email, password, OTP);
	 }
	 public List<Integer> findByStaffValidators() {
		 return (List<Integer>) staffRepository.findByStaffValidators();
	 }
	 public List<Integer> findByStaffHelpdesk() {
		 return (List<Integer>) staffRepository.findByStaffHelpdesk();
	 }
	 private void validateInsertOrUpdate(Staff model) throws RequiredFieldError, MaxLengthError, InvalidEmail, InvalidPassword {
		 
			Validator.required("data creazione", model.getCreatedAt());
			Validator.required("data aggiornamento", model.getUpdatedAt());
			Validator.required("email", model.getEmail());
		    Validator.maxLength("email", model.getEmail(),70);
			Validator.required("password", model.getPassword());
			Validator.maxLength("password", model.getPassword(),255);
			Validator.required("name", model.getName());
			Validator.maxLength("name", model.getName(),30);
			Validator.required("surname", model.getSurname());
			Validator.maxLength("surname", model.getSurname(),40);
			Validator.required("role id", model.getRoleId());
			Validator.maxLength("role id", model.getRoleId(),10);
			Validator.required("next otp code after date ", model.getNextOtpCodeAfterDate());
			Validator.validateEmail("email", model.getEmail());
			Validator.validatePassword("password",  model.getPassword());
		 }
	
}
