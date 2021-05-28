package it.exolab.tesina.mybank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.exolab.tesina.mybank.model.Role;
import it.exolab.tesina.mybank.repository.RoleRepository;



public class RoleService {
	
	private RoleRepository roleRepository;
	
	@Autowired
	public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	public List<Role> findAll(){
		return (List<Role>)this.roleRepository.findAll();
	}
	public void insert(Role model) {
		this.roleRepository.save(model);
	}
	public void update(Role model) {
		this.roleRepository.save(model);
	}
	public void delete(int id) {
		this.roleRepository.delete(id);;
	}
	public Role find(int id) {
		return this.roleRepository.findOne(id);
	}
}
