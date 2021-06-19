package it.exolab.tesina.mybank.factory;


import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
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
import javax.sql.DataSource;


import com.itextpdf.text.DocumentException;

// doCreateTextMessage aggiunge il codice otp al corpo dell'email
// doSendOtpCodeViaEmail invia la mail. Va passata al metodo email del ricevente

public class OtpEmailFactory extends EmailFactoryData {
    
    public static void doSendOtpCodeViaEmail(String mailto, String OTP) {
    	
    	Properties properties = System.getProperties();

        // Setup mail server
        properties.put(hostSettings, host);
        properties.put(portSettings, port);
        properties.put(sslSettings, ssl);
        properties.put(authenticationSettings, auth);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.user", mailto);
        properties.put("mail.smtp.socketFactory.fallback", "false");
        properties.put("mail.smtp.debug", "true");
        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(mailfrom, passfrom);

            }
        });

        // debug SMTP
        session.setDebug(true);

        try {

            MimeMessage message = new MimeMessage(session);
            //	setto il mittente
            message.setFrom(new InternetAddress(mailfrom));
            //	Set To: header field of the header.//??
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailto));
            //	setto l'oggetto della mail
            message.setSubject(subjectForOtp);
            //	setto il testo del messaggio
//            String otpCode = OtpCodeFactory.doGenerateNewOtpCode();
            String textMessage=doCreateTextMessage(OTP);
            
            // .setText per mandare testo non formattato
//            message.setText(textMessage);
            
            // .setContent per mandare testo formattato in html.
            message.setContent(
                   textMessage,
                  "text/html");

            System.out.println(textMessage);							// da cancellare
            // Invio effettivo dell'email
            Transport.send(message);
            System.out.println("Messaggio inviato correttamente");		// da cancellare
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }
    
    public static String doCreateTextMessage(String otpCode) {
    	String ret=placeholderMessageForOtp.replace("[otpPlaceHolder]", otpCode);
    	return ret;
    }
    
    
    public  void doCreateTextMessage1() throws IOException, DocumentException {
     CreateAndSendPdf.write();
	
   
	
		
    }
    public void doSendOtpCodeViaEmail1(String mailto) throws IOException, DocumentException {
    	
    	Properties properties = System.getProperties();

        // Setup mail server
        properties.put(hostSettings, host);
        properties.put(portSettings, port);
        properties.put(sslSettings, ssl);
        properties.put(authenticationSettings, auth);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.user", mailto);
        properties.put("mail.smtp.socketFactory.fallback", "false");
        properties.put("mail.smtp.debug", "true");
        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(mailfrom, passfrom);

            }
        });

        // debug SMTP
        session.setDebug(true);

        try {
        	

        	
            MimeMessage message = new MimeMessage(session);
            //	setto il mittente
            message.setFrom(new InternetAddress(mailfrom));
            //	Set To: header field of the header.//??
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailto));
            //	setto l'oggetto della mail
            message.setSubject("ciao bello");
            //	setto il testo del messaggio
//            String otpCode = OtpCodeFactory.doGenerateNewOtpCode();
            BodyPart messageBodyPart1 = new MimeBodyPart();     
            messageBodyPart1.setText("This is message body");
            MimeBodyPart messageBodyPart2 = new MimeBodyPart();      
            String filename = CreateAndSendPdf.write();    
            DataSource source = (DataSource) new FileDataSource(filename);    
            messageBodyPart2.setDataHandler(new DataHandler((javax.activation.DataSource) source));    
            messageBodyPart2.setFileName(filename);             
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart2); 
           
            
            // .setText per mandare testo non formattato
//            message.setText(textMessage);
            
           

            message.setContent(multipart);							
            // Invio effettivo dell'email
            Transport.send(message);
            System.out.println("Messaggio inviato correttamente");		// da cancellare
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }


}