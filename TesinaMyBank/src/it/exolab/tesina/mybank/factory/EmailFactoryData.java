package it.exolab.tesina.mybank.factory;

public class EmailFactoryData {

	// settings
	final static String hostSettings = "mail.smtp.host";
	final static String portSettings = "mail.smtp.socketFactory.port";
	final static String sslSettings = "mail.smtp.ssl.enable";
	final static String authenticationSettings = "mail.smtp.auth";
	final static String host = "smtp.gmail.com";
	final static String port = "465";
	final static String ssl = "true";
	final static String auth = "true";
	static final String filePath = "C:\\Users\\Matteo\\Desktop\\provaPdf.pdf";

	// dati mail che invia
	final static String mailfrom = "testtesinamybank@gmail.com";
	final static String passfrom = "tesina999!!";

	// oggetto e corpo dell'email
	static String subjectForOtp = "Test - Richiesta nuovo codice otp";
	static String placeholderMessageForOtp = "Di recente hai tentato di accedere al tuo account della MyBank."
			+ "\nCome misura di sicurezza, richiediamo una conferma aggiuntiva prima di consentire l'accesso al tuo account."
			+ "\nSe riconosci questa attività, confermala con il codice di attivazione.\nCodice otp di attivazione:"
			+ "\n\n <h1>[otpPlaceHolder]</h1> \n\n"
			+ "Se non riconosci questa attività, contatta immediatamente il nostro Help Center.\n\nMyBank."
			+ " Questo messaggio è generato automaticamente, ogni email inviata a questo indirizzo non riceverà risposta.";

	static String subjectForAttachment = "Test - invio pdf";
	static String placeholderMessageForAttachment = "test di invio.";
	
	// info per invio pdf
	static String placeholderOggetto = "Fattura ordine no: [transactionIdPlaceHolder]";
	static String Comunicazione = "<h3>Riepilogo del tuo ordine</h3>"
			+ "\n Ecco la fattura riguardante il tuo ordine.";

}
