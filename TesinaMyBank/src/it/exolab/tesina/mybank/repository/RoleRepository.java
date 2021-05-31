package it.exolab.tesina.mybank.repository;

import org.springframework.data.repository.CrudRepository;

import it.exolab.tesina.mybank.model.Role;
import it.exolab.tesina.mybank.model.dto.RoleDTO;

public interface RoleRepository extends CrudRepository<Role, Integer>{
 
}
