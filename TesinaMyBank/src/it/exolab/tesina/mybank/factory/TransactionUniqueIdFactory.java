package it.exolab.tesina.mybank.factory;

public class TransactionUniqueIdFactory {

	
	public String CreateTransactionUniqueId() {
		StringBuilder transactionUniqueId = new StringBuilder();
		String[] alfabeto = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
		for(int i=0; i<=200; i++ ){
			int numero = (int) (Math.random()*(34)) + 1;
			String lettera = alfabeto[numero];
			transactionUniqueId.append(lettera);
		}
		return transactionUniqueId.toString();
	}
}
