package com.proeza.core.resources;

import javax.servlet.http.HttpServletRequest;

public interface IMessageResolver {

	public abstract String getMessage (String code);

	public abstract String getMessage (String code, HttpServletRequest request);

	public abstract String getMessage (String code, HttpServletRequest request, String... params);

	public abstract String getMessage (String code, String... params);

}