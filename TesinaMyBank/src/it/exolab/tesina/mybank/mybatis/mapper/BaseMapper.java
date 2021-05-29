package it.exolab.tesina.mybank.mybatis.mapper;

import java.util.List;

public interface BaseMapper<T> {
	
	public void insert (T model);
	public void update (T model);
	public List<T> findAll();
	
	
	
	public  void validateInsert(T model);
	public   void validateUpdate(T model);
	public T find(Integer id);
	public void delete(Integer id);
	
	
}
