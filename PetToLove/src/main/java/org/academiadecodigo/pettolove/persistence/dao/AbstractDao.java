package org.academiadecodigo.pettolove.persistence.dao;

import org.academiadecodigo.pettolove.persistence.model.Model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class AbstractDao<T extends Model> implements Dao<T> {

    protected Class<T> modelType;

    @PersistenceContext
    protected EntityManager entityManager;

    public AbstractDao(Class<T> modelType) {
        this.modelType = modelType;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<T> findAll() {

        CriteriaQuery<T> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(modelType);
        Root<T> root = criteriaQuery.from(modelType);
        return entityManager.createQuery(criteriaQuery).getResultList();

    }

    @Override
    public T findById(Integer id) {
        return entityManager.find(modelType, id);
    }

    @Override
    public T saveOrUpdate(T modelObject) {
        return entityManager.merge(modelObject);
    }

    @Override
    public void delete(Integer id) {
        entityManager.remove(entityManager.find(modelType, id));
    }
}
