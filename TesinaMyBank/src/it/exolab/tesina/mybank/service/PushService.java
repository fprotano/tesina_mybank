package it.exolab.tesina.mybank.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PushService {

	
	/**
	 * <p>Invia la notifica al sito delle aste quando la transazione è stata completata</p> 
	 * @param url: url del sito delle aste
	 * @param data: dati da inviare al sito delle aste
	 */
	public void notifyTransaction(String url,String data) {
		
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(5000);//attende 5 secondi
					
					sendPost(url,data);
				} catch (InterruptedException | IOException e) {
				
				}
				
			}
			
		});
		t.start();
	}
	
	private  void sendPost(String url,String data) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		// For POST only - START
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(data.getBytes());
		os.flush();
		os.close();
		// For POST only - END

		int responseCode = con.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			
		
		}
	}
}
