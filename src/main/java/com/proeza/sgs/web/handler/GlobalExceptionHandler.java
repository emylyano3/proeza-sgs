package com.proeza.sgs.web.handler;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	public static final String	ERROR_PAGE_CODE	= "error";

	@ExceptionHandler
	public String handleAllException (ModelMap model, Exception e) {
		model.addAttribute("goto", "home");
		model.addAttribute("gotoText", "Ir a Inicio");
		model.addAttribute("errorCode", "500");
		model.addAttribute("errorMessage", e.getMessage());
		model.addAttribute("errorDescription", "Ocurrio un error al cargar la pagina.");
		return ERROR_PAGE_CODE;
	}
}