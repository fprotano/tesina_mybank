package it.exolab.tesina.mybank.factory;

import java.security.SecureRandom;
import java.util.Random;

import org.iban4j.CountryCode;
import org.iban4j.Iban;

public class IbanFactory {
	
	
	private static final String IT = null;





	public static void GenerateIban () {
		  String lower = "abcdefghijklmnopqrstuvwxyz";
	        String upper = lower.toUpperCase();
	        String numeri = "0123456789";
	        String perRandom = upper + lower + numeri;
	        
	        int lunghezzaRandom = 27;

	        SecureRandom sr = new SecureRandom();
	        StringBuilder sb = new StringBuilder(lunghezzaRandom);
	        for (int i = 0; i < lunghezzaRandom; i++) {
	            int randomInt = sr.nextInt(perRandom.length());
	            char randomChar = perRandom.charAt(randomInt);
	            
	            sb.append(randomChar);
	           
	        }

	        System.out.println(sb.toString());
	    }
			
			
			
	
	
	public static void iban() {
		
		
		Iban iban = new Iban.Builder()
                .countryCode(CountryCode.IT)
               .nationalCheckDigit("X")
                .bankCode("19043")
                .branchCode("00000")
                .accountNumber("012345967890")
                
               
                .build();
		System.out.println(iban);
	}
		
 public static void iban2() {
	 String numeri ="0123456789";

	 String perRandom = numeri;
     
     int lunghezzaRandom = 9;

     SecureRandom sr = new SecureRandom();
     StringBuilder sb = new StringBuilder(lunghezzaRandom);
     for (int i = 0; i < lunghezzaRandom; i++) {
         int randomInt = sr.nextInt(perRandom.length());
         char randomChar = perRandom.charAt(randomInt);
         
         sb.append(randomChar);
         

	 Iban iban =  new Iban .Builder()
             .countryCode ( CountryCode . AT )
             .bankCode( "190843" )
             .accountNumber((String) sb)
             .build();
	 System.out.println(iban);
	 
 }
		
	
		
		
 }	
	
	
	
	
	
	}	
	
	


