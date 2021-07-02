package it.exolab.tesina.mybank.test;

import java.io.IOException;

import com.itextpdf.text.DocumentException;

import it.exolab.tesina.mybank.factory.CreateAndSendPdf;
import it.exolab.tesina.mybank.model.Payment;

public class testPdf {
public static void main (String args[]) {
	
//	try {
//		createAndSendPdf.write();
//	} catch (IOException | DocumentException e) {
//		e.printStackTrace();
//	}
	
	 CreateAndSendPdf pdf = new CreateAndSendPdf();
	 Payment payment = new Payment();
	 payment.setEmail("demo@demo.it");
	 payment.setAmount(123.0);
	 payment.setCustomCode("OMN0JIB8Y6C568R132C68R12BD0N123DN9213DB08Y62368RVD23");
	 payment.setTransactionId("IT102830198723081723017230819720398172309178230819273018273019827309182730189723");
	 payment.setId(2222);
	 System.out.println("payment::"+payment+"\nora creo pdf...");
	 try {
		String path=pdf.doMakePdf(payment);
		System.out.println("creato in: "+path);
	} catch (IOException e) {
		System.out.println("c'è stato un problema.");
		e.printStackTrace();
	}
	 System.out.println("fine.");
}}



