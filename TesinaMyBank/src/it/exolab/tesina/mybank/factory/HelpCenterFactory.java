package it.exolab.tesina.mybank.factory;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.exolab.tesina.mybank.model.HelpCenter;
import it.exolab.tesina.mybank.model.Staff;
import it.exolab.tesina.mybank.service.StaffService;

public class HelpCenterFactory {
	
	public HelpCenter fillHelpCenter(HelpCenter helpcenter, int id, StaffService staffService) {
		helpcenter.setFromAccountId(id); //id dell'utente
		List<Integer> staff = staffService.findbyStaffMinimum();
		helpcenter.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
		helpcenter.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
		int numero = (int) (Math.random()*staff.size());
		int staffId = staff.get(numero);
		helpcenter.setAssignedToId(staffId);
		return helpcenter;
	}
}
