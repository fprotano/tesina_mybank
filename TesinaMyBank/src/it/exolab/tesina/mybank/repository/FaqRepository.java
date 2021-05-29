package it.exolab.tesina.mybank.repository;

import org.springframework.data.repository.CrudRepository;

import it.exolab.tesina.mybank.model.Faq;
import it.exolab.tesina.mybank.model.dto.FaqDTO;

public interface FaqRepository extends CrudRepository<FaqDTO, Integer> {

}
