package it.exolab.tesina.mybank.test;

import java.util.Scanner;

import it.exolab.tesina.mybank.factory.OtpEmailFactory;

public class TestSendEmail {
	static OtpEmailFactory otp = new OtpEmailFactory();
	public static void main(String[] args) {
		String sendto="";
		System.out.println("email destinatario: ");
		Scanner s = new Scanner(System.in);
		sendto=s.nextLine();
		System.out.println("l'email verrà inviata a: "+sendto);
		otp.doSendOtpCodeViaEmail(sendto);
	}

}
