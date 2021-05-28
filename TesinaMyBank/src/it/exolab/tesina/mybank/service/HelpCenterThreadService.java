package it.exolab.tesina.mybank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.exolab.tesina.mybank.model.HelpCenterThread;
import it.exolab.tesina.mybank.repository.HelpCenterThreadRepository;

public class HelpCenterThreadService {
	
	private HelpCenterThreadRepository helpCenterThreadRepository;
 
	@Autowired(required=true)
	public void setHelpCenterThreadRepository(HelpCenterThreadRepository helpCenterThreadRepository) {
		this.helpCenterThreadRepository = helpCenterThreadRepository;
	}
	
	public List<HelpCenterThread> findAll(){
		return (List<HelpCenterThread>) helpCenterThreadRepository.findAll();
	}
	public void insert(HelpCenterThread internalTransaction){
		this.helpCenterThreadRepository.save(internalTransaction);
	}
	
	public void delete(Integer id){
		this.helpCenterThreadRepository.delete(id);
	}
	public void update(HelpCenterThread helpCenterThread) {
		helpCenterThreadRepository.save(helpCenterThread);
	}
	
	public HelpCenterThread find(Integer id){
		return helpCenterThreadRepository.findOne(id);
	}

}
