package it.exolab.tesina.mybank.crud;

import java.util.List;

import it.exolab.tesina.mybank.exception.EntityNotFoundError;
import it.exolab.tesina.mybank.model.Fake;
import it.exolab.tesina.mybank.mybatis.mapper.FakeMapper;

public class FakeCRUD  extends GenericCRUD<Fake,FakeMapper> {
	

	public FakeCRUD() {
		super(FakeMapper.class);

	}
	
	@Override
	public void insert(Fake model) throws Exception {
//		validateInsert(model);
//		openConnection();
//		//getMapper().insert(model);
//		closeConnection(true);
		
	}

	@Override
	protected void validateInsert(Fake model) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateUpdate(Fake model) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Fake model) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Fake find(Long id) throws EntityNotFoundError {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Fake> all() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
