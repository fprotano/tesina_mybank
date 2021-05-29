package it.exolab.tesina.mybank.repository;

import org.springframework.data.repository.CrudRepository;

import it.exolab.tesina.mybank.model.HelpCenterThread;
import it.exolab.tesina.mybank.model.dto.HelpCenterThreadDTO;

public interface HelpCenterThreadRepository extends CrudRepository<HelpCenterThreadDTO, Integer>{

}   
