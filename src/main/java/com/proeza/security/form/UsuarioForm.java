package com.proeza.security.form;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.proeza.security.entity.Usuario;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class UsuarioForm extends Usuario {

	private static final long	serialVersionUID	= 1L;
}