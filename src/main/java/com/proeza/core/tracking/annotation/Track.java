package com.proeza.core.tracking.annotation;

public @interface Track {

	String type();

	String prevValueSource();

	String postValueParam();
}