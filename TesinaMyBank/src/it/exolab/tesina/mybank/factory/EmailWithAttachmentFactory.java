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

public class EmailWithAttachmentFactory extends EmailFactoryData {

	public void sendMail(String mailTo, String oggetto, String comunicazione) {

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
			message.setSubject(oggetto);

			Multipart multipart = new MimeMultipart();

			MimeBodyPart attachmentPart = new MimeBodyPart();

			MimeBodyPart textPart = new MimeBodyPart();

			try {

				File allegato = new File(filePath);

				attachmentPart.attachFile(allegato);
				textPart.setText(comunicazione);
				multipart.addBodyPart(textPart);
				multipart.addBodyPart(attachmentPart);

			} catch (IOException e) {

				e.printStackTrace();
			}
			message.setContent(multipart);

			System.out.println("Invio in corso...");
			// Invio del messaggio
			Transport.send(message);
			System.out.println("Messaggio inviato correttamente.");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}
}
