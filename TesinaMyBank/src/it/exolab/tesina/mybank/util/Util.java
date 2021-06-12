 package it.exolab.tesina.mybank.util;

import javax.servlet.http.HttpSession;

public class Util {
	
	public HttpSession sessionCleanerFromHome(HttpSession session) {
		session.removeAttribute("faqAdded");
		return session;
	}
	
	public HttpSession sessionCleanerFromFaq(HttpSession session) {
		session.removeAttribute("staffAdded");
		session.removeAttribute("passwordUpdated");
		return session;
	}
	
	public HttpSession sessionCleanerFromTransactions(HttpSession session) {
		session.removeAttribute("faqAdded");
		session.removeAttribute("staffAdded");
		session.removeAttribute("passwordUpdated");
		return session;
	}
	
	public HttpSession sessionCleanerFromHelpdesk(HttpSession session) {
		session.removeAttribute("faqAdded");
		session.removeAttribute("staffAdded");
		session.removeAttribute("passwordUpdated");
		return session;
	}
	
	public HttpSession sessionCleanerFromRegistrazione(HttpSession session) {
		session.removeAttribute("faqAdded");
		session.removeAttribute("staffAdded");
		session.removeAttribute("passwordUpdated");
		return session;
	}
}
