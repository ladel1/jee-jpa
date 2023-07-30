package fr.avis.dal;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;


import fr.avis.config.EntityManagerFactorySingleton;

public interface DAO<T,ID> {	
    default List<T> findAll() {
        EntityManagerFactory emf = EntityManagerFactorySingleton.getInstance();
        EntityManager em = emf.createEntityManager();
        try {
            Type genericSuperclass = getClass().getGenericInterfaces()[0];
            if (genericSuperclass instanceof ParameterizedType) {
                ParameterizedType pt = (ParameterizedType) genericSuperclass;
                Type[] typeArgs = pt.getActualTypeArguments();
                if (typeArgs.length >= 1) {
                    Class<T> entityClass = (Class<T>) typeArgs[0];
                    TypedQuery<T> q = em.createQuery("SELECT a FROM " + entityClass.getSimpleName() + " a", entityClass);
                    return q.getResultList();
                }
            }
        } finally {
            em.close();
            EntityManagerFactorySingleton.close();
        }
        return null;
    }
	
	default T findById(ID id) {
		return null;
	}
	
}
