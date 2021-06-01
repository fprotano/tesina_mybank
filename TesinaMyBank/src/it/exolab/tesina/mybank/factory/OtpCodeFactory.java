package it.exolab.tesina.mybank.factory;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import it.exolab.tesina.mybank.model.Account;
import it.exolab.tesina.mybank.model.Staff;

public  class OtpCodeFactory {
	public static String doGenerateNewOtpCode() {
		String ret = "";
		StringBuilder stringBuilder = new StringBuilder();
		Character c=null;
		for(int i=0;i<10;i++) {
			c=null;
			while(c==null) {
				c=doGenerateChar();
				stringBuilder.append(c);
			}
		}
		ret=stringBuilder.toString();
		System.out.println(ret);
		return ret;
	}
	
	public static void setCreatedUpdatedAndOtp(Object registrato) {
		if(registrato instanceof Staff) {
			Staff staff = (Staff) registrato;
			staff.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
			staff.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
			staff.setOtpCode(doGenerateNewOtpCode());
//			Long duration = Long.valueOf(((14 * 60) + 59) * 1000);
//			Timestamp time = Timestamp.valueOf(LocalDateTime.now());
//			staff.setOtpCodeExpiresAt(new Timestamp(time.getTime() + duration));
		}	
		else if(registrato instanceof Account) {
			Account account = (Account) registrato;
			account.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
			account.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
			account.setOtpCode(doGenerateNewOtpCode());
			Long duration = Long.valueOf(((14 * 60) + 59) * 1000);
			Timestamp time = Timestamp.valueOf(LocalDateTime.now());
			account.setOtpCodeExpiresAt(new Timestamp(time.getTime() + duration));
//			if(Timestamp.valueOf(LocalDateTime.now()).after(time)) {
//				
//			}
			
		}
	}
	
	public static char doGenerateChar() {
		char ret='0';
		boolean flag=false;
		while(flag!=true) {
			ret=(char) (Math.random()*((122-48)+1)+48);
			flag=doEvaluateChar(ret);
		}
		return ret;
	}
	
	public static boolean doEvaluateChar(int number) {
		boolean ret=false;
		if (	  	(number>47&&number<58)
				||	(number>64&&number<91)
				||	(number>96&&number<123)	)
			ret=true;
		return ret;
	}
	
	public static boolean doValidateOtpCode (String otpCode){
		boolean ret=true;
		boolean tempflag=false;
		if(		otpCode.length()==0) {
			for(int i=0;i<10;i++) {
				if (doEvaluateChar(otpCode.charAt(i))==false) {
					tempflag=true;
				}
			}
			if(tempflag==true)
				ret=false;
		} else {
			ret=false;
		}
		return ret;
	}
}
