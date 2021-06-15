package it.exolab.tesina.mybank.factory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.exolab.tesina.mybank.service.StaffService;

public class StaffAssignFactory {

	public Integer assignToHelpdesk(StaffService staffService) {
		List<Integer> idList = staffService.findByStaffHelpdesk();
		Integer ret=null;
		if(idList.size()>0) {
			Integer i=Integer.valueOf((int) (Math.random()*(idList.size())));
			ret=idList.get(i);
		}
		return ret;
	}
	
	public Integer assignToValidator(StaffService staffService) {
		List<Integer> idList = staffService.findByStaffValidators();
		Integer ret=null;
		if(idList.size()>0) {
			Integer i=Integer.valueOf((int) (Math.random()*(idList.size())));
			ret=idList.get(i);
		}
		return ret;
	}
	
}
