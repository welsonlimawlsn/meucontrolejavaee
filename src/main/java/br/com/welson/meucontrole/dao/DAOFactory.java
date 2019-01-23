package br.com.welson.meucontrole.dao;

import java.lang.reflect.ParameterizedType;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.persistence.EntityManager;

public class DAOFactory {

	@Inject
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Dependent
	@Produces
	public <T> DAO<T> getDAO(InjectionPoint injectionPoint) {
		ParameterizedType parameterizedType = (ParameterizedType) injectionPoint.getType();
		Class<T> classe = (Class<T>) parameterizedType.getActualTypeArguments()[0];
		return new DAOImpl<T>(classe, entityManager);
	}

}
