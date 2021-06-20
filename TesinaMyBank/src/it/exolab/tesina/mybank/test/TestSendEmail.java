package it.exolab.tesina.mybank.test;

import java.io.IOException;
import java.util.Scanner;

import com.itextpdf.text.DocumentException;

import it.exolab.tesina.mybank.factory.OtpCodeFactory;
import it.exolab.tesina.mybank.factory.OtpEmailFactory;

public class TestSendEmail {
	static OtpEmailFactory otp = new OtpEmailFactory();
	static OtpCodeFactory otf = new OtpCodeFactory();
	public static void main(String[] args) throws IOException, DocumentException {
		String sendto="";
		System.out.println("email destinatario: ");
		Scanner s = new Scanner(System.in);
		sendto=s.nextLine();
		System.out.println("l'email verrà  inviata a: "+sendto);
//		otp.doSendOtpCodeViaEmail1(sendto);
	}

}
