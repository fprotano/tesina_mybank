package it.exolab.tesina.mybank.repository;

import org.springframework.data.repository.CrudRepository;

import it.exolab.tesina.mybank.model.HelpCenter;
import it.exolab.tesina.mybank.model.dto.HelpCenterDTO;

public interface HelpCenterRepository extends CrudRepository<HelpCenterDTO, Integer>{
	

}
