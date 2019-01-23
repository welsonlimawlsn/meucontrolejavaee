package br.com.welson.meucontrole.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class DAOImpl<T> implements DAO<T> {

	private Class<T> classe;
	private EntityManager entityManager;

	public DAOImpl(Class<T> classe, EntityManager entityManager) {
		this.classe = classe;
		this.entityManager = entityManager;
	}

	@Override
	public T salvar(T entidade) {
		entityManager.persist(entidade);
		return entidade;
	}

	@Override
	public T alterar(T entidade) {
		entityManager.merge(entidade);
		return entidade;
	}

	@Override
	public void remover(T entidade) {
		entityManager.remove(entidade);
	}

	@Override
	public T procurarPeloId(Long id) {
		return entityManager.find(classe, id);
	}

	@Override
	public List<T> listarTudo() {
		return entityManager.createQuery("SELECT entity FROM " + classe.getSimpleName() + " entity", classe)
				.getResultList();
	}

	@Override
	public List<T> procurarComHQL(String hql, Object... values) {
		TypedQuery<T> query = entityManager.createQuery(hql, classe);
		List<String> paramsId = pegarParametros(hql);
		for (int i = 0; i < paramsId.size(); i++)
			query.setParameter(paramsId.get(i), values[i]);
		return query.getResultList();
	}

	private List<String> pegarParametros(String hql) {
		Pattern pattern = Pattern.compile("(:\\w+)");
		Matcher matcher = pattern.matcher(hql);
		List<String> params = new ArrayList<>();
		while (matcher.find())
			params.add(matcher.group().replace(":", ""));
		return params;
	}

}
