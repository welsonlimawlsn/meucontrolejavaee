package br.com.welson.meucontrole.dao;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryDI {

	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("padrao");

	@RequestScoped
	@Produces
	public EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

	public void closeEntityManager(@Disposes EntityManager entityManager) {
		if (entityManager != null && entityManager.isOpen())
			entityManager.close();
	}

}
