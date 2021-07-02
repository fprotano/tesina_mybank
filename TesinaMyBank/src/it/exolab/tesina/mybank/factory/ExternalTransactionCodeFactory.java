package it.exolab.tesina.mybank.factory;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ExternalTransactionCodeFactory {
	public static String doAppendLabelOnCustomCode(String customCode) {
		customCode=customCode+"#VOIDED"+LocalDateTime.now();
		return customCode;	
	}
	
	public static boolean doValidateAlteratedCustomCode(String customCode) {
		boolean ret=false;
		// nell'if andrebbe inserito se non esiste già il custom code nel db. da fare dopo
		if(customCode.length()<255 && customCode.length()>0)
			ret=true;
		return ret;
	}
	
	public static String doRetrieveCustomCodeFromVoided(String customCode) {
		String originalCode="";
		int index=customCode.indexOf("#VOIDED");
		originalCode=customCode.substring(0, index);
		return originalCode;
	}
	
	public static LocalDateTime doRetrieveWhenVoided(String customCode) {
		LocalDateTime data = null;
		int index=7+customCode.indexOf("#VOIDED");
		String dataPastVoided=customCode.substring(index,customCode.length());
		data=LocalDateTime.parse(dataPastVoided);
		return data;
	}
}
