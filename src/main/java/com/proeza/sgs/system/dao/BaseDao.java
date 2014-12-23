package com.proeza.sgs.system.dao;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public abstract class BaseDao<Entity> {

	@PersistenceContext
	protected EntityManager		entityManager;

	private Class<Entity>		entityClass;

	@Autowired
	private CacheManager		cacheManager;

	public Statistics getCacheStatistics () {
		return this.cacheManager.getCache(getEntityClass().getName()).getStatistics();
	}

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

	public Entity find (Object id) {
		return this.entityManager.find(getEntityClass(), id);
	}

	public Entity persist (Entity entity) {
		this.entityManager.persist(entity);
		return entity;
	}

	public Collection<Entity> persist (Collection<Entity> entities) {
		for (Entity entity : entities) {
			persist(entity);
		}
		return entities;
	}

	public void delete (Entity entity) {
		this.entityManager.remove(entity);
	}
}