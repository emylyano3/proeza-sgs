package com.proeza.core.persistence;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

public abstract class BaseDao<Entity> implements Dao<Entity> {

    @PersistenceContext
    protected EntityManager entityManager;

    private Class<Entity>   entityClass;

    @SuppressWarnings("unchecked")
    private Class<Entity> getEntityClass() {
        if (this.entityClass == null) {
            Class<?> daoSubclass = getClass();
            while (!daoSubclass.getSuperclass().equals(BaseDao.class)) {
                daoSubclass = daoSubclass.getSuperclass();
            }
            this.entityClass = (Class<Entity>) ((ParameterizedType) daoSubclass.getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return this.entityClass;
    }

    @Override
    public Entity find(Object id) {
        return this.entityManager.find(getEntityClass(), id);
    }

    @Override
    public List<Entity> findAll() {
        @SuppressWarnings("unchecked")
        final List<Entity> resultList = this.entityManager
        .createQuery(" select x from " + getEntityClass().getName() + " x")
        .getResultList();
        return resultList;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    protected Entity findByAttribute(SingularAttribute att, Object value) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Entity> criteria = builder.createQuery(getEntityClass());
        Root<Entity> root = criteria.from(getEntityClass());
        criteria.select(root);
        criteria.where(builder.equal(root.get(att), value));
        return this.entityManager.createQuery(criteria).getSingleResult();
    }

    @Override
    public Long getNextId() {
        Long id = (Long) this.entityManager
                .createQuery(" select max(x.id) from " + getEntityClass().getName() + " x")
                .getSingleResult();
        return id == null ? 1 : ++id;
    }

    @Override
    public Entity persist(Entity entity) {
        this.entityManager.persist(entity);
        return entity;
    }

    @Override
    public Collection<Entity> persist(Collection<Entity> entities) {
        for (Entity entity : entities) {
            persist(entity);
        }
        return entities;
    }

    @Override
    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Override
    public void delete(Entity entity) {
        this.entityManager.remove(entity);
    }
}