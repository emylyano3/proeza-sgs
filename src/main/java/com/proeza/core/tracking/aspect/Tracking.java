package com.proeza.core.tracking.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Tracking {
	@Pointcut("execution(* com.proeza.sgs.business.entity.Articulo.setPrecio(java.math.BigDecimal))")
	public void precio () {
	}

	@Before("precio()")
	public void interceptPrecio (JoinPoint jp) {
		System.out.println("interceptPrecio");
	}
}