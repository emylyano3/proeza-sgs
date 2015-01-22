package com.proeza.sgs.web.handler;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

	public static final String	ERROR_PAGE_NAME	= "/root/error.html";

	private static final Logger	log				= Logger.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = Exception.class)
	public ModelAndView handleAllException (HttpServletRequest req, Exception e) throws Exception {
		if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
			throw e;
		}
		ModelAndView model = new ModelAndView();
		model.addObject("goto", "home");
		model.addObject("gotoText", "Ir a Inicio");
		model.addObject("errorCode", "500");
		model.addObject("errorMessage", e.getMessage());
		model.addObject("exception", e);
		model.addObject("errorDescription", "Ocurrio un error al cargar la pagina.");
		model.setViewName(ERROR_PAGE_NAME);
		log.error("Exception handled by " + GlobalExceptionHandler.class.getSimpleName(), e);
		return model;
	}
}