package it.exolab.tesina.mybank.test;

import it.exolab.tesina.mybank.factory.FactoryConvertDTOtoModelOrViceVersa;
import it.exolab.tesina.mybank.model.TransactionUniqueId;
import it.exolab.tesina.mybank.model.dto.TransactionUniqueIdDTO;

public class FactoryTest extends FactoryConvertDTOtoModelOrViceVersa<TransactionUniqueIdDTO,TransactionUniqueId> {
	
	public FactoryTest() {
		super(TransactionUniqueIdDTO.class, TransactionUniqueId.class);
	}
	public void stampaAttributoDTO() {
		dto.setTransactionId(" Silvio Ceeeeeeeeeeeeeeee");
		System.out.println(this.dto.toString());
	}
	
	public void stampaAttributoModel() {
		System.out.println(this.model.toString());
	}
	
	public static void main(String[] args) {
		
		FactoryTest test = new  FactoryTest();
		
		test.stampaAttributoDTO();
		test.stampaAttributoModel();
		
		
		
	
		
		
	}

}
