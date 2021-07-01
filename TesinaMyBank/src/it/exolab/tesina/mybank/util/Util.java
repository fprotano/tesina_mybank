 package it.exolab.tesina.mybank.util;

import javax.servlet.http.HttpSession;

public class Util {
	
	//dentro StaffController
	private String staffRegistrato="staffRegistrato";
	private String staffAdded="staffAdded";
	private String passwordUpdated="passwordUpdated";
	private String staffUpdated="staffUpdated";
	// dentro FaqController
	private String faqAdded="faqAdded";
	private String faqUpdated="faqUpdated";
	private String faqDeleted="faqDeleted";
	
	public HttpSession sessionCleanerFromHome(HttpSession session) {
		session.removeAttribute(staffRegistrato);
		session.removeAttribute(staffAdded);
		session.removeAttribute(staffUpdated);
		session.removeAttribute(passwordUpdated);
		
		session.removeAttribute(faqAdded);
		session.removeAttribute(faqUpdated);
		session.removeAttribute(faqDeleted);
		return session;
	}
	
	public HttpSession sessionCleanerFromFaq(HttpSession session) {
		session.removeAttribute(staffRegistrato);
		session.removeAttribute(staffAdded);
		session.removeAttribute(staffUpdated);
		session.removeAttribute(passwordUpdated);
		return session;
	}
	
	public HttpSession sessionCleanerFromTransactions(HttpSession session) {
		session.removeAttribute(staffRegistrato);
		session.removeAttribute(faqAdded);
		session.removeAttribute(faqUpdated);
		session.removeAttribute(faqDeleted);
		session.removeAttribute(staffAdded);
		session.removeAttribute(passwordUpdated);
		return session;
	}
	
	public HttpSession sessionCleanerFromHelpdesk(HttpSession session) {
		session.removeAttribute(staffRegistrato);
		session.removeAttribute(staffAdded);
		session.removeAttribute(staffUpdated);
		session.removeAttribute(passwordUpdated);
		
		session.removeAttribute(faqAdded);
		session.removeAttribute(faqUpdated);
		session.removeAttribute(faqDeleted);
		return session;
	}
	
	public HttpSession sessionCleanerFromRegistrazione(HttpSession session) {
		session.removeAttribute(staffAdded);
		session.removeAttribute(staffUpdated);
		session.removeAttribute(passwordUpdated);
		
		session.removeAttribute(faqAdded);
		session.removeAttribute(faqUpdated);
		session.removeAttribute(faqDeleted);
		return session;
	}
	
	public HttpSession sessionCleanerFromStaffList(HttpSession session) {
		session.removeAttribute(staffRegistrato);
		session.removeAttribute(staffAdded);
		session.removeAttribute(staffUpdated);
		session.removeAttribute(passwordUpdated);
		
		session.removeAttribute(faqAdded);
		session.removeAttribute(faqUpdated);
		session.removeAttribute(faqDeleted);
		return session;
	}
}
