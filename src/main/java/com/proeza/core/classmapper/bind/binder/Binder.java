package com.proeza.core.classmapper.bind.binder;

import com.proeza.core.classmapper.Mapeable;
import com.proeza.core.classmapper.bind.reader.BindReader;

public interface Binder {

	<M extends Mapeable> void bind (M mapeable, Object binded, BindReader bindReader);
}