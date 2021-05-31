package it.exolab.tesina.mybank.factory;

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
