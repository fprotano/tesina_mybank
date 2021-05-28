package it.exolab.tesina.mybank.service;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;

import it.exolab.tesina.mybank.model.dto.RoleDTO;
import it.exolab.tesina.mybank.repository.RoleRepository;


 
public class RoleService {
	
	private RoleRepository roleRepository;
	
	@Autowired
	public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	public List<RoleDTO> findAll(){
		return (List<RoleDTO>)this.roleRepository.findAll();
	}
	public void insert(RoleDTO model) {
		this.roleRepository.save(model);
	}
	public void update(RoleDTO model) {
		this.roleRepository.save(model);
	}
	public void delete(int id) {
		this.roleRepository.delete(id);;
	}
	public RoleDTO find(int id) {
		return this.roleRepository.findOne(id);
	}
}
