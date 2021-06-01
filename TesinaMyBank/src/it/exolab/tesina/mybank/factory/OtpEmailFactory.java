package it.exolab.tesina.mybank.factory;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

// doCreateTextMessage aggiunge il codice otp al corpo dell'email
// doSendOtpCodeViaEmail invia la mail. Va passata al metodo email del ricevente

public class OtpEmailFactory extends EmailFactoryData {
    
    public void doSendOtpCodeViaEmail(String mailto) {
    	
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
            //	Set To: header field of the header. //??
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailto));
            //	setto l'oggetto della mail
            message.setSubject(subject);
            //	setto il testo del messaggio
            String otpCode = OtpCodeFactory.doGenerateNewOtpCode();
            String textMessage=doCreateTextMessage(otpCode);
            
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
    
    public String doCreateTextMessage(String otpCode) {
    	String ret=placeholderMessage.replace("[otpPlaceHolder]", otpCode);
    	return ret;
    }

}