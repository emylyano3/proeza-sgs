package com.proeza.core.persistence;

import java.util.Collection;
import java.util.List;

public interface Dao<Entity> {

	public abstract Entity find (Object id);

	public abstract List<Entity> findAll ();

	public abstract Entity persist (Entity entity);

	public abstract Collection<Entity> persist (Collection<Entity> entities);

	public abstract void delete (Entity entity);
}