package it.exolab.tesina.mybank.factory;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.exolab.tesina.mybank.model.HelpCenter;
import it.exolab.tesina.mybank.model.Staff;
import it.exolab.tesina.mybank.service.StaffService;

public class HelpCenterFactory {

	private StaffService staffService;
	
	@Autowired(required=true)
	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}
	
	public HelpCenter fillHelpCenter(HelpCenter helpcenter, int id) {
		helpcenter.setFromAccountId(id); //id dell'utente
		helpcenter.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
		List<Staff> staff = this.staffService.findAll();
		return helpcenter;
	}
}
