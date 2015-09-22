package com.proeza.core.classmapper.bind;

import java.lang.reflect.Field;

/**
 * Clase para definir un campo sobre el que se tiene que setear o desde el cual se debe tomar el dato mapeado en la
 * configuracion del objeto anotado.
 *
 * @author c.eschia
 */

public class BindedField {
	/**
	 * Recibe la instancia de la clase que contiene el campo definido en el bind y el campo.
	 */
	public BindedField (Object o, Field f) {
		this.object = o;
		this.field = f;
	}

	private final Object	object;
	private final Field		field;

	public Object getObject () {
		return object;
	}

	public Field getField () {
		return field;
	}

	@Override
	public String toString () {
		return "BindingDef [object=" + this.object.getClass().getSimpleName() + ", field=" + this.field.getName() + ", field type=" + this.field.getType().getSimpleName() + "]";
	}
}