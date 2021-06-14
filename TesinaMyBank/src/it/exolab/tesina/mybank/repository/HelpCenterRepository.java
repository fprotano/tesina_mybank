package it.exolab.tesina.mybank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.exolab.tesina.mybank.model.HelpCenter;

public interface HelpCenterRepository extends CrudRepository<HelpCenter, Integer> {

	public List<HelpCenter> findByFromAccountId(int fromAccountId);

	public List<HelpCenter> findByAssignedToId(int id);

	@Modifying
	@Query("SELECT hc FROM HelpCenter hc WHERE assignedToId = ?1 AND closedAt IS NULL ")
	public List<HelpCenter> findByAssignedToIdAndIsOpen(int id);

	@Modifying
	@Query("SELECT hc FROM HelpCenter hc WHERE assignedToId = ?1 AND closedAt IS NOT NULL ")
	public List<HelpCenter> findByAssignedToIdAndIsClosed(int id);
}
