package com.proeza.core.persistence;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;

public interface Dao<Entity> {

    Entity find (Object id);

    List<Entity> findAll ();

    Entity persist (Entity entity);

    Collection<Entity> persist (Collection<Entity> entities);

    void delete (Entity entity);

    EntityManager getEntityManager ();

    Long getNextId ();
}