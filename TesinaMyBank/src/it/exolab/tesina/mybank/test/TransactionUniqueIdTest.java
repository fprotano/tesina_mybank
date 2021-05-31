package it.exolab.tesina.mybank.test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import it.exolab.tesina.mybank.model.TransactionUniqueId;
import it.exolab.tesina.mybank.model.dto.TransactionUniqueIdDTO;

public class TransactionUniqueIdTest {
    // questo va, me lo casta
	public TransactionUniqueId fromDTOtoModel(TransactionUniqueIdDTO dto) {
		return (TransactionUniqueId)dto;
	}
	//NON FUNZIONA
	public TransactionUniqueIdDTO fromModelToDTO(TransactionUniqueId model) {
		return (TransactionUniqueIdDTO)model;
	}
	//--------------------------------------------------------------------------------------------------------------------
	public List<TransactionUniqueIdDTO> listOfModelToDTO(List<TransactionUniqueId> model) {
		List<Object> myObjects = new ArrayList<Object>();
		myObjects =(List<Object>)(Object)model;
		List<TransactionUniqueIdDTO> customer = myObjects.stream()
                .filter(TransactionUniqueIdDTO.class::isInstance)
                .map(TransactionUniqueIdDTO.class::cast)
                .collect(Collectors.toList());
		
		return customer;
	}
	
	public List<TransactionUniqueIdDTO> listOfModelToDTOParte2(List<TransactionUniqueId> model) {
		List<TransactionUniqueIdDTO> lista_dto = new ArrayList<TransactionUniqueIdDTO>();
		model.stream().forEach(x->lista_dto.add((TransactionUniqueIdDTO)x));

		return lista_dto;
	}
	// questo funziona da model a dto 
	public List<TransactionUniqueIdDTO> listOfModelToDTOParte3(List<TransactionUniqueId> model) {
		List<TransactionUniqueIdDTO> lista_dto = new ArrayList<TransactionUniqueIdDTO>();
		for(TransactionUniqueId m : model)
			lista_dto.add(new TransactionUniqueIdDTO(m.getTransactionId()));
		return lista_dto;
	}
	
	
	public List<TransactionUniqueIdDTO> listOfModelToDTOParte4(List<TransactionUniqueId> model ,List<TransactionUniqueIdDTO> list_dto) {
		for(TransactionUniqueId m : model) {
			TransactionUniqueIdDTO prova = new TransactionUniqueIdDTO();
			prova = (TransactionUniqueIdDTO)m;
			list_dto.add( prova  );
		}
			
		return list_dto;
	}
	// Questo funziona da dto a model
	public List<TransactionUniqueId> listOfDTOtoModel(List<TransactionUniqueIdDTO> list_dto ,List<TransactionUniqueId> model_list) {
		for(TransactionUniqueIdDTO dto : list_dto) {
			model_list.add( (TransactionUniqueId)dto   );
		}
		return model_list;
	}
	public static void main(String[] args) {
		TransactionUniqueIdTest test = new TransactionUniqueIdTest();
		
		TransactionUniqueId model_jpa = new TransactionUniqueId();
		
		TransactionUniqueIdDTO model_dto = new TransactionUniqueIdDTO();
		
		
		
		
		model_jpa.setTransactionId("A007");
		
		model_dto = test.fromModelToDTO(model_jpa);
		
		List<TransactionUniqueId> lista_model = new ArrayList<TransactionUniqueId>();
//		lista_model.add(new TransactionUniqueId("A0009"));
//		lista_model.add(new TransactionUniqueId("A1111"));
		
		
		List<TransactionUniqueIdDTO> lista_dto = new ArrayList<TransactionUniqueIdDTO>();
		lista_dto.add(new TransactionUniqueIdDTO("A1111") );
		lista_dto.add(new TransactionUniqueIdDTO("A5555") );
//		lista_dto = test.listOfModelToDTOParte4(lista_model,lista_dto);
//		
//		for(TransactionUniqueIdDTO dto : lista_dto)
//			System.out.println(dto.toString());
		
		System.out.println(model_dto.toString());
		System.out.println("-----------------------------------------------------------------------------------------------------------------");
		System.out.println(model_jpa.toString());
		System.out.println("-----------------------------------------------------------------------------------------------------------------");
		System.out.println(model_dto.toString());
		
		
//		lista_model = test.listOfDTOtoModel(lista_dto, lista_model);
//		
//		for(TransactionUniqueId model : lista_model)  {
//			System.out.println(model.toString());
//		}
		
		
		
		
		
		
		
	}
	//CONCLUSIONI , IL CAST DTO/MODEL AVVIENE CON SUCCESSO SOLO SE SI CASTA DA DTO A MODEL MA NON VICEVERSA
}
