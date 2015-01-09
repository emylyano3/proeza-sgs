package com.proeza.core.persistence;

import java.util.Collection;

public interface Dao<Entity> {

	public abstract Entity find (Object id);

	public abstract Entity persist (Entity entity);

	public abstract Collection<Entity> persist (Collection<Entity> entities);

	public abstract void delete (Entity entity);

}