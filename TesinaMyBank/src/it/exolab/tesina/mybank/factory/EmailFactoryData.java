package it.exolab.tesina.mybank.factory;

public class EmailFactoryData {
	
	// settings 
		final String hostSettings = "mail.smtp.host";
		final String portSettings = "mail.smtp.socketFactory.port";
		final String sslSettings = "mail.smtp.ssl.enable";
		final String authenticationSettings = "mail.smtp.auth";
		final String host = "smtp.gmail.com";
		final String port = "465";
		final String ssl = "true";
		final String auth = "true";
		
		// dati mail che invia
	    final String mailfrom = "testtesinamybank@gmail.com";
	    final String passfrom = "tesina999!!";
	    
	    // oggetto e corpo dell'email
	    String subject ="Test - Richiesta nuovo codice otp";
	    String placeholderMessage = "Di recente hai tentato di accedere al tuo account della MyBank."
	    		+ "\nCome misura di sicurezza, richiediamo una conferma aggiuntiva prima di consentire l'accesso al tuo account."
	    		+ "\nSe riconosci questa attività, confermala con il codice di attivazione.\nCodice otp di attivazione:"
	    		+ "\n\n <h1>[otpPlaceHolder]</h1> \n\n"
	    		+ "Se non riconosci questa attività, contatta immediatamente il nostro Help Center.\n\nMyBank."
	    		+ " Questo messaggio è generato automaticamente, ogni email inviata a questo indirizzo non riceverà risposta.";
	
}
