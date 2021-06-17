package it.exolab.tesina.mybank.test;

import it.exolab.tesina.mybank.factory.EmailWithAttachmentFactory;

public class EmailAttachmentTest {
	static EmailWithAttachmentFactory eaf = new EmailWithAttachmentFactory();
	public static void main(String[] args) {
		eaf.sendMail("mbertolin88@gmail.com", "prova", "prova testo");
	}

}
