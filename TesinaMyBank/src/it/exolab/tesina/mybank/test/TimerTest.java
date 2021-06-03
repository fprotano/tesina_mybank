package it.exolab.tesina.mybank.test;

import java.io.Console;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import it.exolab.tesina.mybank.factory.IbanFactory;
import it.exolab.tesina.mybank.factory.OtpCodeFactory;

public class TimerTest {
	

	static Timer timer;

	  public static void  CountDown() {
	    timer = new Timer();
	    //timer.schedule(new ScriviDurata(), 0, 1000);
	  }



	public class ScriviDurata extends TimerTask {

	    // durata del conto alla rovescia in secondi
	    int durata = 60;

	    public void run() {
	      if (durata > 0) {
	        System.out.println("Restano " + durata + " secondi");
	        durata--;
	      }else{
	        System.out.println("FINE!");
	        System.exit(0);
	      }
	    }
	  }

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) throws InterruptedException {
	System.out.println(IbanFactory.Genetateiban());
		
		
		
		
		
//		for (int i = 0; i < 1; i++)
//	        {  
//			
//	            System.out.println("Sleep for 2 seconds.");
//	            Thread.sleep(2000);
//	            
//	        }
//
//	        System.out.println("richiedi nuovo codice otp");
//	    }
//		 System.out.println("INIZIA il conto alla rovescia:");
//		     CountDown();
		   	//	OtpCodeFactory.timer();
//	Timer timer = new Timer();
//	TimerTask timerTask = new TimerTask() {
//		int c = 1;
//		@Override
//		public void run() {
//			
//			if(c>0) { 
//				c--;
//			System.out.println(c +"ci" );
//			
//		}else {
//			System.out.println( c +"ciao");
//			timer.cancel();
//		}
//	}
//	};
//	
//	Calendar date = Calendar.getInstance();
//	date.set(Calendar.SECOND, 0);
//	timer.scheduleAtFixedRate(timerTask, 0, 2000);
//	
	


	
	
	
	}	

}
