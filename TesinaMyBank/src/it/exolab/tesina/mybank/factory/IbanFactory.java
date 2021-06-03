package it.exolab.tesina.mybank.factory;

import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.core.env.SystemEnvironmentPropertySource;

public class IbanFactory {
	
	
	public static String GenerateCreditCardNumber() {
		String numeri ="0123456789";
	     Random rand = new Random();
	     
	 String perRandom = "";
	 int lunghezzaRandom = 16;
	 char[] text= new char[lunghezzaRandom];
	  
		 for(int i =0; i<lunghezzaRandom; i++) {
			 text[i]=numeri.charAt(rand.nextInt(numeri.length()));
			 
		 }
		 for(int i =0; i<text.length; i++) {
			  perRandom += text[i];
		     
	 }
		return perRandom;		
		
	}

	public static String GenerateCin() {
		String numeri ="0123456789";
	     Random rand = new Random();
	     
	 String perRandom = "";
	 int lunghezzaRandom = 3;
	 char[] text= new char[lunghezzaRandom];
	  
		 for(int i =0; i<lunghezzaRandom; i++) {
			 text[i]=numeri.charAt(rand.nextInt(numeri.length()));
			 
		 }
		 for(int i =0; i<text.length; i++) {
			  perRandom += text[i];
		     
	 }
		return perRandom;		
		
	}

	public static String GenerateDataScadenzaCarta() {
		

		 int r = (int) (Math.floor(Math.random() * 12) + 1);
	 
	    String a= "/";
	     
	   
	    
	   Date date = new Date();
       Calendar calendar = Calendar.getInstance();

       calendar.setTime(date);
       int dateYear = calendar.get(Calendar.YEAR );
       dateYear = dateYear + 5;
      
       String result = r+a+dateYear;
    
       return result;
     
       

		
	}



	
			
			
			
	
	
	public static String Genetateiban() {
		//metodo per generare una stringa di 12 numeri casuali
				String numeri ="0123456789";
			     Random rand = new Random();
			     
			 String perRandom = "";
			 int lunghezzaRandom = 12;
			 char[] text= new char[lunghezzaRandom];
			  
				 for(int i =0; i<lunghezzaRandom; i++) {
					 text[i]=numeri.charAt(rand.nextInt(numeri.length()));
					 
				 }
				 for(int i =0; i<text.length; i++) {
					  perRandom += text[i];
				     
			 }		
				 //------------------------------------
		 String ret = "";
		 Iban Iban = new Iban.Builder()
                .countryCode(CountryCode.IT)
               .nationalCheckDigit("X")
                .bankCode("19043")
                .branchCode("00000")
                .accountNumber(perRandom)
                
               
                .build();
	        String iban2 = java.lang.String.valueOf(Iban);
		//System.out.println(iban);
		return iban2;
	}
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

		
	
		
		
 
	
	
	
	
	
	}	
	
	


