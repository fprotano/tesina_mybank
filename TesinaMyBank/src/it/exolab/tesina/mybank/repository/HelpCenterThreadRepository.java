package it.exolab.tesina.mybank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.exolab.tesina.mybank.model.HelpCenterThread;
import it.exolab.tesina.mybank.model.dto.HelpCenterThreadDTO;

public interface HelpCenterThreadRepository extends CrudRepository<HelpCenterThread, Integer>{

	@Query("SELECT hc, hct FROM help_center hc INNER JOIN help_center_thread hct ON hc.id=hct.help_center_id WHERE hc.from_account_id=?1")
	public List<HelpCenterThread> findbyFromAccountId(Integer id);
}   
