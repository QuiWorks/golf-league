package com.ejp.golf.league.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public abstract class AbstractCrudService<ENTITY> implements CrudService<ENTITY> {
    protected final EntityManagerFactory entityManagerFactory;

    public AbstractCrudService() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("golf_league");
    }

    @Override
    public List<ENTITY> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<ENTITY> query = entityManager.createQuery("SELECT e FROM " + getTableName() + " e", getEntityClass());
        List<ENTITY> resultList = query.getResultList();
        entityManager.close();
        return resultList;
    }

    @Override
    public List<ENTITY> findAll(String filter) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<ENTITY> query = entityManager.createQuery(
                "SELECT e FROM " + getTableName() + " e " +
                        "WHERE " + getFilterColumn() + " " + getFilterComparator() + " '" + filter + "'",
                getEntityClass());
        List<ENTITY> resultList = query.getResultList();
        entityManager.close();
        return resultList;
    }

    @Override
    public void delete(ENTITY value) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(value) ? value : entityManager.merge(value));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void save(ENTITY entry) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(entry);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void update(ENTITY entry) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(entry);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    protected abstract String getTableName();

    protected abstract Class<ENTITY> getEntityClass();

    protected String getFilterColumn() {
        return "id";
    }

    protected String getFilterComparator() {
        return "=";
    }
}
