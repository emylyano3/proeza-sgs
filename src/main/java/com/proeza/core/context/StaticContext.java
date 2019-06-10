package com.proeza.core.context;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Clase singleton de contexto para cargar beans desde el contexto de spring.
 */
@Component
public class StaticContext implements ApplicationContextAware {

	private static ApplicationContext context;

	@Override
	public void setApplicationContext (ApplicationContext appContext) throws BeansException {
		context = appContext;
	}

	public static ApplicationContext getContext () {
		return context;
	}

	/**
	 * Obtenemos un bean en base al tipo.
	 *
	 * @param beanType
	 *            El tipo de bean que se requiere
	 * @return El bean solicitado
	 * @throws RuntimeException
	 *             si no se encontro el componente
	 */
	public static <Bean> Bean get (Class<Bean> beanType) throws RuntimeException {
		if (context == null) {
			return null;
		}
		@SuppressWarnings("rawtypes")
		Map beans = context.getBeansOfType(beanType);
		if (beans == null || beans.isEmpty()) {
			throw new RuntimeException("No se pudo levantar el componente: " + beanType.getSimpleName());
		} else if (beans.size() > 1) {
			throw new RuntimeException("No se pudo levantar el componente: " + beanType.getSimpleName() + ". Existe mas de un bean para su tipo");
		}
		@SuppressWarnings("unchecked")
		Bean b = (Bean) beans.values().iterator().next();
		return b;
	}

	@SuppressWarnings("unchecked")
	public static <Bean> Bean get (String beanName) throws RuntimeException {
		return (Bean) context.getBean(beanName);
	}
}
