package it.exolab.tesina.mybank.exception;

public class GenericError extends Exception {
	public static final int C_999 = 999;
	public static final int A_001 = 001;
	public static final int A_002= 002;
	public static final int A_003= 003;
	public static final int A_004= 004;
	public static final int B_001= 001;
	public static final int B_002= 002;
	public static final int B_003= 003;
	public static final int B_004= 004;
	public static final int C_001= 001;
	public static final int C_002= 002;
	public static final int C_003= 003;
	public static final int D_001= 001;
	public static final int D_002= 002;
	public static final int D_003= 003;
	
	private int code;
	public GenericError(int code) {
		this.code=code;
	}

	public int getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return "Errore sconosciuto n." + code+", contattare l'amministratore";
	}
	
	
	public static Integer getCode(Exception ex) {

		if(ex instanceof RequiredFieldError) {
			RequiredFieldError rq = (RequiredFieldError) ex;
			
		}
		if(ex instanceof MaxLengthError) {
			MaxLengthError rq = (MaxLengthError) ex;
		   if(rq.getField().equals("password"))
			   return GenericError.A_002;
		   if(rq.getField().equals("email"))
			   return GenericError.B_002;
			   
		}
		if(ex instanceof UniqueFieldError) {
			UniqueFieldError rq = (UniqueFieldError) ex;
		
		}
		if(ex instanceof FormatError) {
			FormatError rq = (FormatError) ex;
		
		}
		if(ex instanceof MinLengthError) {
			MinLengthError rq = (MinLengthError) ex;
		
		}
		if(ex instanceof MinValueError) {
			MinValueError rq = (MinValueError) ex;
		
		}
		if(ex instanceof InvalidEmail ) {
			InvalidEmail rq =  (InvalidEmail) ex;
		

		}
		if(ex instanceof InvalidPassword) {
			InvalidPassword rq = (InvalidPassword) ex;

		}
		
		
		return null;

	}
	
	
	
	
	
}
