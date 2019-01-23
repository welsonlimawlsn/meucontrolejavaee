package br.com.welson.meucontrole.dao;

import java.util.List;

public interface DAO<T> {

	T salvar(T entidade);

	T alterar(T entidade);

	void remover(T entidade);

	T procurarPeloId(Long id);

	List<T> listarTudo();

	List<T> procurarComHQL(String hql, Object... values);

}
