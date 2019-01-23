package br.com.welson.meucontrole.interceptor;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.welson.meucontrole.anotacao.Transactional;

@Interceptor
@Transactional
public class TransactionalInterceptor {
	
	@Inject
	private EntityManager entityManager;
	
	@AroundInvoke
	public Object invoke(InvocationContext context) {
		Object o = null;
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			o = context.proceed();
            if (transaction.getRollbackOnly()) {
                transaction.rollback();
            } else {
                transaction.commit();
            }
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        return o;
	}

}
