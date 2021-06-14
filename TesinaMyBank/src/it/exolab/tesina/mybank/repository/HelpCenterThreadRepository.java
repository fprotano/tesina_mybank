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
	@Query("SELECT hct FROM HelpCenterThread hct JOIN FETCH hct.helpCenter AS hc WHERE hc.fromAccountId=?1")
	public List<HelpCenterThread> findbyFromAccountId(Integer id);
	
	@Transactional
	@Modifying
	@Query("SELECT hct FROM HelpCenterThread hct JOIN FETCH hct.helpCenter AS hc WHERE hc.assignedToId=?1 AND hc.fromAccountId=?2")
	public List<HelpCenterThread> findByStaffIdAndAccountId(int staffId, int accountId);
	
	public List<HelpCenterThread> findByHelpCenterId(int helpcenterId);
}
