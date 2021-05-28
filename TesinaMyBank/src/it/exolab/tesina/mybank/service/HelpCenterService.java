package it.exolab.tesina.mybank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.exolab.tesina.mybank.model.HelpCenter;
import it.exolab.tesina.mybank.repository.HelpCenterRepository;

public class HelpCenterService {
	
	private HelpCenterRepository helpCenterRepository;
	
	@Autowired(required=true)
	public void setHelpCenterRepository(HelpCenterRepository helpCenterRepository) {
		this.helpCenterRepository=helpCenterRepository;
	}
	
	public List<HelpCenter> findAll(){
		return (List<HelpCenter>) helpCenterRepository.findAll();	
	}
	
	public void insert( HelpCenter helpCenter) {
		helpCenterRepository.save(helpCenter);
	}
	
	public void update(HelpCenter helpCenter) {
		helpCenterRepository.save(helpCenter);
	}
	
	
	public void delete(int id) {
		helpCenterRepository.delete(id);
	}
	
	public HelpCenter find(int id) {
		return helpCenterRepository.findOne(id);
	}

}
