package com.proeza.core.datamapper;

import com.proeza.core.datamapper.exception.DescriptorCreationException;

public abstract class AnnotationDescriptorReader implements DescriptorReader {
	private Class<?>	annotatedClass;

	public AnnotationDescriptorReader (Class<?> annotatedClass) throws DescriptorCreationException  {
		setAnnotatedClass(annotatedClass);
		load();
	}

	public final void load () throws DescriptorCreationException {
		readAnnotations();
	}

	protected abstract void readAnnotations() throws DescriptorCreationException;

	public Class<?> getAnnotatedClass () {
		return this.annotatedClass;
	}

	private void setAnnotatedClass (Class<?> annotatedClass) {
		this.annotatedClass = annotatedClass;
	}
}