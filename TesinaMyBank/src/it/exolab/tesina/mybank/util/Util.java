 package it.exolab.tesina.mybank.util;

import javax.servlet.http.HttpSession;

public class Util {
	
	public HttpSession sessionCleaner(HttpSession session) {
		session.removeAttribute("staffAdded");
		session.removeAttribute("passwordUpdated");
		session.removeAttribute("faqAdded");
		return session;
	}
}
