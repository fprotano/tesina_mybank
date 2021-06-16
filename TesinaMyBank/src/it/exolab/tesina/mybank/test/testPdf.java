package it.exolab.tesina.mybank.test;

import java.io.IOException;

import it.exolab.tesina.mybank.factory.createAndSendPdf;

public class testPdf {
public static void main (String args[]) {
	try {
		createAndSendPdf.write();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
}
}
