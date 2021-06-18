package it.exolab.tesina.mybank.factory;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import it.exolab.tesina.mybank.model.HelpCenterThread;

public class HelpCenterThreadFactory {

	
	public HelpCenterThread fillHelpCenterThread(HelpCenterThread helpCenterThread, int id) {
		helpCenterThread.setHelpCenterId(id);
		helpCenterThread.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
		return helpCenterThread;
	}
}
