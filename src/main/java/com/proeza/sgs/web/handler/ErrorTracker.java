package com.proeza.sgs.web.handler;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.proeza.core.error.builder.HandledErrorBuilder;
import com.proeza.core.error.dto.HandledErrorDTO;
import com.proeza.core.error.entity.HandledError;
import com.proeza.core.service.IErrorService;
import com.proeza.core.util.date.DateUtil;

@Component
public class ErrorTracker {

    private static final Logger log = Logger.getLogger(ErrorTracker.class);

    @Autowired
    private IErrorService       errorService;

    public void trackError(Throwable e) {
        log.info("Tracking error: " + e);
        HandledErrorBuilder errorBuilder = new HandledErrorBuilder();
        String stack = ExceptionUtils.getStackTrace(e);
        int length = Math.min(stack.length(), HandledError.STACK_LENGTH);
        errorBuilder.withStack(stack.substring(0, length));
        errorBuilder.withExcepcion(e.getClass().getName());
        errorBuilder.withFecha(DateUtil.createNow());
        errorBuilder.withMensaje(e.getMessage());
        this.errorService.create(new HandledErrorDTO(errorBuilder.build()));
    }
}