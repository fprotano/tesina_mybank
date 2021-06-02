package it.exolab.tesina.mybank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import it.exolab.tesina.mybank.model.HelpCenterThread;
import it.exolab.tesina.mybank.model.dto.HelpCenterThreadDTO;

public interface HelpCenterThreadRepository extends CrudRepository<HelpCenterThread, Integer>{

	@Transactional
	@Modifying
	@Query("SELECT hc FROM HelpCenter hc JOIN FETCH hc.helpCenter WHERE hc.fromAccountId=?1")
	public List<HelpCenterThread> findbyFromAccountId(Integer id);
	
	
}   
