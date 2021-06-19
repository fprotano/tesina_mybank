package it.exolab.tesina.mybank.factory;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import it.exolab.tesina.mybank.model.Account;
import it.exolab.tesina.mybank.model.Staff;
import it.exolab.tesina.mybank.service.StaffService;

public class OtpCodeFactory {
	public static String doGenerateNewOtpCode() {
		String ret = "";
		StringBuilder stringBuilder = new StringBuilder();
		Character c = null;
		for (int i = 0; i < 10; i++) {
			c = null;
			while (c == null) {
				c = doGenerateChar();
				stringBuilder.append(c);
			}
		}
		ret = stringBuilder.toString();
		return ret;
	}

	public static void CreateAccountOrStaff(Object registrato) {
		if (registrato instanceof Staff) {
			Staff staff = (Staff) registrato;
			staff.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
			staff.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
			staff.setNextOtpCodeAfterDate(Timestamp.valueOf(LocalDateTime.now().plusHours(1)));
			staff.setOtpCode(doGenerateNewOtpCode());
			// da deprecare
//			Long duration = Long.valueOf(((14 * 60) + 59) * 1000);
//			Timestamp time = Timestamp.valueOf(LocalDateTime.now());
//			staff.setOtpCodeExpiresAt(new Timestamp(time.getTime() + duration));
			staff.setOtpCodeExpiresAt(Timestamp.valueOf(LocalDateTime.now().plusMinutes(3)));
		} else if (registrato instanceof Account) {
			Account account = (Account) registrato;
			account.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
			account.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
			account.setNextOtpCodeAfterDate(Timestamp.valueOf(LocalDateTime.now().plusHours(1)));
			account.setOtpCode(doGenerateNewOtpCode());
			account.setIban(IbanFactory.Genetateiban());
			account.setBalance(1000.0);
			account.setCreditCardNo(IbanFactory.GenerateCreditCardNumber());
			account.setCreditCardCin(IbanFactory.GenerateCin());
			account.setCreditCardExpiresAt(IbanFactory.GenerateDataScadenzaCarta());
			// da deprecare
//			Long duration = Long.valueOf(((14 * 60) + 59) * 200);
//			Timestamp time = Timestamp.valueOf(LocalDateTime.now());
//			account.setOtpCodeExpiresAt(new Timestamp(time.getTime() + duration));
			account.setOtpCodeExpiresAt(Timestamp.valueOf(LocalDateTime.now().plusMinutes(3)));
		}
	}
	
	public static void UpdateOtpTimerForAccountOrStaff(Object model) {
		if (model instanceof Staff) {
			Staff staff = (Staff) model;
			staff.setOtpCodeExpiresAt(Timestamp.valueOf(LocalDateTime.now().plusMinutes(3)));
		} else if (model instanceof Account) {
			Account account = (Account) model;
			account.setOtpCodeExpiresAt(Timestamp.valueOf(LocalDateTime.now().plusMinutes(3)));
		}
	}
	
	public static void UpdateOtpExpirationForAccountOrStaff(Object model) {
		if (model instanceof Staff) {
			Staff staff = (Staff) model;
			staff.setOtpCodeExpiresAt(Timestamp.valueOf(LocalDateTime.now().plusHours(12)));
		} else if (model instanceof Account) {
			Account account = (Account) model;
			account.setOtpCodeExpiresAt(Timestamp.valueOf(LocalDateTime.now().plusHours(12)));
		}
	}

	public static char doGenerateChar() {
		char ret = '0';
		boolean flag = false;
		while (flag != true) {
			ret = (char) (Math.random() * ((122 - 48) + 1) + 48);
			flag = doEvaluateChar(ret);
		}
		return ret;
	}

	public static boolean doEvaluateChar(int number) {
		boolean ret = false;
		if ((number > 47 && number < 58) || (number > 64 && number < 91) || (number > 96 && number < 123))
			ret = true;
		return ret;
	}

	public static boolean doValidateOtpCode(String otpCode) {
		boolean ret = true;
		boolean tempflag = false;
		if (otpCode.length() == 0) {
			for (int i = 0; i < 10; i++) {
				if (doEvaluateChar(otpCode.charAt(i)) == false) {
					tempflag = true;
				}
			}
			if (tempflag == true)
				ret = false;
		} else {
			ret = false;
		}
		return ret;
	}

	// DA DEPRECARE
	// public static void timer() {
	// Timer timer = new Timer();
	//// account.setNextOtpCodeAfterDate(Timestamp.valueOf(LocalDateTime.now()));
	//// Long duration = Long.valueOf(((14 * 60) + 59) * 1000);
	////
	//// Timestamp time = Timestamp.valueOf(LocalDateTime.now());
	//// account.setOtpCodeExpiresAt(new Timestamp(time.getTime() + duration));
	// ;
	// //mail list
	//
	// TimerTask timerTask = new TimerTask() {
	//
	//
	// @Override
	// public void run() {
	// int count = 2;
	//
	// if( count > 0) {
	// count--;
	// System.out.println(" 3 minuti per immetere otp" +count) ;
	// if(count==0) {
	// System.out.println("tempo scaduto");
	// }
	//
	// }else {
	// System.out.println("rifatti mandare il codice");
	// }
	//
	//
	// }

	// final ScheduledExecutorService ses =
	// Executors.newSingleThreadScheduledExecutor();
	// ses.scheduleWithFixedDelay(new Runnable() {
	// @Override
	// public void run() {
	// int c = 1;
	// if(c>0) {
	// System.out.println("hai 3 minuti per inserire l'otp" +c);
	// c--;
	// if (c==0) {
	// System.out.println("ciao");
	// // Thread.sleep(millis);
	// }
	// }
	//
	//
	//
	//
	//
	// }
	// }, 0, 5, TimeUnit.SECONDS);
	//
	// }

	// };
	// timer.schedule(timerTask,1000);
	
	// da deprecare
//	public static void setNewOtpUpdate(Staff staff) {
//		staff.setOtpCode(doGenerateNewOtpCode());
////		Long duration = Long.valueOf(((14 * 60) + 59) * 1000);
////		Timestamp time = Timestamp.valueOf(LocalDateTime.now());
////		staff.setOtpCodeExpiresAt(new Timestamp(time.getTime() + duration));
//		staff.setOtpCodeExpiresAt(Timestamp.valueOf(LocalDateTime.now().plusMinutes(3)));
//		staff.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
//	}

}
