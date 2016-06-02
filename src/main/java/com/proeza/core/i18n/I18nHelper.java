package com.proeza.core.i18n;

import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;

import com.proeza.core.i18n.entity.I18n;
import com.proeza.core.i18n.entity.Traduccion;

public class I18nHelper {

    public static final String DEFAULT_LOCALE = "es_AR";

    public String getTextoLocalizado(I18n i18n) {
        if (i18n == null) {
            return "";
        }
        Locale locale = LocaleContextHolder.getLocale();
        Traduccion traduccion = i18n.getTraduccion(locale.toString());
        if (traduccion == null) {
            traduccion = i18n.getTraduccion(DEFAULT_LOCALE);
        }
        return traduccion == null ? "" : traduccion.getTexto();
    }
}