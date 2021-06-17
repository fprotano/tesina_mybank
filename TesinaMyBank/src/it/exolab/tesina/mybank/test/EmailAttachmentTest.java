package it.exolab.tesina.mybank.test;

import it.exolab.tesina.mybank.factory.EmailWithAttachmentFactory;
import it.exolab.tesina.mybank.factory.CreateAndSendPdf;

public class EmailAttachmentTest {
	static EmailWithAttachmentFactory eaf = new EmailWithAttachmentFactory();
	static CreateAndSendPdf casp = new CreateAndSendPdf();
	public static void main(String[] args) {
		String ri=casp.write();
		System.out.println(ri);
		eaf.sendMail("mbertolin88@gmail.com", "prova", "prova testo");
		int rd=casp.delete();
		if(rd==1) {
			System.out.println("Il pdf temporaneo è stato rimosso.");
		} else {
			System.out.println("Il pdf temporaneo non è stato trovato.");
		}
	}

}
