package com.proeza.core.persistence;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class BaseDao<Entity> implements Dao<Entity> {

	@PersistenceContext
	protected EntityManager	entityManager;

	private Class<Entity>	entityClass;

	@SuppressWarnings("unchecked")
	private Class<Entity> getEntityClass () {
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
	public Entity find (Object id) {
		return this.entityManager.find(getEntityClass(), id);
	}

	@Override
	public List<Entity> findAll () {
		@SuppressWarnings("unchecked")
		final List<Entity> resultList = this.entityManager
		    .createQuery(" select x from " + getEntityClass().getName() + " x")
		    .getResultList();
		return resultList;
	}

	@Override
	public Entity persist (Entity entity) {
		this.entityManager.persist(entity);
		return entity;
	}

	@Override
	public Collection<Entity> persist (Collection<Entity> entities) {
		for (Entity entity : entities) {
			persist(entity);
		}
		return entities;
	}

	public EntityManager getEntityManager () {
		return this.entityManager;
	}

	@Override
	public void delete (Entity entity) {
		this.entityManager.remove(entity);
	}
}