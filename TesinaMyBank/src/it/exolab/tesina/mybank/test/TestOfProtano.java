package it.exolab.tesina.mybank.test;

import it.exolab.tesina.mybank.factory.FactoryConvertDTOtoModelOrViceVersa;
import it.exolab.tesina.mybank.model.TransactionUniqueId;
import it.exolab.tesina.mybank.model.dto.TransactionUniqueIdDTO;

public class TestOfProtano extends  FactoryConvertDTOtoModelOrViceVersa<TransactionUniqueIdDTO, TransactionUniqueId>{

	public TestOfProtano() {
		super(TransactionUniqueIdDTO.class, TransactionUniqueId.class);
	}
	
	public TransactionUniqueId cambiaDaDtoToModel( TransactionUniqueIdDTO model_dto ) {
		return (TransactionUniqueId) this.fromDtoToModel(model_dto);
	}
	
	
	
	
	public static void main(String[] args) {
		TestOfProtano test = new TestOfProtano();
		
        TransactionUniqueId model_jpa = new TransactionUniqueId();
		
		TransactionUniqueIdDTO model_dto = new TransactionUniqueIdDTO();
		model_dto.setTransactionId("Apice");
		
		System.out.println("dto vale ------>"+model_dto.toString());
		System.out.println("model vale ---->"+model_jpa.toString());
		
//		model_jpa = (TransactionUniqueId) FactoryConvertDTOtoModelOrViceVersa.fromDtoToModel(model_dto, TransactionUniqueId.class);
		model_jpa = test.cambiaDaDtoToModel(model_dto);
		
		
		System.out.println("model vale ---->"+model_jpa.toString());
		
		
		
		
		
		
		
		
		
		
		
	}

}
