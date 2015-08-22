package com.proeza.sgs.web.handler;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.proeza.core.error.builder.HandledErrorBuilder;
import com.proeza.core.error.dto.HandledErrorDTO;
import com.proeza.core.service.IErrorService;
import com.proeza.core.util.date.DateUtil;

@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String  ERROR_PAGE_NAME = "/root/error.html";

    private static final Logger log             = Logger.getLogger(GlobalExceptionHandler.class);

    @Autowired
    private IErrorService       errorService;

    @ExceptionHandler(value = Exception.class)
    public ModelAndView handleAllException (HttpServletRequest req, Exception e) throws Exception {
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }
        ModelAndView model = new ModelAndView();
        model.addObject("goto", "/home");
        model.addObject("gotoText", "Ir a Inicio");
        model.addObject("errorCode", "500");
        model.addObject("errorMessage", e.getMessage());
        model.addObject("exception", e);
        model.addObject("errorDescription", "Ocurrio un error al cargar la pagina.");
        model.setViewName(ERROR_PAGE_NAME);
        log.error("Exception handled by " + GlobalExceptionHandler.class.getSimpleName(), e);
        trackError(e);
        return model;
    }

    private void trackError (Exception e) {
        HandledErrorBuilder errorBuilder = new HandledErrorBuilder();
        errorBuilder.withStack(ExceptionUtils.getStackTrace(e));
        errorBuilder.withExcepcion(e.getClass().getName());
        errorBuilder.withFecha(DateUtil.createNow());
        errorBuilder.withMensaje(e.getMessage());
        this.errorService.create(new HandledErrorDTO(errorBuilder.build()));
    }
}