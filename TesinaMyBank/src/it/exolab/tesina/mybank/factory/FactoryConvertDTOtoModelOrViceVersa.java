package it.exolab.tesina.mybank.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import it.exolab.tesina.mybank.util.Validator;

public class FactoryConvertDTOtoModelOrViceVersa<T,K> {
	
	private Class<T> model_dto ;        
	private Class<K> model_jpa ;
	protected T dto;
	protected K model;
	
	
	public FactoryConvertDTOtoModelOrViceVersa(Class<T> model_dto, Class<K> model_jpa) {
		this.model_dto = model_dto;
		this.model_jpa = model_jpa;
		try {
			this.dto = this.model_dto.newInstance();
			this.model=this.model_jpa.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	protected K fromDTOtoModel(T dto) { // funziona
		return (K)dto;
	}
	protected T fromModelToDTO(K model) { // non funziona
		return (T)model;
	}
	
	protected List<T> fromModelToDTO(List<K> model) {
		return (List<T>)model;
	}
	//-------------------------------------------------------------------------- UTILS FOR SERVICES
	
	protected Validator validator= new Validator();
	
	
	protected  Object fromDtoToModel(Object dtoObject) {
		Object modelObject=null;
		try {
			model_jpa = (Class<K>) Class.forName(model_jpa.getCanonicalName());
			Constructor<K> ctor = (Constructor<K>) dtoObject.getClass().getConstructor();
			modelObject = ctor.newInstance();
			
			Field[] properties = model_jpa.getDeclaredFields();
			for(Field property : properties) {
				String propertyName = property.getName();
				String getterName = "get"+ propertyName.substring(0,1).toUpperCase() +propertyName.substring(1);
				String setterName = "set"+ propertyName.substring(0,1).toUpperCase() +propertyName.substring(1);
				Method getterMethod =  model_jpa.getMethod(getterName);
				Class returnType = getterMethod.getReturnType();
				Method setterMethod =  dtoObject.getClass().getMethod(setterName,returnType);
				Object value = getterMethod.invoke(dtoObject);
				setterMethod.invoke(modelObject,value);
			}		
//			System.out.println(modelObject);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return modelObject;
	}
	
	
	
}
