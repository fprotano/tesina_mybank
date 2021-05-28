package it.exolab.tesina.mybank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.exolab.tesina.mybank.model.ExternalTransaction;
import it.exolab.tesina.mybank.model.Faq;
import it.exolab.tesina.mybank.repository.ExternalTransactionRepository;
import it.exolab.tesina.mybank.repository.FaqRepository;

public class FaqService {

	
private FaqRepository faqRepository;
	
	@Autowired(required=true)
	public void setFaqRepository(FaqRepository faqRepository) {
		this.faqRepository=faqRepository;
	}
	
	public List<Faq> findAll(){
		return (List<Faq>) faqRepository.findAll();	
	}
	
	public void insert(Faq faq) {
		faqRepository.save(faq);
	}
	
	public void update(Faq faq) {
		faqRepository.save(faq);
	}
	
	
	
	public void delete(int id) {
		faqRepository.delete(id);
	}
	
	
	
	public Faq find(int id) {
		return faqRepository.findOne(id);
	}
}
