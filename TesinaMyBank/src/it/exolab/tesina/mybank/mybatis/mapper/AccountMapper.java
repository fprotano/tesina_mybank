package it.exolab.tesina.mybank.mybatis.mapper;

import it.exolab.tesina.mybank.model.dto.AccountDTO;

public interface AccountMapper extends BaseMapper<AccountDTO> {

	@Override
	default void validateInsert(AccountDTO model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	default void validateUpdate(AccountDTO model) {
		// TODO Auto-generated method stub
		
	}


	
	
	
	
	
	
	
	
	
	
}
