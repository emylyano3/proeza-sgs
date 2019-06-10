package com.proeza.core.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.proeza.core.i18n.entity.I18n;
import com.proeza.core.i18n.entity.Traduccion;
import com.proeza.sgs.config.root.ContextLocale;

import static org.springframework.util.StringUtils.*;

@Component
@Scope(scopeName = BeanDefinition.SCOPE_PROTOTYPE)
public class I18nHelper {

	public static final String	DEFAULT_LOCALE	= "es_AR";

	@Autowired
	private ContextLocale		contextLocale;

	public String getTextoLocalizado (I18n i18n) {
		if (i18n == null) {
			return "";
		}
		Traduccion traduccion = i18n.getTraduccion(getLocale());
		if (traduccion == null) {
			traduccion = i18n.getTraduccion(DEFAULT_LOCALE);
		}
		return traduccion == null ? "" : traduccion.getTexto();
	}

	private String getLocale () {
		try {
			String locale = this.contextLocale.getLocale();
			if (isEmpty(locale)) {
				locale = LocaleContextHolder.getLocale().toString();
			}
			return isEmpty(locale) ? DEFAULT_LOCALE : locale;
		} catch (Exception e) {
			return DEFAULT_LOCALE;
		}
	}
}