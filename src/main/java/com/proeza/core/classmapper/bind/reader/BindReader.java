package com.proeza.core.classmapper.bind.reader;

import java.lang.reflect.Field;

import com.proeza.core.classmapper.ClassMapperUtil;
import com.proeza.core.classmapper.Mapeable;
import com.proeza.core.classmapper.annotation.Bind;
import com.proeza.core.classmapper.bind.BindedField;
import com.proeza.core.classmapper.exception.BindedFieldException;
import com.proeza.core.classmapper.exception.BindedFieldNotFoundException;
import com.proeza.core.classmapper.exception.MappedFieldNotFoundException;
import com.proeza.core.context.StaticContext;

public abstract class BindReader {

	private ClassMapperUtil	util	= StaticContext.get(ClassMapperUtil.class);

	/**
	 * Indica cuando el bindeo a un atributo es directo<br>
	 * Esto es cuando el {@link Bind} se declara sin especificar el atributo {@link Bind#source()} o el que el atributo
	 * {@link Bind#source()} tenga una ruta directa.
	 * <p>
	 * <b>Ejemplo:</b>
	 * <ul>
	 * <li><b>Enlace Directo (t�cito):</b></li> <tt>@Bind</tt>
	 * <li><b>Enlace Directo 2:</b></li> <tt>@Bind(field="campo")</tt>
	 * <li><b>Enlace Inirecto:</b></li> <tt>@Bind(field="campo.subcampo")</tt>
	 * </ul>
	 */
	public boolean isDirectBind () {
		return isTacitBind() || !getBindedDeclared().contains(".");
	}

	/**
	 * Indica si el bindeo al campo es tacito, es decir que no se haya declarado explicitamente.<br>
	 * Esto es cuando el {@link Bind} se declara sin especificar el atributo {@link Bind#source()} <li><b>Enlace
	 * Tacito:</b></li> <tt>@Bind</tt>
	 */
	public boolean isTacitBind () {
		return getBindedDeclared() == null || "".equals(getBindedDeclared());
	}

	/**
	 * Indica si el bindeo tiene definido un enlace reverso.
	 */
	public boolean isBackwardBindDefined () {
		return getBackwardBinder() != null && !"".equals(getBackwardBinder());
	}

	/**
	 * Devuelve la ruta de enlace definitiva. Si el bindeo es tacito devuelve el nombre del campo anotado en el objeto
	 * mapeado.
	 */
	public String getBindPath () {
		return isTacitBind() ? getAnnotatedField().getName() : getBindedDeclared();
	}

	/**
	 * Idem {@link #getBindPath()} solo que devuelve la ruta spliteada en un array de <tt>String</tt>. El array tendra
	 * tantas posiciones como indirecciones la ruta.
	 * <p>
	 * <b>Ejemplo:</b>
	 * <ul>
	 * <li>El <tt>getBindedPath</tt> de <tt>@Bind(field="campo")</tt> devuelve [campo]</li>
	 * <li>El <tt>getBindedPath</tt> de <tt>@Bind(field="campo.subcampo")</tt> devuelve [campo, subcampo]</li>
	 * </ul>
	 */
	public String[] getBindPathSplitted () {
		return getBindPath().trim().split("\\.");
	}

	/**
	 * Busca el campo enlazado siguiendo la ruta que conduce al mismo, especificada en el {@link Bind} definido en el
	 * campo del mapeable.<br>
	 * Si la ruta que apunta al campo enlazado pasa por alguna referencia nula, entonces al no poder recorrer la ruta
	 * completa se devuelve <b><tt>null</tt></b>.
	 * <P>
	 * 
	 * @return Una instancia de {@link BindedField}
	 * @see BindedField
	 */
	public BindedField getBindedField (Mapeable mapeable, Object binded) throws BindedFieldNotFoundException {
		try {
			if (isDirectBind()) {
				// TODO: En vez de enviar true, tomar la definicion del mapping existente en el mapeable
				return new BindedField(binded, util.getField(binded.getClass(), getBindPath(), true));
			}
			return util.getBindedField(getBindPathSplitted(), binded);
		} catch (BindedFieldNotFoundException e) {
			throw e;
		} catch (Exception e) {
			StringBuilder msg = new StringBuilder();
			msg
				.append("Ocurrio un error inesperado buscando el campo enlazado ")
				.append(getBindPath())
				.append(" apuntado desde el bindeo declarado en ")
				.append(mapeable.getClass())
				.append(" causado por: ")
				.append(e.getMessage());
			throw new BindedFieldException(msg.toString(), e);
		}
	}

	/**
	 * Busca el campo mapeado siguiendo la ruta que conduce al mismo, especificada en el {@link Bind} definido en el
	 * campo del mapeable.<br>
	 * Si la ruta que apunta al campo enlazado pasa por alguna referencia nula, entonces al no poder recorrer la ruta
	 * completa se devuelve <b><tt>null</tt></b>.
	 * <P>
	 * 
	 * @return Una instancia de {@link BindedField}
	 * @see BindedField
	 */
	public BindedField getMappedField (Mapeable mapeable, Object binded) throws MappedFieldNotFoundException {
		try {
			if (isDirectMapped()) {
				return new BindedField(mapeable, getAnnotatedField());
			}
			Object mapped = util.getFieldValue(getAnnotatedField(), mapeable);
			return util.getBindedField(getMappedPathSplitted(), mapped);
		} catch (BindedFieldNotFoundException e) {
			StringBuilder msg = new StringBuilder();
			msg
				.append("El campo ")
				.append(getMappedPath())
				.append(" apuntado desde el bindeo declarado en ")
				.append(mapeable.getClass())
				.append(" no existe.");
			throw new MappedFieldNotFoundException(msg.toString(), e);
		} catch (Exception e) {
			StringBuilder msg = new StringBuilder();
			msg
				.append("Ocurrio un error inesperado buscando el campo enlazado ")
				.append(getBindPath())
				.append(" apuntado desde el bindeo declarado en ")
				.append(mapeable.getClass())
				.append(" causado por: ")
				.append(e.getMessage());
			throw new BindedFieldException(msg.toString(), e);
		}
	}

	private boolean isDirectMapped () {
		return getMappedPath() == null || "".equals(getMappedPath());
	}

	private String[] getMappedPathSplitted () {
		return getMappedPath().split("\\.");
	}

	/**
	 * Devuelve la ruta de enlace del campo del objeto bindeado.
	 */
	protected abstract String getBindedDeclared ();

	/**
	 * Devuelve la ruta que apunta al campo mapeado.
	 */
	protected abstract String getMappedPath ();

	/**
	 * Devuelve el campo del mapeado que tiene la anotacion del enlace.
	 */
	public abstract Field getAnnotatedField ();

	/**
	 * Devuelve el enlace reverso si es que existe.
	 */
	public abstract String getBackwardBinder ();

	/**
	 * Si el bindeo es de tipo <tt>Collection</tt> indica el tipo de dato que maneja la coleccion.
	 */
	public abstract Class<?> getCollectionType ();

	/**
	 * Indica si el bind es del tipo <tt>Collection</tt>
	 */
	public abstract boolean isCollectionBind ();
}