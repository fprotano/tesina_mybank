package it.exolab.tesina.mybank.factory;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import it.exolab.tesina.mybank.model.Payment;

public class EmailWithAttachmentFactory extends EmailFactoryData {

	public void sendMail(Payment payment, String mailTo) {

		Properties properties = System.getProperties();

		// Setup mail server
		properties.put(hostSettings, host);
		properties.put(portSettings, port);
		properties.put(sslSettings, ssl);
		properties.put(authenticationSettings, auth);

		// Get the Session object.// and pass
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(mailfrom, passfrom);
			}
		});
		// session.setDebug(true);
		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// mail dell'emittente
			message.setFrom(new InternetAddress(mailfrom));

			// Set To: email del destinatario
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));

			// Set Subject: oggetto
			String oggetto = doReplaceTransactionId(payment.getTransactionId());
			message.setSubject(oggetto);

			Multipart multipart = new MimeMultipart();

			MimeBodyPart attachmentPart = new MimeBodyPart();

			MimeBodyPart textPart = new MimeBodyPart();

			try {
				String filepath = new CreateAndSendPdf().writeCapo(payment);
				File allegato = new File(filepath);
				attachmentPart.attachFile(allegato);
				textPart.setText(Comunicazione);
				multipart.addBodyPart(textPart);
				multipart.addBodyPart(attachmentPart);

			} catch (IOException e) {

				e.printStackTrace();
			}
			message.setContent(multipart, "text/html");

			System.out.println("Invio in corso...");
			// Invio del messaggio
			Transport.send(message);
			System.out.println("Messaggio inviato correttamente.");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
 
	}
	
	public static String doReplaceTransactionId(String payment_transaction_id) {
		String ret = placeholderOggetto.replace("[transactionIdPlaceHolder]", payment_transaction_id);
		return ret;
	}
}
