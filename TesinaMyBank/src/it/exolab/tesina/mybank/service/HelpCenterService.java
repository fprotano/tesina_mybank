package it.exolab.tesina.mybank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.exolab.tesina.mybank.model.HelpCenter;
import it.exolab.tesina.mybank.model.dto.HelpCenterDTO;
import it.exolab.tesina.mybank.mybatis.mapper.HelpCenterMapper;
import it.exolab.tesina.mybank.repository.HelpCenterRepository;

public class HelpCenterService {
	
	private HelpCenterRepository helpCenterRepository;
	
	@Autowired(required=true)
	public void setHelpCenterRepository(HelpCenterRepository helpCenterRepository) {
		this.helpCenterRepository=helpCenterRepository;
	}
	
	public List<HelpCenterDTO> findAll(){
		return (List<HelpCenterDTO>) helpCenterRepository.findAll();	
	}
	
	public void insert( HelpCenterDTO helpCenter) {
		helpCenterRepository.save(helpCenter);
	}
	
	public void update(HelpCenterDTO helpCenter) {
		helpCenterRepository.save(helpCenter);
	}
	
	
	public void delete(int id) {
		helpCenterRepository.delete(id);
	}
	
	public HelpCenterDTO find(int id) {
		return helpCenterRepository.findOne(id);
	}

	

}
