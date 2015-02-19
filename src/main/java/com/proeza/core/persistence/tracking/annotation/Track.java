package com.proeza.core.persistence.tracking.annotation;

public @interface Track {

	String type();

	String prevValueSource();

	String postValueParam();
}